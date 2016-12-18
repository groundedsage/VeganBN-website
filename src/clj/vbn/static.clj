(ns vbn.static
  (:require [rum.core :as rum]
            [vbn.app :as app]
            [clojure.string :as str]
            [clojure.java.io :as io]
            ;[pantomime.mime :refer [mime-type-of]]


            [boot.core :as c]))

(defn render-template [template]
  (str/replace template "{{CONTENT}}" "Happy times!"))


;(def html-template (slurp (io/resource "template.html")))

#_(defn page [content]
    {:status 200
     :body (str/replace html-template "{{CONTENT}}" content)
     :headers {"Content-Type" "text/html"}})

#_(defn handler [req]
    (let [path (:uri req)
          route (app/get-route path)]
      (case route
        :index      (page (rum/render-html (app/home "")))
        :veganism   (page (rum/render-html (app/veganism)))
        :consulting (page (rum/render-html (app/consulting)))
        :community  (page (rum/render-html (app/community)))
        :about-us   (page (rum/render-html (app/about-us)))
        :devcards   (page "") ;; needs js so just send the html
        ;; look for a resource
       nil (if-let [url (io/resource (.substring path 1))]
             {:status 200 :body (-> url
                                    .openConnection
                                    .getInputStream)
              :headers {"Content-Type" (mime-type-of path)}}
             {:status 404 :body "Not found"})
      ;; not found, basically
       {:status 404 :body "Not found"})))

