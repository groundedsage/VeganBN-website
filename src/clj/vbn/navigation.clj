

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

     ["nav li:not(.order-middle)" {:width (em 7)}]

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
          {:font-size (em 1.25)}]]]]]


     ;; Desktop styles
     (at-media {:min-width (px 667) }

               [:nav {:margin-top (em 3)
                      :height (em 4.5)}]

     [:nav
      [:ul {:flex-direction 'row
            :align-items 'center
            }]]
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
                  :bottom "-40%"
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
            :width 'auto
            :min-width (em 6)
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


