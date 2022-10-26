

import java.text.DecimalFormat;
import java.util.ArrayList;
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
	
	public String meanPrice() {
		double sumPrice = 0;
		List<Vehicle> vehicles = this.getAll();
		for(Vehicle v: vehicles) {
			sumPrice += v.getPrice();
		}
		
		DecimalFormat d = Vehicle.getDecimalFormat();
		return d.format(sumPrice/vehicles.size());
	}
	
	public List<Integer> getOldestVehicles(){
		List<Integer> oldestIDs = new ArrayList<>();
		
		List<Vehicle> vehicles = this.getAll();
		if(vehicles.isEmpty())
			return new ArrayList<>();
		
		Vehicle oldest = vehicles.get(0);
		for(Vehicle v: vehicles) {
			if(v.getAge() > oldest.getAge())
				oldest = v;
		}
		
		for(Vehicle v: vehicles) {
			if(oldest.getAge() == v.getAge())
				oldestIDs.add(v.getId());
		}
		
		return oldestIDs;
	}
}
