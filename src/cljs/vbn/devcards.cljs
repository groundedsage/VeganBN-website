(ns vbn.devcards
  (:require [devcards.core :as dc]
            [vbn.index :as i]
            [vbn.components :as comp]

            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]



            )
  (:require-macros [devcards.core :refer [defcard]]))



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









(defn init []
  (devcards.core/start-devcard-ui!)
  )
