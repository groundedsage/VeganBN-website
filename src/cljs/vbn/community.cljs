(ns vbn.community
  (:require [rum.core :as rum]
            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]))

(rum/defc content []
  [:main#main
   (molecule/page-intro
    [:h1 "Community"]
    [:p "To foster the vegan business community we host two different networking meetups every month."]
    [:p "One is purely social and the other boasts guest speakers or a discussion forum regarding pertinent topics for the vegan/vegan friendly business owner."])
   ])

