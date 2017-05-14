(ns vbn.utilstyler
  "
  A macro namespace that generates classes on the fly for usage in cljs.
  # Motivation
  https://ryantsao.com/blog/virtual-css-with-styletron
  # Synopsis:
  In your cljs files you can use inline styles:
      [:div {:class [(css {:border 0})]}]
  If you need to dynamically change a property:
      {:class [(if active? (css {:color \"red\"}) (css {:color \"blue\"})]}
  Pseudo queries:
      (css :hover {...})
  You can use media queries:
      (at-media {:max-width \"600px\"} {:padding 0, :margin \"20px\"})
  Or both:
      (css {:min-width \"400px\"} \"::before\" {...})
  Also partition your css rules by media width:
      (media-part :width [{:padding 0} 400 {:padding \"2px\"} 600 {:padding \"10px\"}])
  # Development live reloading of styles
  Simply include the css file (see one of the first def's below for which one)
  in your html with a <link> and also tell figwheel to watch that directory
  with :css-dirs  [\"...\"].
  # Production:
  Before you do a production build make sure to remove ALL your previously
  compiled js files in resources/public/js/.....
  The namespace that you specified in your cljs build config under the key:
      :main
  Will get compiled LAST, all other dependencies first.
  Thus, this is where you place something like this:
     (goog.style/installStyles (styler/get-css-str true))
  OR you can also just generate a file for production:
     (styler/spit-css! \"resources/css/app.css\" true)
  (also in your :main namespace)
  # TODO:
  - Keyframes
  # Autoprefixing
  Requires postcss installed:
  npm install --global postcss-cli autoprefixer
  "
  (:require
    [clojure.string :as str]
    [garden.core :as garden]
    [clojure.java.shell :as sh]
    [garden.stylesheet :as style]
    [clojure.java.io :as io]
    ;; BOOT SPECIFIC  <- wade
    [boot.core :refer [deftask
                       tmp-dir!
                       commit!
                       add-resource
                       with-pre-wrap
                       input-files
                       by-name
                       tmp-file
                       empty-dir!
                       tmp-path]])
    ;; END BOOT SPECIFIC
  (:import (java.util Date)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Some constants:
(def auto-prefixer-cmd ["postcss" "-u" "autoprefixer"])



(System/setProperty "styler.db" "false")
(def mem-only? (= "false" (System/getProperty "styler.db")))


(def db-file
  "The db will receive e a dump of the styles-atom below. This is needed
  since otherwise the styler would lose previously generated styles when figwheel
  restarts.
  Give it the special name \"false\" to disable dumping the db."
  (or (System/getProperty "styler.db") "cache/styler-db.clj"))

;;;;;;;;;;;;;;;;;;
;;;;    TODO
;;;;;;;;;;;;;;;;;;

;;   Create a styler-db.clj
;;   Hold the reference to the file inside an atom
;;   Dereference the atom inside the db-file definition







(def css-out-file
  "The file that's written to whenever a new style is generated. Include this in your
  html with a <link> and let figwheel auto-reload it."
  "resources/public/css/styler/app.css")

(def css-class-prefix "S")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Simple logger:
(defn log! [& args]
  (spit "/tmp/styler.log" (str (Date.) ": " args "\n") :append true))
;;NEEDS-TASK ** CAN GO WITHOUT**
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Persisting DB:
(def styles-atom (atom
                   (if (and (not mem-only?) (.exists (io/file db-file)))
                     (do
                       (println "Loading db-file: " db-file)
                       (read-string (slurp db-file)))
                     (do
                       (println "Couldn't find file (or mem-only): " db-file)
                       {}))))

;;NEEDS-TASK
;; BOOT SPECIFIC  <- wade





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; We need some debouncing when cljs compiles tons of files at once and figwheel
;; would go nuts about reloading the same file hundreds of times.
(defn- debounce-future
  "Stolen from github:loganlinn
  Returns future that invokes f once wait-until derefs to a timestamp in the past."
  [f wait wait-until]
  (future
    (loop [wait wait]
      (Thread/sleep wait)
      (let [new-wait (- @wait-until (System/currentTimeMillis))]
        (if (pos? new-wait)
          (recur new-wait)
          (f))))))

(defn debounce
  "Stolen from github:loganlinn
  Takes a function with no args and returns a debounced version.
  f does not get invoked until debounced version hasn't been called for `wait` ms.
  The debounced function returns a future that completes when f is invoked."
  [f wait]
  (let [waiting-future (atom nil)
        wait-until (atom 0)]
    (fn []
      (reset! wait-until (+ (System/currentTimeMillis) wait))
      (locking waiting-future
        (let [fut @waiting-future]
          (if (or (not (future? fut)) (future-done? fut))
            (reset! waiting-future (debounce-future f wait wait-until))
            fut))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(let [last (atom "")]
  (defn spit-if-different-from-last
    "Spits the css string if different from last call."
    [curr-css]
    (when-not mem-only?
      (when-not (= curr-css @last)
        (log! "Spitting updated css file" css-out-file {:rules (count @styles-atom)})
        (spit css-out-file curr-css)
        (reset! last curr-css)))))
;;NEEDS-TASK


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defmulti to-garden (fn [this css-class]
                      (or (::type this) (class this))))

(defmethod to-garden :media-query
  [{:keys [query child]} css-class]
  (if css-class
    (style/at-media query (to-garden child css-class))
    (style/at-media query child)))
(comment
  (to-garden {::type :media-query
              :query {:min-width "3px"}
              :child {:color "black"}} "zy")
  (to-garden {::type :media-query
              :query {:min-width "3px"}
              :child [{:color "black"}
                      {:color "green"}]} "zy"))

(defmethod to-garden :pseudo-selector
  [{:keys [pseudo child]} css-class]
  [(str "." css-class pseudo) (to-garden child nil)])
(comment
  (to-garden {::type :pseudo-selector
              :pseudo ":hover"
              :child {:color "black"}} "zy")
  (to-garden {::type :media-query
              :query {:min-width "3px"}
              :child {::type :pseudo-selector
                      :pseudo ":hover"
                      :child {:color "black"}}}
             "zy"))

(defmethod to-garden :keyframes
  [{:keys [child]} css-class]
  (apply style/at-keyframes css-class child))
(comment
  (to-garden {::type :keyframes
              :child [[:from {:opacity 0}]
                      [:to {:opacity 1}]]} "zy"))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Class GEN:
(defn- next-int []
  (inc (count @styles-atom)))

(defn- char-range
  [a b]
  (mapv char
        (range (int a) (int b))))

(def valid-chars (concat (char-range \a \z)
                         (char-range \A \Z)
                         (char-range \0 \9)
                         [\_ \-]))

(defn- unique-id-gen
  "Generates a sequence of unique identifiers seeded with ids sequence"
  [ids]
  ;; Laziness ftw:
  (apply concat
         (iterate (fn [xs]
                    (for [x xs
                          y valid-chars]
                      (str x y)))
                  (map str ids))))

(def inf-ids-seq (unique-id-gen valid-chars))

(defn- new-class
  "Returns an unused new classname"
  []
  (str css-class-prefix (nth inf-ids-seq (next-int))))
#_(repeatedly 100 new-class)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Class add
(defn- add-css-prop
  "Adds the css prop + value to the atom and returns the newly generated class"
  [pv]
  (let [gen-class (new-class)]
    (swap! styles-atom assoc pv gen-class)
    gen-class))

(defmethod to-garden garden.types.CSSAtRule
  [this css-class]
  this)

(defmethod to-garden clojure.lang.APersistentMap
  [this css-class]
  (if css-class
    [(str "." css-class) this]
    this))

(defmethod to-garden clojure.lang.APersistentVector
  [this css-class]
  (if css-class
    [(str "." css-class) (mapv #(to-garden % nil) this)]
    (mapv #(to-garden % nil) this)))
#_(to-garden {:color "blue"} nil)
#_(to-garden {:color "blue"} "foo")

(defn- classnames-for-styles
  [constructor styles]
  (str/join " "
            (reduce-kv
              (fn [xs prop v]
                (assert (or (number? v) (string? v) (coll? v))
                        "CSS value must be a number or a string.")
                (conj xs
                      (let [style {prop v}
                            k (constructor style)]
                        (or (get @styles-atom k)
                            (add-css-prop k)))))
              []
              styles)))

(defn- classname-for-keyframes
  [constructor keyframes]
  (let [k (constructor keyframes)]
    (or (get @styles-atom k)
        (add-css-prop k))))

;; Optimization: Merge the same media queries:
;; Not the prettiest, but it works:
(defn- merge-media-queries
  "Merges the media queries since this isn't done by garden."
  [x]
  (let [{:keys [::other] :as grouped}
        (group-by
          (fn [[k class]]
            (if (= :media-query (::type k))
              (:query k)
              ::other))
          x)]
    (reduce-kv
      (fn [m q queries]
        (assoc m
          {::type :media-query
           :query q
           :child (mapv
                    (fn [[query class]]
                      (to-garden (:child query) class))
                    queries)}
          nil))
      (into {} other)
      (dissoc grouped ::other))))
#_(merge-media-queries @styles-atom)

(defn css-fn
  ([styles]
   (classnames-for-styles identity styles))
  ([pseudo styles]
   (classnames-for-styles #(-> {::type :pseudo-selector
                                :pseudo pseudo
                                :child %}) styles))
  ([media pseudo styles]
   (classnames-for-styles #(-> {::type :media-query
                                :query media
                                :child {::type :pseudo-selector
                                        :pseudo pseudo
                                        :child %}})
                          styles)))

(defn keyframes-fn
  [& args]
  (classname-for-keyframes #(-> {::type :keyframes
                                 :child %})
                           args))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; API INPUT:
(defmacro css
  "Returns a string of (multiple) casses (space separated) for the given styles
   The styles MUST be constants (this is a macro!):
   (css {:display \"block\", :background-color \"blue\"})
   ;; Gets appended behind the newly generated class:
   (css :hover {...})
   (css \":nth-of-type(2)\" {...})
   (css \"::before\" {...})
   ;; Or also with media queries:
   (css {:min-width \"400px\"} \"::before\" {...})"
  ([styles]
   (css-fn styles))
  ([pseudo styles]
   (css-fn pseudo styles))
  ([media pseudo styles]
   (css-fn media pseudo styles)))
#_(css {:display "block", :background-color "blue"})
#_(css :hover {:background-color "green"})
#_(css {:min-width "3px"} :hover {:background-color "green"})

(defmacro keyframes
  [& args]
  (apply keyframes-fn args))

(defn at-media-fn
  [query styles]
  (classnames-for-styles #(-> {::type :media-query
                               :query query
                               :child %}) styles))

(defmacro at-media
  "(at-media {:max-width \"600px\"} {:padding 0, :margin \"20px\"})"
  [query styles]
  (at-media-fn query styles))
#_(at-media {:min-width "400px"} {:display "inline"})

(defn media-part-fn
  "(media-part :width [{:padding 0} 400 {:padding \"2px\"} 600 {:padding \"10px\"}])"
  ;; TODO: Optimize to use the SAME classes:
  [w-or-h xs]
  {:pre [(#{:width :height} w-or-h)]}
  (let [min+ #(-> {(keyword (str "min-" (name w-or-h))) (str (inc %) "px")})
        max! #(-> {(keyword (str "max-" (name w-or-h))) (str % "px")})]
    (->> (partition 3 2 (concat [nil] xs [nil]))
         (mapv
           (fn [[from styles to]]
             (classnames-for-styles
               #(-> {::type :media-query
                     :query (cond
                              (and from to) (merge (min+ from) (max! to))
                              from (min+ from)
                              to (max! to))
                     :child %})
               styles)))
         (str/join " "))))

(defmacro media-part
  "(media-part :width [{:padding 0} 400 {:padding \"2px\"} 600 {:padding \"10px\"}])"
  [w-or-h xs]
  (media-part-fn w-or-h xs))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; OUTPUT
(defn- prefix-css
  "Prefixes the given css string. Pipes s into the command and returns the output. (slow)"
  [cmd s]
  (:out (apply sh/sh (into cmd [:in s]))))
#_(prefix-css auto-prefixer-cmd ".x{flex:center}")

(defn- compile-css
  "Compiles the css in the atom and returns a css string"
  [merge-media? min?]
  (garden/css {:pretty-print? (not min?)}
              (mapv
                (fn [[k css-class]]
                  (to-garden k css-class))
                (if merge-media?
                  (merge-media-queries @styles-atom)
                  @styles-atom))))
#_(compile-css true false)
#_(compile-css true true)

(defn get-css-str-fn
  ([min? prefix?] (get-css-str-fn min? prefix? auto-prefixer-cmd))
  ([min? prefix? cmd]
   (cond->> (compile-css min? min?)
     prefix? (prefix-css cmd))))
#_(get-css-str-fn false true)

(defn get-all-classes
  "for debugging"
  []
  (sort (mapv second @styles-atom)))

(defn del-rule-by-class
  "DEV ONLY"
  [klass]
  (into {}
        (filter (fn [[_ k]] (not= k klass)))
        @styles-atom))
#_(reset! styles-atom (del-rule-by-class "Sbi"))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; API OUTPUT:
(defmacro get-css-str
  "Gets the CSS that's been accumulated. If the cmd is given it will be piped the raw
  css string and should returns a css string (for autoprefixing)"
  ([min? prefix?] (get-css-str-fn min? prefix? auto-prefixer-cmd))
  ([min? prefix? cmd] (get-css-str-fn min? prefix? cmd)))

(defmacro spit-css!
  ([file-name prefix?]
   (spit file-name (get-css-str-fn true prefix? auto-prefixer-cmd)))
  ([file-name prefix? cmd]
   (spit file-name (get-css-str-fn true prefix? cmd))))
;;NEEDS-TASK**CAN LEAVE**


(def spit-css-file!
  "Dumps the CSS. Debounced and not going to touch the css file if unchanged."
  (debounce
    #(spit-if-different-from-last (get-css-str-fn false false))
    400))
#_(spit-css-file!)

(def on-change-listener
  "Listens to any DB changes and dumps the new db as well as spits the CSS string."
  (add-watch styles-atom :dump-db
             (fn [_ _ _ new]
               (spit-css-file!)
               (when-not mem-only?
                 (spit db-file new)))))
;; spitted
