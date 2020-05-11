package car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConn;

public class DaoImpl implements Dao {

	private DBConn db;

	public DaoImpl() {
		db = DBConn.getInstance();
	}

	@Override
	public void insert(Car c) {
		// TODO Auto-generated method stub
		String sql = "insert into car values(?,?,?)";
		Connection conn = db.getConnect();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getCar_id());
			pstmt.setString(2, c.getName());
			pstmt.setInt(3, c.getPrice());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Car> selectByName(String name) {
		// TODO Auto-generated method stub
		String sql = "select * from Car where name=?";
		ArrayList<Car> list = new ArrayList<Car>();
		ResultSet rs = null;
		Connection conn = db.getConnect();

		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Car(rs.getInt(1), rs.getString(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public void delete(int car_id) {
		// TODO Auto-generated method stub
		String sql = "delete car where car_id=?";
		Connection conn = db.getConnect();
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, car_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Car> selectAll() {
		// TODO Auto-generated method stub
		String sql = "select * from car";
		ArrayList<Car> list = new ArrayList<Car>();
		ResultSet rs = null;
		Connection conn = db.getConnect();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {// rs.next(): 검색 결과에서 줄 이동
				int car_id = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				Car c = new Car(car_id, name, price);
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public Car select(int num) {
		// TODO Auto-generated method stub
		String sql = "select * from Car where car_id=?";
		Car c  = null;
		ResultSet rs = null;
		Connection conn = db.getConnect();

		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				c = new Car(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public void updateCar(Car c) {
		// TODO Auto-generated method stub
		String sql = "UPDATE car SET name=?, price=? WHERE car_id=?";
		Connection conn = db.getConnect();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getName());
			pstmt.setInt(2, c.getPrice());
			pstmt.setInt(3, c.getCar_id());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
