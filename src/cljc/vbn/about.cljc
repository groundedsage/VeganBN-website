(ns vbn.about
  (:require [rum.core :as rum]
            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]))

(rum/defc content []
  [:main#main
   (molecule/page-intro
    [:h1 "About"]
    [:p "VBN is Australia’s first and only Peak Body for vegan businesses."])

   [:div.full-width.block-green
    [:div.inside-block
     (atom/h2-home "At VBN we work to:")
     [:div.center
      [:p [:strong.about-strong "GROW"] "  vegan and vegan friendly businesses with world class services."]
      [:p [:strong.about-strong "EDUCATE"] "   the public, businesses and government on veganism."]
      [:p [:strong.about-strong "REPRESENT"] "  the Vegan Business community in political and legal campaigns. "]]]]

   (atom/h2-home "Vision and Values")
   [:div.vision-section
    [:h3 "Vision"] [:.vision [:span.vision-title "A Vegan World"]
                    [:span.simple-vision "Pretty simple really"]]]

   [:div.values-section
    [:h3 "Values"]
    [:ol.values
     [:li [:span "Empathy"] [:p "Express empathy and kindness to all living things."]]
     [:li [:span "Be Inclusive"] [:p "Everyone has their story, their journey, a right to be hear and a right to the truth."]]
     [:li [:span "Positivity"] [:p "Positivity creates possibilities"]]
     [:li [:span "Kaizen"] [:p "Solve small problems and improve continuously.
A big problem is nothing other than a combination of small problems"]]
     [:li [:span "Quality"] [:p "Quality is always better than quantity"]]
     [:li [:span "Transparency"] [:p "Be open about process and clear in communitcation"]]

     [:li [:span "Collaboration"] [:p "Unite efforts because teh whole is always greater than the sum of it's parts."]]

     [:li [:span "On the shelf or off the shelf"] [:p "WILL BE CHANGING THIS ONE"]]

     [:li [:span "Impact"] [:p "Always consider both the local and global impact"]]
     [:li [:span "Inspire"] [:p "Be inspired and inspire others in evry way imaginable"]]]]])
