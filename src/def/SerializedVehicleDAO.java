package def;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerializedVehicleDAO implements VehicleDAO {

	private String fileName;

	public SerializedVehicleDAO(String fileName) {
		this.fileName = fileName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicle> getVehicleList() {
		List<Vehicle> vehicles = new ArrayList<>();
		try {
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName)); //reader -> deserialization
			Object obj = reader.readObject();

			if (obj instanceof Vehicle) {
				vehicles.add((Vehicle) obj);
			} else {
				vehicles = (List<Vehicle>) obj;
			}
			reader.close();
		} catch (Exception e) {
			System.err.println("Error during deserialization: " + e.getMessage());
			System.exit(1);
		}
		return vehicles;
	}

	@Override
	public Vehicle getVehicle(int id) {
		List<Vehicle> vehicles = getVehicleList();

		for (Vehicle v : vehicles) {
			if (v.getId() == id)
				return v;
		}
		return null;
	}

	@Override
	public void saveVehicle(Vehicle vehicle) {
		try {
			File file = new File(fileName);
			List<Vehicle> saved = new ArrayList<Vehicle>();

			if (file.exists()) {
				saved = this.getVehicleList();
				for (Vehicle v : saved) {
					if (v.getId() == vehicle.getId())
						throw new IllegalArgumentException(
								"Error: Vehicle already exists. (id=" + vehicle.getId() + ")");
				}

				saved.add(vehicle);
				file.delete(); // old file, but not sure
			} else 
				saved.add(vehicle);
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName, true));
			writer.writeObject(saved);
			writer.close();
			
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		catch(Exception e) {
			System.err.println("Error during serialization: " + e.getMessage());
			System.exit(1);
		}

	}

	@Override
	public void deleteVehicle(int id) {
		File file = new File(fileName);
		
		List<Vehicle> vehicles = this.getVehicleList();
		boolean flag = false;
		
		for(Vehicle v: vehicles) {
			if(v.getId() == id) {
				flag = true;
				vehicles.remove(v);
				break;
			}
		}
		
		try {
			if(!flag) 
				throw new IllegalArgumentException("Error: Vehicle not found. (id=" + id + ")");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		file.delete();
		for(Vehicle v: vehicles) //the new Vehicles without the deleted
			this.saveVehicle(v); 
		
	}

}
