(ns vbn.styler
  "
  A macro namespace that generates classes on the fly for usage in cljs.
  # Motivation
  https://ryantsao.com/blog/virtual-css-with-styletron
  # Synopsis:
  In your cljs files:
      [:div {:class [(css {:border 0})]}]
  If you need to dynamically change a property:
      {:class [(if active? (css {:color \"red\"}) (css {:color \"blue\"})]}

  You can also do at-media or pseudo selectors:

      {:class (at-media {:min-width \"400px\"} {:padding 0})}
      {:class (css {:min-width \"400px\"} :hover {:color \"green\"})}

  Or partition the media width/height:

      (media-part :width [{:padding 0} 400
                          {:padding \"4px\"} 800
                          {:padding \"14px\"}]
  Which will generate 3 @media queries:

         (max-width: 400px}, {min-width: 401px, max-width: 800px}, {min-width: 801px}

  # Compatibility
  Doesn't use any clojurescript code. So this is compatible with all cljs libraries. Reagent, Om, Rum,
  Quiesent and even non-react based code.
  # Injecting the generated CSS:
  ## Development
  In development you create a namespace (note the ^:figwheel-always):
     (ns ^:figwheel-always your-app.styler-installer
       (:require-macros
          [xyz.utils.styler :as styler])
       (:require [goog.style]))
     (defonce prev (volatile! nil))
     (when @prev
       (goog.style/uninstallStyles @prev))
     (vreset! prev (goog.style/installStyles (styler/get-css-str false)))
  ## Production:
  The namespace that you specified in your cljs build config under the key:
      :main
  Will get compiled LAST, all other dependencies first.
  Thus, this is where you place something like this:
     (goog.style/installStyles (styler/get-css-str true))

  You can also just generate a file for production:

     (styler/spit-css! \"resources/css/app.css\" true)
  # TODO:
  - Keyframes
  Rewrite implemenation to not use records and use a map:
  with keys: {:color 'black} -> [[::media {}]
                                 [::normal {}]
                                 [::pseudo {}]]

  # Autoprefixing
  Requires postcss installed:
  npm install --global postcss-cli autoprefixer
  "
  (:require
    [clojure.string :as str]
    [garden.core :as garden]
    [garden.stylesheet :as style]))

(def ^:private auto-prefixer-cmd ["postcss" "-u" "autoprefixer"])

;





;(def a (atom {})

;(add-watch my-atom :watcher
;  (fn [key atom old-state new-state]
;    (prn new-state)
;    (System/setProperty "styles" (pr-str new-state)))


;(reset! my-atom {:hello "there"})
;(System/getProperty "styles")



;(def global-css-styles (atom {}))
(defn global-styles []
  (if (= nil (System/getProperty "styles"))
    {}
    (read-string (System/getProperty "styles"))))

(defn add-to-global-styles [styles]
  (System/setProperty "styles" (pr-str styles)))





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Class GEN:
(def css-class-prefix "S")
(defonce ^:private counter (volatile! 0))

(defn- next-int []
  (vswap! counter inc))

(defn- char-range
  [a b]
  (mapv char
        (range (int a) (int b))))

(def ^:private valid-chars (concat (char-range \a \z)
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

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Class add
(defn- add-css-prop
  "Adds the css prop + value to the atom and returns the newly generated class"
  [pv]
  (let [gen-class (new-class)]
    ;(swap! global-css-styles assoc pv gen-class)
    (add-to-global-styles (assoc (global-styles) pv gen-class))
    gen-class))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; The map key is always stored without class
(defprotocol GardenDecoder
  "Decodes a type into something garden understands"
  (to-garden [this css-class]))

(extend-protocol GardenDecoder
  garden.types.CSSAtRule
  (to-garden [this css-class]
    this)
  clojure.lang.APersistentMap
  (to-garden [this css-class]
    (if css-class
      [(str "." css-class) this]
      this))
  clojure.lang.APersistentVector
  (to-garden [this css-class]
    (if css-class
      [(str "." css-class) (mapv #(to-garden % nil) this)]
      (mapv #(to-garden % nil) this))))

(defrecord MediaQuery [query child]
  GardenDecoder
  (to-garden [this css-class]
    (if css-class
      (style/at-media query (to-garden child css-class))
      (style/at-media query child))))

(defrecord PseudoSelector [pseudo child]
  GardenDecoder
  (to-garden [this css-class]
    [(str "." css-class pseudo) (to-garden child nil)]))

(defn- classnames-for-styles
  [constructor styles]
  ;(println "\n\n This is the style passed to classnames" styles)
  (str/join " "
            (reduce-kv
              (fn [xs prop v]
                (conj xs
                      (let [style {prop v}
                            k (constructor style)]
                        ;(println "This is the style" style)
                        ;(println "This is prop " prop)
                        ;(println "This is v " v)
                        ;(println "This is k " k)
                        (or (get ;@global-css-styles
                                (global-styles)
                                k)
                            (add-css-prop k)))))
              []
              styles)))

;; Optimization: Merge the smae media queries:
;; Not the prettiest, but it works:
(defn- merge-media-queries
  "Merges the media queries since this isn't done by garden."
  [x]
  (let [{:keys [::other] :as grouped}
        (group-by
          (fn [[k class]]
            (if (instance? MediaQuery k)
              (:query k)
              ::other))
          x)]
    (reduce-kv
      (fn [m q queries]
        (assoc m
          (MediaQuery. q (mapv
                           (fn [[query class]]
                             (to-garden (:child query) class))
                           queries))
          nil))
      (into {} other)
      (dissoc grouped ::other))))

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
   (classnames-for-styles identity styles))
  ([pseudo styles]
   (classnames-for-styles #(PseudoSelector. pseudo %) styles))
  ([media pseudo styles]
   (classnames-for-styles #(MediaQuery. media (PseudoSelector. pseudo %)) styles)))

(defmacro at-media
  "(at-media {:max-width \"600px\"} {:padding 0, :margin \"20px\"})"
  [query styles]
  (classnames-for-styles #(MediaQuery. query %) styles))

(defmacro media-part
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
               #(MediaQuery.
                  (cond
                    (and from to) (merge (min+ from) (max! to))
                    from (min+ from)
                    to (max! to))
                  %)
               styles)))
         (str/join " "))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; OUTPUT
(defn- prefix-css
  "Prefixes the given css string. Pipes s into the command and returns the output. (slow)"
  [cmd s]
  ;; Dynamic in case people want to use this in bootstrapped cljs:
  (require 'clojure.java.shell)
  (let [sh (ns-resolve 'clojure.java.shell 'sh)]
    (:out (apply sh (into cmd [:in s])))))

(defn- compile-css
  "Compiles the css in the atom and returns a css string"
  []
  (garden/css {:pretty-print? false}
              (mapv
                (fn [[k css-class]]
                  (to-garden k css-class))
                (merge-media-queries (global-styles))))) ;@global-css-styles))))


(defn- get-css-str-fn
  [prefix? cmd]
  (cond->> (compile-css)
    prefix? (prefix-css cmd)))

(defn- spit-css-fn!
  [f content]
  (spit f content))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; API OUTPUT:
(defmacro get-css-str
  "Gets the CSS that's been accumulated. If the cmd is given it will be piped the raw
  css string and should returns a css string (for autoprefixing)"
  ([prefix?] (get-css-str-fn prefix? auto-prefixer-cmd))
  ([prefix? cmd] (get-css-str-fn prefix? cmd)))

(defmacro spit-css!
  ([file-name prefix?]
   (spit-css-fn! file-name (get-css-str-fn prefix? auto-prefixer-cmd)))
  ([file-name prefix? cmd]
   (spit-css-fn! file-name (get-css-str-fn prefix? cmd))))
