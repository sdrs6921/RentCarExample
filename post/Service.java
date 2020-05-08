package post;

import java.util.Scanner;

public interface Service {
	void addPost(Scanner sc);

	void getPost(Scanner sc);

	void getPostByMemberId();

	void getAll();

	void editPost(Scanner sc);

	void deletePost(Scanner sc);
}
