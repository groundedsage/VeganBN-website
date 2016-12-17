(ns vbn.web
  (:require [rum.core :as rum]
            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]
            ))


(rum/defc content []
  [:main#main
   (molecule/page-intro
    [:h1 "Web & App"]
    [:p "It’s a jungle out there! Branding and the technology are often viewed as two separate pieces with the quality of one sacrificed for the other. However effective storytelling requires both the branding and the technology to live in harmony. The ever evolving landscape of technology has left many businesses with dated, fragmented and inconsistent technology."])])
