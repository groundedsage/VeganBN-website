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
                                            defclass
                                            ]]))


(def brand-color "#6DEF14")
(def brand-blue "#0d8ec1")


(defstyles screen
  (let [html (rule :html)
        body (rule :body)]

    ;; Might remove this - works but will need to consider
   ; (html [:font-size "calc(1em + 1vw)"})


    (body
     ;; Remove this later
     {:font-family "Helvetica Neue"}
     {:margin 0}




     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   LAYOUT DEFAULTS   ;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

     [:* {:box-sizing 'border-box}
      [:&:focus {:outline 'none}]]

     ;; Normally have html and body in here also however they have been moved to the top
     [:header
      :div
      :section
      :article

      ;; POSSIBLY REMOVE THESE AS DEFAULTS
      :a
      :span
      :main
      :nav
      :ul
      :li
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

     [:.page-padding {:margin {:left (rem 1.5)
                               :right (rem 1.5) }}]




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
       :z-index 1
       :will-change 'height
       :transition-property 'height
       :transition-duration "0.2s"
       :transition-timing-function "cubic-bezier(0.175, 0.885, 0.32, 1.275)" }

      [:span {:display 'none}]


      [:&:focus {;:right 'auto
                 :height (px 150)
                 :outline 'none}
       [:span {:display 'flex}]]]



     [:main {:width "100%"
             :max-width (rem 60)
             :align-self 'center
             :padding {:left (rem 1.5)
                       :right (rem 1.5)}
             }]



     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   NAVIGATION BAR   ;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


     (defselector li)
     (defselector nav)
     (defpseudoclass before)
     (defpseudoclass not)
     (defclass order-middle)

     [:nav {:background-color "#0D8EC1"}]

     ;; Mobile Styles

     [:nav {:padding {:top (em 1.5)
                      :left (em 1.5)
                      :right (em 1.5)
                      :bottom (em 1.5)}
            :z-index 1}]

     [:nav
      [:ul {:height "100%"
            :justify-content 'space-around
                                        ;:max-width (em 75)    ;; add a media query for this
            }]]

     ["nav li:not(.order-middle)" {:width (em 8.125)}]

     [:nav
      [:li
       [:a {:color 'white
            :text-decoration 'none
            :min-height (px 48
                         )
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
          {:font-size (em 1.5)}]]]]]


     ;; Desktop styles
     (at-media {:min-width (px 667) }

               [:nav {:margin-top (em 3)
                      :height (rem 6.25)}]

     [:nav
      [:ul {:flex-direction 'row
            :align-items 'center
            }]]

     [:nav
      [:ul
       [:a ;; Need to ensure it takes focus off home link as it is an image
        [:&:hover
         :&:focus   ;; THIS STYLE ISN'T BEING PICKED UP
         [before {:border-bottom "solid rgba(255,255,255,0.6) 1px"
                  :position 'absolute
                  :content " \"\" "
                  :z-index "5"
                  :bottom "-40%"
                  :width "50%"
                  :left "25%"}]]]]]

     [:nav
      [:li
       [:a {:color 'white
            :text-decoration 'none
            :text-align 'center
            :width 'auto
            :min-width (rem 8.125)
            :position 'relative
            :will-change "font-size"
            :transition-property 'font-size
            :transition-duration "0.2s"
            :transition-timing-function "cubic-bezier(0.175, 0.885, 0.32, 1.275)"}

        ]]]

     [:nav
      [:li.order-middle
       [:span {:display 'none}]]]

     [:.home {:height 'auto
              :position 'relative}]

     [:.order-front {:order 1}]
     [:.order-middle {:order 2}]
     [:.order-end {:order 3}])


     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   GLOBAL STYLES   ;;;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



     [:h1 :h2 :h3 :h4 :p {:margin 0
                          :font-weight 'normal}]

     [:h1 :h2 :h3 :h4 {:font-family 'bitter}]
     [:p :span :ol {:font-family "Source Sans Pro"
                    }]



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
      [:span {:font-size (em 2)}]]

     [:.h1-home {:font-weight 'bold}]

     [:.h1-home
      :.h2-home {:align-self 'center
                 :position 'relative}]

     [:.home-component {:flex-direction 'row
                        :flex-wrap 'wrap
                        :justify-content 'center
                        :align-items 'center}
      [:p {:font-size (rem 1.125)
     ;      :padding-left (rem 1.5)
           }]
      [:svg {:max-width (rem 14.375)
             :margin (rem 1.5)
                                        ;:width "25%"
                                        ;:min-width (px 180)
             }
       ]
      ]

     (at-media {:min-width (px 850)}
               [:.home-component
                [:p {:width "100%"
                     :padding-left (rem 1.5)}]])


     (at-media {:max-width (px 600) }
               [:.rwd-break {:display 'none}]
               )


     [(line-under s/before) {:border-bottom "solid 1px rgba(51,61,71, 0.2)"
                             :position 'absolute
                             :top "110%"
                             :right "10%" 
                             :width "80%"
                             :content " \" \" "
                             }]

     [:.at-our-core {:flex-direction 'row
                     :flex-wrap 'wrap
                     :align-items 'baseline
                     :justify-content 'center
                     }
            [:div {:padding {:left (rem 1.5)
                      :right (rem 1.5)}}]
      ]

     (at-media {:min-width (px 667)}
               [:.at-our-core
                {:width "100%"
                 :max-width (rem 40)
                 :justify-content 'space-between}])

     [:.sign-up-box {:background-color brand-blue}]

     [:.blurb-image {:width "100%"
                     :height 'auto
                     :margin-top (rem 1.5)
                     :background-color 'grey
                     :border-radius "10%"}]




     (at-media {:min-width (px 568) }

               [:.blurb {:flex-direction 'row}]

               [:.blurb-image {
                :margin-right (rem 1.5)
                :max-width "30%"
                :height "100%"}
                ]

               [:.image-title-text-cta {:width "calc(70% - 1.5rem)"}]
               )




     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   VERTICAL RHYTHM   ;;;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


     ;; These need to be reviewed !!


     ["main * + *" {:margin-top (rem 1.5)}]

     [:li :dt :dd :br :th :td {:margin 0}]

      ["* + h2" "* + h3" {:margin-top (em 1.5)}]

      ["main * + *:not(p)" {:margin-top "3rem 0"}]
      [":main :empty" {:display 'none}]



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

      [:h1 {:font-size "2em"
            :line-height "1.25em"}]
      [:h2 {:font-size "1.625em"
            :line-height "1.15284615em"}]
      [:h3 {:font-size "1.375em"
            :line-height "1.13636364em"}]
      [:h4 {:font-size "1.125em"
            :line-height "1.11111111em"}]

                                        ;OLD STYLES
                                        ;[:p {:font-size "1em"
                                        ;     :line-height "1.25em"
                                        ;     :max-width "28em"
                                        ;     }]



      ;; Paragraph styles

      [:p
       {:margin "1.5em 0"
        :font-size (rem 1)
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
         :background-size "100% 0.05em"
         }
        [:&:focus {:outline 'none
                   :background-color "#cef"}]]

      ]
     ))
    )


                                        ;(defstyles print
                                        ;  )
                                        ;“@media print {
                                        ;body > *:not(main) {
                                        ;                    display: none;
                                        ;                    }
                                        ;}”




