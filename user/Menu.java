package user;

import java.util.Scanner;

public class Menu {

	private post.Menu postMenu;
	private rent.service.RentService rentService;

	public Menu() {
		postMenu = new post.Menu(new post.ServiceImpl(new post.DaoImpl()));
		rentService = new rent.service.RentServiceImpl();
	}

	public void run(Scanner sc) {
		String str = "�޴��� �������ּ���.\n1.��Ʈ�ϱ� 2.��Ʈ��ȸ 3.�ı� 4.���� �޴�";
		boolean flag = true;
		int menu = 0;
		while (flag) {
			System.out.println(str);
			menu = sc.nextInt();
			
			switch (menu) {
			case 1:
				rentService.getRentAvailableList(sc);
			case 2:
				rentService.printRentedList(sc, member.Menu.curUser);
				break;
			case 3:
				postMenu.run(sc);
				break;
			case 4:
				flag = false;
				break;
			}
		}
	}
}
