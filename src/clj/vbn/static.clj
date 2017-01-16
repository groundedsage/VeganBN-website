(ns vbn.static
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



(defn gather-routes
  "Gathers all generated routes and emits the keyword of each route."
  []
  (->> (route-seq my-routes)
       (map #(first %))
       (map #(conj [] %))
       (map #(into {} %))
       (map #(:handler %))
       (into #{})))

(defn route-content
  "Takes a route and renders the corresponding markup"
  [route]
  (case route
    :index      (rum/render-static-markup (app/home))
    :veganism   (rum/render-static-markup (app/veganism))
    :consulting (rum/render-static-markup (app/consulting))
    :community  (rum/render-static-markup (app/community))
    :about-us   (rum/render-static-markup (app/about-us))
    :web        (rum/render-static-markup (app/web))
    :not-found  (rum/render-static-markup (app/not-found))))

(defn get-path [route]
  "Catches edge cases regarding getting the route path"
  (cond
    (= :index route) "index.html"
    (= :not-found route) "404.html"
    :else (subs (path-for my-routes route) 1)))

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
                (get-atomic-css (styler/get-css-str false)))))
  (println "\n\n*** THIS IS THE OUT FILE ***" (slurp out-file)))




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


(deftask string-template
  "Takes the template file and replaces placeholder text with the page content and changes file name"
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


(deftask make-page
  [r route VAL kw "Route of page"
   t template-file NAME str "Name of the template file to use, default is template.html"]
  (let [url (get-path route)]
    (string-template :template-file (or template-file "template.html")
                     :target-file url
                     :placeholder "{{CONTENT}}"
                     :content (route-content route))))


(deftask make-pages []
  (reduce comp (map #(make-page :route %) (gather-routes))))
