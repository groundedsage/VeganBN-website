(ns vbn.atoms
  (:require [rum.core :as rum] ))

(rum/defc h1 [text]
  [:h1 text])

(rum/defc h1-home [text]
  [:h1.h1-home.line-under text])

(rum/defc h2 [text]
  [:h2 text])

(rum/defc h2-home [text]
  [:h2.h2-home.line-under text])

(rum/defc h3 [text]
  [:h3 text])

(rum/defc h4 [text]
  [:h4 text])

(rum/defc intro []
  [:div.intro-text
   [:p "intro text"]])


(rum/defc p [text]
  [:p text])


(rum/defc button [text]
  [:button [:span text]])


(rum/defc blurb-image [src alt-text]
  [:img.blurb-image {:src src
                     :alt alt-text}])


(rum/defc circle-icon [name]
  [:svg {:height "8rem"
         :width "80%"
         :viewBox "0 0 123 123"}
   [:use {:xlink-href (str name ".svg#" name)}]])


(rum/defc bigger-than-business []
  [:svg {:height "4.5em"
         :width "100%"
         :viewBox "0 0 230 100"}
   [:use {:xlink-href "bigger-than-business.svg#bigger-than-business"}]])


(rum/defc movement []
  [:svg {:height "7.5em"
         :width "100%"
         :viewBox "0 0 238 175"}
   [:use {:xlink-href "movement.svg#movement"}]])

