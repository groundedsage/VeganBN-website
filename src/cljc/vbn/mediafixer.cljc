(ns vbn.mediafixer
  (:require [clojure.string :as str]
            #?(:cljs [cljs.reader :refer [read-string]])))

;; Atom to map queries to their screen query
(def all-queries (atom #{}))
(reset! all-queries #{})

(def atomic-css (atom ()))
(reset! atomic-css ())

;(def my-str ".Sm{flex-wrap:wrap}@media(min-width:30rem){.Sg{flex-direction:row}}@media(min-width:10rem){.Sy{flex-direction:row}}")
;(def empty-str "Random string")
;(def new-string ".Sm{flex-wrap:wrap}.Sk{width:2em}.Se{padding-left:1.5em}.Sj{margin-right:3em}.Sb{display:flex}.Sc{flex-direction:column}.Sl{flex-direction:row}.Sd{align-items:baseline}.Sf{padding-right:1.5em}")


;; Extracts media query
;(str/index-of my-str "@") ;19
;(str/index-of my-str "}}") ; 104 +2
;(subs my-str 19 (+ 104 2))


;; Function that extracts query
(defn extract-query [my-str]
  (let [start (str/index-of my-str "@")
        end   (+ 2 (str/index-of my-str "}}"))]
      (subs my-str start end)))

;; Example function - extracts query from original string
;(extract-query my-str)



;; Takes the first min-width of the query and gets the measurement
(defn get-measurement [extracted-query]
  (read-string
    (->> (re-find #"min-width:\d+rem|px|em" extracted-query)
         (re-find #"\d+"))))

;;Example function - gets measurement from extracted query
;(get-measurement (extract-query my-str))




;; Sets extracted query as a pattern to be removed from original string
(defn my-query-pattern [my-str]
  (->
    (clojure.string/replace (extract-query my-str) "(" "\\(")
    (clojure.string/replace ")" "\\)")
    (clojure.string/replace "{" "\\{")
    (clojure.string/replace "}" "\\}")
    (re-pattern)))

;; Example function - removes query from original string
;(str/replace my-str (my-query-pattern my-str) "")





;;
(defn add-query-to-map [my-str]
  (let [query (extract-query my-str)
        order (get-measurement query)]
      (swap! all-queries conj [order query]))) ;[[:order number] [:css query]])))

;; Example adding the query to the vector along with the screen query number
;(add-query-to-map my-str)

;; Add queries to map and remove from original string recursively


(defn separate-queries [my-str]
        (if (not (boolean (re-find #"@media" my-str)))
         my-str
         (do
           (add-query-to-map my-str)
           (separate-queries (str/replace my-str (my-query-pattern my-str) "")))))

; Example of resursive edit
;(separate-queries my-str)
;@all-queries


;Example of result vector with all the queries
;(sort @all-queries)
;(get-in @all-queries [0 1])


; Delete this
;(str new-string (get-in @all-queries [0 1]))

(defn edit-css [css-str]
  (do
    (swap! atomic-css conj (separate-queries css-str))
    (reset! all-queries (sort @all-queries))))

;(edit-css my-str)

(defn get-atomic-css [css-str]
  (do
    (edit-css css-str)
    (reduce str
        (reverse
         (swap! atomic-css conj
                (str/join (map #(second %1) @all-queries)))))))


;(get-atomic-css (vbn.styler/get-css-str false))

;(println "*******  THIS IS FROM MEDIA FIXER ******"(get-atomic-css (vbn.styler/get-css-str false)))
