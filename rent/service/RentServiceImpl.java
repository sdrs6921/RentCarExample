package rent.service;
import java.util.List;
import java.util.Scanner;

import dbconn.DBConn;
import member.Member;
import rent.dao.RentDao;
import rent.vo.CarVO;
import rent.vo.RentVO;


public class RentServiceImpl implements RentService {
	
	private RentDao rentDao = null;
	
	private DBConn dbConnection = DBConn.getInstance();
	public RentServiceImpl() {
		rentDao = new RentDao(dbConnection.getConnect());
	}
	
	

	@Override
	public void getRentAvailableList(Scanner sc) {
		System.out.println("랜트 기간을 입렵해주세요. ( 'YYYY-MM-DD' 형식)");
		System.out.print("시작날짜:");
		String startDate = sc.next();
		System.out.print("반납날짜:");
		String endDate = sc.next();
		
		RentVO rentVO = new RentVO();
		rentVO.setRentDate(startDate);
		rentVO.setReturnDate(endDate);
		List<CarVO> carList = rentDao.getRentAvailableList(rentVO);
	
		if( carList.isEmpty() ) {
			System.out.println("랜트 가능한 차량이 없습니다.");
			return;
		}
		
		System.out.println("ID\t차량명\t가격(1일단위)");
		for(CarVO carVO : carList){
			System.out.println( carVO.getCarId() + "\t" + carVO.getName() + "\t" + carVO.getPrice());
		}
		
		rentCar(sc , rentVO, carList, member.Menu.curUser);
	}

	

	@Override
	public void rentCar(Scanner sc, RentVO rentVO, List<CarVO> carList, Member member) {
		System.out.println("랜트 하실 차량의 ID를 입력하세요. ( 취고 -1 입력)");
		int carId = sc.nextInt();
		if( carId == -1 ) return;
		
		rentVO.setCarId(carId);
		rentVO.setUserId(member.getId());
		
		rentDao.addRent(rentVO);
		
	}

	@Override
	public void printRentedList(Scanner sc, Member member) {
		RentVO rent = new RentVO();
		rent.setUserId(member.getId());
		
		List<RentVO> rentList = rentDao.getRentedList(rent);
		
		System.out.println("No.\t차량명\t랜트일\t\t반납일\t\t결제여부");
		for( int i = 0 ; i < rentList.size() ; i++){
			System.out.println( i + "\t" + rentList.get(i).getCarName() + "\t" + rentList.get(i).getRentDate().substring(0, 10) + "\t" + rentList.get(i).getReturnDate().substring(0, 10) + "\t" + rentList.get(i).isPaid() );
		}
		
		System.out.println("1. 결제하기 2. 취소하기 3. 뒤로가기");
		int command =sc.nextInt();
		
		
		switch (command) {
		case 1:
			System.out.println("결제할 예약의 순번를 입력하세요.");
			payRent(rentList.get(sc.nextInt()));
			break;
		case 2:
			System.out.println("취소할 예약의 순번를 입력하세요.");
			cancelRent(rentList.get(sc.nextInt()));
			break;
		case 3:
			return;

		}
	}



	   @Override
	   public void noPaidRentList(Scanner sc) {
	      List<RentVO> rentList = rentDao.getNoPaidList();
	      
	      System.out.println("No.\t차량명\t랜트일\t\t반납일\t\t결제여부");
	      for( int i = 0 ; i < rentList.size() ; i++){
	         System.out.println( i + "\t" + rentList.get(i).getCarName() + "\t" + rentList.get(i).getRentDate().substring(0, 10) + "\t" + rentList.get(i).getReturnDate().substring(0, 10) + "\t" + rentList.get(i).isPaid() );
	      }
	      
	   }



	@Override
	public void cancelRent(RentVO rentVO) {
		rentDao.deleteRent(rentVO);
		
	}



	@Override
	public void payRent(RentVO rentVO) {
		rentVO.setPaid(true);
		rentDao.updateRent(rentVO);
	}



	
}
