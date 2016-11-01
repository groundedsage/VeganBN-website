(ns vbn.app
  (:require [rum.core :as rum]
            [devcards.core :as dc])
  (:require-macros [devcards.core :refer [defcard]]))

(enable-console-print!)

;; Accessibility defaults
(rum/defc skip-to-main []
  [:a.skip-to-main {:href "#main"}
   [:span "Skip to main content"]
   ])

(rum/defc main [content]
  "Enters content into main container with id=\"main\" "
  [:main#main content])

(rum/defc inline-link [text link]
  [:a {:href link} text])

;; Site specific


(rum/defc hidden []
  [:span  {:hidden true} "This is hidden text"])


(rum/defc navigation []
  [:nav
   [:ul
    [:li.order-middle [:a {:href "#main"} [:span {:aria-hidden true} "Home"]
      [:svg.home {:alt "VBN Logo Home"
                  :viewBox "0 0 158 172"}
       [:use
        {:xlink-href "logo.svg#logo"}]]]]
    [:li.order-front [:a {:href "/about-us.html"} [:span "About Us"]]]
    [:li.order-front [:a {:href "/veganism.html"} [:span "Veganism"]]]
    [:li.order-end [:a {:href "/consulting.html"} [:span "Consulting"]]]
    [:li.order-end [:a {:href "/community.html"} [:span "Community"]]]

    ]])

     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   HOME PAGE SPECIFIC   ;;;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(rum/defc banner-image[]
  [:div#banner-image
   [:span "Fostering a better world through business"]])


(rum/defc h1-home [text]
  [:h1.h1-home.line-under text])

(rum/defc bigger-than-business []
  [:div.home-component
   [:svg
    {:alt "VBN Logo Home"
     :viewBox "0 0 255 100"}
    [:use {:xlink-href "bigger-than-business.svg#bigger-than-business"}]]
   [:p "There is a monumental shift happening all over the world right now! We believe in a better world and we are here to make that happen."]])

(rum/defc movement []
  [:div.home-component
   [:svg {:alt "Movement"
          :viewbox "0 0 238 175"}
    [:use {:xlink-href "movement.svg#movement"}]]
   [:p "The vegan movement continues to grow exponentially, however businesses still have little knowledge on how to cater to this rapdily growing industry."]])

(rum/defc h2-home [text]
  [:h2.h2-home.line-under text])

(rum/defc community []
  [:div
   [:svg {:alt "Community"
          :viewbox "0 0 238 175"}
    [:use {:xlink-href "community.svg#community"}]]
   [:p "We are about community"]])


(rum/defc education []
  [:div
   [:svg {:alt "Education"}
    [:use {:xlink-href "education.svg#education"}]]
   [:span "We are about education"]])

(rum/defc sign-up []
  [:div.sign-up-box
   [:p "We are always up to new and interesting things. We can send you a few emails from time to time to let you know what is happening in the community."]])

(rum/defc blurb-image []
  [:img.blurb-image {:src ""
         :alt ""}])

(rum/defc h3 [text]
  [:h3 text])

(rum/defc blurb []
  [:div.blurb
;   {:style {:background 'blue}}
   (blurb-image)
   [:div
   (h3 "Meetups TWICE a month")
   [:p "We encourage people from all walks of life to come to our meetups. If you are interested in veganism, entrepreneurship, or you are just a business owner with a little curiosity about what a vegan is then this event is the one for you"]
   [:button "I want to know more"]]])



(rum/defc intro []
  [:div.intro-text
  [:p "intro text"]])


(rum/defc page [content]
  [:div
   (skip-to-main)
   (navigation)
   [:main#main
    (banner-image)
    (h1-home "Vegan Business Network")
    (bigger-than-business)
    (movement)

    (h2-home "At Our Core")
[:div.at-our-core
    (community)
    (education)]
    (sign-up)

    (h2-home "What We Do")
    (blurb)




    ]
   ;(intro)

   (hidden)
   ])

(defn init []
  (rum/mount (page) (. js/document (getElementById "container"))))
