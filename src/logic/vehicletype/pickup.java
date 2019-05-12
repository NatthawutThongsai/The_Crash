package logic.vehicletype;

import javafx.scene.image.Image;
import logic.Vehicle;
import logic.VehicleProperties;

public class pickup extends Vehicle implements VehicleProperties {

	public pickup(String url) {

		setVelocity();
		setHitpoint();
		setImage(new Image(url));
		setConfig();
		this.type = "pickup";

	}

	@Override
	public void setVelocity() {
		this.velocity = 7;
	}

	@Override
	public void setHitpoint() {
		this.hitpoint = 4;

	}

	@Override
	public void setConfig() {

		setFitHeight(100);
		setFitWidth(100);
		setLayoutX(300);
		setLayoutY(500);

	}

}
