//This program accepts user input applicant information and 
//computes a score/ qualifcation report

import java.util.*;
import java.io.*;

public class ApplicantChecker {
	
	void main(){
		
		Scanner console = new Scanner(System.in);
			
			//Prints a silly little house and some silly little people
			for (int i = 0; i <= 45; i++) {
			System.out.print("=");
			}
			System.out.println();
			System.out.println("WELCOME TO THE APPLICANT QUALIFICATION CHECKER");
			System.out.println();
			System.out.println("         o          ,___,          o           ");
			System.out.println("        /|\\        /_\\__\\         /|\\         ");
			System.out.println("        / \\        |_|__|         / \\         ");
			System.out.println();
			for (int i = 0; i <= 45; i++) {
			System.out.print("=");
			}
			System.out.println();
			System.out.println();
			
			//Collect applicant first and last name
			System.out.println("Please provide the applicant's first name:");
			String firstName = console.next();
			System.out.println();
			System.out.println("Please provide the applicant's last name:");
			String lastName = console.next();
			System.out.println();
			
			//collects applicant income and ensures it is a numeric value
			double grossIncome = 0.0;
			System.out.println("Please provide the applicant's monthly gross income:");
			while (grossIncome <= 0) {
				if (console.hasNextDouble()) {
					grossIncome = console.nextDouble();
					System.out.println();
				} else {
					System.out.println("Please enter a numeric value.");
					console.next();
				}
			}
			
			//Collects applicant debt and ensures it is a numeric value
			double debt = -1;
			System.out.println("Please provide the applicant's monthly debt payment:");
			while (debt < 0) {
				if (console.hasNextDouble()) {
					debt = console.nextDouble();
					System.out.println();
				} else {
					System.out.println("Please enter a numeric value.");
					console.next();
				}
			}
			
			//Creates new object for applicant debt to income ratio using above input
			DebtToIncome ratio = new DebtToIncome(grossIncome, debt);
			
			//Checks if applicant has pets and ensures a yes or no response
			System.out.println("Does applicant have pets? Please enter yes or no:");
			String pets = "";
			while (!(pets.toLowerCase().equals("yes") || pets.toLowerCase().equals("no"))) {
				if (console.hasNext()) {
					pets = console.next();
					System.out.println();
				}
				if (!(pets.toLowerCase().equals("yes") || pets.toLowerCase().equals("no"))) {
					System.out.println("Please enter yes or no.");
				}
			}
			
			//Prompts for the property interested and makes sure it is valid
			System.out.println("Please enter the property name:");
			Property inQuestion = null;
			while (inQuestion == null) {
				try {
					String request = console.next();
					System.out.println();
					inQuestion = propertyInfo(request);
				} catch (IllegalArgumentException iae) {
					System.out.println("An error has occured with the requested property's info");
				}
			}
			
			//Collects expense
			double utils = addUtils(inQuestion);
			double yardCost = yardCareCost(inQuestion);
			double expenses = utils + yardCost + inQuestion.getRent() + inQuestion.getExtra() + debt;
			double leftover = leftOver(expenses, ratio);
			
			//Calculates total expense for property in consideration of income and debt
			double usage = moneyUse(ratio, utils, yardCost, inQuestion.getRent(), inQuestion.getExtra());
			
			//Prints the score
			System.out.println(firstName + "'s score is: " + returnScore(usage));
			System.out.println();
			
			//Generates a report if prompted to do so
			System.out.println("Would you like a report?:");
			String response = console.next();
			if (response.toLowerCase().equals("yes")) {
			
				String score = returnScore(usage);
				printReport(firstName, lastName, score, inQuestion, usage, utils, yardCost, ratio, leftover, pets);
				System.out.println();
				System.out.println("Please check file for " + firstName.toLowerCase() + lastName.toLowerCase() + "scorereport.txt");
				System.out.println();
			}
			
			//Closing message
			System.out.println();
			System.out.println("Thank you for using the Applicant Qualification Checker!");
			
	}
	
