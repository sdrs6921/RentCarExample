package post;

import java.util.Scanner;

public class Menu {
	private Service service;
	
	public Menu(Service service) {
		this.service = service;
	}
	
	public void run(Scanner sc) {
		boolean flag = true;
		String str = "1.리뷰등록 2.리뷰검색 3.리뷰수정 4.리뷰삭제 5.이전 메뉴>>";
		int menu;
		
		System.out.println("리뷰 기능을 실행합니다.");
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
		System.out.println("이용해 주셔서 감사합니다.");
	}
}
