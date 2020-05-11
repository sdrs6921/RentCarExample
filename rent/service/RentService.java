package rent.service;
import java.util.List;
import java.util.Scanner;

import member.Member;
import rent.vo.CarVO;
import rent.vo.RentVO;



public interface RentService {
	
	public void getRentAvailableList(Scanner sc);
	
	public void rentCar(Scanner sc, RentVO rentVO, List<CarVO> carList, Member member);
	
	public void noPaidRentList(Scanner sc);
	
	public void printRentedList(Scanner sc, Member member);
	
	public void cancelRent(RentVO rentVO);
	
	public void payRent(RentVO rentVO);
}