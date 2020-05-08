package post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConn;

public class DaoImpl implements Dao {
	private DBConn db;

	public DaoImpl() {
		// TODO Auto-generated constructor stub
		this.db = DBConn.getInstance();
	}

	@Override
	public void insertPost(Post p) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO post VALUES (seq_post.nextval, ?, ?, ?)";
		Connection conn = db.getConnect();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getMemberId());
			pstmt.setString(2, p.getPostName());
			pstmt.setString(3, p.getContent());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Post selectPost(int num) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM post WHERE post_id = ?";
		Post p = null;
		ResultSet rs = null;
		Connection conn = db.getConnect();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				p = new Post();
				p.setPostId(rs.getInt(1));
				p.setMemberId(rs.getString(2));
				p.setPostName(rs.getString(3));
				p.setContent(rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public ArrayList<Post> selectAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM post";
		ArrayList<Post> list = new ArrayList<Post>();
		ResultSet rs = null;
		Connection conn = db.getConnect();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Post p = new Post();
				p.setPostId(rs.getInt(1));
				p.setMemberId(rs.getString(2));
				p.setPostName(rs.getString(3));
				p.setContent(rs.getString(4));

				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Post> selectPostByMemberId(String memberId) {
		// TODO Auto-generated method stub
		ArrayList<Post> list = new ArrayList<Post>();
		String sql = "SELECT * FROM post WHERE member_id = ?";
		Connection conn = db.getConnect();
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Post p = new Post();
				p.setPostId(rs.getInt(1));
				p.setMemberId(rs.getString(2));
				p.setPostName(rs.getString(3));
				p.setContent(rs.getString(4));
				
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void deletePost(int num) {
		// TODO Auto-generated method stub
		String sql = "DELETE post WHERE post_id = ?";
		Connection conn = db.getConnect();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updatePost(Post p) {
		// TODO Auto-generated method stub
		String sql = "UPDATE post SET post_name = ?, post_content = ? WHERE post_id = ?";
		Connection conn = db.getConnect();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getPostName());
			pstmt.setString(2, p.getContent());
			pstmt.setInt(3, p.getPostId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
