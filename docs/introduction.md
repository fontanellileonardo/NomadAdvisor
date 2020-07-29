# Project Documentation - Introduction
The system is an application that allows a customer to choose a city to visit and a hotel where he/she can stay overnight.
A customer can register and log in, after the log in he/she’s able to consult a list of cities where he/she can see many details regarding the life in the city, monthly cost of living in dollars, nightlife and so on.
He/she can also filter the list of cities by selecting the preferred characteristic.
In addition, the application shows him/her a list of hotels where he/she can stay overnight. By selecting a hotel, is possible to see the related reviews with rate and comments.
Moreover, he/she can add new reviews about hotels.
In this area he/she can also define some preferences about the characteristics of cities, and the system will use these data in order to show him/her the cities that fits with the selected characteristics.
The system has also an administration area where an employee can perform some management operations.
He/she can view all the registered customers and manage hotels and cities present in the system by selecting, adding, removing or updating them.
He can also see some statistics such as:
* A pie chart that shows the distribution of users’ preferences about cities
* A pie chart that shows the distribution of characteristic of the cities that are stored in the
system.

Thanks to these two statistics an Employee can understand the type of application user and check if the service offered reflects the request. Thanks to the latest chart an Employee can also see if there are few cities that offer certain services and possibly evaluate the expansion of the site in order to cover more characteristics and attract more users.

## Functional Requirements
The system provides a log-in/registration interface. In order to register to the system, a Customer must fill a form indicating Name, Surname, Password, Nickname and Email. These two last attributes must be unique in the system. It is assumed that only the Customer can register because the Employees are already registered.
In order to log-in to the system a Customer must fill a form indicating the email and the password chosen in the registration phase.
The system provides a customer interface that allows to search the cities. It’s possible to search by specifying one or more of these filters: Temperature, Air Quality, Quality of Life, Friendly, Healthcare, Nightlife, Cost, Safety, Walkability, Free WiFi and English Speaking. Each of these values have a score from one to five except Temperature and Cost. The system searches the cities having these values greater than the values inserted by the Customer (except for Temperature and Cost).
Temperature has four possible ranges expressed in Celsius: lower than 0, from 0 to 15, from 15 to 25 and greater than 25. Cost has a value expressed in dollars and the system search the cities having a monthly cost lower than the value specified by the customer. The system shows the first 30 cities.

At the beginning the system retrieves from the logged customer the preferences he/she inserted in his/her profile and shows the best 30 cities that satisfy the Customer preferences. In particular, the preferences refer to the city’s characteristics and the system searches the cities in which those features have a score greater than three except for Temperature and Cost. Search cities where the temperature is from 15 to 25 degrees Celsius and the monthly cost is less than 2000$.
For each city the system shows in a table the Name, the State and the set of attributes listed in the filters before. A Customer can select the city that he/she prefers, and he/she can view the hotels related to it.
In relation to the last action, the system provides an interface in which the Customer can see a table with all the hotels with their information: Name, Address and Website. This last attribute is not always available data. By selecting one of them the system provides the first 30 related reviews from the most recent one with their attributes: Username, Rating, Comment and Date. Moreover, he/she can add a review by selecting a mark and writing a comment.
The system allows to go back to the interface with the cities information so that he/she can start a new research.
The system provides the possibility to create a customized profile.
The customer can visit his/her private area, containing his/her personal information, both from Cities and Hotels Interfaces.
In this area, a customer can set/update his/her city’s preferences: Temperature, Air Quality, Quality of Life, Friendly, Healthcare, Nightlife, Cost, Safety, Walkability, Free Wi-Fi and English Speaking. For each of them the Customer can tick what he/she prefers.
The system allows to go back to the interface with the cities information so that he can start a new research.

The system provides also an Employee Interface where only him/her is able to access. Here, only he/she can see all the registered customers and the existing cities and hotels in the system. In particular, he/she can see a table showing the customers personal information: Name, Surname, Username and Email.
He/she can also see a table with cities information: Name, State and Characteristics as Temperature, Air Quality, Quality of Life, Friendly, Healthcare, Nightlife, Cost, Safety, Walkability, Free Wi-Fi and English Speaking.
The system provides a form in order to add new cities or update the existing ones. In this form, in order to add a new city, the Employee shall specify all the related fields: Name, State, Temperature, Air Quality, Quality of Life, Friendly, Healthcare, Nightlife, Cost, Safety, Walkability, Free Wi-Fi and English Speaking. We assume that the pair “Name-State” of the city is unique. When a pair of these attributes refers to an already existing one, the inserted fields in the form will be used to update the previous ones.
Alternatively, he/she can also choose to only update a city in the system. In this case, the Employee shall specify Name and State of the interested city and fill only the fields of the Characteristics that he/she wants to change.

Choosing a city, he can view all the related hotels in a table showing their information: Name, Address and Website. These two last attributes are not always available data.
The system provides a form in order to add new hotels in the selected city. In this form the Employee shall specify all the related fields: Name, Address and Website. We assume that the name of the hotel for a certain city is unique. When a hotel’s name refers to an already existing name, the inserted fields in the form will be used to update the previous one.

By selecting a row in the Cities or Hotels tables, he/she can delete one of them.

The system performs some statistics on the available data. In particular, allows to the Employees to view all the customer’s preferences in percentage thanks to a Pie Chart. The system also allows to view by a Pie Chart the number of cities that have a specific characteristic in percentage, only the cities with an attribute’s score greater than three are considered in this counting except for the Temperature and Cost. It considers cities where the temperature is from 15 to 25 degrees Celsius and the monthly cost is less than 2000$.

All the described interfaces allow to log out and come back to the login/registration interface.

## Non-Functional Requirements
The system is designed in order to be user friendly; it has a graphical interface that is simple and intuitive and guide the user to make the right choice by showing also messages in case of errors.


The system manages the passwords in a secure way because it encrypts the passwords using SHA-1 when the user inserts them, so the passwords are sent to the DB already encrypted. This is done in order to prevent sniffing and to store them already encrypted.
In addition, the system uses a specific user to connect to the DB, in this way the application has limited privileges on the DB in particular it can only modify the specific DB dedicated for the application.
Since the system uses a replica set to guarantee high availability, the database is configured to use an internal authentication protocol among replica servers to have a secure communication among them.

We retrieved the city’s data from the website https://nomadlist.com/ by scraping. Before doing this the Term of Service of the site has been read in order to be sure that it was allowed. Moreover the file https://nomadlist.com/robots.txt is empty, this imply that is allowed to do scraping/crawling.

## Data pre-processing
The datasets used to produce the data for the application are:
https://www.kaggle.com/jiashenliu/515k-hotel-reviews-data-in-europe https://www.kaggle.com/datafiniti/hotel-reviews
These are two datasets about hotel reviews. The first one contains 515k hotel reviews in the most important Europe cities, the second one contains 20k hotel reviews in different cities around the USA.
We realized a Python script to extract the data on which we were interested and to uniform the format of the two datasets.
So, we produced a JSON file for each collection that contains only the data useful for the application using the chosen format.

Then for each city in the dataset, we added information by scraping the website https://nomadlist.com/ that provides a lot of information regarding the most important cities in the world.
The scraping activity has been carried out by implementing a Python script with BeautifulSoap framework that allowed to parse the HTML documents.

The downloaded page contains the data on which we were interested on a table, so we looked for the “<tr>” tag to retrieve them.