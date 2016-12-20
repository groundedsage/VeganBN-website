(ns vbn.base
  (:refer-clojure :exclude [rem not])
  (:require [garden.stylesheet :refer [at-media]]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;   THIS IS EXPERIMENTAL IS DEPENDS ON    ;;;;;;;;
;;;;;;;;;   THE FINAL MERGE OF GARDEN 2.0 BRANCH  ;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(def headings {:h1 {:top (em 2.441) :bottom (em 1.602)}
               :h2 {:top (em 1.953) :bottom (em 1.424)}
               :h3 {:top (em 1.563) :bottom (em 1.266)}
               :h4 {:top (em 1.250) :bottom (em 1.125)}})

(def text-top  1.5)

(def h1-top     2.441)
(def h1-bottom  1.602)

(def h2-top     1.953)
(def h2-bottom  1.424)

(def h3-top     1.563)
(def h3-bottom  1.266)

(def h4-top     1.250)
(def h4-bottom  1.125)

(def lowest-width 25)
(def highest-width   62.5)
(def width-range (- highest-width lowest-width))


(at-media {:max-width (em lowest-width)}

          [:html {:font-size (rem text-bottom)}]
          [:h1 {:font-size (em h1-bottom) }]
          [:h2 {:font-size (em h2-bottom)}]
          [:h3 {:font-size (em h3-bottom)}]
          [:h4 {:font-size (em h4-bottom)}])

(at-media {:min-width (em lowest-width) :max-width (em highest-width)}

          [:html {:font-size (calc (+ (em 1)
                                      (/ (* (text-top - 1)
                                            (100vw - (em lowest-width)))
                                         width-range)))}])
