
# Applicant Qualification Program

By: Maddy Muchnikoff

CS& 141 

## So What Does This Program Do?

This applicant qualification program assesses an one’s financial capability of affording a rental property by collecting their personal and financial details and computing a score based on their debt-to-income ratio along with associated property costs. The program prompts the user for their first and last name, monthly gross income and debt, pet ownership status, and property of interest. The program then retrieves the property's details from a property file (intended to be updated with available properties), including rent, utility costs (also from respective home type files), yard care, and extra costs. It then computes the applicant’s overall monthly expenses and financial standing relative to their income and desired home. A final score is then determined, which represents the applicant’s ability to comfortably afford the property.

The program also offers the option to create a detailed report that delves more into each aspect of the score. If requested, it creates a text file that takes on the first and last name of the applicant followed by “score report” for ease of file differentiation. The report summarizes key financial factors such as the applicant's debt-to-income, total monthly expenses, individual monthly expenses, leftover income, and pet status compared to that of the property (not included in score but purely for manager/ owner reference). Various helper methods are included toward the end to format the report, determine the utility file name, format doubles to represent currency, etc. The methods of the class fetch property information, calculate utility costs, and determine yard care costs.The program is repeatedly structured with try-catch to ensure potential errors in user input and file operations are handled appropriately, allowing for corrections of improper input, on behalf of the user and information, to identify and correct problems that may occur.

## How to Use

To use, open the repository with GitHub desktop. It may be used with any IDE compatible with Java. Once compiled and executed, it will prompt the user for input such as “Please provide the applicant’s first name:” If incorrect input is presented to the program, such as a word instead of a number for income, a message detailing the problem is printed and the user may try again. You will only need to enter the applicant's first and last name, their monthly gross income and debt, whether or not they have pets, and the property they want to rent. Once this is done, a score is provided with the range of (GREAT, GOOD, FAIR, POOR). The user will then be asked if they would like a more detailed report created. If yes is entered, the report is created in the repository zone and the closing message is printed. If no is entered, just the closing message is printed. 

## (Known) Limitations

Numbers included in the applicant’s name will not be flagged and will be used as a “name.” If this occurs unwantedly, the program would have to be closed and re-run. Additionally, if a negative value is entered for income or debt, the program will not throw an error message, but it will make you go down a line and keep entering until a non negative number is received. This may look as if the negative numbers are being computed, but they are simply stuck there on the screen without a message explaining why. 

## TXT Files

