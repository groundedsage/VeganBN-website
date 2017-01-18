(ns vbn.molecules
  #?(:cljs (:require-macros  [vbn.styler :refer [css at-media]]))
  (:require [rum.core :as rum]
            [vbn.atoms :as atom]
            [vbn.components :refer [my-routes]]

            [bidi.bidi :as b :refer [match-route path-for]]
            #?(:clj [vbn.styler :refer [css at-media get-css-str]])
            #?(:cljs [vbn.navigation :refer [link current-token]])))



;; define clojure version of link
#?(:clj (rum/defc link [link & content]
          [:a {:href link} content]))




(rum/defc our-definition [content]
  [:div content])

(rum/defc three-dimensions [content]
  [:div content])


(rum/defc blurb-title-second [content]
  (let [{:keys [image alt-text title text cta cta-key]} content]
    [:div
      {:class [(css {::margin-top "2rem"})
               (at-media {:min-width "35rem"} {:flex-direction "row"})]}
     [:div
      {:class [(at-media {:min-width "35rem"} {:margin-right "5%"
                                               :max-width "35%"})]}
      (atom/blurb-image image alt-text)]
     [:div
       {:class [(at-media {:min-width "35rem"} {:width "60%"})]}
      [:h3 title]
                                        ;(reduce conj [:section])
      text
      (cond
        (string? cta-key) [:a.button-link {:href cta-key} [:button
                                                           [:span cta]]]
        :else (link (path-for my-routes cta-key)
                    [:button [:span cta]]))

      #_[:button [:span cta]]]]))

(rum/defc blurb-title-second-veganism [content]
  (let [{:keys [image alt-text title text cta cta-key]} content
        text (conj [:div] text)]
    [:div]
    {:class [(css {::margin-top "2rem"})
             (at-media {:min-width "35rem"} {:flex-direction "row"})]
     [:div
       {:class [(at-media {:min-width "35rem"} {:margin-right "5%"
                                                :max-width "35%"})]}
      (atom/blurb-image image alt-text)]
     [:div
      {:class [(at-media {:min-width "35rem"} {:width "60%"})]}
      [:h3 title]
      text
      #_(if (= :cta-key :sign-up)
         [:button {:href "#sign-up"}
          [:span cta]]
         (link (path-for my-routes cta-key)
               [:button [:span cta]]))]}))

#_(rum/defc navigation []
   [:nav
    [:ul
     [:li.order-middle (link (path-for my-routes :index)
                             [:span {:aria-hidden true} "Home"]
                             [:svg.home {:alt "VBN Logo Home"
                                         :viewBox "0 0 160 150"}
                              [:use {:xlink-href "/logo.svg#logo-2"}]])]
     [:li.order-front (link (path-for my-routes :veganism) [:span "Veganism"])]
     [:li.order-front   (link (path-for my-routes :consulting) [:span "Consulting"])]
     [:li.order-end (link (path-for my-routes :community) [:span "Community"])]
     [:li.order-end (link (path-for my-routes :about-us) [:span "About Us"])]]])




(rum/defc page-intro [& content]
  (conj [:div
         {:class [(css {:align-items "center"})]}]
    ;{:style {:align-items 'center}
        content))

(rum/defc icon-with-text [icon-name text]
  [:div
   {:class [(css {:width "100%"
                  :max-width "13em"
                  :align-items "center"
                  :padding-left "1.5rem"
                  :padding-right "1.5rem"})]}

   (atom/circle-icon icon-name)
   [:span
    {:class [(css {:white-space "nowrap"})]}
    ;{:style {:white-space "nowrap"}}
    text]])

(rum/defc bigger-than-business [text]
  [:div.home-component
   (atom/bigger-than-business)
   (atom/p text)])

(rum/defc movement [text]
  [:div.home-component
   (atom/movement)
   (atom/p text)])
