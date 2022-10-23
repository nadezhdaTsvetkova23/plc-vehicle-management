package def;

public class Truck extends Vehicle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Truck(String brand, String model, int yearBuild, double basePrice, long id) {
		super(brand, model, yearBuild, basePrice, id);
	}

	@Override
	public double getDiscount() {
		double discount = this.getAge() * 5;
		if (discount > 20)
			discount = 20;
		double discountValue = this.getBasePrice()*discount/100;
		return discountValue;
		
	}

}
