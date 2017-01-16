(ns vbn.community
  #?(:cljs (:require-macros  [vbn.styler :refer [css at-media]]))
  (:require [rum.core :as rum]
            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]
            #?(:clj [vbn.styler :refer [css at-media get-css-str]])))


(def meetup-num [(css {:width "2em"})])
(def meetup-text [(css {:flex-direction "row"
                          :flex-wrap "wrap"})])

(rum/defc content []
  [:main#main.footer-buffer
   (molecule/page-intro
    [:h1
     "Community"]
    [:p "To foster the vegan business community we host two different networking meetups every month."]
    [:p "One is purely social and the other boasts guest speakers or a discussion forum regarding pertinent topics for the vegan/vegan friendly business owner."])

   ;; Should try out putting the css in a let binding or a def for reusability
   [:div {:class [(css {:align-self "center"
                        :width "100%"
                        :max-width "35em"
                        :border-style "solid"
                        :border-width "0.2em"
                        :padding "2em"
                          ;Need to replace black with brand-dark
                        :border-color "black"
                        :border-radius "0.5em"})]}
    [:div {:class [(css {:flex-direction "row"
                         :flex-wrap "wrap"})]}
     [:img.meetup-logo {:src "photos/meetup-logo.png"
                        :alt-text "Meetup Logo"}]

     [:div#meetup-text-group {:style {:padding "1em"}}
      [:div {:class meetup-text}
       [:div {:class meetup-num} "103"] "Members"]
      [:div {:class meetup-text}
       [:div {:class meetup-num} "2"] "Meetups this month"]]]
    [:button#meetup-button.buffer-top-large [:span "Join our Meetup group"]]]])

   ;; API FOR MEMBERS
   ;;https://api.meetup.com/VeganBN?photo-host=public&sig_id=23539071&only=members&sig=3ef1f60056ecefb460690cfe496e3d07ac0188f5
   ;; API FOR EVENTS
   ;; https://api.meetup.com/2/events?offset=0&format=json&limited_events=False&group_urlname=VeganBN&only=time&photo-host=public&page=20&fields=&order=time&desc=false&status=upcoming&sig_id=23539071&sig=4befcc42261f8a53f40516ab2c65b367c8150699
   ;; CLJ TIME - (c/from-long  (:time  <naam variable))
;#?(:clj (println "This is how it looks inside community.clj "(get-css-str false)))