	//This method gathers info on all properties in file
	public static Property propertyInfo(String request) {
		
		//Bringhs in a file for the available properties
		File properties = new File("properties.txt");
		
		//Makes sure the file exists
		if (!properties.canRead()) {
			System.out.println("Property file was not found");
			return null;
		}
		
		//Process the file and extract organized info for each property
		try{ 
			Scanner scan = new Scanner(properties);
			int n = 0;
			//Checks for how many lines of propeties are in file (one prop per line)
			while(scan.hasNextLine()) {
				n++;
				scan.nextLine();
			}
			
			//Creates an array for all properties in file
			Property[] propertyInfo = new Property[n];
		
			scan = new Scanner(properties);
			
			int myProperty = -1;
			for (int i = 0; i < n; i++) {
			
				String name = scan.next();
				//Checks if the name of the current property read matches the requested property
				//Keeps track of location of the one that does (if any))
				if (name.equals(request)) {
					myProperty = i;
				}
				//Reads other attributes
				String type = scan.next();
				int bedrooms = scan.nextInt();
				String city = scan.next();
				double rent = scan.nextDouble();
				String yard = scan.next();
				String pets = scan.next();
				double extra = scan.nextInt();
				double acres = scan.nextDouble();
				String[] utils = scan.nextLine().trim().split(" "); //Read utilities as an array
				//Adds each property + attributtes to the array of properties
				propertyInfo[i] = new Property(name, type, bedrooms, city, rent, yard, pets, extra, acres, utils);
		
			}
			
			//If the value is unchanges (no match found), prints message and returns null to signal no success
			if (myProperty == -1) {
				System.out.println("Unable to find requested property in file");
				return null;
			}
			
			//Returns requested property
			return propertyInfo[myProperty];
			
		//catches possible exceptions for resilience to invalid input
		} catch (FileNotFoundException fnfe) {
			System.out.println("The property file was not found");
		} catch (Exception e) {
			System.out.println("An unexpected error occured when attempting to locate property file");
		}
		
		
		return null;
	}
	
	//Combines the utility cost for all tenant responsible utils per property
	public static double addUtils(Property property) {
		
		//Retrieve property utils
		String eachUtil = property.eachUtil();
		Scanner scan = new Scanner(eachUtil);
		double totalUtilityCost = 0.0;
		
		//Process each utility costs from the input
		while (scan.hasNext()) {
			
			try{
			//String objectys for each util type
			String heat = scan.next();
			String cooking = scan.next();
			String hotWater = scan.next();
			String water = scan.next();
			String sewer = scan.next();
			String garbage = scan.next();
			
			//Utilizes utilInfo method to calculate the property's coost for each util type and energy/location
			totalUtilityCost += utilInfo(property, "Heat", heat, property.getBedrooms());
			totalUtilityCost += utilInfo(property, "Cooking", cooking, property.getBedrooms());
			totalUtilityCost += utilInfo(property, "HotWater", hotWater, property.getBedrooms());
			totalUtilityCost += utilInfo(property, water, property.getCity(), property.getBedrooms());
			totalUtilityCost += utilInfo(property, sewer, property.getCity(), property.getBedrooms());
			totalUtilityCost += utilInfo(property, garbage, property.getBedrooms());
			totalUtilityCost += utilInfo(property, "Appliances", property.getBedrooms());
			
			//To handle exceptions whilst processing
			} catch (Exception e) {
				System.out.println("An unknown error occured with utility data.");
			}
		}
		
		return totalUtilityCost;
	}
	
