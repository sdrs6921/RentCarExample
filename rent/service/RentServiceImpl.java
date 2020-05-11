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
		System.out.println("��Ʈ �Ⱓ�� �Է����ּ���. ( 'YYYY-MM-DD' ����)");
		System.out.print("���۳�¥:");
		String startDate = sc.next();
		System.out.print("�ݳ���¥:");
		String endDate = sc.next();
		
		RentVO rentVO = new RentVO();
		rentVO.setRentDate(startDate);
		rentVO.setReturnDate(endDate);
		List<CarVO> carList = rentDao.getRentAvailableList(rentVO);
	
		if( carList.isEmpty() ) {
			System.out.println("��Ʈ ������ ������ �����ϴ�.");
			return;
		}
		
		System.out.println("ID\t������\t����(1�ϴ���)");
		for(CarVO carVO : carList){
			System.out.println( carVO.getCarId() + "\t" + carVO.getName() + "\t" + carVO.getPrice());
		}
		
		rentCar(sc , rentVO, carList, member.Menu.curUser);
	}

	

	@Override
	public void rentCar(Scanner sc, RentVO rentVO, List<CarVO> carList, Member member) {
		System.out.println("��Ʈ �Ͻ� ������ ID�� �Է��ϼ���. ( ��� -1 �Է�)");
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
		
		System.out.println("No.\t������\t��Ʈ��\t\t�ݳ���\t\t��������");
		for( int i = 0 ; i < rentList.size() ; i++){
			System.out.println( i + "\t" + rentList.get(i).getCarName() + "\t" + rentList.get(i).getRentDate().substring(0, 10) + "\t" + rentList.get(i).getReturnDate().substring(0, 10) + "\t" + rentList.get(i).isPaid() );
		}
		
		System.out.println("1. �����ϱ� 2. ����ϱ� 3. �ڷΰ���");
		int command =sc.nextInt();
		
		
		switch (command) {
		case 1:
			System.out.println("������ ������ ������ �Է��ϼ���.");
			payRent(rentList.get(sc.nextInt()));
			break;
		case 2:
			System.out.println("����� ������ ������ �Է��ϼ���.");
			cancelRent(rentList.get(sc.nextInt()));
			break;
		case 3:
			return;

		}
	}



	   @Override
	   public void noPaidRentList(Scanner sc) {
	      List<RentVO> rentList = rentDao.getNoPaidList();
	      
	      System.out.println("No.\t������\t��Ʈ��\t\t�ݳ���\t\t��������");
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
