(ns cards)

                                        ;(defcard
                                        ;  "Skip to main"
                                        ;  (skip-to-main))







(defcard
  "label" 
  (label "This is the label here" "This is a link"))

(defcard
  "main-testing"
  (main "This is some main content"))

(defn init []
  (devcards.core/start-devcard-ui!)
  )
