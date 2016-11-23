(ns vbn.components
  (:require [rum.core :as rum]))

;; HELPER COMPONENTS

(rum/defc svg-icon [meta]
  (let [{:keys [alt viewbox href]} meta]
    [:svg {:alt alt
           :viewbox viewbox}
     [:use {:xlink-href href}]]))
