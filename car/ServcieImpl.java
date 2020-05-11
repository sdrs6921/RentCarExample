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
		System.out.print("�̸�:");
		c.setName(sc.next());
		System.out.print("�� �Ϸù�ȣ:");
		c.setCar_id(sc.nextInt());
		System.out.print("����:");
		c.setPrice(sc.nextInt());
		
		if (dao.select(c.getCar_id()) == null) {
			dao.insert(c);
		}
		else {
			System.out.println("�ߺ��� �� �Ϸù�ȣ�Դϴ�. �ٸ� ��ȣ�� �Է����ּ���.");
		}
	}
	
	

	@Override
	public void searchCarByName(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.print("ã�� ��ǰ�� �̸��� �Է��� �ּ���:");
		String name = sc.next();
		ArrayList<Car> list = dao.selectByName(name);
		if (list.isEmpty()) {
			System.out.println("������ �������� �ʽ��ϴ�.");
		} else {
			System.out.println("No.\t������\t����(1�ϴ���)");
			for (Car c : list) {
				System.out.println(c.getCar_id() + "\t" + c.getName() + "\t" + c.getPrice());
			}
		}
	}

	@Override
	public void deleteCar(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.print("������ ������ �Ϸù�ȣ�� �Է��� �ּ��� :");
		int num = sc.nextInt();
		
		if (dao.select(num) != null) {
			dao.delete(num);
		}else {
			System.out.println("������ ������ �������� �ʽ��ϴ�.");
		}
	}

	@Override
	public void selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Car> list = dao.selectAll();
		System.out.println("No.\t������\t����(1�ϴ���)");
		for (Car c : list) {
			System.out.println(c.getCar_id() + "\t" + c.getName() + "\t" + c.getPrice());
		}
	}

	@Override
	public void searchCarById(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("������ �Ϸù�ȣ�� �Է��� �ּ���.");
		int num = sc.nextInt();
		Car c;
		if ((c = dao.select(num)) != null) {
			System.out.println("No.\t������\t����(1�ϴ���)");
			System.out.println(c.getCar_id() + "\t" + c.getName() + "\t" + c.getPrice());
		} else {
			System.out.println("�ش� ��ȣ�� ������ �������� �ʽ��ϴ�.");
		}
	}

	@Override
	public void editCar(Scanner sc) {
		// TODO Auto-generated method stub
		Car c = null;
		System.out.println("�����Ͻ� ������ ��ȣ�� �Է����ּ���");
		int num = sc.nextInt();
		
		c = dao.select(num);
		
		if (c == null) {
			System.out.println("�ش� ��ȣ�� ������ �������� �ʽ��ϴ�.");
		} else {
			System.out.println("������ ������ �����մϴ�.");
			System.out.print("���� �̸�:");
			c.setName(sc.next());
			System.out.print("1�� �뿩�� ����:");
			c.setPrice(sc.nextInt());
			dao.updateCar(c);
		}
	}

}
