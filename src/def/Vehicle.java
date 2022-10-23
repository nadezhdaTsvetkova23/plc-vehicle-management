package def;

import java.io.Serializable;

public abstract class Vehicle implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String brand;
	private String model;
	private int yearBuild;
	private double basePrice;
	private long id;
	
	public static final int thisYear = 2022;
	
	public Vehicle(String brand, String model, int yearBuild, double basePrice, long id) {
		if(brand.isEmpty() || model.isEmpty())
			throw new IllegalArgumentException("Error: Invalid parameter.");
		this.brand = brand;
		this.model = model;
		if(yearBuild > thisYear || yearBuild <= 1900)
			throw new IllegalArgumentException("Error: Year built invalid.");
		this.yearBuild = yearBuild;
		if(basePrice <= 0)
			throw new IllegalArgumentException("Error: Base price invalid.");
		this.basePrice = basePrice;
		this.id = id;
	}
	
//	public Vehicle() {
//		// TODO Auto-generated constructor stub
//	}

	public double getBasePrice() {
		return basePrice;
	}
	
	public int getAge() {
		return thisYear - yearBuild;
	}
	
	public double getPrice() {
		return basePrice - getDiscount();
	}
	
	public abstract double getDiscount();
}
