package post;

import java.util.ArrayList;

public interface Dao {
	void insertPost(Post p);

	Post selectPost(int num);

	ArrayList<Post> selectAll();

	ArrayList<Post> selectPostByMemberId(String Id);
	
	void deletePost(int num);
	
	void updatePost(Post p);
}
