(ns vbn.components
  (:require [rum.core :as rum]
            [bidi.bidi :as b :refer [match-route path-for]]
            #?(:cljs [vbn.navigation :refer [link current-token]])

            ))

;; HELPER COMPONENTS

(rum/defc svg-icon [meta]
  (let [{:keys [alt viewbox href]} meta]
    [:svg {:alt alt
           :viewbox viewbox}
     [:use {:xlink-href href}]]))

;; Accessibility defaults

(rum/defc skip-to-main []
  [:a.skip-to-main {:href "#main"}
   [:span "Skip to main content"]])

(rum/defc main [content]
  "Enters content into main container with id=\"main\" "
  [:main#main content])


(def my-routes ["/" [[#{"" "index.html"} :index]
                     ["veganism.html" :veganism]
                     ["about-us.html" :about-us]
                     ["consulting.html" :consulting]
                     ["community.html" :community]
                     ["devcards.html" :devcards]]])
                                        ;[true :not-found]]])


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
