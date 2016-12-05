(ns vbn.base
  (:refer-clojure :exclude [rem not])
  (:require [garden.stylesheet :refer [at-media]]))

{:h1 {:top (em 2.441) :bottom (em 1.602)}
 :h2 {:top (em 1.953) :bottom (em 1.424)}
 :h3 {:top (em 1.563) :bottom (em 1.266)}
 :h4 {:top (em 1.250) :bottom (em 1.125)}}


(at-media {:max-width (em 25)}

          [:html {:font-size (em 1)}]
          [:h1 "1em"]

          )

