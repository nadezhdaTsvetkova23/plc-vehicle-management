/**
 * @author Nadezhda Tsvetkova
 * @id 11942924
 */
import java.text.DecimalFormat;

public class Car extends Vehicle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int yearOfLastInspection;

	public Car(int id, String brand, String model, int yearBuild, double basePrice, int yearOfLastInspection) {
		super(id, brand, model, yearBuild, basePrice);
		if (yearOfLastInspection > thisYear || yearOfLastInspection < yearBuild)
			throw new IllegalArgumentException("Error: Inspection year invalid.");
		this.yearOfLastInspection = yearOfLastInspection;
	}

	@Override
	public double getDiscount() { // in %
		double discount = this.getAge() * 5 + (thisYear - this.yearOfLastInspection) * 2; // -1?
		if (discount > 15)
			discount = 15;
		double discountValue = this.getBasePrice() * discount / 100;
		return discountValue;
	}
	
	@Override
	public String toString() {
		DecimalFormat d = Vehicle.getDecimalFormat();
		return "Type:       Car" +
                super.toString() + "\n" +
                "Inspection: " + this.yearOfLastInspection + "\n" +
                "Base price: " + d.format(getBasePrice()) + "\n" +
                "Price:      " + d.format(getPrice());
	}

}
