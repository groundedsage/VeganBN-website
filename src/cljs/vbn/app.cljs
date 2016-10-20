(ns vbn.app
  (:require [rum.core :as rum]
            [devcards.core :as dc])
  (:require-macros [devcards.core :refer [defcard]]))

(enable-console-print!)
(devcards.core/start-devcard-ui!)

(rum/defc label [text]
  [:div
   [:h1 "A label"]
   [:p text]])


(defcard
  "label"
  (label "This is the label"))


;(defn init []
;  (rum/mount (label) (. js/document (getElementById "container"))))
