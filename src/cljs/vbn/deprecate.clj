(ns vbn.app
  (:require [rum.core :as rum]
            [devcards.core :as dc]
            [bidi.bidi :as b :refer [match-route]]

            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]

            [vbn.about :as about]
            [vbn.veganism :as veganism]
            [vbn.community :as community]
            [vbn.consulting :as consulting]

            [vbn.index :as i])
  (:require-macros [devcards.core :refer [defcard
                                          defcard-doc
                                          mkdn-pprint-source]]
                   [cljs.core.match :refer [match]]
                   ))

(enable-console-print!)


;; HELPER COMPONENTS
(comment

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
                     "community.html" :community
                     "devcards.html" :devcards}])

(defn get-route []
  (:handler (match-route my-routes
                         (.-pathname
                          (.-location js/window)) )))



(rum/defc navigation  [current-route]
  (let [links (assoc nav-links current-route "#main")]
  [:nav
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


(rum/defc home [content]
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


    (hidden)

    ])









(defcard
  "#Atoms
   Below is a list of all the atoms used throughout the website")




(defcard Heading-1
  (atom/h1 "Heading 1")
  ;(i/h1-home "Heading 1 - Home Page")


  )
;(defcard-doc "Source" (mkdn-pprint-source i/h1-home))


(defcard Heading-2
  (atom/h2 "Heading 2")
  ;(i/h2-home "Heading 2 - Home Page")
  )

(defcard Heading-3
  (atom/h3 "Heading 3")
  )

(defcard Heading-4
  (atom/h4 "Heading 4"))

(defcard Paragraph-Text
  (atom/p "Here is what the paragraph text looks like."))

(defcard Button
  (atom/button "This is a button"))

(defcard Blurb-image
  (atom/blurb-image "photos/meetup.jpg" "meetup"))

(defcard Circle-icon-Community
  (atom/circle-icon "community"))

(defcard Circle-icon-Education
  (atom/circle-icon "education"))


(defcard Bigger-than-business
  (atom/bigger-than-business))

(defcard Movement
  (atom/movement))

(defcard Intro-text
  (atom/intro [:span "This is intro text"]))

(defcard
  "#Molecules")


(rum/defc blurbs-title-second [content]
    (molecule/blurb-title-second {:image "photos/nature.jpg"
                         :alt-text "Waterfall in rainforest"
                         :title "Sustainability"
                         :text "We encourage people from all walks of life to come to our meetups. If you are interested in veganism, entrepreneurship, or you are just a business owner with a little curiosity about what a vegan is then this event is the one for you"
                         :cta "Cowspiracy.org"}))

(defcard Blurb
  (molecule/blurb-title-second {:image "photos/nature.jpg"
                       :alt-text "Waterfall in rainforest"
                       :title "Sustainability"
                       :text "We encourage people from all walks of life to come to our meetups. If you are interested in veganism, entrepreneurship, or you are just a business owner with a little curiosity about what a vegan is then this event is the one for you"
                       :cta "Cowspiracy.org"})
  )


(rum/defc title-top []
  [:div
   (atom/blurb-image "photos/nature.jpg")
   (atom/h3 "Sustainability")
   (atom/p "We all know we need to do something more. Otherwise each successive generation is going to have less animal diversity, plant diversity, resources and beauty in the world to enjoy. If current trends continue we could wipe out ourselves taking a large chunk of the planet with us. If you are someone who showers less, drives as little as possible and recycles religuously. Or just someone looking to do a little more for the planet and your fellow human beings. We highly suggest watching Cowspiracy. They also have an amazing infographic on their website.")
   (atom/button "Cowspiracy.org")])







(rum/defc page [content]
  [:div
   (skip-to-main)
   (navigation (get-route))
   (case (get-route)
     :index (home)
     :about-us (about/content)
     :veganism (veganism/content)
     :community (community/content)
     :consulting (consulting/content))

   ;; add core.match here for each page CORE.MATCH won't work with more than 1 match option
   ])




(defn init [] (rum/mount
               (if (= :devcards (get-route))
                 (devcards.core/start-devcard-ui!)
                 (page))
                (. js/document (getElementById "container"))))

)
