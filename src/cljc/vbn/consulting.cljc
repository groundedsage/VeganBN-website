(ns vbn.consulting
  #?(:cljs (:require-macros  [vbn.styler :refer [css at-media]]))
  (:require [rum.core :as rum]
            [bidi.bidi :refer [path-for]]

            #?(:cljs [vbn.navigation :refer [link current-token]])
            [vbn.components :refer [my-routes]]

            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]
            #?(:clj [vbn.styler :refer [css at-media get-css-str]])))

;; define clojure version of link
#?(:clj (rum/defc link [link & content]
          [:a {:href link} content]))





(rum/defc consult-block []
  [:div {:class ["full-width"
                 "consulting-block"
                 (css {:color "white"
                       :background-color "#333D47"
                       :margin-top "-0.3em"})]}
   [:div.inside-block.extra-padding
    ;; Leave consulting block text because it uses calc
    [:span.consulting-block-text "You bring the idea. "
     [:strong;.green-text
      ;; Replacing this green text shows up on the Community page
      ;; on the stats of members and number of meetups
          {:class [(css {:color "#00ff7f"
                         :letter-spacing "0.01em"})]}
       "We bring it to life."]]]])



;;;;;;;;;;;;;;; END OF DELETING

(rum/defc content []
  [:main#main
   (molecule/page-intro
    [:h1 "Consulting"]
    [:p "We provide full service business consulting"])

   [:span.consulting-top-text.buffer-top  "What does that mean?"]
   (consult-block)


   [:div {:class ["buffer-top-large"
                  "consult-component"
                  (css {:flex-direction "row"
                        :flex-wrap "wrap"
                        :justify-content "center"
                        :align-items "center"})]}
    (atom/native)
    [:div
     [:h3 {:class [(css {:background "red"})]} "We're Native Vegans"]
     [:p {:class [(css {:font-size "1.125em"})]} "The vegan community is our home. Our finger is on the pulse of this beautiful community. We know what is happening. When it is happening. Why it is happening. We are also creating and driving change ourselves."]]]




   [:div.block-green.full-width.buffer-top-large
    [:div.inside-block

     (atom/services)

     [:h2.centre.services-align "Services"]
     [:div.bullet-padding
      [:div.three-up
       [:div.inside-three.center-to-60
        (atom/strategy)
        (atom/h3 "Strategy")
        [:ul.service-list
         [:li "Business Coaching"]
         [:li "Branding"]
         [:li "Legal"]]]


       [:div.inside-three.center-to-60
        (atom/digital)
        (atom/h3 "Digital")
        [:ul.service-list
         [:li (link (path-for my-routes :web) [:span "Web & Apps"])]
         [:li "Video & Animation"]
         [:li "Social Media Marketing"]]]

       [:div.inside-three.center-to-60
        (atom/physical)
        (atom/h3 "Print")
        [:ul.service-list
         [:li "Business Cards"]
         [:li "Flyers & Posters"]
         [:li "Signage & Merch"]]]]]]]

   [:div {:class ["consult-component"
                  "buffer-top-large"
                  (css {:flex-direction "row"
                        :flex-wrap "wrap"
                        :justify-content "center"
                        :align-items "center"})]}
    (atom/harmonise)

    [:div
     (atom/h2 "Harmonise the services")
     [:p {:class [(css {:font-size "1.125em"})]} "One stop shops for professional business services are rare. But today communication between multiple teams is critical. Co-ordinating communication between lawyers, designers, developers and marketers is the last thing you want to be doing when starting a business. "]

     [:p {:class [(css {:font-size "1.125em"})]} "We are ready to bring some harmony to this entire process letting you work on what makes your business special."]]]

   [:div {:class ["full-width"
                  "buffer-top-large"
                  (css {:color "white"
                        :background-color "#333D47"})]}
    [:div.inside-block
     [:div {:class ["consult-component"
                    (css {:flex-direction "row"
                          :flex-wrap "wrap"
                          :justify-content "center"
                          :align-items "center"})]}
      (atom/puzzle)
      [:div
       (atom/h2 "How we do it")
       [:p {:class [(css {:font-size "1.125em"})]} "We do everything with our in house team or through tight partnerships. If what you need goes outside of our in house offerings. We have a strong network of talented individuals and organisations we can call upon. "]
       [:p {:class [(css {:font-size "1.125em"})]} "We can tell you who is vegan and those that can get the job done. We also provide a service of verification and can review your list of candidates to ensure they are capable of doing the work required to suit your needs. This allows us to cater to the most advanced needs, the budget restricted or any location challenges."]]]]]])
