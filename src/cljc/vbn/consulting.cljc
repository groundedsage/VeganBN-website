(ns vbn.consulting
  (:require [rum.core :as rum]
            [bidi.bidi :refer [path-for]]

            #?(:cljs [vbn.navigation :refer [link current-token]])
            [cljs-css-modules.macro :as s]
            ;#?(:clj [cljs-css-modules.macro :as s])



            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]))



(s/defstyle style
  [".container" {:color "#333d47"}]
  [".green-text" {:color "#6def14"}])

(def class-names (atom #?(:clj (:green-text (:map style)))
                       #?(:cljs (:green-text style)) ))






;;;; REMOVE LATER


(def my-routes ["/" [[#{"" "index.html"} :index]
                     ["veganism.html" :veganism]
                     ["about-us.html" :about-us]
                     ["consulting/"  [["index.html" :consulting]
                                      ["web.html" :web]]]
                     ["community.html" :community]
                                        ;["devcards.html" :devcards]
                     ]])
                                        ;[true :not-found]]])

;;;;;;;;;;;;;;; END OF DELETING

(rum/defc content []
  [:main#main
   (molecule/page-intro
    [:h1 "Consulting"]
    [:p "We provide full service business consulting"])

   [:span.consulting-top-text  "What does that mean?"]
   [:div.block-grey.full-width
    {:style {:margin-top "-0.3em"
             :padding-top "1em"
             :padding-bottom "1em"}}
    [:div.inside-block.extra-padding
     [:span.consulting-block-text "You bring the idea. "
      [:strong
       {:class @class-names}
       ;;Appears to be an issue when conditionally adding classname to element
      ; #?(:clj {:class (:green-text (:map style))})
      ; #?(:cljs {:class-name (:green-text style)})
       "We bring it to life."]]]]

   [:div.home-component
    (atom/native)
    [:div
     [:h3 "We're Native Vegans"]
     [:p "The vegan community is our home. Our finger is on the pulse of this beautiful community. We know what is happening. When it is happening. Why it is happening. We are also creating and driving change ourselves."]]]

   [:div.block-green.full-width
    [:div.inside-block

     (atom/services)

     (atom/h2 "Services")
     [:div.bullet-padding
      [:div.three-up
       [:div.inside-three
        (atom/strategy)
        (atom/h3 "Strategy")
        [:ul
         [:li "Business Coaching"]
         [:li "Branding"]
         [:li "Legal"]]]


       [:div.inside-three
        (atom/digital)
        (atom/h3 "Digital")
        [:ul
         [:a {:href "/consulting/web.html"} [:li "Web & Apps"]]
         [:li "Video & Animation"]
         [:li "Social Media Marketing"]]]

       [:div.inside-three
        (atom/physical)
        (atom/h3 "Print")
        [:ul
         [:li "Business Cards"]
         [:li "Flyers & Posters"]
         [:li "Signage & Merch"]]]]]]]

   [:div.home-component
    (atom/harmonise)

    [:div
     (atom/h2 "Harmonise the services")
     [:p "One stop shops for professional business services are rare. But today communication between multiple teams is critical. Co-ordinating communication between lawyers, designers, developers and marketers is the last thing you want to be doing when starting a business. "]

     [:p "We are ready to bring some harmony to this entire process letting you work on what makes your business special."]]]

   [:div.block-grey.full-width
    [:div.inside-block
     [:div.home-component
      (atom/puzzle)
      [:div
       (atom/h2 "How we do it")
       [:p "We do everything with our in house team or through tight partnerships. If what you need goes outside of our in house offerings. We have a strong network of talented individuals and organisations we can call upon. "]
       [:p "We can tell you who is vegan and those that can get the job done. We also provide a service of verification and can review your list of candidates to ensure they are capable of doing the work required to suit your needs. This allows us to cater to the most advanced needs, the budget restricted or any location challenges."]]]]]

   ])









;; NEED TO ASSOC STYLES IN A REDUCE THEN PLACE INSIDE AN ATOM????

(comment

  #?(:clj
     (s/defstyle new
       [".new" {:color "yellow"}]))


  (println "\n" "*** Printing from style ***")
  (clojure.pprint/pprint style)

  (println "\n" "*** Printing from new ***")
  (clojure.pprint/pprint new)

  (defn merged-styles [old new]
    (println "\n"  "***This is the old css with new*** \n" (:css old) "\n" "\n"(:css new)))
  #_(reduce #(conj (:css %1) (:css %2))
            old
            new)
  (merged-styles style new)

  (def p {:name "James" :age 26})

  (println (assoc p :name "happy"))

  (defn merge-styles [old new]
    (assoc old :css (str (:css old) "\n" "\n" (:css new))))
  (merge-styles style new)

  (println style))

