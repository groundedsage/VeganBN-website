(ns vbn.static
  (:require [rum.core :as rum]
            [vbn.app :as app]
            [clojure.string :as str]
            [clojure.java.io :as io]
            [pantomime.mime :refer [mime-type-of]]


            [boot.core :as c]))

#_(defn copy-file [source-path dest-path]
    (io/copy (io/file source-path) (io/file dest-path)))

;;(copy-file "/home/username/squirrel.txt" "/home/username/burt-reynolds.txt")

#_(defn render-template [template]
  (str/replace template "{{CONTENT}}" "Happy times!"))


;(def html-template (slurp (io/resource "template.html")))

#_(c/deftask gen-page []
  (println "thistest")
  (show))

#_(defn generate-page []

;;;; TO DO

  ;;1. Copy index.html (template) into fileset creating a new name with routes
  ;;2. String replace the {{CONENT}} and other parts which act as a placeholder
  ;;3. Recurse over the files until all of them are copied into the fileset

  (spit "target/flubber.txt" "hello"))


#_(defn page [content]
    {:status 200
     :body (str/replace html-template "{{CONTENT}}" content)
     :headers {"Content-Type" "text/html"}})

#_(defn handler [req]
    (let [path (:uri req)
          route (app/get-route path)]
      (case route
        :index      (page (rum/render-html (app/home "")))
        :veganism   (page (rum/render-html (app/veganism)))
        :consulting (page (rum/render-html (app/consulting)))
        :community  (page (rum/render-html (app/community)))
        :about-us   (page (rum/render-html (app/about-us)))
        :devcards   (page "") ;; needs js so just send the html
        ;; look for a resource
       nil (if-let [url (io/resource (.substring path 1))]
             {:status 200 :body (-> url
                                    .openConnection
                                    .getInputStream)
              :headers {"Content-Type" (mime-type-of path)}}
             {:status 404 :body "Not found"})
      ;; not found, basically
       {:status 404 :body "Not found"})))

#_(c/deftask lc
   "Compile .lc files."
   []
   (let [tmp (c/tmp-dir!)]                           ; [1]
     (fn middleware [next-handler]                   ; [2]
       (fn handler [fileset]                         ; [3]
         (c/empty-dir! tmp)                          ; [4]
         (let [in-files (c/input-files fileset)      ; [5]
               lc-files (c/by-ext [".lc"] in-files)] ; [6]
           (doseq [in lc-files]                      ; [7]
             (let [in-file  (c/tmp-file in)          ; [7.i]
                   in-path  (c/tmp-path in)          ; [7.ii]
                   out-path (lc->uc in-path)         ; [7.iii]
                   out-file (io/file tmp out-path)]  ; [7.iv]
               (compile-lc! in-file out-file)))      ; [7.v]
           (-> fileset                               ; [8]
               (c/add-resource tmp)                  ; [9]
               c/commit!                             ; [10]
               next-handler))))))
