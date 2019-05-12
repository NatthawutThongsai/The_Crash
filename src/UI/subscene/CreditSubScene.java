package UI.subscene;


import UI.view.TheCrashSubScene;
import javafx.scene.image.ImageView;


public class CreditSubScene extends TheCrashSubScene {
	ImageView creditImage;

	public CreditSubScene() {
		creditImage = new ImageView("file:res/image/credit.png");
		creditImage.setFitWidth(THE_CRASH_SUBSCENE_WIDTH-200);
		creditImage.setFitHeight(THE_CRASH_SUBSCENE_HEIGHT-200);
		creditImage.setLayoutX(50);
		creditImage.setLayoutY(50);
		getPane().getChildren().add(creditImage);
	}
}
