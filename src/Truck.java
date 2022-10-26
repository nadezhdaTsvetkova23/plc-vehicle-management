/**
 * @author Nadezhda Tsvetkova
 * @id 11942924
 */
import java.text.DecimalFormat;

public class Truck extends Vehicle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Truck(int id, String brand, String model, int yearBuild, double basePrice) {
		super(id, brand, model, yearBuild, basePrice);
	}

	@Override
	public double getDiscount() {
		double discount = this.getAge() * 5;
		if (discount > 20)
			discount = 20;
		double discountValue = this.getBasePrice() * discount / 100;
		return discountValue;

	}

	@Override
	public String toString() {
		DecimalFormat d = Vehicle.getDecimalFormat();
		return "Type:       Truck" +
        super.toString() + "\n" +
        "Base price: " + d.format(super.getBasePrice()) + "\n" +
        "Price:      " + d.format(super.getPrice());                
	}

}
