(ns vbn.index
  (:require [rum.core :as rum]
            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]
            [vbn.organisms :as organism]))


     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   HOME PAGE SPECIFIC   ;;;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(rum/defc banner-image[]
  [:div#banner-image.full-width
   [:span "Fostering a better world through business"]])

(rum/defc sign-up []
  [:div.sign-up-box.full-width.buffer-top-large
   [:div.inside-block
    [:p "We are always up to new and interesting things. We can send you a few emails from time to time to let you know what is happening in the community."]]])


(rum/defc content []
  [:main#main.footer-buffer
   (banner-image)
   (atom/h1-home "Vegan Business Network")
   (organism/bigger-than-business)
   (organism/movement)
   (organism/at-our-core)
   (sign-up)
   (organism/what-we-do)
   ;[:a {:href "/devcards.html"} "Devcards"]
   ])


   ;(hidden)

   ;(link (path-for my-routes :devcards) "Dev Cards")])
