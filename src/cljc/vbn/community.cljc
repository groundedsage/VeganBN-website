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
   ;; API FOR EVENTS
   ;; https://api.meetup.com/2/events?offset=0&format=json&limited_events=False&group_urlname=VeganBN&only=time&photo-host=public&page=20&fields=&order=time&desc=false&status=upcoming&sig_id=23539071&sig=4befcc42261f8a53f40516ab2c65b367c8150699
   ;; CLJ TIME - (c/from-long  (:time  <naam variable))



   ])

