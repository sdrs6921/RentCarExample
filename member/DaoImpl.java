package member;

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
		db = DBConn.getInstance();
	}
	@Override
	public void insert(Member member) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO rent_member VALUES (?, ?, ? ,?, ?)";
		Connection conn = db.getConnect();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getTel());
			
			if (member.isAdmin()) {
				pstmt.setInt(5, 1);
			} else {
				pstmt.setInt(5, 0);
			}
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Member select(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM rent_member WHERE member_id = ?";
		Member member = null;
		ResultSet rs = null;
		Connection conn = db.getConnect();
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				member = new Member();
				member.setId(rs.getString(1));
				member.setPasswd(rs.getString(2));
				member.setName(rs.getString(3));
				member.setTel(rs.getString(4));
				if (rs.getInt(5) == 1) {
					member.setAdmin(true);
				} else {
					member.setAdmin(false);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return member;
	}

	@Override
	public void updateMember(Member member) {
		// TODO Auto-generated method stub
		String sql = "UPDATE rent_member SET passwd=?, name=?, tel=? WHERE member_id=?";
		Connection conn = db.getConnect();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getTel());
			pstmt.setString(4, member.getId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMember(Member member) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM rent_member WHERE member_id = ? AND passwd = ?";
		Connection conn = db.getConnect();
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Member> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
