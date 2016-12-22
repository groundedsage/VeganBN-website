(ns vbn.styles
  (:refer-clojure :exclude [rem not])
  (:require [garden.def :refer [defrule defstyles]]
            [garden.stylesheet :refer [rule at-media]]

            ;; My changes
            [garden.core :refer [css]]
            [garden.units :refer [rem
                                  em
                                  px]]
            [garden.selectors :as s :refer [attr=
                                            attr-starts-with
                                            attr-matches
                                            focus
                                            defselector
                                            defpseudoclass
                                            defclass]]))



(def brand-color "#00ff7f")
(def brand-blue
 ; "#0000e0"
 ; "#0000B5"
 ; "#1F3A93"
 ; "#205d86"
  "#3a539b"
    )
(def brand-dark "#333D47")
(def cta-colour "#E6F14A")

;; New Blue #0000B5
;; Other Blue #0000e0
;; #1F3A93 Looks good
;; #205d86 a bit lighter
;; Another #3a539b

;; New Green #6FDC6F


(defstyles screen
  (let [html (rule :html)
        body (rule :body)]

    (body
     ;; Remove this later
     {:color "#050709"}

     [:h6 {:font-size "calc(1.602em + (2.441 - 1.602) * (100vw - 25em)/(62.5 - 25))"}]




     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   LAYOUT DEFAULTS   ;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

     [:* {:box-sizing 'border-box}
      [:&:focus {:outline 'none}]]

     ;; Normally have html and body in here also however they have been moved to the top
     [:header
      :div   ; This interferes with the top level of devcards navigation
      :section
      :article

      ;; POSSIBLY REMOVE THESE AS DEFAULTS
      ;:a
     ; :span
      :main
      :nav
      :ul
      ;:li
      :svg

      {:display 'flex
       :position 'relative
       :flex-direction 'column
       :align-items 'stretch
       :flex-shrink 0
       :border "0 solid black"
       :margin 0
       :padding 0
       :max-width "100%"}]

     [:nav [:li
            {:display 'flex
             :position 'relative
             :flex-direction 'column
             :align-items 'stretch
             :flex-shrink 0
             :border "0 solid black"
             :margin 0
             :padding 0
             :max-width "100%"}]]

     [:nav [:ul {:max-width (em 53)
                 :width "100%"
                 :align-self 'center}]]


     [:.page-padding {:margin {:left (rem 1.5)
                               :right (rem 1.5)}}]

     [:li {:padding-left (rem 0.3125)}]
     [:ol.values {:margin-left (rem -0.3125)}]

     [:.values-section {:display 'flex
                        :flex-direction 'column
                        :align-items 'baseline
                        :padding {:left (em 1.5)
                                  :right (em 1.5)}}]
     [:.vision-section
      :.bullet-padding {:padding {:left (em 1.5)
                                  :right (em 1.5)}}]




     (at-media {:min-width (rem 30)}
               [:.values-section
                :.vision-section  {:flex-direction 'row
                                   :align-items 'baseline
                                   :padding {:left 0
                                             :right 0}}

                [:h3 {:margin-right (em 3)}]]
               [:ol.values
                :.vision {:width "80%"}])





     [:.vision-title {:font-weight 'bold
                      :font-size (em 1.125)
                      :line-height (em 1.5)}]


     [:ol.values
      [:li {:font-weight 'bold}]

      ["span:first-child"{
                          :font-size (em 1.125)
                          :line-height (em 1.5)}]

      [:p {:margin {:top (em 0.5)
                    :bottom (em 2.5)}}]]

     [:.simple-vision {:margin {:top (em 0.5)
                                :bottom (em 2.5)}}]





     ;; Remove default styling of button
     [:button {:padding 0}
      [:span {:width "100%"
              :font-family 'bitter
              :font-size (em 1)
              :display 'flex
              :justify-content 'center
              :color cta-colour
              :letter-spacing (em 0.01)}]]

     ;; Custom button styles

     [:button {:min-height (px 60)
               :background-color brand-dark   ;MAY CHANGE
               :border-radius (px 10)}

      [:&:hover
       :&:focus
       {:background-color cta-colour}
       [:span {:color brand-dark
               :font-weight 'bold}]]]

     [:.image-title-text-cta [:button {:margin-top 'auto}]]








     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   INCLUSIVITY PATTERNS   ;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


     ;; VISUALLY HIDDEN
     [:.visually-hidden {:position 'absolute
                         :width (px 1)
                         :height (px 1)
                         :overflow 'hidden
                         :clip "rect (1px, 1px, 1px, 1px)"}]

     ;;  SKIP TO MAIN
     [:.skip-to-main
                                        ;(attr= "href" "#main") - targets too many attributes
      {:position 'absolute
       :top 0

       ;;My Additions
       :height (px 0)
       :font-size (em 2)
       :width "100%"
       :color 'black
       :background-color brand-color
       :justify-content 'space-around
       :align-items 'center
       :flex-direction 'row
       :z-index 2
       :will-change 'height
       :transition-property 'height
       :transition-duration "0.2s"
       :transition-timing-function "cubic-bezier(0.175, 0.885, 0.32, 1.275)"}

      [:span {:display 'none}]


      [:&:focus {;:right 'auto
                 :height (px 150)
                 :outline 'none}
       [:span {:display 'flex}]]]



     [:main {:width "100vw"
             :max-width (em 53)
             :align-self 'center
             :padding {:left (em 1.5)
                       :right (em 1.5)}}]




     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   NAVIGATION BAR   ;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


     (defselector li)
     (defselector nav)
     (defpseudoclass before)
     (defpseudoclass not)
     (defclass order-middle)

     [:nav {:background-color brand-blue}]

     ;; Mobile Styles

     [:nav {:padding {:top (em 1.5)
                      :left (em 1.5)
                      :right (em 1.5)
                      :bottom (em 1.5)}
            :z-index 1}]


     ["nav li:not(.order-middle)" {:width (em 7)}]

     [:nav
      [:li
       [:a {:color 'white
            :text-decoration 'none
            :min-height (px 48)
           ; :font-weight 'bold
            :letter-spacing (em 0.05)

            :display 'flex
            :flex-direction 'column
            :justify-content 'center}]]]

     [:.home {:height (em 5)
              :position 'absolute
              :right 0
              :top "8%"}]

     [:nav
      [:li
       [:a {:will-change "font-size"
            :transition-property 'font-size
            :transition-duration "0.4s"
            :transition-timing-function "ease-out"}
        [:&:hover
         :&:focus

         [:span
          {:font-size (em 1.25)}]]]]]


     ;; Desktop styles
     (at-media {:min-width (px 667)}

               [:nav
                [:ul {:height "100%"
                      :justify-content 'space-around}]]
                                        ;:max-width (em 75)    ;; add a media query for this



               [:nav {:margin-top (em 3)
                      :height (em 4.5)}]

      [:nav
       [:ul {:flex-direction 'row
             :align-items 'center}]]

      [:nav
       [:ul
        ["a[href^=\"#\"]"
         [before {:border-bottom "solid rgba(255,255,255,0.6) 1px"
                  :position 'absolute
                  :content " \"\" "
                  :z-index "5"
                  :bottom "-40%"
                  :width "50%"
                  :left "25%"}]]]]

      [:nav
       [:ul
        [:a
         [:&:hover
          :&:focus   ;; THIS STYLE ISN'T BEING PICKED UP

          [before {:border-bottom "solid rgba(255,255,255,0.6) 1px"
                   :position 'absolute
                   :content " \"\" "
                   :z-index "5"
                   :bottom "-20%"
                   :width "50%"
                   :left "25%"}]]]]]
      [:nav
       [:a
        [
          :&:hover
          :&:focus
         [:svg
           {:transform "rotate(4deg) scale(1.1)"}]]]]

      [:nav
       [:li
        [:a {:color 'white
             :text-decoration 'none
             :text-align 'center
             :position 'relative
             :will-change "font-size"
             :transition-property 'font-size
             :transition-duration "0.2s"
             :transition-timing-function "cubic-bezier(0.175, 0.885, 0.32, 1.275)"}]]]



      [:nav
       [:li.order-middle
        [:span {:display 'none}]]]

      [:.home {:height 'auto
               :position 'relative}]

      [:.order-front {:order 1}]
      [:.order-middle {:order 2
                       :width (em 6)}]
      [:.order-end {:order 3}])


     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   GLOBAL STYLES   ;;;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



     [:h1 :h2 :h3 :h4 :p {:margin 0
                          :font-weight 'normal}]

     [:h1 :h2 :h3 :h4 {:font-family 'bitter}]
     [:p :span :ol :ul {:font-family "Source Sans Pro"}]


     [:.block-grey {:color 'white
                    :background-color brand-dark}
      [:p {:letter-spacing (em 0.01)}]]

     [:.block-blue {:color 'white
                    :background-color brand-blue}]

     [:.block-green {:background-color brand-color}]

     [:.inside-block
      {:width "100vw"
       :max-width (em 53)
       :align-self 'center
       :padding {:left (em 1.5)
                 :right (em 1.5)
                 :top (em 4.5)
                 :bottom (em 4.5)}}]
     #_[:.extra-padding
      {:padding-top (em 4.5)
       :padding-bottom (em 4.5)}]


   



     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   HOME PAGE SPECIFIC   ;;;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

     (defclass line-under)


     ;; Only solution available to set 100vw without being wrecked by scrollbar
     {:overflow-x 'hidden}

     [:.full-width {:max-width "100vw"
                    :left "50%"
                    :right "50%"
                    :margin-left "-50vw"
                    :margin-right "-50vw"}]


     [:#banner-image {:height (em 18.75)
                                        ;:max-height "100%"
                      :background-color 'grey
                      :background-image "url(\"/css/banner-image.jpg\") "
                      :background-size 'cover
                      :background-repeat 'no-repeat
                      :background-position "50% 40%"
                      :justify-content 'center
                      :align-items 'center
                      :padding (rem 1.5)}]
     [:#banner-image
      [:span {:font-size (em 2)
              :color 'white}]]

     [:.h1-home {:font-weight 'bold}]

     [:.h1-home
      :.h2-home {:align-self 'center
                 :position 'relative}]

     [:.home-component {:flex-direction 'row
                        :flex-wrap 'wrap
                        :justify-content 'center
                        :align-items 'center}
      [:p {:font-size (em 1.125)}]
     ;      :padding-left (rem 1.5)

      [:svg {:max-width (em 10)
             :margin (em 1.5)}]]
                                        ;:width "25%"
                                        ;:min-width (px 180)




     (at-media {:min-width (px 850)}
               [:.home-component
                [:svg {:margin-right (em 3)}]
                [:p {:width "100%"
                     :max-width (em 30)}]])



     (at-media {:max-width (px 600)}
               [:.rwd-break {:display 'none}])



     [(line-under s/before) {:border-bottom "solid 1px rgba(51,61,71, 0.2)"
                             :position 'absolute
                             :top "110%"
                             :right "10%"
                             :width "80%"
                             :content " \" \" "}]


     [:.at-our-core {:flex-direction 'row
                     :flex-wrap 'wrap
                     :align-items 'baseline
                     :justify-content 'space-around
                     :align-self 'center
                     :width "100%"}

            [:div {:padding {:left (rem 1.5)
                             :right (rem 1.5)}}]]



     [:.sign-up-box {:color 'white
                     :background-color brand-blue}]

     [:.blurb-image {:width "100%"
                     :height 'auto
                     :margin-top (rem 1.5)
                     :background-color 'grey
                     :border-radius (px 10)}]




     (at-media {:min-width (px 568)}

               [:.blurb {:flex-direction 'row}]

               [:.img-container {
                                   :margin-right "5%"
                                   :max-width "35%"}]

               [:.image-title-text-cta {:width "60%"}])





     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   VERTICAL RHYTHM   ;;;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


     ;; These need to be reviewed !!

     [:h1
      {:margin-top (em 1.5)}]

     ["main * + *" {:margin-top (rem 1.5)}]

     [:li :dt :dd :br :th :td {:margin 0}]

     ["* + h2" "* + h3" {:margin-top (em 1.5)}]

     ["main * + *:not(p)" {:margin-top "rem 0"}]
     [":main :empty" {:display 'none}]

     ["p + p" {:margin-top 0}]



     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   SHOW EXTERNAL LINK   ;;;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


     ;; [(attr-starts-with "http")(not (attr-matches "your domain"))::after
     ;;  :display 'inline-block
     ;;  :width (em 1)
     ;;  :height (em 1)
     ;;  :background-image "url('path/to/external-icon.svg')"]
     ;;  :background-repeat 'no-repeat
     ;;  :background-position 'center
     ;;  :background-size "75%"
     ;;
     ;;  ALTERNATIVE TEXT RULES
     ;;
     ;;  :content "(external link)"
     ;;  :overflow 'hidden
     ;;  :white-space 'no-wrap
     ;;  :text-indent (em 1)         ;the width of the icon
     ;;  ]



     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   PARAGRAPH STYLES   ;;;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


                                        ;OLD STYLES
                                        ;[:p {:font-size "1em"
                                        ;     :line-height "1.25em"
                                        ;     :max-width "28em"
                                        ;     }]



      ;; Paragraph styles

     [:p
      {:margin "1.5em 0"
       :font-size (em 1)
       :line-height 1.5
       :max-width (em 35)}


      ;; NEED TO REVIEW AS IT IS ONLY VISIBLE UP CLOSE
      ;; In addition when focused the text shadow is visible when adding a background
      [:a
       {:text-decoration 'none
        :text-shadow "0.05em 0 0 #fff, -0.05em 0 0 #fff, 0 0.05em 0 #fff, 0 -0.05em 0 #fff, 0.1em 0 0 #fff, -0.1em 0 0 #fff, 0 0.1em 0 #fff, 0 -0.1em 0 #fff"
        :background-image "linear-gradient(to right, currentColor 0%, currentColor 100%)"
        :background-repeat "repeat-x"
        :background-position "bottom 0.05em center"
        :background-size "100% 0.05em"}

       [:&:focus {:outline 'none
                  :background-color "#cef"}]]]


     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   CONSULTING PAGE   ;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

     [:.buffer-top {:margin-top (em 1.5)}]
     [:.buffer-top-large {:margin-top (em 3)}]

     [:.consulting-block {:margin-top (em -0.3)}]


     [:.green-text {:color "#6def14"
                    :letter-spacing (em 0.01)} ]

     [:.centre {:align-self 'center}]
     [:.services-align {:margin-left (em -0.2)}]

     [:ul.service-list {:margin-left (rem -0.3125)}]
     [:ul.service-list
      [:a {:text-decoration 'underline}
       [:&:hover
        :&:focus {:font-weight 'bold}]]
      [:li {:line-height (em 1.5)
            :min-height (px 48)}]]


     (at-media {:max-width (em 76.85)}
               [:.home-component
                [:h2 {:align-self 'center
                      :margin-left (em -1)}]]

               )

     (at-media {:min-width (em 80)}

               [:.consult-component
                [:svg {:margin-left 0
                       :height (em 15)
                       :max-width (em 13)
                       }]])

     (at-media {:max-width (em 60)}
               [:.center-to-60 {:align-self 'center}])

     (at-media {:min-width (rem 120)}
               [:.bump-width {:max-width (rem 110)}
                [:.inside-three {:font-size (rem 0.8)}] ])


     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   WEB & APP PAGE    ;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


     (at-media {:min-width (em 60)}
               [:.bump-left {:margin-right (em 1.5)}]
               [:.four-up {:flex-direction 'row
                           :flex-wrap 'wrap
                           :justify-content 'space-around
                           }])

     (at-media {:min-width (em 60)}
               [:.column-four {:flex-direction 'row
                               :flex-wrap 'wrap
                               :justify-content 'space-around
                               :width "100%"
                               }])

     (at-media {:min-width (rem 120)}
               [:.four-up {:flex-wrap 'no-wrap}]
               [:.column-four {:margin-top 0
                               :width "50%"}])

     [:.four-up [:svg {:align-self 'center}]]

     [:.three-up-six
      [:svg
       {:align-self 'center}]
      ]

     [:.buffer-left {:margin-left (em 1.5)}]

     (at-media {:max-width (em 60)}
               [:.three-up-six
                :.four-up
                [:h3 {:align-self 'center}]])

     (at-media {:min-width (em 60)}
               [:.three-up {:flex-direction 'row
                            :justify-content 'space-between}]
               [:.three-up-six {:flex-direction 'row
                                :flex-wrap 'wrap
                                :justify-content 'space-around}
                [:h3 {:font-size (em 1.4)}]]

               [:.buffer-left {:margin-left 0}]
               [:.inside-three {:padding (em 1)
                                :margin-top 0
                                :max-width (em 16)}])

     [:.drop-top-padding {:padding-top 0}]
     [:.make-top-margin {:margin-top (rem 1.5)}]


     [:.center {:align-self 'center}]
     [:.center-items {:align-items 'center}]


     (at-media {:min-width (em 75)}
               [:.principles {:flex-direction 'row
                              :justify-content 'space-between}]
               [:.principle {:width "45%"}])

     [:.pricing-options {
                         :border-style 'outset
                         :border-width (em 0.4)
                         :border-color brand-color
                         :border-radius (em 0.5)
                         :align-items 'center
                         :padding (em 1)
                         :background brand-dark
                         :color 'white
                         :width (em 19)}
      [:h3 {:margin-top 0}]
      ]
     [:.pricing-features [:li {:line-height (em 1.5)}]]

     [:.column-four
      [:.inside-three {:max-width (em 19)}]]

     [:.row {:flex-direction 'row}]
     [:.circle {:background brand-dark
                :border-radius "50%"
                :width (em 8)
                :height (em 8)
                :border-width (em 0.4)
                :border-style 'outset
                :border-color brand-color
                :color 'white
                :align-items 'center
                :justify-content 'center
                }
      [:h3 {:margin-top 0}]]

     [:.initial-consult {:margin-bottom (em 3)
                         :font-size (em 1.5)}]

     [:input
      :textarea {:margin-top (em 0.2)
                 :min-height (px 48)
                 :border-radius (em 0.2)
                 :border-style 'solid
                 :border-color brand-dark
                 :padding (em 0.3)
                 :font-size (rem 1)
                 :border-width (rem 0.05)
                 :max-width (rem 35)
                 :background-color "#ADFFD6"
                 }
      [:&:focus
       :&:hover {:background-color 'white
                 :border-width (em 0.1)}]]
     [:form
      [:p {:flex-direction 'column
           :display 'flex
           }]]

     [:.bump-area-height {:min-height (em 6)
                          :max-width "100%"}]





     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   ABOUT PAGE    ;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

     [:.about-strong {:margin-right (em 0.2)
                      :letter-spacing (em 0.05)}]

     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   COMMUNITY PAGE    ;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

     [:.community-box {:padding (em 2)
                       :border-style 'solid
                       :border-width (em 0.2)
                       :border-radius (em 0.5)
                       :border-color brand-dark}]




     )))





                                        ;(defstyles print
                                        ;  )
                                        ;“@media print {
                                        ;body > *:not(main) {
                                        ;                    display: none;
                                        ;                    }
                                        ;}”



