/**
 * @author Nadezhda Tsvetkova
 * @id 11942924
 */


import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public abstract class Vehicle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String brand;
	private String model;
	private int yearBuild;
	private double basePrice;
	private int id;

	public static final int thisYear = 2022;

	public Vehicle(int id, String brand, String model, int yearBuild, double basePrice) {
		if (brand.isEmpty() || model.isEmpty())
			throw new IllegalArgumentException("Error: Invalid parameter.");
		this.brand = brand;
		this.model = model;
		if (yearBuild > thisYear || yearBuild <= 1900)
			throw new IllegalArgumentException("Error: Year built invalid.");
		this.yearBuild = yearBuild;
		if (basePrice <= 0)
			throw new IllegalArgumentException("Error: Base price invalid.");
		this.basePrice = basePrice;
		this.id = id;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public int getAge() {
		return thisYear - yearBuild;
	}

	public double getPrice() {
		return basePrice - getDiscount();
	}

	public int getId() {
		return this.id;
	}

	@Override
	public String toString() {
		DecimalFormat d = Vehicle.getDecimalFormat();
		return "\n" +
                "Id:         " + this.id + "\n" +
                "Brand:      " + this.brand + "\n" +
                "Model:      " + this.model + "\n" +
                "Year:       " + this.yearBuild;
	}

	public abstract double getDiscount();

	public static DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols d = DecimalFormatSymbols.getInstance();
		d.setDecimalSeparator('.');
		return new DecimalFormat("0.00", d);
	}
}
