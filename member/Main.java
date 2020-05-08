package member;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dao MemberDao = new DaoImpl();
		Service service = new ServiceImpl(MemberDao);
		Scanner sc = new Scanner(System.in);
		Menu menu = new Menu(service);
		
		menu.run(sc);
		sc.close();
	}

}
