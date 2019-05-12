package UI.subscene;

import UI.view.TheCrashSubScene;
import javafx.scene.image.ImageView;

public class HelpSubScene extends TheCrashSubScene {
	ImageView helpImage;

	public HelpSubScene() {
		helpImage = new ImageView("file:res/image/help.png");
		helpImage.setFitWidth(THE_CRASH_SUBSCENE_WIDTH - 100);
		helpImage.setFitHeight(THE_CRASH_SUBSCENE_HEIGHT - 100);
		helpImage.setLayoutX(50);
		helpImage.setLayoutY(25);
		getPane().getChildren().add(helpImage);
	}
}