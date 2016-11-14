(ns vbn.devcards
  (:require [devcards.core :as dc]
            [vbn.index :as i]
            [vbn.components :as comp]
            )
  (:require-macros [devcards.core :refer [defcard]]))

(defcard
  "Heading 1"
  (i/h1-home "Heading 1 - Home Page"))

(defcard
  "Heading 2"
  (i/h2-home "Heading 2 - Home Page"))

(defcard
  "Heading 3"
  (i/h3 "Heading 3 - Home Page"))

(defcard
  "Bigger Than Business Symbol"
  (comp/svg-icon {:alt "Bigger Than Business"
             :viewbox "0 0 255 175"
             :href "bigger-than-business.svg#bigger-than-business"}))

(defcard
  "Bigger than business "
  (i/bigger-than-business) )


(defcard
  "Movement Symbol"
  (i/movement) )

(defcard
  "Community"
  (i/community))

(defcard
  "Education"
  (i/education))

(defcard
  "Blurbs"
  (i/blurbs-title-second))



(defn init []
  (devcards.core/start-devcard-ui!)
  )
