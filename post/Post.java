package post;

public class Post {
	private int postId;
	private String memberId;
	private String postName;
	private String content;
	
	public Post() {}

	public Post(int postId, String memberId, String postName, String content) {
		super();
		this.postId = postId;
		this.memberId = memberId;
		this.postName = postName;
		this.content = content;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
