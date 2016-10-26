(ns vbn.styles
  (:refer-clojure :exclude [rem not])
  (:require [garden.def :refer [defrule defstyles]]
            [garden.stylesheet :refer [rule]]

            ;; My changes
            [garden.core :refer [css]]
            [garden.units :refer [rem
                                  em
                                  px]]
            [garden.selectors :refer [attr=
                                      attr-starts-with
                                      attr-matches
                                      focus
                                      ]]))


(defstyles screen
  (let [html (rule :html)
        body (rule :body)]

    ;; Might remove this
    (html [:font-size "calc(1em + 1vw)"])

    ;; Body css rules - ADDED BY ME
    (body

     ;; Remove this later
     {:font-family "Helvetica Neue"}



     ;;;;;;;;;  INCLUSIVITY  ;;;;;;;;;;


     ;; VISUALLY HIDDEN
     [:.visually-hidden {:position 'absolute
                         :width (px 1)
                         :height (px 1)
                         :overflow 'hidden
                         :clip "rect (1px, 1px, 1px, 1px)"}]

     ;;  SKIP TO MAIN
     [(attr= "href" "#main") {:position 'absolute
                              :top 0
                              :right "100%"}
      [:&:focus {:right 'auto}]]


     ;;;;;;;;;  LAYOUT DEFAULTS  ;;;;;;;;;;


     [* {:box-sizing 'border-box}]

     [:html
      :body
      :header
      :div
      :section
      :article
      {:position 'flex
       :flex-direction 'column
       :align-items 'stretch
       :flex-shrink 0
       :border "0 solid black"
       :margin 0
       :padding 0
       :max-width "100%"}
      ]




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
     )
    ))


                                        ;(defstyles print
                                        ;  )
                                        ;“@media print {
                                        ;body > *:not(main) {
                                        ;                    display: none;
                                        ;                    }
                                        ;}”




