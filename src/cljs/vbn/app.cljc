(ns vbn.app
  (:require [rum.core :as rum]
            [devcards.core :as dc]
            [bidi.bidi :as b :refer [match-route path-for]]
            [vbn.index :as i]
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

(def my-routes ["/" {"" :index
                     "index.html" :index
                     "veganism.html" :veganism
                     "about-us.html" :about-us
                     "consulting.html" :consulting
                     "community.html" :community
                     "devcards.html" :devcards}])

(defn get-route [url]
  (:handler (match-route my-routes url)))

;; define clojure version of link
#?(:clj (rum/defc link [link & content]
          [:a {:href link} content]))

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

(rum/defc home [content]
  [:div
   (navigation)
   [:main#main
    (i/banner-image)
    (i/h1-home "Vegan Business Network")
    (i/bigger-than-business)
    (i/movement)

    (i/h2-home "At Our Core")
    [:div.at-our-core
     (i/community)
     (i/education)]
    (i/sign-up)

    (i/h2-home "What We Do")
    (i/blurbs-title-second)


    (hidden)

    (link (path-for my-routes :devcards) "Dev Cards")]])

(rum/defc veganism []
  [:div
   (skip-to-main)
   (navigation)
   [:main#main
    [:h1 "This is the veganism page"]]])

(rum/defc consulting []
  [:div
   (skip-to-main)
   (navigation)
   [:main#main
    [:h1 "This is the consulting page"]]])

(rum/defc community []
  [:div
   (skip-to-main)
   (navigation)
   [:main#main
    [:h1 "This is the community page"]]])

(rum/defc about-us []
  [:div
   (skip-to-main)
   (navigation)
   [:main#main
    [:h1 "This is the About Us page"]]])

#?(:cljs
   (rum/defc page < rum/reactive []
     (let [token (rum/react current-token)]
       (case (get-route token)
         :index      (home)
         :veganism   (veganism)
         :consulting (consulting)
         :community  (community)
         :about-us   (about-us)
         :devcards   (devcards/init)
         ;; not found, basically
         (home)))))

#?(:cljs
   (defn init []
     (rum/mount (page) (. js/document (getElementById "container")))))
