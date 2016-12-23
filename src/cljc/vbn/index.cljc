(ns vbn.index
  (:require [rum.core :as rum]
            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]
            [vbn.organisms :as organism]))


     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     ;;;;;;;;;   HOME PAGE SPECIFIC   ;;;;;;;;;;;;
     ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(rum/defc banner-image[]
  [:div#banner-image.full-width
   [:span "Fostering a better world through business"]])

(rum/defc sign-up []
  [:div#sign-up.sign-up-box.full-width.buffer-top-large
   [:div.inside-block.center-items
    [:p "We are always up to new and interesting things. We can send you a few emails from time to time to let you know what is happening in the community."]
    [:div.form-width
     {:dangerouslySetInnerHTML
      {:__html
 "
<!-- Begin MailChimp Signup Form -->
<div id=\"mc_embed_signup\">
<form action=\"//veganbusinessnetwork.us13.list-manage.com/subscribe/post?u=3e449a3b219823344ae7ae47a&amp;id=a3633bf54c\" method=\"post\" id=\"mc-embedded-subscribe-form\" name=\"mc-embedded-subscribe-form\" class=\"validate\" target=\"_blank\" novalidate>
    <div id=\"mc_embed_signup_scroll\">
	
<div class=\"mc-field-group\">
	<label class=\"sign-up-label\" for=\"mce-EMAIL\">Email Address </label>
	<input type=\"email\" value=\"\" name=\"EMAIL\" class=\"required email\" id=\"mce-EMAIL\">
</div>
	<div id=\"mce-responses\" class=\"clear\">
		<div class=\"response\" id=\"mce-error-response\" style=\"display:none\"></div>
		<div class=\"response\" id=\"mce-success-response\" style=\"display:none\"></div>
	</div>    <!-- real people should not fill this in and expect good things - do not remove this or risk form bot signups-->
    <div style=\"position: absolute; left: -5000px;\" aria-hidden=\"true\"><input type=\"text\" name=\"b_3e449a3b219823344ae7ae47a_a3633bf54c\" tabindex=\"-1\" value=\"\"></div>
    <div class=\"clear\"><input type=\"submit\" value=\"Subscribe\" name=\"subscribe\" id=\"mc-embedded-subscribe\" class=\"button\"></div>
    </div>
</form>
</div>

<!--End mc_embed_signup-->


"


       }}]


    ]])


(rum/defc content []
  [:main#main.footer-buffer
   (banner-image)
   (atom/h1-home "Vegan Business Network")
   (organism/bigger-than-business)
   (organism/movement)
   (organism/at-our-core)
   (sign-up)
   (organism/what-we-do)
   ;[:a {:href "/devcards.html"} "Devcards"]
   ])


   ;(hidden)

   ;(link (path-for my-routes :devcards) "Dev Cards")])
