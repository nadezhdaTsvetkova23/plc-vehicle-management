package def;

public class Car extends Vehicle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int yearOfLastInspection;

//	public Car(String brand, String model, int yearBuild, double basePrice, long id) {
//		super(brand, model, yearBuild, basePrice, id);
//		// TODO Auto-generated constructor stub
//	}

	public Car(String brand, String model, int yearBuild, double basePrice, int id, int yearOfLastInspection) {
		super(brand, model, yearBuild, basePrice, id);
		if(yearOfLastInspection > thisYear || yearOfLastInspection < yearBuild)
			throw new IllegalArgumentException("Error: Inspection year invalid.");
		this.yearOfLastInspection = yearOfLastInspection;
	}

	@Override
	public double getDiscount() { // in %
		double discount = this.getAge() * 5 + (thisYear - this.yearOfLastInspection) * 2; //-1?
		if (discount > 15)
			discount = 15;
		double discountValue = this.getBasePrice()*discount/100;
		return discountValue;
	}

}
