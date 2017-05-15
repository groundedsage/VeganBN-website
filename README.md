# Vegan Business Network (Redesign)
This is the next iteration of the Vegan Business Network website. https://www.veganbusinessnetwork.org/

Developed to be hosted as a static site using the JAM stack (Javascript, API's and Markup).
You can learn more about the JAM stack here: https://jamstack.org/

My intention of this project is to create a foundation. Making it possible to get off the ground quickly for the most basic of websites instead of defaulting to Wordpress or similar options. That still allows incremental changes to become a single page application without sacrificing productivity or performance. Using Clojurescript and Clojure.

## Rationale

The browser today is powerful enough to perform like 90% of the tasks we use our devices for. Progressive Web Apps (PWA) provide a future where distribution of digital products/services can be achieved with a single codebase and without serious fragmentation of technologies we experience today https://medium.com/dev-channel/why-progressive-web-apps-vs-native-is-the-wrong-question-to-ask-fb8555addcbb#.u024lehn0. The significant performance improvements are only set to get better with Web Assembly (WASM) as well https://medium.com/javascript-scene/what-is-webassembly-the-dawn-of-a-new-era-61256ec5a8f6#.hloyw1tv4. Even without WASM the performance of JavaScript is improving faster than we realise https://www.youtube.com/watch?v=LopU-kMpe8I.

The JAM stack throws out the old model of progressive enhancement where the server handles requests from users without JavaScript. This old model produces a lot of overhead for very small edge cases. For circumstances where it is absolutely necessary it is possible to utilize 'Functions as a Service' (FaaS) using Cloud functions, such as Webtask.io, AWS Lambda, Google Cloud Functions or Azure Functions etc. for small tasks, otherwise it is also possible to create your own API within your server code. By adopting the JAM stack we can drastically reduce the amount of moving parts, complications and costs inherint in the current Status Quo of web development and deployment.


## Design goals (no order)
* is usable and looks clean on all devices from a _watch_ to a _tv_.
* is accessible & usable in a delightful way by the blind, colour blind, contrast sensitive, people with poor literacy skills and poor motor skills
* is fast, meaning the site is downloaded and usable within 2 seconds never more than 3 seconds
* is semantic for search engines and future AI assistants
* use as much modern browser technology as possible for future proofing. To avoid negative experiences only use features with 80% browser usage or more unless it provides significant improvement in speed, bloat reduction, aesthetic or has an immediately obvious reason it will become an anti-pattern. Polyfill anything that is less than 80% using feature detection.


## Current Status

I have been working on ensuring I have a good foundation to build upon. Therefore the design elements such as colours, the logo and all other visual elements which do not affect layout are incomplete.

Typography has been made fluid so that the font continually changes size based on viewport width between a large range of mobile devices. The rest of the layout is relative to the font size, parent container or the viewport.

All routes are now rendered into a static `html` file and then once the JS is downloaded the pages turn into a Single Page Application (SPA).


### TODO
* Fix my implementation of styles so that it works like Styletron https://ryantsao.com/blog/virtual-css-with-styletron taking inspiration for reusability from http://tachyons.io/. Whilst also providing extensibility like PostCSS http://postcss.org/ (also allowing it to use PostCSS Plugins) with the flexibility not provided by CSS like http://elementqueries.com/
* Figure out how to use Devcards as a way to establish a pattern library. Similar to what has been implemented at Pattern Lab http://patternlab.io/
* Work on deployment pipeline

## TODO - Long Term
* integrate service workers and turn into a progressive web app
* provide as much sane defaults for accessibility as possible
* provide options as to which features are wanted
* turn all of this into a template for others to use easily


To view current status enter this command in the root folder `boot dev`. 
To build enter `boot build target` 
For production enter `boot production build target`.


## Tools used.

* **Boot + various tasks** (Build tool) - http://boot-clj.com/
* **Rum** (React wrapper which can do SSR) - https://github.com/tonsky/rum
* **Garden** (CSS) - https://github.com/noprompt/garden
* **Bidi** (Routing) - https://github.com/juxt/bidi
* **Devcards** (Visual Dev tool) - https://github.com/bhauman/devcards

### Future
* **Datascript** (Client side database) - https://github.com/tonsky/datascript

## Technical Decision Rationale

I have chosen to use **Rum** as it is:
* arguably much cleaner than any other React wrapper or React itself 
* it is very small in size so it is something I can learn as someone new to programming 
* it was the only option at the time that provided Server Side Rendering (I believe this is still mostly true, the solution is also elegent)
* it doesn't impose any opinions on how you keep your state
* it works with other CLJS wrapper libraries so I can reuse components if I decide to rewrite in Om Next, Reagent or Quiescent

I have chosen to use **Boot** as it is:
* advocated as the best way to do work with Clojurescript by the community
* I don't know Leiningen well enough to be confident with it
* it looked easier to learn and extend
* I would be learning more Clojure when setting up and extending my build tool
* I feel it is much easier to work with than tools such as Webpack

I have chosen to use **Garden** as it is:
* works both in Clojure and Clojurescript using a lot of CLJC files
* means I can continue learning the language instead of learning new Post/Pre Processors for CSS which has a lot of churn
* can easily be used from a pure Clojurescript side if serverside Clojurescript becomes a thing. With the help of Lumo https://github.com/anmonteiro/lumo, Mach https://github.com/juxt/mach and the experimental JS Closure Compiler from Google.



## Services used.

* **Netlify** (Hosting/Deployment) - https://www.netlify.com/

##  (Someday)
* **NetlifyCMS** (CMS) - https://www.netlifycms.org/



### Other useful third party services

* **Snipcart** (ecommerce) - https://snipcart.com/
* **AuthO** (Authentication) - https://auth0.com/
* **Webtask** (Serverless code runner) - https://webtask.io/
* **Discourse** (Forum) - http://www.discourse.org/
* **Prismic** (CMS) - https://prismic.io/
* **Contentful** (CMS) - https://www.contentful.com/
* **Algolia** (Search) - https://www.algolia.com/
* **Lunr** (Search) - https://lunrjs.com/



## Important references

### Design

* Contrast Sensitivity - http://irlen.com/what-is-irlen-syndrome/
* Accessible Colour Palette Generator - http://colorsafe.co/
* Accessible Colour Finding Tool - http://www.dasplankton.de/ContrastA/
* Readability (Literacy) Tool for Chrome - https://chrome.google.com/webstore/detail/tray-readability-tool/eccbjfaplogblgjpopbihpgfgmlgjamf?hl=en-GB
* Readability (Literacy) uses Node.js - https://chrome.google.com/webstore/detail/tray-readability-tool/eccbjfaplogblgjpopbihpgfgmlgjamf?hl=en-GB
* Colour Blindness Emulator (Mac) - https://itunes.apple.com/au/app/sim-daltonism/id693112260?mt=12
* Responsive design tool - http://material.io/resizer/

### Dev

* HTML5 Cross Browser Polyfills - https://github.com/Modernizr/Modernizr/wiki/HTML5-Cross-Browser-Polyfills
* Font Face Observer - https://github.com/bramstein/fontfaceobserver
* Global Costs of Site - https://whatdoesmysitecost.com/
* Item Properties - http://schema.org/Product
* Structured Data Testing Tool - https://search.google.com/structured-data/testing-tool
* Browser support for features - http://caniuse.com/
* Site speed tester - https://www.webpagetest.org/
* Site speed and responsiveness tester - https://testmysite.thinkwithgoogle.com/
* What my browser can do - https://whatwebcando.today/
* What goes in a head - https://github.com/joshbuchea/HEAD
* Serverless Framework - https://serverless.com/
* Google AI API's - https://cloud.google.com/products/machine-learning/
* DNS Perf - http://www.dnsperf.com/
* Facts about web users worldwide - https://www.webworldwide.io/
* Google Web fonts helper - https://google-webfonts-helper.herokuapp.com/fonts/source-sans-pro?subsets=latin
* Device metrics - https://material.io/devices/
* Progressive Web App and Performance auditing - https://github.com/GoogleChrome/lighthouse
* SVG optimizer (node) - https://github.com/svg/svgo
* SVG optimizer (online) - https://jakearchibald.github.io/svgomg/
* Video and audio command line editor - https://ffmpeg.org/

## Notes for those critiquing my process
* I am still a very new developer. I love to spend time in the field of design and find the intersectionality of these two fields very interesting with the amount that they overlap. My Git commits and messages are likely all over the shop as I am still learning the process of using Git and only use it at present as a safety net for my learning.


## Gratitude Journal

* **Andre R**. - Helping me for a very long time to use his Macro to create atomic styles.
* **Alan Dipert** - Helping me for a very long time to render the html pages through Boot filesets.
* **Eric Normand** - My clojure mentor. Helping me get things kicked off.
* All creators and contributors of libraries I have used and learnt from.
