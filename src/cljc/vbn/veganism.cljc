(ns vbn.veganism
  #?(:cljs (:require-macros  [vbn.styler :refer [css at-media]]))
  (:require [rum.core :as rum]
            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]
            [devcards.core :as dc]
            #?(:clj [vbn.styler :refer [css at-media get-css-str]])))

(rum/defc content []
  [:main#main
   (molecule/page-intro
    [:h1 "Veganism"]
    [:p "The world is changing rapidly. So many new things to learn with so little time. But what is Veganism? Why do the people who claim to be vegan have such a strong belief that everyone should be vegan? How do I still run my business in a way that embraces this movement without having to shut down or completely change my business? We answer all these questions right here!"])


   [:div {:class ["full-width"
                  "buffer-top-large"
                  (css {:color "white"
                        :background-color "#333D47"})]}
    [:div.inside-block

      [:h2 "Our Definition of Vegan"]
      [:p "A vegan actively seeks to stop any exploitation and harm caused to human and non-human animals both directly and indirectly. Through pragmatic choices made daily against using, wearing or eating anything which is a product of this."]]]

   [:div.center-items.dimension-buffer
    ;{:class [(css {:margin-top "4.5rem"
    ;               :align-items "center"
    ;               :background "red"

    (atom/h2-home "The 3 Dimensions of Veganism")
    [:p "There is 3 key points to veganism which combine to create strong feelings within those who choose to embrace the vegan philosophy entirely. "]]



   (molecule/blurb-title-second {:image "photos/nature.jpg"
                                    :alt-text "Waterfall in rainforest"
                                    :title "Sustainability"
                                    :text [:div [:p "We all know we need to do something more, otherwise each successive generation is going to have less animal diversity, plant diversity, resources and beauty in the world to enjoy. If current trends continue we could wipe out ourselves taking a large chunk of the planet with us."]
                                           [:p "If you are someone who showers less, drives as little as possible and recycles religuously, or just someone looking to do a little more for the planet and your fellow human beings then veganism is a way of life for you to do this."]

                                           [:p "To find out more we highly suggest watching Cowspiracy. They also have an amazing infographic on their website."]]
                                 :cta "Cowspiracy.com"
                                 :cta-key "http://www.cowspiracy.com/"})

   (molecule/blurb-title-second {:image "photos/watermelon.jpg"
                                   :alt-text "Child eating watermelon"
                                   :title "Nutrition"
                                   :text [:div [:p "All around the world there are many top level athletes that hold world records and many more going vegan proving that plant sources of protein are sufficient and in many cases better than the alternative animal sources."]
                                          [:p "In addition to this there is many doctors and physicians that are treating patients with a 100% plant based diet."]
                                          [:p "If nutrition is a field that interests you we suggest spending some time on."]]

                                 :cta "Nutritionfacts.org"
                                 :cta-key "http://nutritionfacts.org/"})

   (molecule/blurb-title-second {:image "photos/ethics.jpg"
                                   :alt-text  "Baby cow tagged and just another number waiting to be killed for food"
                                   :title "Ethics"
                                   :text [:div [:p "If you are someone who cringes when you see someone kick a dog, cat or any other innocent animal. Someone who hates people who kill innocent animals for fun and enjoyment or someone who dislikes other cultures eating animals which are normally treated as pets such as dogs."]
                                          [:p "If you still eat meat chances are you have never really thought too deeply about where that meat comes from. Which is fair enough as the thought of it makes us quickly shut out any deeper thinking to defend ourselves."]
                                          [:p "Melanie Joy is a psychologist who explains the psychology of eating meat and treating animals as objects that we own."]]
                                 :cta "Carnism.org"
                                 :cta-key "https://www.carnism.org/"})



   [:div.full-width.block-green.buffer-top-large
    [:div.inside-block
     (atom/h2 "How does this help my business?")
     [:p "By doing some research on each of the 3 dimensions of veganism. You will be more capable of handling a vegan customer and adapting to current world trends. Vegans are a chatty bunch, they stand together and are very strong willed. In a world where people are more connected and brands are defined by their customers. Vegans with either burn you or praise you. We are here to make sure they praise you. It’s better for you business and it’s better for the vegans. We love it when everyone wins!"]
     [:p"If you are very timepoor or just looking to have some guidance. We offer consulting services to teach you how to adapt your business so you never get burnt. "]]]])
