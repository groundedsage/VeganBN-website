(ns vbn.atoms
  #?(:cljs (:require-macros  [vbn.styler :refer [css at-media]]))
  (:require [rum.core :as rum]
    #?(:clj [vbn.styler :refer [css at-media get-css-str]])))

(rum/defc h1 [text]
  [:h1 text])

(def line-under {:border-bottom "solid 1px rgba(51,61,71, 0.2)"
                 :position "absolute"
                 :top "110%"
                 :right "10%"
                 :width "80%"
                 :content " \" \" "})

(rum/defc h1-home [text]
  [:h1
    {:class [(css {:font-weight "bold"
                   :align-self "center"
                   :position "relative"
                   ;;[REMOVE LATER]
                   :margin-top "1.5em"})]}
                   ;;Should be able to make below into symbol
;              :before {:border-bottom "solid 1px rgba(51,61,71, 0.2)"
;                               :position "absolute"
;                               :top "110%"
;                               :right "10%"
;                               :width "80%"
;                               :content " \" \" "})]}
  ;.h1-home.line-under.buffer-top
   text])

(rum/defc h2 [text]
  [:h2 text])

(rum/defc h2-home [text]
  [:h2.h2-home.line-under text])

(rum/defc h3 [text]
  [:h3 text])

(rum/defc h4 [text]
  [:h4 text])

(rum/defc intro []
  [:div.intro-text
   [:p "intro text"]])


(rum/defc p [text]
  [:p text])


(rum/defc button [text]
  [:button [:span text]])


(rum/defc blurb-image [src alt-text]
  [:img.blurb-image {:src src
                     :alt alt-text}])



(rum/defc circle-icon [name]
  [:svg {:height "8rem"
         :width "80%"
         :viewBox "0 0 123 123"}
   [:use {:xlink-href (str name ".svg#" name)}]])


(rum/defc bigger-than-business []
  [:svg {:height "4.5em"
         :width "100%"
         :viewBox "0 0 230 100"}
   [:use {:xlink-href "bigger-than-business.svg#bigger-than-business"}]])


(rum/defc movement []
  [:svg {:height "7.5em"
         :width "100%"
         :viewBox "0 0 238 175"}
   [:use {:xlink-href "movement.svg#movement"}]])

(rum/defc native []
  [:svg {:height "9em"
         :width "100%"
         :viewBox "0 0 394 369"}
   [:use {:xlink-href "/native.svg#native"}]])

(rum/defc harmonise []
  [:svg {:height "9em"
         :width "100%"
         :viewBox "0 0 181 93"}
   [:use {:xlink-href "/yingyang.svg#harmonise"}]])

(rum/defc puzzle []
  [:svg {:height "9em"
         :width "100%"
         :viewBox "0 0 444 443"}
   [:use {:xlink-href "/puzzle.svg#puzzle"}]])

(rum/defc services []
  [:svg {:height "13em"
         :width "100%"
         :viewBox "0 0 222 122"}
   [:use {:xlink-href "/services.svg#services"}]])

(rum/defc strategy []
  [:svg {:height "7em"
         :width "7em"
         :viewBox "0 0 200 188"}
   [:use {:xlink-href "/svg/strategy.svg#strategy"}]])

(rum/defc digital []
  [:svg {:height "7em"
         :width "7em"
         :viewBox "0 0  136 164"}
   [:use {:xlink-href "/svg/digital.svg#digital"}]])

(rum/defc physical []
  [:svg {:height "7em"
         :width "7em"
         :viewBox "0 0 170 163"}
   [:use {:xlink-href "/svg/print.svg#print"}]])

(rum/defc information-architecture []
  [:svg.buffer-left {:height "10em"
                     :width "10em"
                     :viewBox "0 0 363 309"}
   [:use {:xlink-href "/svg/information-architecture.svg#information-architecture"}]])

(rum/defc visual-language []
  [:svg.bump-left {:height "10em"
                   :width "10em"
                   :viewBox "0 0 363 309"}
   [:use {:xlink-href "/svg/visual-language.svg#visual-language"}]])


(rum/defc mock-up []
  [:svg.buffer-left {:height "10em"
                     :width "10em"
                     :viewBox "0 0 255 309"}
   [:use {:xlink-href "/svg/mock-up.svg#mock-up"}]])

(rum/defc micro-interactions []
  [:svg.bump-left {:height "10em"
                   :width "10em"
                   :viewBox "0 0 247 272"}
   [:use {:xlink-href "/svg/micro-interactions.svg#micro-interactions"}]])

(rum/defc develop []
  [:svg.bump-left {:height "10em"
                   :width "10em"
                   :viewBox "0 0 361 301"}
   [:use {:xlink-href "/svg/develop.svg#develop"}]])

(rum/defc happy-days []
  [:svg.bump-left {:height "10em"
                   :width "10em"
                   :viewBox "0 0 314 272"}
   [:use {:xlink-href "/svg/happy-days.svg#happy-days"}]])


(rum/defc safe []
  [:svg.bump-left {:height "10em"
                   :width "10em"
                   :viewBox "0 0 147 96"}
   [:use {:xlink-href "/svg/safe.svg#safe"}]])


(rum/defc bird []
  [:svg.bump-left {:height "10em"
                   :width "10em"
                   :viewBox "0 0 223 108"}
   [:use {:xlink-href "/svg/bird.svg#bird"}]])

(rum/defc evolve []
  [:svg.bump-left {:height "10em"
                   :width "10em"
                   :viewBox "0 0 2539 1500"}
   [:use {:xlink-href "/svg/evolve.svg#evolve"}]])

(rum/defc rock-solid []
  [:svg.bump-left {:height "10em"
                   :width "10em"
                   :viewBox "0 0 153 158"}
   [:use {:xlink-href "/svg/rock-solid.svg#rock-solid"}]])



(rum/defc dollar []
  [:svg {:height "1em"
                   :width "1em"
                   :viewBox "0 0 53 98"}
   [:use {:xlink-href "/svg/dollar.svg#dollar"}]])
