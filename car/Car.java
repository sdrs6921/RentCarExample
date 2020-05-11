package car;

public class Car {

	private int car_id;
	private String name;
	private int price;
	
	public Car(){
		
	}
	
	public Car(int car_id, String name, int price) {
		this.name = name;
		this.car_id = car_id;
		this.price = price;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCar_id() {
		return car_id;
	}

	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Car [name=" + name + ", car_id=" + car_id + ", price=" + price + "]";
	}


}
