(ns vbn.consulting
  (:require [rum.core :as rum]
            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]))

(rum/defc content []
  [:main#main
   (molecule/page-intro
    [:h1 "Consulting"])
   ])

