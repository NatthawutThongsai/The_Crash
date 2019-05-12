package logic.vehicletype;

import javafx.scene.image.Image;
import logic.Vehicle;
import logic.VehicleProperties;

public class f1 extends Vehicle implements VehicleProperties {
	
	public f1(String url) {

		setVelocity();
		setHitpoint();
		setImage(new Image(url));
		setConfig();
		this.type ="f1";

	}

	@Override
	public void setVelocity() {
		this.velocity = 10;
	}

	@Override
	public void setHitpoint() {
		this.hitpoint = 2;

	}

	@Override
	public void setConfig() {
		
		setFitHeight(100);
		setFitWidth(100);
		setLayoutX(300);
		setLayoutY(500);

	}

}
