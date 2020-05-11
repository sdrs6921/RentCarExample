package car;

import java.util.Scanner;

public interface Service {
	void addCar(Scanner sc);
	
	void searchCarById(Scanner sc);
	
	void searchCarByName(Scanner sc);
	
	void deleteCar(Scanner sc);
	
	void editCar(Scanner sc);
	
	void selectAll();
}
