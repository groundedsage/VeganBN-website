(ns vbn.atoms
  (:require [rum.core :as rum] ))

(rum/defc h1 [text]
  [:h1 text])

(rum/defc h2 [text]
  [:h2 text])

(rum/defc h3 [text]
  [:h3 text])

(rum/defc h4 [text]
  [:h4 text])

(rum/defc p [text]
  [:p text])

(rum/defc button [text]
  [:button text])

(rum/defc blurb-image [src alt-text]
  [:img.blurb-image {:src src
                     :alt alt-text}])

(rum/defc circle-icon [name]
  [:div {:style {:width "100%"
                 :max-width "250px"
                 :align-items "center"}}
  [:svg {:height "80%"
         :width "80%"
         :viewBox "0 0 123 123"
         }
   [:use
    {:xlink-href (str name ".svg#" name)}]]])

(rum/defc bigger-than-business []
  [:div.home-component
   [:svg {
          :height "100%"
          :width "100%"
          :viewBox "0 0 230 100"}
    [:use 
     {:xlink-href "bigger-than-business.svg#bigger-than-business"}]]
   
   ])

(rum/defc movement []
  [:div.home-component
   [:svg {
          :height "100%"
          :width "100%"
          :viewBox "0 0 238 175"}
    [:use 
     {:xlink-href "movement.svg#movement"}]]
   
   ])

(rum/defc intro [text]
  [:div.intro-text text])


