package logic;

import javafx.scene.image.ImageView;

public abstract class Vehicle extends ImageView{
	protected int velocity;
	protected int hitpoint;
	protected int vehicleRadius;
	protected String type;
	
	public abstract void setVelocity();
	public abstract void setHitpoint();

	public int getVelocity() {
		return this.velocity;
	}
	public int getHitpoint() {
		return this.hitpoint;
	}
	public String getType() {
		return this.type;
	}


}
