package UI;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class VehiclePicker extends VBox {

	private ImageView circleImage;
	private ImageView shipImage;

	private String circleNotChoosen = "file:res/image/grey_circle.png";
	private String circleChoosen = "file:res/image/circle_choosen.png";

	private VEHICLE vehicle;
	private boolean isCircleChoosen;

	public VehiclePicker(VEHICLE vehicle) {
		circleImage = new ImageView(circleNotChoosen);
		shipImage = new ImageView(vehicle.geturl());
		shipImage.setFitHeight(100);
		shipImage.setFitWidth(100);
		this.vehicle = vehicle;
		isCircleChoosen = false;
		this.setAlignment(Pos.CENTER);
		this.getChildren().add(circleImage);
		this.getChildren().add(shipImage);
	}
	
	public VEHICLE getVehicle() {
		return vehicle;
	}
	public boolean getIsCircleChoosen() {
		return isCircleChoosen;
	}
	
	public void setIsCircleChoosen(boolean isCircleChoosen) {
		this.isCircleChoosen = isCircleChoosen;
		String ImageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
		circleImage.setImage(new Image(ImageToSet));
	}
}
