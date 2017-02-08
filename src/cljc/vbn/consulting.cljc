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


;;;;;;;;;
;;;;;;;;;   CONSULTING BLOCK
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(rum/defc consult-block []
  [:div {:class ["full-width"
                 "consulting-block"
                 (css {:color "white"
                       :background-color "#333D47"
                       :margin-top "-0.3em"})]}
   [:div {:class [(css {:width "100vw"
                        :max-width "53em"
                        :align-self "center"
                        :padding-left "1.5em"
                        :padding-right "1.5em"
                        :padding-top "4.5em"
                        :padding-bottom "4.5em"})]}
    ;; Leave consulting block text because it uses calc
    [:span.consulting-block-text "You bring the idea. "
     [:strong;.green-text
      ;; Replacing this green text shows up on the Community page
      ;; on the stats of members and number of meetups
          {:class [(css {:color "#00ff7f"
                         :letter-spacing "0.01em"})]}
       "We bring it to life."]]]])













;;;;;;;;;
;;;;;;;;;   NATIVE SECTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(rum/defc native []
  [:div {:class ["buffer-top-large"
                 "consult-component"
                 (css {:flex-direction "row"
                       :flex-wrap "wrap"
                       :justify-content "center"
                       :align-items "center"})]}
   (atom/native)
   [:div
    [:h3 {:class (css {:background "red"} :hover {:color "green"})} "We're Native Vegans"]
    [:p {:class [(css {:font-size "1.125em"})]} "The vegan community is our home. Our finger is on the pulse of this beautiful community. We know what is happening. When it is happening. Why it is happening. We are also creating and driving change ourselves."]]])



;;;;;;;;;
;;;;;;;;;   SERVICES SECTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(rum/defc service
  ([text] [:li {:class [(css {:line-height "1.5em"
                              :min-height "48px"})]}
           [:span text]])

  ([text route] [:li {:class [(css {:line-height "1.5em"
                                    :min-height "48px"
                                    :text-decoration "underline"})]}

                 (link (path-for my-routes route)[:span text])]))
; THIS NEEDS TO BE ADDED TO HERE ^^^^^
;[:a {:text-decoration 'underline}
; [:&:hover
;  :&:focus {:font-weight 'bold})


;;;;
;;;;  FULL SLICE
;;;;

(rum/defc services []
  [:div.block-green.full-width.buffer-top-large
   [:div {:class [(css {:width "100vw"
                        :max-width "53em"
                        :align-self "center"
                        :padding-left "1.5em"
                        :padding-right "1.5em"
                        :padding-top "4.5em"
                        :padding-bottom "4.5"})]}

    (atom/services)

    [:h2 {:class [(css {:margin-left "-0.2em"
                        :align-self "center"})]}
     ;.centre.services-align
      "Services"]
    [:div {:class [(css {:padding-left "1.5em"
                         :padding-right "1.5em"})]}
     [:div {:class [(css {:flex-direction "row"
                          :justify-content "space-between"})]}
      [:div {:class [(css {:margin-top "2em"})
                     (at-media {:max-width "60rem"} {:align-self "center"})]}
                     ;; Doesn't appear to allow multipl media queries
                     ;(at-media {:min-width "60rem"} {:padding "1em"})]}
       (atom/strategy)
       (atom/h3 "Strategy")
       [:ul.service-list
         (service "Business Coaching")
         (service "Branding")
         (service "Legal")]]


      [:div {:class [(css {:margin-top "2em"})
                     (at-media {:max-width "60rem"} {:align-self "center"})]}
                     ;; Doesn't appear to allow multipl media queries
                     ;(at-media {:min-width "60rem"} {:padding "1em"})]}
       (atom/digital)
       (atom/h3 "Digital")
       [:ul.service-list
        (service "Web & Apps" :web)
        (service "Video & Animation")
        (service "Social Media Marketing")]]

      [:div {:class [(css {:margin-top "2em"})
                     (at-media {:max-width "60rem"} {:align-self "center"})]}
                     ;; Doesn't appear to allow multipl media queries
                     ;(at-media {:min-width "60rem"} {:padding "1em"})]}
       (atom/physical)
       (atom/h3 "Print")
       [:ul.service-list
        (service "Business Cards")
        (service "Flyers & Posters")
        (service "Signage & Merch")]]]]]])



;;;;;;;;;
;;;;;;;;;   SERVICES SECTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(rum/defc harmony []
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

    [:p {:class [(css {:font-size "1.125em"})]} "We are ready to bring some harmony to this entire process letting you work on what makes your business special."]]])



;;;;;;;;;
;;;;;;;;;   PUZZLE SECTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(rum/defc puzzle []
   [:div {:class ["full-width"
                  "buffer-top-large"
                  (css {:color "white"
                        :background-color "#333D47"})]}
    [:div {:class [(css {:width "100vw"
                         :max-width "53em"
                         :align-self "center"
                         :padding-left "1.5em"
                         :padding-right "1.5em"
                         :padding-top "4.5em"
                         :padding-bottom "4.5"})]}
     [:div {:class ["consult-component"
                    (css {:flex-direction "row"
                          :flex-wrap "wrap"
                          :justify-content "center"
                          :align-items "center"})]}
      (atom/puzzle)
      [:div
       (atom/h2 "How we do it")
       [:p {:class [(css {:font-size "1.125em"})]} "We do everything with our in house team or through tight partnerships. If what you need goes outside of our in house offerings. We have a strong network of talented individuals and organisations we can call upon. "]
       [:p {:class [(css {:font-size "1.125em"})]} "We can tell you who is vegan and those that can get the job done. We also provide a service of verification and can review your list of candidates to ensure they are capable of doing the work required to suit your needs. This allows us to cater to the most advanced needs, the budget restricted or any location challenges."]]]]])






;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;   FULL PAGE CONTENT   ;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(rum/defc content []
  [:main#main
   (molecule/page-intro
    [:h1 "Consulting"]
    [:p "We provide full service business consulting"])

   [:span.consulting-top-text.buffer-top  "What does that mean?"]
   (consult-block)
   (native)
   (services)
   (harmony)
   (puzzle)])
