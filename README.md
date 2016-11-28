# Vegan Business Network (Redesign)
This is the next iteration of the Vegan Business Network website. https://www.veganbusinessnetwork.org/

Developed to be hosted on Netlify using the JAM stack (Javascript, API's and Markup).
You can learn more about the JAM stack here: https://jamstack.org/

My intention of this project is to create a foundation. Making it possible to get off the ground quickly for the most basic of websites instead of defaulting to Wordpress or similar options. That still allows incremental changes to become a single page application without sacrificing productivity or performance.

## Design goals (no order)
* is usable and looks clean on all devices from a _watch_ to a _tv_. 
* is accessible & usable in a delightful way by the blind, colour blind, contrast sensitive, people with poor literacy skills and poor motor skills
* is fast, meaning the site is downloaded and usable within 2 seconds never more than 3 seconds
* is semantic for search engines and future AI assistants

## Current Status

I have been working on ensuring I have a good foundation to build upon. Therefore the design elements such as colours, the logo and all other visual elements which do not affect layout are incomplete.

Typography has been made fluid so that the font continually changes size based on viewport width between a large range of mobile devices. The rest of the layout is relative to the font size, parent container or the viewport.

I am still figuring out how to use Devcards as a way to establish a pattern library. Similar to what has been implemented at Pattern Lab http://patternlab.io/

To view current status enter this command in the root folder `boot dev`

##Tools used.

* **Boot + various tasks** (Build tool) - http://boot-clj.com/
* **Rum** (React wrapper which can do SSR) - https://github.com/tonsky/rum
* **Garden** (CSS) - https://github.com/noprompt/garden
* **Bidi** (Routing) - https://github.com/juxt/bidi
* **Devcards** (Visual Dev tool) - https://github.com/bhauman/devcards

###Future
* **Datascript** (Client side database) - https://github.com/tonsky/datascript
* **CLJS-CSS** (CSS Modules) - https://github.com/matthieu-beteille/cljs-css-modules

## Services used. (Soon)

* **Netlify** - https://www.netlify.com/
* **Prismic** - https://prismic.io/









