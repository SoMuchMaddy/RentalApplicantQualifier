//This class represents a property as an object. Helps to organize attributes and processing

import java.util.*;

public class Property {
	
	//Instance variables for attributes
	private String name;
	private String type;
	private int bedrooms;
	private String city;
	private double rent;
	private String yard;
	private String pets;
	private double extra;
	private double acres;
	private String[] utils;
	
	//Default contructor to initialize  a property with default values
	public Property() {
		
		//Calls the constructor with paramters with the below values
		this("", "", 0, "", 0, "", "", 0, 0.0, null);
	}
	
	//Parameterized constructor that initializes a property with provided values
	public Property(String name, String type, int bedrooms, String city, 
	double rent, String yard, String pets, double extra, double acres, String[] utils) {
		
		//Uses setters to ensure they are valid
		setName(name);
		setType(type);
		setBedrooms(bedrooms);
		setCity(city);
		setRent(rent);
		setYard(yard);
		setPets(pets);
		setExtra(extra);
		setAcres(acres);
		setUtils(utils);
	}
	
	//Setter for name, sets to none to signify null input
	public void setName(String name) {
		if (name == null) {
			
			this.name = "none";
		}
		this.name = name;
	}
	
	//Setter for type, sets to none to signify null input
	public void setType(String type) {
		if (type == null) {
			
			this.type = "none";
		}
		this.type = type;
	}
	
	//Setter for bedroom count, cannot be negative so if negative, sets to zero
	public void setBedrooms(int bedrooms) {
		if (bedrooms < 0) {
			
			this.bedrooms = 0;
		}
		this.bedrooms = bedrooms;
	}
	
	//Setter for city, sets to none to signify null input
	public void setCity(String city) {
		if (city == null) {
			
			this.city = "none";
		}
		this.city = city;
	}
	
	//Setter for rent, cannot logically be negative so if negative, sets to zero
	public void setRent(double rent) {
		if (rent < 0) {
			
			this.rent = 0;
		}
		this.rent = rent;
	}
	
	//Setter for yard, sets to none to signify null input
	public void setYard(String yard) {
		if (yard == null) {
			
			this.yard = "none";
		}
		this.yard = yard;
	}
	
	//Setter for pets, sets to none to signify null input
	public void setPets(String pets) {
		if (pets == null) {
			
			this.pets = "n/a";
		}
		this.pets = pets;
	}
	
	//Setter for extra costs, cannot logically be negative so if negative, sets to zero
	public void setExtra(double extra) {
		if (extra < 0) {
			
			this.extra = 0;
		}
		this.extra = extra;
	}
	
	//Setter for acreage, cannot logically be negative so if negative, sets to zero
	public void setAcres(double acres) {
		if (acres < 0) {
			
			this.acres = 0;
		}
		this.acres = acres;
	}
	
	//Setter for utilities, sets empty array if input is null
	private void setUtils(String[] utils) {
		if(utils == null) {
			utils = new String[0];
			//Default empty array
		}
		this.utils = utils;
	}
	
	//Getter for prop name
	public String getName() {
		return name;
	}
	
	//Getter for home type
	public String getType() {
		return type;
	}
	
	//Getter for bedroom count
	public int getBedrooms() {
		return bedrooms;
	}
	
	//Getter for prop city
	public String getCity() {
		return city;
	}
	
	//Getter for rent price
	public double getRent() {
		return rent;
	}
	
	//Getter for if there is a yard
	public String getYard() {
		return yard;
	}
	
	//Getter foor if pets are allowed
	public String getPets() {
		return pets;
	}
	
	//Getter for extra costs
	public double getExtra() {
		return extra;
	}
	
	//Getter for property acreage
	public double getAcres() {
		return acres;
	}
	
	//Getter for the array of utilities
	public String[] getUtils() {
		//Returns a copy of the array so as not to compromise it 
		return Arrays.copyOf(utils, utils.length);
	}
	
	//Method tthat helps to display each utility seperated by a space
	public String eachUtil() {
		
		String str = "";
		for (String util : utils) {
			str += " " + util;
		}
		return str;
	}
	
	//Method to return a formatted String for the property with details laid out nicely
	//Also includes the util types above the energy sources for each (improves readability)
	public String toString() {
		return "Name: " + name + "\n" + "Type: " + type + "\n" + "Bedrooms: " + bedrooms +
		"\n" + "City: " + city + "\n" + "Rent: $"
		+ rent + "\n" + "Yard: " + yard + "\n" + "Pets: " + pets + "\n" + 
		"Extra: " + extra + "\n" + "Acres: " + acres + "\n" +
		"Util Types: Heat  Hot Water  Cooking  Water  Sewer  Garbage" + "\n" +
		"Utils: " + eachUtil() + "\n"; 
		//Calls the each util method to lay out all of the utils nicely not in array format with brackets
	}
}
