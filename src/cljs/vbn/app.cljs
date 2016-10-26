(ns vbn.app
  (:require [rum.core :as rum]
            [devcards.core :as dc])
  (:require-macros [devcards.core :refer [defcard]]))

(enable-console-print!)

;; Accessibility defaults
(rum/defc skip-to-main []
  [:a.skip-to-main {:href "#main"}
   [:span "Skip to main content"]
   ])

(rum/defc main [content]
  "Enters content into main container with id=\"main\" "
  [:main#main content])

(rum/defc inline-link [text link]
  [:a {:href link} text])

;; Site specific


(rum/defc hidden []
  [:span  {:hidden true} "This is hidden text"])


(rum/defc navigation []
  [:nav
   [:ul
    [:li.order-3 [:a {:href "#main"}
                  [:svg.home {:alt "VBN Logo Home"
                              :viewBox "0 0 158 172"
                              }
                [:use
                 {:xlink-href "logo.svg#logo"}]]]]
    [:li.order-1 [:a {:href "/about-us.html"} "About Us"]]
    [:li.order-2 [:a {:href "/veganism.html"} "Veganism"]]
    [:li.order-4 [:a {:href "/consulting.html"} "Consulting"]]
    [:li.order-5 [:a {:href "/community.html"} "Community"]]

    ]])







(rum/defc label [text]
  [:div
   [:h1 "A label"]
   [:p text (inline-link text "#")]])


(rum/defc page [content]
  [:div
   (skip-to-main)
   (navigation)
   (hidden)

   ])

(defn init []
  (rum/mount (page) (. js/document (getElementById "container"))))
