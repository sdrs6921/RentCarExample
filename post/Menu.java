package post;

import java.util.Scanner;

public class Menu {
	private Service service;
	
	public Menu(Service service) {
		this.service = service;
	}
	
	public void run(Scanner sc) {
		boolean flag = true;
		String str = "1.������ 2.����˻� 3.������� 4.������� 5.���� �޴�>>";
		int menu;
		
		System.out.println("���� ����� �����մϴ�.");
		while (flag) {
			System.out.print(str);
			menu = sc.nextInt();
			
			switch(menu) {
			case 1:
				service.addPost(sc);
				break;
			case 2:
				service.getPost(sc);
				break;
			case 3:
				service.editPost(sc);
				break;
			case 4:
				service.deletePost(sc);
				break;
			case 5:
				flag = false;
				break;
			}
		}
		System.out.println("�̿��� �ּż� �����մϴ�.");
	}
}
