(ns vbn.organisms
  (:require [rum.core :as rum]
            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]

            ))


(rum/defc bigger-than-business []
  [:div.home-component
   (atom/bigger-than-business)
   [:p "There is a monumental shift happening all over the world right now! We believe in a better world and we are here to make that happen."]])



(rum/defc movement []
  [:div.home-component
   (atom/movement)
   [:p "The vegan movement continues to grow exponentially, however businesses still have little knowledge on how to cater to this rapdily growing industry."]])



(rum/defc what-we-do []
  [:div.buffer-top-large
   (atom/h2-home "What We Do")
   (molecule/blurb-title-second {:image "photos/meetup.jpg"
                                 :alt-text "Cafe"
                                 :title "Meetups TWICE a month"
                                 :text [:p "We encourage people from all walks of life to come to our meetups. If you are interested in veganism, entrepreneurship, or you are just a business owner with a little curiosity about what a vegan is then this event is the one for you"]
                                 :cta "I want to know more"
                                 :cta-key :community})
   (molecule/blurb-title-second {:image "photos/consulting.jpg"
                                 :alt-text "Cafe"
                                 :title "Consulting"
                                 :text [:p "We provide an array of services to help everyone from the budding entrepreneur to both the small and the large business owner. If you are ready to catapult to new heights we are ready to help"]
                                 :cta "I'm READY!"
                                 :cta-key :consulting})
   (molecule/blurb-title-second {:image "photos/road.jpg"
                                 :alt-text "Cafe"
                                 :title "The Road Ahead"
                                 :text [:p "We have so many plans and exciting things we are eager to show you. If you would like to stay in the loop don't forget to sign up to our mailing list"]
                                 :cta "Sign me up"
                                 :cta-key "#sign-up"})])

(rum/defc at-our-core []

  [:div.buffer-top-large
   (atom/h2-home "At Our Core") 
   [:div.at-our-core
    (molecule/icon-with-text "community" "We are about community")
    (molecule/icon-with-text "education" "We are about education")]])