![image](https://github.com/user-attachments/assets/4d252c09-700b-496d-92e7-c4f7864142b1)
![image](https://github.com/user-attachments/assets/886be81f-73a5-45e8-aa7d-1e9870d9ae2c)
![image](https://github.com/user-attachments/assets/577d87ce-055a-4b8f-983d-d6cbbcfa4b0b)
![image](https://github.com/user-attachments/assets/315ba879-ba46-4566-9922-c6d74940862b)
![image](https://github.com/user-attachments/assets/63d5b2c7-5a87-4bb2-960b-bbb1db19a555)

## Example 1

![image](https://github.com/user-attachments/assets/90560876-ee0d-480d-99a8-ef7019fa9b84)
![image](https://github.com/user-attachments/assets/f24e3cef-a1b3-40c0-9954-603812a4ae08)

## Example 2

![image](https://github.com/user-attachments/assets/ed0a32f3-1f6b-49d6-8aba-eb1f41475020)
![image](https://github.com/user-attachments/assets/89dbd0bb-7a46-4dc8-9790-ea9cf85e1a1a)

## Example 3
![image](https://github.com/user-attachments/assets/e1291b63-f631-4bc0-8045-9e49cf2f473d)
![image](https://github.com/user-attachments/assets/f84028fb-c273-4059-a822-dea9b1b5b026)

## Software Design and Repository

(ApplicantChecker.java)

The main method starts with printing a little banner to the screen with the name of the program and a little text art of a house and stick people. Below, messages are printed to the screen that prompt the user to enter different types of applicant data. Scanner is used to store these inputs into the appropriate types, checking for invalid input and prompting for reentry if given. A new object for debt to income is created that takes in the previously stored gross income and debt variables. When prompted for if they have pets, the program makes sure the input is a yes or no answer. Below, a Property object is created that becomes the user input property which is then sent to be drawn out of the property.txt file by use of the propertyInfo method. Double variables are created to store the method returned utilities, yard cost, expenses, and leftover funds. A double is also created to add all these up into the total expense, plugged into a method that returns one of 4 scores, and is returned in a score statement. Finally, the use is prompted for whether or not they want a report, and if yes, the necessary variables are plugged into the call to printReport and a message tells the user what the file name for the report is (made of the applicant's first and last name.” 

The propertyInfo method utilizes a file for properties to read line by line with the scanner. A new property array is created with a length of the amount of properties in the file. The scanner is “reset” and it loops through the properties file, assigning each Property instance variable to the next value of  each Property. However, iff the name of one of the properties matches the requested property, the index is recorded and called upon below to have that particular property as the one to use. A message is printed if no matching property is found. There is also a try catch for if the properties file was not found and other exceptions. Null is returned by default.

The addUtils method uses the above found property to pull out each of its utilities with the Property eachUtil getter method. A scanner then parses this String of utilities for use in the below while loop that continues so long as there is a nex token. Inside a try catch for exceptions, variables are created for each token of the utility String and plugged into the utilInfo method. What is returned is then added up into a double variable and returned.
The utilInfo methods are overloaded and take different parameters. For the first and main of these methods, itt accepts the property, utility type, and the energy type or city as some utilities such as water have costs that vary based on location instead of energy. To begin, if the energy type or utilType (in the case of water or sewer) is marked as Owner, a cost of 0 is returned because that signifies itt is covered by the owner and not included in the cost. If not, The property type is grabbed and plugged into the homeType method to determine which utility file the method will use for the property. A scanner goes through that utility file and is used in the below while loop that continues while there is a next line and the boolean stop is still equal to false. Inside the while loop, a temp variable is created for that particular line and a scanner is created for that line. If the next token is equal to the utility type, the next conditional is entered. If the next token is equal to its energy or city, a for loop is entered. The for loop keeps scanning the following int tokens until the number of token it has scanned is equal to the bedroom count of the home. Once it is found, stop becomes true and the nested loop is exited. There are try catches for the utility file not being found and others. 

The next utilInfo method is pretty much the same except that it doesn’t check for two String values before the costs because there are none for garbage and appliances (blame Kitsap housing not me). 
The yardCareCost method accepts the property and gets the acres instance variable value to compare with different size ranges in else if statements. The cost variable is set to the cost associated with the acreage range. If the acreage exceeds 1, there is an extra cost added to the 1 acre cost per rounded extra acre.

moneyUse accepts all the costs (debt to income, utilities, yard care, rent, and extra) and adds them together. This total amount is then converted to a percentage value and returned. 

leftOver draws upon the total expenses but also the debt to income object, taking the gross income from it and subtracting the expenses.

returnScore uses the money use percentage to get an enum score from that is turned into a string (getName()). 

printReport simply creates a new file and prints to that file a more detailed range of information than a single worded score. It was also pretty useful for catching small computation errors. It catches errors with the file creation.

Format and formBreaker are little methods that simply help to reduce formatting redundancy for currency and breakers in the report file. 

At last! I at first used else ifs for the homeType methods, but determined it would be a perfect spot for switch. The homeType String is turned into the word of the switch case upon which the input String matches and is returned.

(Property.java)

This Property object contains private instance variables for each aspect of a potential property for rent. A default constructor and a parameterized constructor are made. The parameterized constructor calls upon the setter methods for each variable. The setters check for improper values and are set to a new value if given one. Ex: rent cannot be negative as much as I’d like it to be. The one for setUtils being a variable is set to private and creates an empty array if input is null. There are also getters for each instance variable of the Property. There is also a method for relaying each utility as a String for functionality in the program that went into play up above. The toString also lays out all of the aspects nicely and even includes a key for the utility types above them being printed with the eachUtil method!

(DebtToIncome.java)

A shorter object, the DebtToIncome object has instance variables off income, debt, and ratio. For the constructors, ratio is not a parameter but is instead gathered from the setRation method internally. The setRatio is created from dividing the set debt by the set income. The setIncome and setDebt also check for improper input (negatives). Below the getters, the twoString simply returns the ratio. 

(Score.java)

This enum lays out four possible scores (GREAT, GOOD, FAIR, and POOR). The score is determined by the getScore method below that accepts a double input for the applicant usage percentage. The score within the proper range is returned with POOR being the default. 

(Repository)

In addition to the above classes, my repository contains txt documents for lists of properties, mobile home utilities, single family home utilities, apartment utilities, and multi family utilities. 

## Citations, Challenges, and Other

https://www.youtube.com/watch?v=kfkh0KMZyGg - Shows how to create a house from the keyboard (used in my program header)

![image](https://github.com/user-attachments/assets/ae49fbd0-ec53-4ed8-b573-12fc8db4d95e)

- Used to convert the enum score into a String so it could be returned for printing

https://www.youtube.com/watch?v=Om3qzMoaIUo - For the switch used in homeType()

One of my biggest sources was my amazing manager Bry Yeager providing me with utility info sheets from the U.S Department of Housing and Urban Development for Bremerton & Kitsap County. She also assisted in providing some ideas for what the report should include and allowed me to take this class :)

For challenges, the code itself was not so bad but writing this README and making it more readable/ including comments was a challenge. I also ran out of time to add a GUI due to work getting busier and wanting to prioritize function. 

I’m sorry I don’t have more to list but I really wanted to avoid as much citation needing to be done as possible so I mainly utilized the textbook and recorded lectures. 

Thank you! - Maddy 

