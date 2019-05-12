package logic.vehicletype;

import javafx.scene.image.Image;
import logic.Vehicle;
import logic.VehicleProperties;

public class normal extends Vehicle implements VehicleProperties {
	
	public normal(String url) {

		setVelocity();
		setHitpoint();
		setImage(new Image(url));
		setConfig();
		this.type = "normal";


	}

	@Override
	public void setVelocity() {
		this.velocity = 8;
	}

	@Override
	public void setHitpoint() {
		this.hitpoint = 3;

	}

	@Override
	public void setConfig() {
		
		setFitHeight(100);
		setFitWidth(100);
		setLayoutX(300);
		setLayoutY(500);

	}

}
