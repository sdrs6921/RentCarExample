package member;

import java.util.Scanner;

public class ServiceImpl implements Service {
	private Dao dao;
	
	public ServiceImpl(Dao dao) {
		// TODO Auto-generated constructor stub
		this.dao = dao;
	}
	@Override
	public void addMember(Scanner sc) {
		// TODO Auto-generated method stub
		Member member = new Member();
		System.out.println("회원가입을 시작합니다.");
		System.out.print("ID:");
		member.setId(sc.next());
		System.out.print("Password:");
		member.setPasswd(sc.next());
		System.out.print("Name:");
		member.setName(sc.next());
		System.out.print("Tel:");
		member.setTel(sc.next());
		System.out.print("1.관리자 2.사용자 :");
		if (sc.nextInt() == 1) {
			member.setAdmin(true);
		} else {
			member.setAdmin(false);
		}
		
		if (dao.select(member.getId()) == null) {
			dao.insert(member);
		} else {
			System.out.println("중복된 아이디입니다.");
		}
	}

	@Override
	public void editMember(Scanner sc) {
		// TODO Auto-generated method stub
		Member member = new Member();
		System.out.println("회원 정보를 수정합니다.");
		System.out.print("ID:");
		member.setId(sc.next());
		System.out.print("Password:");
		member.setPasswd(sc.next());
		System.out.print("Name:");
		member.setName(sc.next());
		System.out.println("Tel:");
		member.setTel(sc.next());
		
		if (dao.select(member.getId()) != null) {
			dao.updateMember(member);
		} else {
			System.out.println("존재하지 않는 회원입니다.");
		}
	}

	@Override
	public void getMember(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMember(Scanner sc) {
		// TODO Auto-generated method stub
		Member member = new Member();
		System.out.print("ID:");
		member.setId(sc.next());
		System.out.print("PassWord:");
		member.setPasswd(sc.next());
		
		if (member.equals(dao.select(member.getId()))) {
			dao.deleteMember(member);
		} else {
			System.out.println("일치하는 회원 정보가 없습니다.");
		}
	}
	@Override
	public Member logIn(Scanner sc) {
		// TODO Auto-generated method stub
		Member member = new Member();
		System.out.println("로그인을 시작합니다.");
		System.out.print("ID:");
		member.setId(sc.next());
		System.out.print("PassWord:");
		member.setPasswd(sc.next());
		
		Member other = dao.select(member.getId());
		
		if (other == null || !member.equals(other)) {
			return null;
		} else {
			return other;
		}
	}

}
