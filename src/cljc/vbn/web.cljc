(ns vbn.web
  (:require [rum.core :as rum]
            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]))


(rum/defc icon-title-text [title text]
  [:div title text])

(rum/defc content []
  [:main#main
   (molecule/page-intro
    [:h1 "Web & App"]
    [:p "It’s a jungle out there! Branding and the technology are often viewed as two separate pieces with the quality of one sacrificed for the other. However effective storytelling requires both the branding and the technology to live in harmony. The ever evolving landscape of technology has left many businesses with dated, fragmented and inconsistent technology."])



   [:div.full-width
    [:div.inside-block.bump-width
     (atom/h2-home "4 Reasons to Choose Us")
     [:div.four-up
      [:div.column-four
       [:.inside-three
        (atom/rock-solid)
        (icon-title-text
         [:h3 "Rock Solid Foundations"]
         [:div [:p "Built on the most reliable
technology the web has to offer."]
          [:p"Seamlessly grow from a simple blog to an international phenomenom without ever noticing a difference in speed or crashing."]])]

       [:.inside-three
        (atom/bird)
        (icon-title-text
         [:h3 "Speed Matters"]
         [:div [:p "Your website will load faster than the rest. We strive for a sub 2 second load time."]
          [:p "Over 50% of mobile users will abandon sites that take longer than 3 seconds to load. Sites that load faster have longer user sessions and can more than double revenue generated from sales or ads."]])]]

      [:div.column-four
       [:.inside-three
        (atom/safe)
        (icon-title-text
         [:h3 "Ultra Secure"]
         [:div [:p "We provide bulletproof security. Your visitors can rest easy knowing your site will never have any malware.
"]
          [:p "The possibilities of your site being hacked or taken down are eliminated and significantly reduced with a very smart system design and advanced protection."]])]


       [:.inside-three
        (atom/evolve)
        (icon-title-text
         [:h3 "Designed to Evolve"]
         [:div [:p "Evolve from a simple website to an online application. Then jump from the web into a phone, tablet, desktop or TV!
"]
          [:p "Our system allows your business to grow without any major technology changes or massive rewrites."]])]]]]]




   [:div.block-grey.full-width
    [:div.inside-block
     [:h2.center "We have two guiding principles"]
     [:div.principles

      [:div.principle.make-top-margin
       [:h3 "Speed"]
       [:p "We emphasize speed throughout our development process and in our final product."]
       [:p "Immediate feedback on a design results in a smoother creative flow and is the difference between testing all the ideas both you and our team have or just the ones we have time for. "]
       [:p "Today more people visit websites or use applications on their mobiles instead of desktops. This means much slower download speeds resulting in very slow loading times and a bad user experience. People expect things to load in under 2 seconds and we can meet these expectations."]]


      [:div.principle
       [:h3 "Simplicity"]
       [:p "A well designed solution is always the most simple. Often decisions are based on what is the most flashy or easiest option. The hidden side of these decisions is the result of visual and technical systems which lack the ability grow, evolve, adapt and be maintained."]
       [:p "At VBN we choose the minimal set of tools necessary to get the job done and we carry this minimalism right through to the products we deliver. The flexibility this process provides is umatched for the budgets we can work with.
"]]]]]




   [:div.block-blue.full-width
    {:style {:margin-top "0"}}
    [:div.inside-block.bump-width
     [:h2.center "Our Process"]

     [:p.center "We focus on immediate feedback throughout our entire  process. As such it is highly iterative and interactive from the very beginning. You are completely engaged throughout the entire process. When you start with us after the initial consult we provide you with a completely interactive unfinished design prototype. From here we progressively evolve this prototype togther to produce a design that feels almost real. Knowing this is exactly what you want we begin the final stage of actually building your website or application."]

     [:div.three-up-six
      [:div.inside-three
       (atom/information-architecture)
       (icon-title-text
        [:h3 "Information Architecture"]
        [:p "Starting with the initial consult we establish the current state of your business and it’s future plans. From here we can design the structure of the information on your website or application to ensure that it achieves your current and future business objectives/ desired goals. "])]


      [:div.inside-three
       (atom/visual-language)
       (icon-title-text
        [:h3 "Visual Language"]
        [:p "Starting with the initial consult we review the visual language your business currently has or what is missing. From here we develop the unique style of your website or application in a way that can easily be reviewed and evolve years later.
"])]

      [:div.inside-three
       (atom/mock-up)
       (icon-title-text
        [:h3 "Mock-up"]
        [:p "Bringing your unique style and information architecture together we create the first real representation of what the final product will look like using real or representative information."])]

      [:div.inside-three
       (atom/micro-interactions)
       (icon-title-text
        [:h3 "Micro-interactions"]
        [:p "Once we have established the look and flow of your website or application we can focus on micro-interactions. It is here that you can truly set yourself apart. We create small interactive and intelligent animations. This is what delights your users."])]

      [:div.inside-three
       (atom/develop)
       (icon-title-text
        [:h3 "Develop and Deploy"]
        [:p "Throughout the entire process we develop finalised designs to ensure it is completed as quickly as possible. At this stage we add the final touches and then release your website or application into the wild. "])]

      [:div.inside-three
       (atom/happy-days)
       (icon-title-text
        [:h3 "Happy Days"]
        [:p "You can rest easy knowing you’ve made the best choice."])]]]]


   [:div.center-items.buffer-top-large
    [:h2 "What's the cost?"]
    [:p "Everyone has different needs so it will almost always end up with a custom quote. However in the interest of saving both of us time we have provided you with prices for a variety of general website combinations. These prices are based on a typical website and conditions apply with respect to upkeep fees. Prices are introductory and will change in the future."]

    [:div.row.circle (atom/dollar) [:h3 "250"]]
    [:span.initial-consult "Initial Consult"]


    [:p "Our initial consultation covers up to two hours discussion regarding your needs and any preliminary designing. We offer so much value it is impossible for us to do this for free. We provide a detailed info sheet following the initial consult and any designs done will also be provided to you in PDF format. This means you can easily take all that value to another studio of give it to a friend doing your website."]
    [:p "If you still love us and want to continue. Rest assured that this money goes towards your final payment."]]





   [:div.bullet-padding
    [:div.column-four
     [:div.inside-three
      [:div.pricing-options
       [:div.row
        (atom/dollar)
        [:h3 "3000"]]
       [:span "Simple Site"]]
      [:ul.pricing-features
       [:li "Without a CMS"]
       [:li "Custom Site Design"]
       [:li "Custom domain that you own"]
       [:li "You own the site code"]
       [:li "Never pay any upkeep fees"]]]

     [:div.inside-three
      [:div.pricing-options
       [:div.row
        (atom/dollar)
        [:h3 "3800"]]
       [:span "Dynamic Site"]]
      [:ul.pricing-features
       [:li "Advanced CMS"]
       [:li "Custom Site Design"]
       [:li "Custom domain that you own"]
       [:li "You own the site code"]
       [:li "Never pay any upkeep fees"]]]]

    [:div.column-four
     [:div.inside-three
      [:div.pricing-options
       [:div.row
        (atom/dollar)
        [:h3 "4800"]]
       [:span "Dynamic Site +"]]
      [:ul.pricing-features
       [:li "Advanced CMS"]
       [:li "Custom Site Design"]
       [:li "Custom domain that you own"]
       [:li "You own the site code"]
       [:li "Never pay any upkeep fees"]]]

     [:div.inside-three
      [:div.pricing-options
       [:div.row
        (atom/dollar)
        [:h3 "4500"]]
       [:span "Dynamic Site + E-commerce"]]
      [:ul.pricing-features
       [:li "Advanced CMS"]
       [:li "Custom Site Design"]
       [:li "Custom domain that you own"]
       [:li "You own the site code"]
       [:li "Upkeep starts at 2% of transactions with a minimum of 10 dollars (excluding fees from your choice of payment gateway).
Non-profits, schools or crowdfunding projects are exempt from the minimum 10 dollars and the fee is reduced to 1.5%. "]]]]]


   [:div.block-green.full-width
    [:div.inside-block.center-items
     [:h2 "What are you waiting for?"]
     [:div.form-width
      [:form {:name "contact"
              "netlify" true}
       [:p
        [:label "Your Name:"]
        [:input {:type "text"
                 :name "name"}]]
       [:p
        [:label "Your Email:"]
        [:input {:type "email"}]]

       [:p
        [:label "Let us know what your dreaming up"]
        [:textarea.bump-area-height {:name "message"}]]

       [:p
        [:button [:span "Send"]]]
       ]]

     ]]])
