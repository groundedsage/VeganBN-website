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

                                        ;(defselector a )

(def brand-color "#6DEF14")


(defstyles screen
  (let [html (rule :html)
        body (rule :body)]

    ;; Might remove this
    (html [:font-size "calc(1em + 1vw)"])

    (body

     ;; Remove this later
     {:font-family "Helvetica Neue"}
     {:margin 0}

     (at-media {:min-width (px 1500)}
     {:background 'blue})

     ;;;;;;;;;  LAYOUT DEFAULTS  ;;;;;;;;;;


     [:* {:box-sizing 'border-box}
      [:&:focus {:outline 'none}]]

     ;; Normally have html and body in here also
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
       :max-width "100%"}
      ]


     ;;;;;;;;;  INCLUSIVITY  ;;;;;;;;;;


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
                                        ; :right "100%"

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


     ;;;;;;;;;  NAVIGATION BAR  ;;;;;;;;;;

     (defselector li)
     (defpseudoclass before)
     (defpseudoclass not)
     (defclass home)


     [:nav {:height (px 100)
            :background-color "#0D8EC1"
            :margin-top (em 3)}]

     [:nav
      [:ul {:flex-direction 'row
            :justify-content 'space-around
            :height "100%"
            :align-items 'center
            }]]

     [:nav
      [:ul
       [:li ;; Need to ensure it takes focus off home link as it is an image
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
            :min-width (rem 8.125)
            :position 'relative
            :will-change "font-size, opacity"
            :transition-property 'font-size
            :transition-duration "0.2s"
            :transition-timing-function "cubic-bezier(0.175, 0.885, 0.32, 1.275)"}

        [:&:focus
         :&:hover {:font-size (em 1.5)}]]]]

     [:.order-1 {:order 1}]
     [:.order-2 {:order 2}]
     [:.order-3 {:order 3}]
     [:.order-4 {:order 4}]
     [:.order-5 {:order 5}]




     ;; VERTICAL RHYTHM

     ;; [:main * + * {:margin-top (rem 1.5)}]

     [:li :dt :dd :br :th :td {:margin 0}]

     ;; [:* + h2
     ;;  :* + h3 {:margin-top (em 1.5)}]

     ;; [:main * + *:not(p) {:margin-top "3rem 0}]
     ;; [:main ":empty" {:display 'none}]





     ;; SHOW LINK IS EXTERNAL

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





     ;; Paragraph styles

     [:p
      {:margin "1.5em 0"
       :font-size (rem 1)
       :line-height 1.5}


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




