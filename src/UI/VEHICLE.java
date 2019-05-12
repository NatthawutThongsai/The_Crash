package UI;

public enum VEHICLE {
	NORMAL("file:res/image/vehicle/normal_car.png", "file:res/image/vehicle/normal_car.png","NORMAL"),
	F1("file:res/image/vehicle/f1.png", "file:res/image/vehicle/f1.png","F1"),
	TANK("file:res/image/vehicle/tank.png", "file:res/image/vehicle/tank.png","TANK"),
	PICKUP("file:res/image/vehicle/pickup.png", "file:res/image/vehicle/pickup.png","PICKUP");

	private String urlVehicle;
	private String urlLife;
	private String typeVehicle;

	private VEHICLE(String urlVehicle,String urlLife,String typeVehicle) {
		this.urlVehicle = urlVehicle;
		this.urlLife = urlLife;
		this.typeVehicle = typeVehicle;
	}

	public String geturl() {
		return this.urlVehicle;
	} 
	public String getUrlLife() {
		return this.urlLife;
	}
	public String gettypeVehicle() {
		return this.typeVehicle;
	}
}

