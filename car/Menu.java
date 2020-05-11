package car;

import java.util.Scanner;

public class Menu {
	private Service service;
	private rent.service.RentService rentService;

	public Menu() {
		this.service = new ServcieImpl();
		this.rentService = new rent.service.RentServiceImpl();
	}

	public void run(Scanner sc) {
		boolean flag = true;
		String str = "1.차량 등록 2.차량 이름으로 검색 3.차량번호로 검색 4.차량 삭제 5.전체 차량 조회 6.미결제 차량 조회 7.뒤로가기";
		int menu;
		while (flag) {
			System.out.println(str);
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				service.addCar(sc);
				break;
			case 2:
				service.searchCarByName(sc);
				break;
			case 3:
				service.searchCarById(sc);
				break;
			case 4:
				service.deleteCar(sc);
				break;
			case 5:
				service.selectAll();
				break;
			case 6:
				rentService.noPaidRentList(sc);
				break;
			case 7:	
				flag = false;
			}
		}
	}
}
