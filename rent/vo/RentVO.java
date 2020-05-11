package rent.vo;


public class RentVO {
    private String userId;
    //자동차 정보
    private int carId;
    private String carName;
    
    private int rentId;
    private String rentDate;
    private String returnDate;
    private boolean paid;


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public int getRentId() {
		return rentId;
	}
	public void setRentId(int rentId) {
		this.rentId = rentId;
	}
	public String getRentDate() {
		return rentDate;
	}
	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	@Override
	public String toString() {
		return "RentVO [userId=" + userId + ", carId=" + carId + ", carName=" + carName + ", rentId=" + rentId
				+ ", rentDate=" + rentDate + ", returnDate=" + returnDate + ", paid=" + paid + "]";
	}

	
	
	

    


    
}