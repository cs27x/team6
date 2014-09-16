#Assignment 1 Spec

##Overview
Create an application to find the nearest place to buy beer and offer a place to
store your favorite beers that were available at that place.

This should use the Dataset available at <a href="https://data.nashville.gov/dataset/Beer-Permit-Locations/3wb6-xy3j">
Beer Permit Locations</a> for the city of Nashville and be implemented as a mobile application.

##User Stories
0. The user should open the application and view a list of the closest locations to get beer.
1. The user should be able to filter by places to buy beer for consumption in the location vs. to go beer.
2. The user should be able to view a map of closest places to buy beer
  - This can be accomplished by clicking list view to launch google maps.
3. The user should be able to log the types of beer that are sold at a given location by typing in the beer.
  - Initially stored locally, so it is available only to users, but could be extended in the future.
4. The user should be able to remove the beer from under a given store.
5. A user can store multiple beers under a given store.
6. The user can search for a place and find the information they have saved about the place.

##Possibly Helpful Technical Methods
0. To access the JSON api for the Beer Permit Locations use the endpoint <a href="http://data.nashville.gov/resource/3wb6-xy3j.json">http://data.nashville.gov/resource/3wb6-xy3j.json</a>
1. If you are unfamiliar with the Javascript Object Notation format that data.nashville.gov
   can provide data in, you can read this tutorial:
   http://www.copterlabs.com/blog/json-what-it-is-how-it-works-how-to-use-it/
2. For other helpful information on working with JSON objects, see the example assignment spec <a href="https://raw.githubusercontent.com/cs27x/cs278_2014/master/assignments/asgn1/Example.md">here</a>
3. The API docs for the Nashville Open Data api can be found <a href="http://dev.socrata.com/docs/filtering.html">here</a>

##Evaluation Rubric
