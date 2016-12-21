(ns vbn.consulting
  (:require [rum.core :as rum]
            [bidi.bidi :refer [path-for]]

            #?(:cljs [vbn.navigation :refer [link current-token]])

            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]))

;; define clojure version of link
#?(:clj (rum/defc link [link & content]
          [:a {:href link} content]))






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

   [:span.consulting-top-text.buffer-top  "What does that mean?"]
   [:div.block-grey.full-width.consulting-block
    [:div.inside-block.extra-padding
     [:span.consulting-block-text "You bring the idea. "
      [:strong.green-text "We bring it to life."]]]]

   [:div.home-component.buffer-top-large.consult-component
    (atom/native)
    [:div
     [:h3 "We're Native Vegans"]
     [:p "The vegan community is our home. Our finger is on the pulse of this beautiful community. We know what is happening. When it is happening. Why it is happening. We are also creating and driving change ourselves."]]]




   [:div.block-green.full-width.buffer-top-large
    [:div.inside-block

     (atom/services)

     [:h2.centre.services-align "Services"]
     [:div.bullet-padding
      [:div.three-up
       [:div.inside-three.center-to-60
        (atom/strategy)
        (atom/h3 "Strategy")
        [:ul.service-list
         [:li "Business Coaching"]
         [:li "Branding"]
         [:li "Legal"]]]


       [:div.inside-three.center-to-60
        (atom/digital)
        (atom/h3 "Digital")
        [:ul.service-list
         [:li (link (path-for my-routes :web) [:span "Web & Apps"])]
         [:li "Video & Animation"]
         [:li "Social Media Marketing"]]]

       [:div.inside-three.center-to-60
        (atom/physical)
        (atom/h3 "Print")
        [:ul.service-list
         [:li "Business Cards"]
         [:li "Flyers & Posters"]
         [:li "Signage & Merch"]]]]]]]

   [:div.home-component.consult-component.buffer-top-large
    (atom/harmonise)

    [:div
     (atom/h2 "Harmonise the services")
     [:p "One stop shops for professional business services are rare. But today communication between multiple teams is critical. Co-ordinating communication between lawyers, designers, developers and marketers is the last thing you want to be doing when starting a business. "]

     [:p "We are ready to bring some harmony to this entire process letting you work on what makes your business special."]]]

   [:div.block-grey.full-width.buffer-top-large
    [:div.inside-block
     [:div.home-component.consult-component
      (atom/puzzle)
      [:div
       (atom/h2 "How we do it")
       [:p "We do everything with our in house team or through tight partnerships. If what you need goes outside of our in house offerings. We have a strong network of talented individuals and organisations we can call upon. "]
       [:p "We can tell you who is vegan and those that can get the job done. We also provide a service of verification and can review your list of candidates to ensure they are capable of doing the work required to suit your needs. This allows us to cater to the most advanced needs, the budget restricted or any location challenges."]]]]]

   ])









;; NEED TO ASSOC STYLES IN A REDUCE THEN PLACE INSIDE AN ATOM????

#_(comment

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



  ;; CLOSEST SOLUTION BELOW

  ;;#?[cljs-css-modules.macro :as s]
  #_(s/defstyle style
      [".container" {:color "#333d47"}]
      [".green-text" {:color "#6def14"}])

  #_(def class-names (atom #?(:clj (:green-text (:map style)))
                           #?(:cljs (:class-name (:green-text style))) ))



  ;;{:class @class-names}
  ;;Appears to be an issue when conditionally adding classname to element
  ;; #?(:clj {:class (:green-text (:map style))})
  ;; #?(:cljs {:class-name (:green-text style)})






  (println style))

