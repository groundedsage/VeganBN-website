(ns vbn.molecules
  (:require [rum.core :as rum]
            [vbn.atoms :as atom] ))

(rum/defc page-intro [content]
  [:div content])

(rum/defc our-definition [content]
  [:div content])

(rum/defc three-dimensions [content]
  [:div content])


(rum/defc blurb-title-second [content]
  (let [{:keys [image alt-text title text cta]} content]
    [:div.blurb
     (atom/blurb-image image alt-text)
     [:div.image-title-text-cta
      [:h3 title]
      text
      [:button cta]]]))


