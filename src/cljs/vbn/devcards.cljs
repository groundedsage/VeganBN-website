(ns vbn.devcards
  (:require [devcards.core :as dc]
            [vbn.index :as i]
            [vbn.components :as comp]
            [rum.core :as rum]

            [vbn.atoms :as atom]
            [vbn.molecules :as molecule])




  (:require-macros [devcards.core :refer [defcard defcard-doc mkdn-pprint-source   ]]))



(defcard
  "#Atoms
   Below is a list of all the atoms used throughout the website")

(defcard Skip-to-main
  "Allows people who are unable to use a mouse and rely on Tab'ing the interface to Skip over the navigation upon entering a new page"
  (comp/skip-to-main))



(defcard Heading-1
  (atom/h1 "Heading 1"))
  ;(i/h1-home "Heading 1 - Home Page")


(defcard-doc
  "This is the source code for h1"
  (mkdn-pprint-source atom/h1) )



(defcard Heading-2
  (atom/h2 "Heading 2"))
  ;(i/h2-home "Heading 2 - Home Page")


(defcard Heading-3
  (atom/h3 "Heading 3"))


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
  [:main#main
   (molecule/blurb-title-second {:image "photos/nature.jpg"
                                 :alt-text "Waterfall in rainforest"
                                 :title "Sustainability"
                                 :text "We encourage people from all walks of life to come to our meetups. If you are interested in veganism, entrepreneurship, or you are just a business owner with a little curiosity about what a vegan is then this event is the one for you"
                                 :cta "Cowspiracy.org"})])

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







(defn init []
  (devcards.core/start-devcard-ui!))
