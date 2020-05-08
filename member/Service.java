package member;

import java.util.Scanner;

public interface Service {
	void addMember(Scanner sc);
	
	void editMember(Scanner sc);
	
	void getMember(Scanner sc);
	
	void deleteMember(Scanner sc);
	
	Member logIn(Scanner sc);
}
