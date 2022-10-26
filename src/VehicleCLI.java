
/**
 * @author Nadezhda Tsvetkova
 * @id 11942924
 */
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleCLI {

	static VehicleManagement manage;

	public static void main(String[] args) {

		DecimalFormat df = new DecimalFormat("#.00");
		try {
			if (args.length >= 2)
				manage = new VehicleManagement(new SerializedVehicleDAO(args[0]));
			else
				throw new IllegalArgumentException("Error: Invalid parameter.");

			switch (args[1]) {
			case "show":
				if (args.length == 2) {
					List<Vehicle> vehicles = manage.getAll();
					for (Vehicle v : vehicles) {
						System.out.println(v + "\n");
					}
				}
				if (args.length == 3) {
					List<Vehicle> vehicles = manage.getAll();
					for (Vehicle v : vehicles) {
						if (v.getId() == Integer.parseInt(args[2])) {
							System.out.println(v + "\n");
							break;
						}
					}
					// if (manage.getAll().contains(manage.getSpecific(Integer.parseInt(args[2]))))
					// System.out.println(manage.getSpecific(Integer.parseInt(args[2])) + "\n");
				}
				break;
			case "add":
				try {
					Vehicle newVehicle = null;

					if (args[2].equalsIgnoreCase("car")) {
						if (args.length != 9)
							throw new IllegalArgumentException("Error: Invalid parameter.");
						newVehicle = new Car(Integer.parseInt(args[3]), args[4], args[5], Integer.parseInt(args[6]),
								Double.parseDouble(args[7]), Integer.parseInt(args[8]));
					} else if (args[2].equalsIgnoreCase("truck")) {
						if (args.length != 8)
							throw new IllegalArgumentException("Error: Invalid parameter.");
						newVehicle = new Truck(Integer.parseInt(args[3]), args[4], args[5], Integer.parseInt(args[6]),
								Double.parseDouble(args[7]));
					} else
						throw new IllegalArgumentException("Error: Invalid parameter.");
					manage.addVehicle(newVehicle);
				} catch (NumberFormatException e) {
					System.out.println("Error: Invalid parameter.");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "del":
				manage.deleteVehicle(Integer.parseInt(args[2]));
				break;
			case "count":
				if (args.length == 2) {
					System.out.println(manage.totalVehicles());
				} else if (args[2].equalsIgnoreCase("Car")) {
					System.out.println(manage.totalCars());
				} else if (args[2].equalsIgnoreCase("Truck")) {
					System.out.println(manage.totalTrucks());
				}
				break;
			case "meanprice":
				System.out.println(manage.meanPrice());
				break;

			case "oldest":
				String result = manage.getOldestVehicles().stream().map(n -> String.valueOf(n))
						.collect(Collectors.joining("\n", "Id: ", ""));

				System.out.println(result);
				break;
			default:
				throw new IllegalArgumentException("Error: Invalid parameter.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
