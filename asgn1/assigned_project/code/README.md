Create Data structures on first run
	- Restaurant
		- Private instance variables
			- Name
			- On/Off Campus
			- Meal plan/money
			- Lat
			- Long.
			- Hours
				- Array of open and close times for each week
			- Type of food
		- Public Methods
			- isOpen
				- No arguments returns if its opened now
				- With a datetime as an argument it will return if it will be open at that time
			- isMealPlan
			- distanceToLocation(GPS lat and longitude)
			- hasDelivery
			- isOnCampus
			- typeOfFood
			- getHours
			- timeToClose


	- All the fields should be populated from local database.
	- RestaurantList
		- List of all the available restaurants that are created when the application is launched
		- Singleton class that should be accessible from anywhere within the application
		- Has methods for getting Restaurants with the different sort and filters
			- Sort
				- Distance from current location
					- Use the Latitude and longitude stored for each restaurant and compare this to current location
					- Return a sorted list of the restaurants
				- Sort by time to close
			- Filters
				- Open
				- On meal plan
				- Food type
				- On Campus


2 - Database
	Create a Restaurant and RestaurantList classes w/ unit tests
	Figure out how to persist this over running the app


2 - Android layout  - Justin
	List view
		Displays the meal status, the open status, and distance in list view for each element in the list
	Detail View
		Displays:
			Name
			Hours
			Distance to current location
			Food Type
			Payment Options (if it is meal plan and meal money or just meal money)

	Search for Open at a given time

1 - GPS access location
	1. Figure out android API to get current location
	2. Android api to figure out distance between two locations
	3. Work with storage team to create the sort by location method

1. I want to be able to see, at a glance, the closest places where I can use my meal plan.
2. I would like to be able to filter (on/off-campus, meal plan/money, open now, type of food, delivery optionÉ) and sort (distance, price, closing soonÉ) a list of Vanderbilt dining locations.
3. As someone who doesnÕt like cafeteria food, I would like to know which restaurants are on the vandy card.
4. As a busy student, I would like to check what food delivery options are open.
5. As a new student, I want an easy way to determine what my dining options are.
6. As someone who tends to eat late dinners, I want to know what dining locations are available at when I check the app late at night (ex. 10pm).
7. As someone who likes to plan their day ahead of time, I would like to look up when food locations will be open at a certain later date/time.