	//Delves into necessry utility file to grab the cost for each util type according to energy,
	//location, and bedrooms
	public static double utilInfo(Property property, String utilType, String energyOrCity, int bedrooms) {
		
		//If the utility is covered by owner, the cost would be zero
		if (energyOrCity.equals("Owner") || utilType.equals("Owner")) {
			
			return 0;
		}
		
		//Find the type of home (multi, single, mobile, apartment)
		String type = property.getType();
		//Calls method to simplify converting the file's home type to the utility file hometype name
		String homeType = homeType(type);
		double utilCost = 0;
		
		//Draws upon file for that specific home type
		File utilities = new File(homeType + "utils.txt");
		
		try {
			Scanner scan = new Scanner(utilities);
			
			boolean stop = false; 
			//Initializes to false so that scanning continues until the right info is found 
			int j = 1;
		
			while (scan.hasNextLine() && !stop) {
				//ensures there is a next line in util file
				
				String temp = scan.nextLine();
				Scanner scan2 = new Scanner(temp);
				
				//For each line of the file, checks whether the first word is the util type required (ex: heat)
				if (scan2.next().equals(utilType)) {
				
					//If the next word is the property util type's energy source or city, continue
					if (scan2.next().equals(energyOrCity)) {
						
						//Grabs the int in the following sequence that aligns with the property bedroom count (size)
						for (int i = 0; i < 4; i++) {
							
							if (bedrooms == i) {
						
								utilCost = scan2.nextInt();
							}
							
							scan2.next();
							//Moves to next token
						}
						
						//once found the loop can stop
						stop = true;
					}
				
				}
				
				j++;
			}
			
		//For if there is no utility file
		} catch (FileNotFoundException fnfe) {
			System.out.println("The " + homeType + " utility file was not found");
		//For if an unexpected exception happens
		} catch (Exception e) {
			System.out.println("An unknown error occured with utility processing");
		}
		
		return utilCost;
	}
	
	//Overloaded method for previous for use with utrl type that does not vary based on 
	//enery or location
	public static double utilInfo(Property property, String utilType, int bedrooms) {
		
		//If the utility is covered by owner, the cost would be zero
		if (utilType.equals("Owner")) {
			
			return 0;
		}
		
		//Find the type of home (multi, single, mobile, apartment)
		String type = property.getType();
		//Calls method to simplify converting the file's home type to the utility file hometype name
		String homeType = homeType(type);
		double utilCost = 0;
		
		//Draws upon file for that specific home type
		File utilities = new File(homeType + "utils.txt");
		
		try {
			Scanner scan = new Scanner(utilities); 
		
			boolean stop = false;
			//Initializes to false so that scanning continues until the right info is found 
			int j = 1;
			
			//Read through file line by lince
			while (scan.hasNextLine() && stop == false) {
				
				String temp = scan.nextLine();
				Scanner scan2 = new Scanner(temp);
				
				//Check if first word in line is the needed utility type 
				if (scan2.next().equals(utilType)) {
					
					//If yes, find the following int in the sequence that corresponds to bedroom count (size)
					for (int i = 1; i < 4; i++) {
					
						if (bedrooms == i) {
							utilCost = scan2.nextInt();
						}
						
						scan2.next();
						//Move to next value
					}
				
					stop = true;
					//Exit loop after finding cost
				}
				
				j++;
			}
			
		//For if there is no utility file
		} catch (FileNotFoundException fnfe) {
			System.out.println("The " + homeType + " utility file was not found");
		//For if an unexpected exception happens
		} catch (Exception e) {
			System.out.println("An unknown error occured with utility processing");
		}
		
		return utilCost;
	}
	
	//Calculates the cost of bi-weekly yard care based on property acreage
	public static double yardCareCost(Property property) {
	
		double acres = property.getAcres();
		//Gets the property's acreage
		double cost = 0;
		
		//Determines cost with acreage size ranges
		if (acres <= 0.125) {
			
			cost = 70;
		} else if (acres <= 0.25) {
			
			cost = 130;
		} else if (acres <= 0.50) {
			
			cost = 150;
		} else if (acres <= 1.0) {
			
			cost = 210;
		} else if (acres > 1.0) {
			
		//Calculates additional cost for properties exceeding 1 acre (Note: this is uncommon for residential rentals)
		double extraAcres = acres - 1;
		int extraCost = (int) Math.round(extraAcres) * 90; 
			
		cost = 210 + extraCost;
		}
		
		return cost;
	}
	
