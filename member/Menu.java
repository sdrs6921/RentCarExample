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
		String str = "메뉴를 선택해 주세요.\n1.로그인 2.회원가입 3.회원정보수정 4.회원 탈퇴 5.종료 >>";
		boolean flag = true;
		while (flag) {
			System.out.print(str);
			menu = sc.nextInt();
			
			switch(menu) {
			case 1:
				if ((curUser = service.logIn(sc)) != null) {
					System.out.println("로그인 성공!!");
					if (curUser.isAdmin()) {
						System.out.println("관리자 모드 실행");
						//TODO 관리자 모드로 들어가는 Menu
					}
					else {
						System.out.println("사용자 모드 실행");
						//TODO 사용자 모드 실행
						post.Menu postMenu = new post.Menu(new post.ServiceImpl(new post.DaoImpl()));
						postMenu.run(sc);
					}
				} else {
					System.out.println("로그인 실패!!");
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
		
		System.out.println("이용해주셔서 감사합니다.");
	}
}
