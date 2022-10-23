package def;

import java.util.List;

public class VehicleManagement {
	private VehicleDAO vehicleDAO;
	
	public VehicleManagement(VehicleDAO v) {
		vehicleDAO = v;
	}
	
	public List<Vehicle> getAll(){
		return vehicleDAO.getVehicleList();
	}
	
	public Vehicle getSpecific(int id) {
		return vehicleDAO.getVehicle(id);
	}
	
	public void addVehicle(Vehicle v) {
		vehicleDAO.saveVehicle(v);
	}
	
	public void deleteVehicle(int id) {
		vehicleDAO.deleteVehicle(id);
	}
	
	public int totalVehicles() {
		return this.getAll().size();
	}
	
	public int totalCars() {
		int cars = 0;
		List<Vehicle> vehicles = this.getAll();
		for(Vehicle v: vehicles) {
			if(v instanceof Car)
				cars++;
		}
		return cars;
	}
	
	public int totalTrucks() {
		int trucks = 0;
		List<Vehicle> vehicles = this.getAll();
		for(Vehicle v: vehicles) {
			if(v instanceof Car)
				trucks++;
		}
		return trucks;
	}
	
//	public double meanPrice() {
//		
//	}
}
