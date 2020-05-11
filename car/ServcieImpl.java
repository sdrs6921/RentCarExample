package car;

import java.util.ArrayList;
import java.util.Scanner;

import car.Dao;
import car.DaoImpl;
import car.Car;

public class ServcieImpl implements Service {
	
	private Dao dao;

	public ServcieImpl() {
		dao = new DaoImpl();
	}

	@Override
	public void addCar(Scanner sc) {
		// TODO Auto-generated method stub
		Car c = new Car();
		System.out.print("이름:");
		c.setName(sc.next());
		System.out.print("차 일련번호:");
		c.setCar_id(sc.nextInt());
		System.out.print("가격:");
		c.setPrice(sc.nextInt());
		
		if (dao.select(c.getCar_id()) == null) {
			dao.insert(c);
		}
		else {
			System.out.println("중복된 차 일련번호입니다. 다른 번호를 입력해주세요.");
		}
	}
	
	

	@Override
	public void searchCarByName(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.print("찾을 제품의 이름을 입력해 주세요:");
		String name = sc.next();
		ArrayList<Car> list = dao.selectByName(name);
		if (list.isEmpty()) {
			System.out.println("차량이 존재하지 않습니다.");
		} else {
			System.out.println("No.\t차량명\t가격(1일단위)");
			for (Car c : list) {
				System.out.println(c.getCar_id() + "\t" + c.getName() + "\t" + c.getPrice());
			}
		}
	}

	@Override
	public void deleteCar(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.print("삭제할 차량의 일련번호를 입력해 주세요 :");
		int num = sc.nextInt();
		
		if (dao.select(num) != null) {
			dao.delete(num);
		}else {
			System.out.println("삭제할 차량이 존재하지 않습니다.");
		}
	}

	@Override
	public void selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Car> list = dao.selectAll();
		System.out.println("No.\t차량명\t가격(1일단위)");
		for (Car c : list) {
			System.out.println(c.getCar_id() + "\t" + c.getName() + "\t" + c.getPrice());
		}
	}

	@Override
	public void searchCarById(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("차량의 일련번호를 입력해 주세요.");
		int num = sc.nextInt();
		Car c;
		if ((c = dao.select(num)) != null) {
			System.out.println("No.\t차량명\t가격(1일단위)");
			System.out.println(c.getCar_id() + "\t" + c.getName() + "\t" + c.getPrice());
		} else {
			System.out.println("해당 번호의 차량이 존재하지 않습니다.");
		}
	}

	@Override
	public void editCar(Scanner sc) {
		// TODO Auto-generated method stub
		Car c = null;
		System.out.println("수정하실 차량의 번호를 입력해주세요");
		int num = sc.nextInt();
		
		c = dao.select(num);
		
		if (c == null) {
			System.out.println("해당 번호의 차량이 존재하지 않습니다.");
		} else {
			System.out.println("차량의 정보를 번경합니다.");
			System.out.print("차량 이름:");
			c.setName(sc.next());
			System.out.print("1일 대여료 가격:");
			c.setPrice(sc.nextInt());
			dao.updateCar(c);
		}
	}

}
