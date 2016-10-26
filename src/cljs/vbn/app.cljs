(ns vbn.app
  (:require [rum.core :as rum]
            [devcards.core :as dc])
  (:require-macros [devcards.core :refer [defcard]]))

(enable-console-print!)


(rum/defc skip-to-main []
  [:a {:href "#main"} "Skip to main content"])

(rum/defc main [content]
  "Enters content into main container with id=\"main\" "
  [:main#main content])

(rum/defc inline-link [text link]
  [:a {:href link} text])





(rum/defc label [text]
  [:div
   [:h1 "A label"]
   [:p text (inline-link text "#")]])



;(defcard
;  "Skip to main"
;  (skip-to-main))



(defcard
  "label"
  (label "This is the label here" "This is a link"))

(defcard
  "main-testing"
  (main "This is some main content"))

(defn init []
  (devcards.core/start-devcard-ui!)
  )

;(defn init []
;  (rum/mount (label) (. js/document (getElementById "container"))))
