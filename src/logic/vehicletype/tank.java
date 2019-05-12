package logic.vehicletype;

import javafx.scene.image.Image;
import logic.Vehicle;
import logic.VehicleProperties;

public class tank extends Vehicle implements VehicleProperties {
	
	public tank(String url) {

		setVelocity();
		setHitpoint();
		setImage(new Image(url));
		setConfig();
		this.type = "tank";


	}

	@Override
	public void setVelocity() {
		this.velocity = 5;
	}

	@Override
	public void setHitpoint() {
		this.hitpoint = 5;

	}

	@Override
	public void setConfig() {
		
		setFitHeight(100);
		setFitWidth(100);
		setLayoutX(300);
		setLayoutY(500);

	}


}
