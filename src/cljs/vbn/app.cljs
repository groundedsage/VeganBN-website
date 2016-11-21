(ns vbn.app
  (:require [rum.core :as rum]
            [devcards.core :as dc]
            [bidi.bidi :as b :refer [match-route]]

            [vbn.index :as i])
  (:require-macros [devcards.core :refer [defcard]]))

(enable-console-print!)


;; HELPER COMPONENTS


(rum/defc svg-icon [meta]
  (let [{:keys [alt viewbox href]} meta]
    [:svg {:alt alt
           :viewbox viewbox}
     [:use {:xlink-href href}]]))




;; Accessibility defaults
(rum/defc skip-to-main []
  [:a.skip-to-main {:href "#main"}
   [:span "Skip to main content"]
   ])

(rum/defc main [content]
  "Enters content into main container with id=\"main\" "
  [:main#main content])

(rum/defc inline-link [text link]
  [:a {:href link} text])

;; Site specific


(rum/defc hidden []
  [:span  {:hidden true} "This is hidden text"])


;; NAVIGATION

(def nav-links {:index "index.html"
                :about-us "about-us.html"
                :veganism "veganism.html"
                :consulting "consulting.html"
                :community "community.html"})

(def my-routes ["/" {"" :index
                     "index.html" :index
                     "veganism.html" :veganism
                     "about-us.html" :about-us
                     "consulting.html" :consulting
                     "community.html" :community
                     "devcards.html" :devcards}])

(defn get-route []
  (:handler (match-route my-routes
                         (.-pathname
                          (.-location js/window)) )))



(rum/defc navigation  [current-route]
  (let [links (assoc nav-links current-route "#main")]
  [:nav
   [:ul
    [:li.order-middle [:a {:href (:index links)}
                       [:span {:aria-hidden true} "Home"]
                       [:svg.home {:alt "VBN Logo Home"
                                   :viewBox "0 0 158 172"}
                        [:use {:xlink-href "logo.svg#logo"}]]]]
    [:li.order-front [:a {:href (:about-us links)} [:span "About Us"]]]
    [:li.order-front [:a {:href (:veganism links)} [:span "Veganism"]]]
    [:li.order-end [:a {:href (:consulting links)} [:span "Consulting"]]]
    [:li.order-end [:a {:href (:community links)} [:span "Community"]]]

    ]]))


(rum/defc home [content]
   [:main#main
    (i/banner-image)
    (i/h1-home "Vegan Business Network")
    (i/bigger-than-business)
    (i/movement)

    (i/h2-home "At Our Core")
[:div.at-our-core
    (i/community)
    (i/education)]
    (i/sign-up)

    (i/h2-home "What We Do")
    (i/blurbs-title-second)


    (hidden)

    ])


(rum/defc veganism []
  [:main#main


   [:h1 "Veganism"]
   [:p "The world is changing rapidly. So many new things to learn with so little time. But what is Veganism? Why do the people who claim to be vegan have such a strong belief that everyone should be vegan? How do I still run my business in a way that embraces this movement without having to shut down or completely change my business? We answer all these questions right here!"]

   [:h2 "Our Definition of Vegan"]
   [:p "A vegan actively seeks to stop any exploitation and harm caused to human and non-human animals both directly and indirectly. Through pragmatic choices made daily against using, wearing or eating anything which is a product of this."]

   [:h2 "The 3 Dimensions of Veganism"]
   [:p "There is 3 key points to veganism which combine to create strong feelings within those who choose to embrace the vegan philosophy entirely. "]

   [:h3 "Sustainability"]
   (i/blurb-image "photos/nature.jpg" "Waterfall in rainforest")
   [:p "We all know we need to do something more. Otherwise each successive generation is going to have less animal diversity, plant diversity, resources and beauty in the world to enjoy. If current trends continue we could wipe out ourselves taking a large chunk of the planet with us. 
If you are someone who showers less, drives as little as possible and recycles religuously. Or just someone looking to do a little more for the planet and your fellow human beings. We highly suggest watching Cowspiracy. They also have an amazing infographic on their website."]
   [:button "Cowspiracy.org"]

   [:h3 "Nutrition"]
   (i/blurb-image "photos/watermelon.jpg" "Child eating watermelon")
   [:p "All around the world there are many top level athletes that hold world records and many more going vegan proving that plant sources of protein are sufficient and in many cases better than the alternative animal sources.
In addition to this there is many doctors and physicians that are treating patients with a 100% plant based diet. 
If nutrition is a field that interests you we suggest spending some time on."]
   [:button "Nutritionfacts.org"]

   [:h3 "Ethics"]
   (i/blurb-image "photos/ethics.jpg" "Baby cow tagged and just another number waiting to be kille dfor food ")
   [:p "If you are someone who cringes when you see someone kick a dog, cat or any other innocent animal. Someone who hates people who kill innocent animals for fun and enjoyment or someone who dislikes other cultures eating animals which are normally treated as pets such as dogs. 
If you still eat meat chances are you have never really thought too deeply about where that meat comes from. Which is fair enough as the thought of it makes us quickly shut out any deeper thinking to defend ourselves.
Melanie Joy is a psychologist who explains the psychology of eating meat and treating animals as objects that we own. "]
   [:button "Carnism.org"]

   [:h3 "How does this help my business?"]
   [:p "By doing some research on each of the 3 dimensions of veganism. You will be more capable of handling a vegan customer and adapting to current world trends. Vegans are a chatty bunch, they stand together and are very strong willed. In a world where people are more connected and brands are defined by their customers. Vegans with either burn you or praise you. We are here to make sure they praise you. It’s better for you business and it’s better for the vegans. We love it when everyone wins!
If you are very timepoor or just looking to have some guidance. We offer consulting services to teach you how to adapt your business so you never get burnt. "]

   ])


(rum/defc page [content]
  [:div
   (skip-to-main)
   (navigation (get-route))

   ;(home)
   (veganism)
   ;; add core.match here for each page
   ])





  (defcard
  "Heading 1"
  (i/h1-home "Heading 1 - Home Page"))

(defcard
  "Heading 2"
  (i/h2-home "Heading 2 - Home Page")) 

(defcard
  "Heading 3"
  (i/h3 "Heading 3 - Home Page"))



(defcard
  "Bigger than business "
  (i/bigger-than-business) )


(defcard
  "Movement Symbol"
  (i/movement) )

(defcard
  "#Community"
  (i/community))

(defcard
  "Education"
  (i/education))

(defcard
  "Blurbs"
  (i/blurbs-title-second))


(defn init [] (rum/mount
               (if (= :devcards (get-route))
                 (devcards.core/start-devcard-ui!)
                 (page))
                (. js/document (getElementById "container"))))

