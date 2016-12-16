(ns library.molecules
  (:require [devcards.core :as dc]
            [vbn.index :as i]
            [vbn.components :as comp]
            [rum.core :as rum]
            [vbn.atoms :as atom]
            [vbn.molecules :as molecule])
  (:require-macros [devcards.core :refer [defcard defcard-doc mkdn-pprint-source   ]]))


(defcard
  "#Molecules")

(defcard Blurb
  (molecule/blurb-title-second {:image "photos/nature.jpg"
                       :alt-text "Waterfall in rainforest"
                       :title "Sustainability"
                       :text "We encourage people from all walks of life to come to our meetups. If you are interested in veganism, entrepreneurship, or you are just a business owner with a little curiosity about what a vegan is then this event is the one for you"
                       :cta "Cowspiracy.org"}))

(defcard Icon-with-text
  (molecule/icon-with-text "community" "We are about Community"))


