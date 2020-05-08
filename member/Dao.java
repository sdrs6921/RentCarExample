package member;

import java.util.ArrayList;

public interface Dao {
	void insert(Member member);
	
	Member select(String id);
	
	void updateMember(Member member);
	
	void deleteMember(Member member);
	
	ArrayList<Member> selectAll();
}
