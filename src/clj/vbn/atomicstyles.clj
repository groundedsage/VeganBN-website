(ns vbn.atomicstyles
  (:require [rum.core :as rum]
            [vbn.app :as app]
            [vbn.components :refer [my-routes]]
            [clojure.string :as str]
            [clojure.java.io :as io]
            [boot.core :as c]
            [vbn.styler :as styler]
            [vbn.mediafixer :refer [get-atomic-css]]

            [bidi.bidi :refer [path-for route-seq]]
            [boot.core :refer [deftask
                               tmp-dir!
                               commit!
                               add-resource
                               with-pre-wrap
                               input-files
                               by-name
                               tmp-file
                               empty-dir!
                               tmp-path]]))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ADDING ATOMIC STYLES ISN'T WORKING THIS WAY  ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn make-atomic-styles [in-file out-file]
  ;(println  "\n\n*** THIS IS THE IN FILE ***" (slurp in-file)
      (doto out-file
          io/make-parents
          (spit
           (str (slurp in-file)
                " "
                ;(styler/get-css-str false)
                ; (get-atomic-css
                 (styler/get-css-str false)))))
  ;(println "\n\n*** THIS IS THE OUT FILE ***" (slurp out-file)))




(deftask add-atomic-styles
    "Adds atomic styles to final css"
    []
    (let [tmp (tmp-dir!)]
      (with-pre-wrap [fileset]
        (empty-dir! tmp)
        (let [in-files (input-files fileset)
              css-file (by-name ["garden.css"] in-files)]
          (doseq [in css-file]
            (let [in-file (tmp-file in)
                  in-path (tmp-path in)
                  out-path in-path
                  out-file (io/file tmp out-path)]
              (make-atomic-styles in-file out-file)))
          (-> fileset
              (add-resource tmp)
              commit!)))))
