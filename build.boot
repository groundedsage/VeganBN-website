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
  [r route VALUE kw "The route in the form of a keyword"
   o template VALUE code "An atom containing the original template"]
  (let [tmp (tmp-dir!)]
    (with-pre-wrap [fileset]
      (empty-dir! tmp)
      (let [in-files (input-files fileset) ;; Currently ignored
            template (by-name ["template.html"] in-files) ;;Currently ignored
            template-new template]
        (doseq [in template]
          (let [in-file (tmp-file in)
                in-path (tmp-path in)
                out-path in-path
                out-file (io/file tmp out-path)]
            (render-template in-file out-file route)))

        (-> fileset
            (add-resource tmp)
            commit!)))))

#_(deftask get-template
  "Extracts the template.html from the fileset and places it inside an atom"
  []
  (with-pre-wrap [fileset]
    (let [in-files (input-files fileset)
          template (by-name ["template.html"] in-files)
          original-template (atom template)]
      original-template)))

(deftask string-template
  "Does the thing"
  [f template-file VALUE str "Name of the template file to use"
   t target-file VALUE str "Name of the output file to produce"
   p placeholder VALUE str "The placeholder to recognize and replace"
   c content VALUE str "Content to replace placeholder with"]
  (with-pre-wrap [fs]
    (let [tmpd (tmp-dir!)]
      (if-let [template-file (some->> (input-files fs)
                                      (by-name [template-file])
                                      first
                                      tmp-file)]
        (let [template-content   (slurp template-file)
              output-file        (doto (io/file tmpd target-file) io/make-parents)
              quoted-placeholder (java.util.regex.Pattern/quote placeholder)]
          (spit output-file (.replaceAll template-content quoted-placeholder content))
          (-> fs
              (add-resource tmpd)
              commit!))
        (throw (ex-info "No template file found" {:opts *opts*}))))))



#_(deftask generate-pages []
  (generate-page :route :community)
  (sift :move{#"template.html" "community.html"}))

(defn route-content [route]
  (case route
    :index "hello"
    :veganism   (rum/render-static-markup (app/veganism))
    :consulting (rum/render-static-markup (app/consulting))
    :community  (rum/render-static-markup (app/community))
    :about-us   (rum/render-static-markup (app/about-us))))

(deftask make-page
  [r route VAL kw "Route of page"
   t template-file NAME str "Name of the template file to use, default is template.html"]
  (string-template :template-file (or template-file "template.html")
                   :target-file (subs (path-for app/my-routes route) 1)
                   :placeholder "{{CONTENT}}"
                   :content (route-content route)))


(def routes #{:veganism :consulting :community :about-us})

(deftask make-pages []
  (reduce comp (map #(make-page :route %) (gather-routes))))


(deftask build []
  (comp (speak)
        (cljs)
        (make-pages)
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
