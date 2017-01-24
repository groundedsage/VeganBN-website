(ns vbn.about
  #?(:cljs (:require-macros  [vbn.styler :refer [css at-media]]))
  (:require [rum.core :as rum]
            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]

            #?(:clj [vbn.styler :refer [css at-media get-css-str]])))









;;;;;;;;;
;;;;;;;;;   VBN WORKS TO
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def about-strong [(css {:margin-right "0.2em"
                         :letter-spacing "0.05em"})])

(rum/defc vbn-works-to []
  [:div.full-width.block-green
   [:div {:class [(css {:width "100vw"
                        :max-width "53em"
                        :align-self "center"
                        :padding-left "1.5em"
                        :padding-right "1.5em"
                        :padding-top "4.5em"
                        :padding-bottom "4.5"})]}
    (atom/h2-home "At VeganBN we work to:")
    [:div.center
     [:p [:strong {:class about-strong} "GROW"] "  vegan and vegan friendly businesses with world class services."]
     [:p [:strong {:class about-strong} "EDUCATE"] "   the public, businesses and government on veganism."]
     [:p [:strong {:class about-strong} "REPRESENT"] "  the Vegan Business community in political and legal campaigns. "]]]])


;;;;;;;;;
;;;;;;;;;   VISION & VALUES
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def value-items [(css {:margin-top "0.5em"
                        :margin-bottom "2.5em"})])

(rum/defc value [item desc]
  [:li
   [:span {:class [(css {:font-weight "bold"})]} item]
   [:p {:class value-items} desc]])



(rum/defc vision-values []
  [:div
    (atom/h2-home "Vision and Values")
    [:div {:class [(css {:padding-left "1.5em"
                         :padding-right "1.5em"})
                   (at-media {:min-width "30rem"} {:flex-direction "row"
                                                   :align-items "baseline"
                                                   :padding-left 0
                                                   :padding-right 0})]}
     [:h3 {:class [(css {:margin-right "3em"})]} "Vision"]
     [:div {:class [(at-media {:min-width "30rem"} {:width "80%"})]}
           [:span {:class [(css {:font-weight "bold"
                                     ;;NEED TO FIXBELOW
                                 :font-size "1.125em"
                                 :line-height "1.5em"})]}
                               ;.vision-title
            "A Vegan World"]
           [:span {:class [(css {:margin-top "0.5em"
                                 :margin-bottom "0.5em"})]}
                      ;.simple-vision
             "Pretty simple really"]]]
   [:div
    {:class [(css {:display "flex"
                   :align-items "baseline"
                   :padding-left "1.5em"
                   :padding-right "1.5em"})
             (at-media {:min-width "30rem"} {:flex-direction "row"
                                             :padding-left 0
                                             :padding-right 0})]}
    [:h3 {:class [(css {:margin-right "3em"})]}
      "Values"]
    [:ol {:class [(css {:margin-left "-0.3125rem"})
                  (at-media {:min-width "30rem"} {:width "80%"})]}

     (value "Mindfulness" "Consideration of actions and the impact is has. Consideration of the journey other people are on and where they may have come from.")
     (value "Inclusivity" "Always include others who wish to be included and be inviting. Always collaborate when an opportunity presents itself. Unite efforts because the whole is always greater than the sum of it’s parts.")
     (value "Positivity" "Always strive for a more positive perspective on all situations. Positivity creates possibilities beyond our wildest imaginations. Avoid the use of negative words and phrases.")
     (value "Kaizen" "Solve small problems and improve continuously. A big problem is nothing other than a combination of small problems")
     (value "Now" "The best time to do anything is now. Think deeply act swiftly. Trust intuition. Draw on past experiences and be thoughtful while acting in the now. The past and failures is for learning. The future is the next now.")
     (value "Transparency" "Be open about process and clear in communitcation")

     (value "Collaboration" "Unite efforts because teh whole is always greater than the sum of it's parts.")

     (value "Passion" "Be driven by a higher purpose and believe everything you do has a postivite impact on the world.")

     (value "Impact" "Always consider both the local and global impact")
     (value "Truth" "Be transparent and open in communication. For clarity communicate with simple words when possible and explain things as simply as possible. Always be happy to repeat what has been spoken. Be honest.")]]])






;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
 ;;;;;;;;;   FULL PAGE CONTENT   ;;;;;;;;;;
 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(rum/defc content []
  [:main#main.footer-buffer
   (molecule/page-intro
    [:h1 "About"]
    [:p "VBN is Australia’s first and only Peak Body for vegan businesses."])
   (vbn-works-to)
   (vision-values)])
