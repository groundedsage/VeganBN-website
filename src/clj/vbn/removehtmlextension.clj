(ns vbn.removehtmlextension
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


(defn remove-html [in-file out-file]
  ;(println (slurp in-file))
  (doto out-file
      io/make-parents
      (spit (str/replace (slurp in-file) #".html" ""))))
  ;(println (slurp out-file)))


(deftask remove-html-task
  "Removes the html from routes"
  []
  (let [tmp (tmp-dir!)]
    (with-pre-wrap [fileset]
      (empty-dir! tmp)
      (let [in-files (input-files fileset)
            file-with-html (by-name ["components.cljc"] in-files)]
        (doseq [in file-with-html]
          (let [in-file (tmp-file in)
                in-path (tmp-path in)
                out-path in-path
                out-file (io/file tmp out-path)]
            (remove-html in-file out-file)))
        (-> fileset
            (add-resource tmp)
            commit!)))))
