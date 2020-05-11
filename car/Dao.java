package car;

import java.util.ArrayList;

import car.Car;

public interface Dao {
	void insert(Car c);
	
	Car select(int num);

	ArrayList<Car> selectByName(String name);
	
	void delete(int car_id);
	
	void updateCar (Car c);
	
	ArrayList<Car> selectAll();
}
