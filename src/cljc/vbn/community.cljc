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

   [:div
    [:div
     [:span "99 of members"]
     [:span "0 Meetups this month"]]
    [:button "Join our Meetup group"]]

   ;; API FOR MEMBERS
   ;;https://api.meetup.com/VeganBN?photo-host=public&sig_id=23539071&only=members&sig=3ef1f60056ecefb460690cfe496e3d07ac0188f5 


   ])