	//Calcutes the percentage income that goes toward total expenses
	public static double moneyUse(DebtToIncome debtToIncome, double utils, double yard, double rent, double extra) {
		
		double moneyUse = debtToIncome.getDebt() + utils + yard + rent + extra;
		double expensesRatio = (moneyUse * 100) / debtToIncome.getIncome();
		
		return expensesRatio;
	}
	
	//Shows the currency amount of income left over after expenses
	public static double leftOver(double expenses, DebtToIncome dti) {
		
		return dti.getIncome() - expenses;
	}
	
	//Determines a score based on the money use percentage using ranges seeked by property owners,
	//management companies, and lenders with a call to the Score enum
	public static String returnScore(double usage) {
		
		//Returns the enum name as a String
		return Score.getScore(usage).name();
	}
	
	//Generates a full report on aspects of an applicants qualification for an property extending beyond score
	public static void printReport(String firstName, String lastName, String score, Property property,
	 double ratio, double utils, double yardCost, DebtToIncome dtiratio, double leftover, String pets) {
		
		try{ 
			//Creates the report file to start with the applicant's name
			PrintStream output = new PrintStream(new File(firstName.toLowerCase() + lastName.toLowerCase() + "scorereport.txt"));
			
			output.println("APPLICANT QUALIFICATION REPORT");
			output.println();
			output.println("First Name: " + firstName);
			output.println("Last Name: " + lastName);
			output.println("Property Interested: " + property.getName());
			output.println();
			output.println("Score: " + score);
			formBreaker(output);
			output.println("Debt to Income Ratio: " + format(dtiratio.getRatio()) + "%");
			output.println("Gross Monthly Income: $" + format(dtiratio.getIncome()));
			output.println("Monthly Debt: $" + format(dtiratio.getDebt()));
			formBreaker(output);
			output.println("Monthly Rent: $" + format(property.getRent()));
			output.println("Monthly Utility Cost: $" + format(utils));
			output.println("Monthly Yard Care Cost: $" + format(yardCost));
			output.println("Monthly Extra Charge: $" + format(property.getExtra()));
			formBreaker(output);
			output.println("Income Left Over: $" + format(leftover));
			output.println("Expenses Percentage of Income: " + format(ratio) + "%");
			formBreaker(output);
			output.println("Applicant Has Pets?: " + pets.toUpperCase());
			output.println("Does Property Allow Pets?: " + property.getPets().toUpperCase());
			
		//A catch for the chance a probelm occurs creating the file
		} catch (FileNotFoundException fnfe) {
			System.out.println("A problem has occured creating report file");
		}
	}
	
	//Formats a number to two decimal places in order to properly represent currency
	public static String format(double number) {
		
		return String.format("%.2f", number);
	}
	
	//Consolidates the code for dividing each section of the report file (helps with readability/organization)
	public static void formBreaker(PrintStream output) {
		
		output.println();
		for (int i = 0; i <= 30; i++) {
			output.print("-");
		}
		output.println();
		output.println();
	}
	
	//Returns the corresponding util file starter for hometype when given the property type
	public static String homeType(String type) {
		
		String homeType = "";
		
		//Uses a switch statement to match the property type in file 
		switch (type) {
			
			//If home type is the String following "case", assign the below homeType String to the following String
			case "Single":
				homeType = "singlehome";
				break;
			case "Multi":
				homeType = "multifam";
				break;
			case "Apartment":
				homeType = "apartment";
				break;
			case "Mobile":
				homeType = "mobilehome";
				break;
			default:
				homeType = "none";
				break;
			}
			
		return homeType;
	}
}

