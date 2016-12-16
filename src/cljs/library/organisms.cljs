(ns library.organisms
  (:require [devcards.core :as dc]
            [vbn.index :as i]
            [vbn.components :as comp]
            [rum.core :as rum]
            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]
            [vbn.organisms :as organism])
  (:require-macros [devcards.core :refer [defcard defcard-doc mkdn-pprint-source   ]]))

(defcard Bigger-than-business
  (organism/bigger-than-business))

(defcard Movement
  (organism/movement))

(defcard At-our-core
  (organism/at-our-core))

(defcard What_We_Do
  (organism/what-we-do))
