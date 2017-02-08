(ns library.atoms

  (:require [devcards.core :as dc]
            [vbn.index :as i]
            [vbn.components :as comp]
            [rum.core :as rum]

            [vbn.atoms :as atom]
            [vbn.molecules :as molecule])




  (:require-macros [devcards.core :refer [defcard defcard-doc mkdn-pprint-source]]))



(defcard
  "#Atoms
   Below is a list of all the atoms used throughout the website")

(defcard Skip-to-main
  "Allows people who are unable to use a mouse and rely on Tab'ing the interface to Skip over the navigation upon entering a new page"
  (comp/skip-to-main))



(defcard Heading-1
  (atom/h1 "Heading 1"))


#_(defcard-doc
   "This is the source code for h1"
   (mkdn-pprint-source atom/h1))



(defcard Heading-2
  (atom/h2 "Heading 2"))


(defcard Heading-3
  (atom/h3 "Heading 3"))


(defcard Heading-4
  (atom/h4 "Heading 4"))


(defcard Home-H1
  (atom/h1-home "Home H1"))


(defcard Home-H2
  (atom/h2-home "Home H2"))


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
