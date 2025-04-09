//This class represents debt to income as an object. It calculatyes and manages opne's 
//debt to income radtio as well as the individual aspects and other ways
//oof representing it 

import java.util.*;

public class DebtToIncome {
	
	
	//Instance variables for storage of the income, debt, and the ratio created from the former two
	private double income;
	private double debt;
	private double ratio;
	
	//Default constructor that initualizes the debt and income to 0.0 using setter methods
	public DebtToIncome() {
		
		setIncome(0.0);
		setDebt(0.0);
		setRatio(income, debt);
		//Sets the ratio instance variable with the income and debt values
	}
	
	//Constructor that accepts income and debt and uses those values to compute the ratio
	public DebtToIncome(double income, double debt) {
		
		setIncome(income);
		setDebt(debt);
		setRatio(income, debt);
	}
	
	//Computes the percentage representation of the debt to income 
	public void setRatio(double income, double debt) {
		
		ratio = debt / income * 100;
	}
	
	//Setter for gross income, if income is less than 0 (big yikes if possible), sets to 0
	public void setIncome(double income) {
		if (income < 0) {
			this.income = 0.0;
		}
		this.income = income;
	}
	
	//Setter for debt, if debt is below 0 (that would be wonderful), sets to 0
	public void setDebt(double debt) {
		if (debt < 0) {
			this.debt = 0.0;
		}
		this.debt = debt;
	}
	
	//Getter for income
	public double getIncome() {
		
		return income;
	}
	
	//Getter for debt
	public double getDebt() {
		
		return debt;
	}
	
	//Returns the money left over after the debt is taken out of the gross income
	public double getLeftover() {
		
		return income - debt;
	}
	
	//Getter for debt to income ratio
	public double getRatio() {
		
		return ratio;
	}
	
	//Returns a formatted string with the debt to income rati0 expressed as a percentage with two decimal places
	public String toString() {
		
		return "Debt to income ratio is: " + String.format("%.2f", ratio) + "%";
	}
	
}
