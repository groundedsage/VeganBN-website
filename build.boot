(set-env!
 :source-paths    #{"src/cljs" "src/clj"}
 :resource-paths  #{"resources"}
 :dependencies '[[adzerk/boot-cljs          "1.7.228-1"  :scope "test"]
                 [adzerk/boot-cljs-repl     "0.3.0"      :scope "test"]
                 [adzerk/boot-reload        "0.4.8"      :scope "test"]
                 [pandeiro/boot-http        "0.7.2"      :scope "test"]
                 [com.cemerick/piggieback   "0.2.1"      :scope "test"]
                 [org.clojure/tools.nrepl   "0.2.12"     :scope "test"]
                 [samestep/boot-refresh "0.1.0" :scope "test"]
                 [com.novemberain/pantomime "2.8.0"]
                 [weasel                    "0.7.0"      :scope "test"]
                 [org.clojure/clojurescript "1.7.228"]
                 [rum "0.10.4"]
                 [org.martinklepsch/boot-garden "1.3.2-0" :scope "test"]
                 [devcards "0.2.2"]
                 [bidi "2.0.14"]
                 [org.clojure/core.match "0.3.0-alpha4"]])


(require
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
 '[adzerk.boot-reload    :refer [reload]]
 '[pandeiro.boot-http    :refer [serve]]
 '[samestep.boot-refresh :refer [refresh]]
 '[org.martinklepsch.boot-garden :refer [garden]]


 '[clojure.string :as str]
 '[clojure.java.io :as io]

 '[vbn.static :as static]
 '[vbn.app :as app]
 '[bidi.bidi :refer [path-for route-seq]]
 '[rum.core :as rum])

(def next-route (path-for app/my-routes :community))

(defn render-template
  [in-file out-file route]
  (doto out-file
    io/make-parents
    (spit (str/replace (slurp in-file) "{{CONTENT}}"
                       (case route
                         :veganism   (rum/render-static-markup (app/veganism))
                         :consulting (rum/render-static-markup (app/consulting))
                         :community  (rum/render-static-markup (app/community))
                         :about-us   (rum/render-static-markup (app/about-us)))))))


(defn gather-routes
  "Gathers all generated routes and emits the keyword of each route."
  []
  (->> (route-seq app/my-routes)
       (map #(first %))
       (map #(conj [] %))
       (map #(into {} %))
       (map #(:handler %))
       (into #{})))

;; Need to capture the initial fileset and map that with generate-page and sort over all routes



(deftask generate-page
  "Takes a page route as a keyword."
  [r route VALUE kw "The route in the form of a keyword"]
  (let [tmp (tmp-dir!)]
    (with-pre-wrap [fileset]
      (empty-dir! tmp)
      (let [in-files (input-files fileset)
            template (by-name ["template.html"] in-files)]
        (doseq [in template]
          (let [in-file (tmp-file in)
                in-path (tmp-path in)
                out-path in-path
                out-file (io/file tmp out-path)]
            (render-template in-file out-file route)))
        (-> fileset
            (add-resource tmp)
            commit!)))))



(deftask build []
  (comp (speak)
        (cljs)
        (comp
         (generate-page :route :veganism)
         (sift :move{#"template.html" "veganism.html"}))
        (garden :styles-var 'vbn.styles/screen :output-to "css/garden.css")))





(deftask run []
  (comp (serve) ;:handler 'vbn.static/handler

  ;; TODO

  ; Serve up static html files from the fileset


        (watch)
        (cljs-repl)
        (reload)
        (build)))

(deftask production []
  (task-options! cljs {:optimizations :advanced}
                      garden {:pretty-print false})

  identity)

(deftask development []
  (task-options! cljs {:optimizations :none
                       :source-map true
                     ;  :compiler-options {:parallel-build true}}
                       :compiler-options {:devcards true}}
                 reload {:on-jsload 'vbn.app/init})

  identity)


;; My own task
;(deftask devcards []
;  (task-options! cljs {:compiler-options {:devcards true}}
;                 serve {:dir "devcards/resources"})


(deftask dev
  "Simple alias to run application in development mode"
  []
  (comp
   (development)
   (run)))
