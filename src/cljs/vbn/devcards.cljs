(ns vbn.devcards
  (:require [devcards.core :as dc]
            [vbn.index :as i]
            )
  (:require-macros [devcards.core :refer [defcard]]))




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
