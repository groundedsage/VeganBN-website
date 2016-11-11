(ns vbn.app
  (:require [rum.core :as rum]
            [devcards.core :as dc]
            [bidi.bidi :as b :refer [match-route]]

            [vbn.index :as i])
  (:require-macros [devcards.core :refer [defcard]]))

(enable-console-print!)


;; HELPER COMPONENTS


(rum/defc svg-icon [meta]
  (let [{:keys [alt viewbox href]} meta]
    [:svg {:alt alt
           :viewbox viewbox}
     [:use {:xlink-href href}]]))




;; Accessibility defaults
(rum/defc skip-to-main []
  [:a.skip-to-main {:href "#main"}
   [:span "Skip to main content"]
   ])

(rum/defc main [content]
  "Enters content into main container with id=\"main\" "
  [:main#main content])

(rum/defc inline-link [text link]
  [:a {:href link} text])

;; Site specific


(rum/defc hidden []
  [:span  {:hidden true} "This is hidden text"])


;; NAVIGATION

(def nav-links {:index "index.html"
                :about-us "about-us.html"
                :veganism "veganism.html"
                :consulting "consulting.html"
                :community "community.html"})

(def my-routes ["/" {"" :index
                     "index.html" :index
                     "veganism.html" :veganism
                     "about-us.html" :about-us
                     "consulting.html" :consulting
                     "community.html" :community}])

(defn get-route []
  (:handler (match-route my-routes
                         (.-pathname
                          (.-location js/window)) )))



(rum/defc navigation  [current-route]
  ;(let [links (update nav-links current-route "#main")]
  (let [links (assoc nav-links current-route "#main")]
  [:nav
   (js/console.log  (str
                     (current-route nav-links)
                    ; (update p :age "#main")
                     links
                     ))
   [:ul
    [:li.order-middle [:a {:href (:index links)}
                       [:span {:aria-hidden true} "Home"]
                       [:svg.home {:alt "VBN Logo Home"
                                   :viewBox "0 0 158 172"}
                        [:use {:xlink-href "logo.svg#logo"}]]]]
    [:li.order-front [:a {:href (:about-us links)} [:span "About Us"]]]
    [:li.order-front [:a {:href (:veganism links)} [:span "Veganism"]]]
    [:li.order-end [:a {:href (:consulting links)} [:span "Consulting"]]]
    [:li.order-end [:a {:href (:community links)} [:span "Community"]]]

    ]]))


(rum/defc home-page [content]
  [:div
   (skip-to-main)
   (navigation )
   [:main#main
    (i/banner-image)
    (i/h1-home "Vegan Business Network")
    (i/bigger-than-business)
    (i/movement)

    (i/h2-home "At Our Core")
[:div.at-our-core
    (i/community)
    (i/education)]
    (i/sign-up)

    (i/h2-home "What We Do")
    (i/blurbs-title-second)



    ]
                                        ;(intro)

   (hidden)
   ])

(rum/defc veganism []
  [:div
   (skip-to-main)
   (navigation)
   [:main#main
    [:h1 "This is the veganism page"]]])


(def pages {:index (home-page)
            :veganism (veganism)})

(defn init []
    (rum/mount
    ;((:handler (match-route my-routes current-route)) pages)
(navigation (get-route))
   (. js/document (getElementById "container"))))


(comment

  (defcard
  "Heading 1"
  (h1-home "Heading 1 - Home Page"))

(defcard
  "Heading 2"
  (h2-home "Heading 2 - Home Page"))

(defcard
  "Heading 3"
  (h3 "Heading 3 - Home Page"))

(defcard
  "Bigger Than Business Symbol"
(svg-icon {:alt "Bigger Than Business"
           :viewbox "0 0 255 175"
           :href "bigger-than-business.svg#bigger-than-business"}))

(defcard
  "Bigger than business "
  (bigger-than-business) )


(defcard
  "Movement Symbol"
  (movement) )

(defcard
  "Community"
  (community))

(defcard
  "Education"
  (education))

(defcard
  "Blurbs"
  (blurbs-title-second))



(defn init []
  (devcards.core/start-devcard-ui!)
  )
)
