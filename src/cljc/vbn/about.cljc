(ns vbn.about
  #?(:cljs (:require-macros  [vbn.styler :refer [css at-media]]))
  (:require [rum.core :as rum]
            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]

            #?(:clj [vbn.styler :refer [css at-media get-css-str]])))

(def about-strong [(css {:margin-right "0.2em"
                         :letter-spacing "0.05em"})])

(rum/defc content []
  [:main#main.footer-buffer
   (molecule/page-intro
    [:h1 "About"]
    [:p "VBN is Australia’s first and only Peak Body for vegan businesses."])

   [:div.full-width.block-green
    [:div.inside-block
     (atom/h2-home "At VeganBN we work to:")
     [:div.center
      [:p [:strong {:class about-strong} "GROW"] "  vegan and vegan friendly businesses with world class services."]
      [:p [:strong {:class about-strong} "EDUCATE"] "   the public, businesses and government on veganism."]
      [:p [:strong {:class about-strong} "REPRESENT"] "  the Vegan Business community in political and legal campaigns. "]]]]

   (atom/h2-home "Vision and Values")
   [:div.vision-section
    [:h3 "Vision"] [:.vision [:span
                              {:class [(css {:font-weight "bold"
                                             ;;NEED TO FIXBELOW
                                             :font-size "1.125em"
                                             :line-height "1.5em"})]}
                              ;.vision-title
                              "A Vegan World"]
                    [:span
                      {:class [(css {:margin-top "0.5em"
                                           :margin-bottom "0.5em"})]}
                     ;.simple-vision 
                     "Pretty simple really"]]]

   [:div
    {:class [(css {:display "flex"
                   :flex-direction "column"
                   :align-items "baseline"
                   :padding-left "1.5em"
                   :padding-right "1.5em"})
             (at-media {:min-width "30rem"} {:flex-direction "row"
                                             :padding-left 0
                                             :padding-right 0})]}
    [:h3
      {:class [(css {:margin-right "3em"})]}
      "Values"]
    [:ol.values
     [:li [:span "Mindfulness"] [:p "Consideration of actions and the impact is has. Consideration of the journey other people are on and where they may have come from."]]
     [:li [:span "Inclusivity"] [:p "Always include others who wish to be included and be inviting. Always collaborate when an opportunity presents itself. Unite efforts because the whole is always greater than the sum of it’s parts."]]
     [:li [:span "Positivity"] [:p "Always strive for a more positive perspective on all situations. Positivity creates possibilities beyond our wildest imaginations. Avoid the use of negative words and phrases."]]
     [:li [:span "Kaizen"] [:p "Solve small problems and improve continuously.
A big problem is nothing other than a combination of small problems"]]
     [:li [:span "Now"] [:p "The best time to do anything is now. Think deeply act swiftly. Trust intuition. Draw on past experiences and be thoughtful while acting in the now. The past and failures is for learning. The future is the next now."]]
     [:li [:span "Transparency"] [:p "Be open about process and clear in communitcation"]]

     [:li [:span "Collaboration"] [:p "Unite efforts because teh whole is always greater than the sum of it's parts."]]

     [:li [:span "Passion"] [:p "Be driven by a higher purpose and believe everything you do has a postivite impact on the world."]]

     [:li [:span "Impact"] [:p "Always consider both the local and global impact"]]
     [:li [:span "Truth"] [:p "Be transparent and open in communication. For clarity communicate with simple words when possible and explain things as simply as possible. Always be happy to repeat what has been spoken. Be honest."]]]]])

;#?(:clj (println "This is how it looks inside about.clj "(get-css-str false)))
