package rent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.Member;
import rent.vo.CarVO;
import rent.vo.RentVO;


public class RentDao {
    private Connection conn;
    private PreparedStatement pstmt = null;

    public RentDao(Connection conn){
        this.conn = conn;
    }

    //빌릴 수 있는 차량 조회
    public List<CarVO> getRentAvailableList(RentVO rentVO){
    	ArrayList<CarVO> rentList = new ArrayList<>();
        String queryString = "SELECT DISTINCT car.car_id, car.name , car.price from car "+
        "LEFT OUTER JOIN rent r ON r.car_id = car.car_id "+
        "WHERE (r.rent_date NOT BETWEEN TO_DATE(?) AND TO_DATE(?) "+
        "AND r.return_date NOT BETWEEN TO_DATE(?) AND TO_DATE(?)) "+
        "OR r.rent_id is null";

        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(queryString);
            pstmt.setString(1, rentVO.getRentDate());
            pstmt.setString(2,rentVO.getReturnDate());
            pstmt.setString(3, rentVO.getRentDate());
            pstmt.setString(4, rentVO.getReturnDate());
            
            rs = pstmt.executeQuery();

            while( rs.next() ){
                CarVO carInfo = new CarVO();
                carInfo.setCarId(rs.getInt("CAR_ID"));
                carInfo.setName(rs.getString("NAME"));
                carInfo.setPrice(rs.getInt("PRICE"));
                rentList.add(carInfo);
            }

            pstmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rentList;
    }
    
    //고객이 빌린것 조회
    public List<RentVO> getRentedList(RentVO rentVO){
    	ArrayList<RentVO> rentList = new ArrayList<>();
        String queryString = "select r.user_id, r.car_id, r.paid, r.rent_id, r.rent_date, r.return_date, c.name  FROM RENT r JOIN car c ON r.car_id = c.car_id WHERE user_id = ?";

        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(queryString);
            pstmt.setString(1, rentVO.getUserId());
            
            rs = pstmt.executeQuery();

            while( rs.next() ){
                RentVO rentInfo = new RentVO();
                rentInfo.setUserId(rs.getString("user_id"));
                rentInfo.setCarId(rs.getInt("car_id"));
                rentInfo.setPaid(rs.getString("paid").equals("T")  ? true: false );
                rentInfo.setRentId(rs.getInt("rent_id"));
                rentInfo.setRentDate(rs.getString("rent_date"));
                rentInfo.setReturnDate(rs.getString("return_date"));
                rentInfo.setCarName(rs.getString("name"));
                rentList.add(rentInfo);
            }

            pstmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rentList;
    }
    
    //결제가 되지 않은 예약 조회
    public List<RentVO> getNoPaidList(){
    	ArrayList<RentVO> rentList = new ArrayList<>();
    	String queryString = "select r.user_id, r.car_id, r.paid, r.rent_id, r.rent_date, r.return_date, c.name FROM RENT r JOIN car c ON r.car_id = c.car_id WHERE r.paid = 'F'";
    	ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(queryString);
            
            rs = pstmt.executeQuery();

            while( rs.next() ){
                RentVO rentInfo = new RentVO();
                rentInfo.setUserId(rs.getString("user_id"));
                rentInfo.setCarId(rs.getInt("car_id"));
                rentInfo.setPaid(rs.getString("paid").equals("T")  ? true: false );
                rentInfo.setRentId(rs.getInt("rent_id"));
                rentInfo.setRentDate(rs.getString("rent_date"));
                rentInfo.setReturnDate(rs.getString("return_date"));
                rentInfo.setCarName(rs.getString("name"));
                rentList.add(rentInfo);
            }

            pstmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rentList;
    }

    
    public int addRent(RentVO rentVO){
    	String queryString = "INSERT INTO RENT(RENT_ID, USER_ID, CAR_ID, RETURN_DATE, RENT_DATE, PAID) "
    			+ "VALUES (ID_SEQ.nextval, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'), ?)";

    	int result = 0;

        try {
            pstmt = conn.prepareStatement(queryString);
            pstmt.setString(1, rentVO.getUserId());
            pstmt.setInt(2, rentVO.getCarId());
            pstmt.setString(3, rentVO.getReturnDate());
            pstmt.setString(4, rentVO.getRentDate());
            pstmt.setString(5, "F");
            
            result = pstmt.executeUpdate();

            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateRent(RentVO rentVO){
    	String queryString = "UPDATE RENT"
    			+ " SET CAR_ID = ?, paid=?, RENT_DATE=TO_DATE(?,'YYYY-MM-DD'), RETURN_DATE=TO_DATE(?,'YYYY-MM-DD')"
    			+ " where rent_id = ?";
    	int result = 0;

        try {
            pstmt = conn.prepareStatement(queryString);
            pstmt.setInt(1, rentVO.getCarId());
            pstmt.setString(2, rentVO.isPaid() ? "T" : "F");
            pstmt.setString(3, rentVO.getRentDate().substring(0, 10));
            pstmt.setString(4, rentVO.getReturnDate().substring(0, 10));
            pstmt.setInt(5, rentVO.getRentId());
            result = pstmt.executeUpdate();

            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteRent(RentVO rentVO){
    	String queryString = "DELETE FROM rent WHERE rent_id = ?";
    	int result = 0;

        try {
            pstmt = conn.prepareStatement(queryString);
            pstmt.setInt(1, rentVO.getRentId());
            result = pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }




}