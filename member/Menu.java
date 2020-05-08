package member;

import java.util.Scanner;

public class Menu {
	private Service service;
	public static Member curUser;
	
	public Menu(Service service) {
		this.service = service;
	}
	
	public void run(Scanner sc) {
		int menu;
		String str = "�޴��� ������ �ּ���.\n1.�α��� 2.ȸ������ 3.ȸ���������� 4.ȸ�� Ż�� 5.���� >>";
		boolean flag = true;
		while (flag) {
			System.out.print(str);
			menu = sc.nextInt();
			
			switch(menu) {
			case 1:
				if ((curUser = service.logIn(sc)) != null) {
					System.out.println("�α��� ����!!");
					if (curUser.isAdmin()) {
						System.out.println("������ ��� ����");
						//TODO ������ ���� ���� Menu
					}
					else {
						System.out.println("����� ��� ����");
						//TODO ����� ��� ����
						post.Menu postMenu = new post.Menu(new post.ServiceImpl(new post.DaoImpl()));
						postMenu.run(sc);
					}
				} else {
					System.out.println("�α��� ����!!");
				}
				break;
			case 2:
				service.addMember(sc);
				break;
			case 3:
				service.editMember(sc);
				break;
			case 4:
				service.deleteMember(sc);
				break;
			case 5:
				flag = false;
				break;
			}
		}
		
		System.out.println("�̿����ּż� �����մϴ�.");
	}
}
