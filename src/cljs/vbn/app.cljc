(ns vbn.app
  (:require [rum.core :as rum]
            [devcards.core :as dc]
            [bidi.bidi :as b :refer [match-route path-for]]

            [vbn.index :as i]
            [vbn.veganism :as v]
            [vbn.consulting :as ct]
            [vbn.community :as cm]
            [vbn.about :as a]





            #?(:cljs [vbn.navigation :refer [link current-token]])
            #?(:cljs [vbn.devcards :as devcards])))

#?(:cljs (enable-console-print!))

;; Accessibility defaults

(rum/defc skip-to-main []
  [:a.skip-to-main {:href "#main"}
   [:span "Skip to main content"]])

(rum/defc main [content]
  "Enters content into main container with id=\"main\" "
  [:main#main content])

;; Site specific

(rum/defc hidden []
  [:span  {:hidden true} "This is hidden text"])


;; NAVIGATION

(def my-routes ["/" [[#{"" "index.html"} :index]
                     ["veganism.html" :veganism]
                     ["about-us.html" :about-us]
                     ["consulting.html" :consulting]
                     ["community.html" :community]
                     [true :not-found]
                     ]])

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
                                        :viewBox "0 0 158 172"}
                             [:use {:xlink-href "logo.svg#logo"}]])]
    [:li.order-front (link (path-for my-routes :about-us) [:span "About Us"])]
    [:li.order-front (link (path-for my-routes :veganism) [:span "Veganism"])]
    [:li.order-end   (link (path-for my-routes :consulting) [:span "Consulting"])]
    [:li.order-end   (link (path-for my-routes :community) [:span "Community"])]]])


(rum/defc page-wrapper [content]
  [:div
   (skip-to-main)
   (navigation)
   content])

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


         ;;
         ;;  Devcards remains at the top of the page after visiting and returning to normal pages
         ;;

         :devcards   (devcards/init)
         ;; not found, basically
         (home)))))

#?(:cljs
   (defn init []
     (rum/mount (page) (. js/document (getElementById "container")))))
