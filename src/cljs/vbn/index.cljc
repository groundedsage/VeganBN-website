(ns vbn.index
  (:require [rum.core :as rum]))

     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   HOME PAGE SPECIFIC   ;;;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(rum/defc banner-image[]
  [:div#banner-image.full-width
   [:span "Fostering a better world through business"]])


(rum/defc h1-home [text]
  [:h1.h1-home.line-under text])

(rum/defc bigger-than-business []
  [:div.home-component
   [:svg {
            :height "100%"
            :width "100%"
            :viewBox "0 0 230 100"}
      [:use
       {:xlink-href "bigger-than-business.svg#bigger-than-business"}]]

   [:p "There is a monumental shift happening all over the world right now!" [:br.rwd-break] " We believe in a better world and we are here to make that happen."]])



(rum/defc movement []
  [:div.home-component
   [:svg {
          :height "100%"
          :width "100%"
          :viewBox "0 0 238 175"}
    [:use
     {:xlink-href "movement.svg#movement"}]]


   [:p "The vegan movement continues to grow exponentially, however businesses still have little knowledge on how to cater to this rapdily growing industry."]])

(rum/defc h2-home [text]
  [:h2.h2-home.line-under text])

(rum/defc community []
  [:div {:style {:width "100%"
                 :max-width "250px"
                 :align-items "center"}}
   [:svg {:height "80%"
          :width "80%"
          :viewBox "0 0 123 123"}
                                        ; :preserveAspectRatio "xMidyMid slice"

    [:use
     {:xlink-href "community.svg#community"}]]
   [:p "We are about community"]])


(rum/defc education []
  [:div {:style {:width "100%"
                 :max-width "250px"
                 :align-items "center"}}
   [:svg {:height "80%"
          :width "80%"
          :viewBox "0 0 123 123"}
                                        ; :preserveAspectRatio "xMidyMid slice"

    [:use
     {:xlink-href "education.svg#education"}]]
   (comment
     (svg-icon {:alt "Education"
                :viewbox "0 0 238 175"
                :href "education.svg#education"}))

   [:span "We are about education"]])

(rum/defc sign-up []
  [:div.sign-up-box.full-width
   [:div.inside-block
    [:p "We are always up to new and interesting things. We can send you a few emails from time to time to let you know what is happening in the community."]]])

(rum/defc blurb-image [src alt-text]
  [:img.blurb-image {:src src
                     :alt alt-text}])

(rum/defc h3 [text]
  [:h3 text])



(rum/defc image-title-text-cta [content]
  (let [{:keys [image title text cta]} content]
   [:div.image-title-text-cta
    (h3 title)
    [:p text]
    [:button cta]]))


(rum/defc blurbs-title-second []
  [:div
   [:div.blurb
    (blurb-image "photos/meetup.jpg" "meetup")
    (image-title-text-cta {:title "Meetups TWICE a month"
                           :text "We encourage people from all walks of life to come to our meetups. If you are interested in veganism, entrepreneurship, or you are just a business owner with a little curiosity about what a vegan is then this event is the one for you"
                           :cta "I want to know more"})]
   [:div.blurb
    (blurb-image "photos/consulting.jpg" "consulting")
    (image-title-text-cta {:title "Consulting"
                           :text "We provide an array of services to help everyone from the budding entrepreneur to both the small and the large business owner. If you are ready to catapult to new heights we are ready to help."
                           :cta "I'm READY!"})]
   [:div.blurb
    (blurb-image "photos/road.jpg" "more")
    (image-title-text-cta {:title "More"
                           :text "We have so many plans and exciting things we are eager to show you. If you would like to sta in the loop don't forget to sign up to our mailing list."
                           :cta "Sign me up!"})]])



(rum/defc intro []
  [:div.intro-text
   [:p "intro text"]])


(rum/defc content []
  [:main#main
   (banner-image)
   (h1-home "Vegan Business Network")
   [:a {:href "/devcards.html"} "Devcards"]

   (bigger-than-business)
   (movement)

   (h2-home "At Our Core")
   [:div.at-our-core
    (community)
    (education)]
   (sign-up)

   (h2-home "What We Do")
   (blurbs-title-second)])


   ;(hidden)

   ;(link (path-for my-routes :devcards) "Dev Cards")])
