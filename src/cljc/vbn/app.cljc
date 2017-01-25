(ns vbn.app
  #?(:cljs (:require-macros  [vbn.styler :refer [css get-css-str]]))
  (:require [rum.core :as rum]
            [devcards.core :as dc]
            [bidi.bidi :as b :refer [match-route path-for]]

            [vbn.index :as i]
            [vbn.veganism :as v]
            [vbn.consulting :as ct]
            [vbn.community :as cm]
            [vbn.about :as a]
            [vbn.web :as w]

            [vbn.components :refer [my-routes]]

            ;[vbn.styler :refer [installer-hack]]
            #?(:clj [vbn.styler :refer [css installer-hack]])
            ;#?(:cljs [vbn.mediafixer :refer [get-atomic-css]])
            #?(:cljs [goog.style])



            #?(:cljs [vbn.navigation :refer [link current-token]])
            #?(:cljs [vbn.devcards :as devcards])))



;; Accessibility defaults

(rum/defc skip-to-main []
  [:a.skip-to-main {:href "#main"}
   [:div
    [:div.skip-button
     [:span "Skip to main content"]
     [:span.skip-enter "Press enter to skip to main content"]]
    [:span.skip-continue "Tab or Click anywhere to continue"]]])

(rum/defc main [content]
  "Enters content into main container with id=\"main\" "
  [:main#main content])

;; Site specific

(rum/defc hidden []
  [:span  {:hidden true} "This is hidden text"])




(defn get-route [url]
  (:handler (match-route my-routes url)))

;; define clojure version of link
#?(:clj (rum/defc link [link & content]
          [:a {:href link} content]))


;; NEED TO ASSOC "#MAIN" onto the current URL

(rum/defc navigation []
  [:nav
   [:ul
    [:li.order-middle (link (path-for my-routes :index)
                            [:span {:aria-hidden true} "Home"]
                            [:svg.home {:alt "VBN Logo Home"
                                        :viewBox "0 0 160 150"}
                             [:use {:xlink-href "/logo.svg#logo"}]])]
    [:li.order-front (link (path-for my-routes :veganism) [:span "Veganism"])]
    [:li.order-front   (link (path-for my-routes :consulting) [:span "Consulting"])]
    [:li.order-end (link (path-for my-routes :community) [:span "Community"])]
    [:li.order-end (link (path-for my-routes :about-us) [:span "About Us"])]]])


(rum/defc footer []
  [:footer
   [:div {:class ["full-width"
                  (css {:color "white"
                        :background-color "#3a539b"})]}
    [:div {:class [(css {:width "100vw"
                         :max-width "53em"
                         :align-self "center"
                         :padding-left "1.5em"
                         :padding-right "1.5em"
                         :padding-top "4.5em"
                         :padding-bottom "4.5em"})]}

     [:h2 "Contact Us"]
     [:p "We have a lot of exciting plans which will be implemented in 2017. If you have any queries or would like to collaborate please send us an email"]
     [:p "Additionally if you have any skills which fit the services listed on our "
      (link (path-for my-routes :consulting)[:span.footer-link "consulting"]) " page and would like to join our team contact us with a cover letter, along with a resume and/or portfolio of your work"]
     [:p  [:b "Email: "]
      [:a {:href "mailto:wade@veganbusinessnetwork"}
       "wade@veganbusinessnetwork.org"]]]]])



(rum/defc page-wrapper [content]
  [:div ;#?(:cljs {:class [(styler/css {:background "blue"})]})
   ;#?(:clj {:class [(clj-styler/css {:background "blue"})]})
   ;{:class [(css {:background "black"})]}
   (skip-to-main)
   (navigation)
   content
   (footer)])


(rum/defc home []
  (page-wrapper (i/content)))

(rum/defc veganism []
  (page-wrapper (v/content)))

(rum/defc consulting []
  (page-wrapper (ct/content)))

(rum/defc community []
  (page-wrapper (cm/content)))

(rum/defc about-us []
  (page-wrapper (a/content)))

(rum/defc web []
  (page-wrapper (w/content)))


(rum/defc not-found []
  [:h1 "Page Not Found"])

;;
;;   Need to find a way to condense the page-wrapper with the page
;;




#?(:cljs
   (rum/defc page < rum/reactive []
     (let [token (rum/react current-token)]
       (case (get-route token)
         :index      (home)
         :veganism   (veganism)
         :consulting (consulting)
         :community  (community)
         :about-us   (about-us)
         :not-found  (not-found)
         :web        (web)


         :devcards   (devcards/init)))))
;; not found, basically
                                        ;(home)))))



;#?(:cljs (goog.style/installStyles (get-atomic-css(styler/get-css-str false))))

;; This println somehow stops the style classnames from incorrectly being put on
;; the wrong html elements in the final cljs output
;(println @global-css-styles)
;#?(:cljs (goog.style/installStyles (get-atomic-css(styler/get-css-str false))))

;; Hack to fix styles
;(installer-hack)
;(css {:random "random"})

#?(:cljs
   (do
     (defonce prev (volatile! nil))
     (when @prev
       (goog.style/uninstallStyles @prev))
     (vreset! prev (goog.style/installStyles (get-css-str false)))))

#?(:cljs
   (defn init []
     (rum/mount (page) (. js/document (getElementById "container")))))
