package post;

import java.util.ArrayList;
import java.util.Scanner;

import member.Member;

public class ServiceImpl implements Service {
	private Dao dao;
	private Member cur;
	
	public ServiceImpl(Dao dao) {
		this.dao = dao;
		cur = member.Menu.curUser;
	}
	@Override
	public void addPost(Scanner sc) {
		// TODO Auto-generated method stub
		Post p = new Post();
		p.setMemberId(cur.getId());
		sc.nextLine();
		System.out.print("제목: ");
		p.setPostName(sc.nextLine());
		System.out.print("내용: ");
		p.setContent(sc.nextLine());
		dao.insertPost(p);
	}

	@Override
	public void getPost(Scanner sc) {
		// TODO Auto-generated method stub
		Post p;
		getAll();
		System.out.print("보고 싶은 리뷰의 번호를 입력해 주세요 >>");
		p = dao.selectPost(sc.nextInt());
		System.out.println(p.getPostName());
		System.out.println(p.getContent());
	}

	@Override
	public void getPostByMemberId() {
		// TODO Auto-generated method stub
		ArrayList<Post> list = new ArrayList<Post>();
		list = dao.selectPostByMemberId(cur.getId());
		
		for (Post p : list) {
			System.out.println(p.getPostId() + " " + p.getPostName());
		}
	}

	@Override
	public void getAll() {
		// TODO Auto-generated method stub
		ArrayList<Post> list = new ArrayList<Post>();
		list = dao.selectAll();
		
		for (Post p : list) {
			System.out.println(p.getPostId() + "." + p.getPostName() + " 작성자:" + p.getMemberId());
		}
	}

	@Override
	public void editPost(Scanner sc) {
		// TODO Auto-generated method stub
		Post p = new Post();
		getPostByMemberId();
		System.out.print("수정할 번호를 입력해 주세요 >>");
		p.setPostId(sc.nextInt());
		sc.nextLine();
		p.setMemberId(cur.getId());
		System.out.print("제목을 수정해 주세요 :");
		p.setPostName(sc.nextLine());
		System.out.print("내용을 수정해 주세요 :");
		p.setContent(sc.nextLine());
		dao.updatePost(p);
	}

	@Override
	public void deletePost(Scanner sc) {
		// TODO Auto-generated method stub
		getPostByMemberId();
		System.out.print("삭제할 번호를 입력해 주세요 :");
		dao.deletePost(sc.nextInt());
	}

}
