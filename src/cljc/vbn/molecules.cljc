(ns vbn.molecules
  (:require [rum.core :as rum]
            [vbn.atoms :as atom] ))


(rum/defc our-definition [content]
  [:div content])

(rum/defc three-dimensions [content]
  [:div content])


(rum/defc blurb-title-second [content]
  (let [{:keys [image alt-text title text cta]} content]
    [:div.blurb
     [:.img-container (atom/blurb-image image alt-text)]
     [:div.image-title-text-cta
      [:h3 title]
      ;(reduce conj [:section])
      text
      [:button [:span cta]]]]))

(rum/defc blurb-title-second-veganism [content]
  (let [{:keys [image alt-text title text cta]} content
        text (conj [:div] text)]
    [:div.blurb
     [:.img-container (atom/blurb-image image alt-text)]
     [:div.image-title-text-cta
      [:h3 title]
      text
      [:button [:span cta]]]]))



(rum/defc page-intro [& content]
  (conj [:div {:style {:align-items 'center}}]
        content))

(rum/defc icon-with-text [icon-name text]
  [:div {:style {:width "100%"
                :max-width "13em"
                :align-items "center"}}
   (atom/circle-icon icon-name)
   [:span {:style {:white-space "nowrap"}} text]])

(rum/defc bigger-than-business [text]
  [:div.home-component
   (atom/bigger-than-business)
   (atom/p text)])

(rum/defc movement [text]
  [:div.home-component
   (atom/movement)
   (atom/p text)])
