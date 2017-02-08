(ns vbn.components
  (:require [rum.core :as rum]
            [bidi.bidi :as b :refer [match-route path-for]]
            #?(:cljs [vbn.navigation :refer [link current-token]])))



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

  ;; define clojure version of link
#?(:clj (rum/defc link [link & content]
          [:a {:href link} content]))

                                      ;; NAVIGATION

  (def my-routes ["/" [[#{"" "index"} :index]
                       ["veganism" :veganism]
                       ["about-us" :about-us]
                       ["consulting/"  [[#{"" "index"} :consulting]
                                        ["web" :web]]]
                       ["community" :community]]])
                                          ;["devcards.html" :devcards]
            ;[true :not-found]]])

(rum/defc navigation []
  [:nav
   [:ul
    [:li.order-middle (link (path-for my-routes :index)
                            [:span {:aria-hidden true} "Home"]
                            [:svg.home {:alt "VBN Logo Home"
                                        :viewBox "0 0 160 150"}
                             [:use {:xlink-href "logo.svg#logo"}]])]
    [:li.order-front (link (path-for my-routes :about-us) [:span "About Us"])]
    [:li.order-front (link (path-for my-routes :veganism) [:span "Veganism"])]
    [:li.order-end   (link (path-for my-routes :consulting) [:span "Consulting"])]
    [:li.order-end   (link (path-for my-routes :community) [:span "Community"])]]])
