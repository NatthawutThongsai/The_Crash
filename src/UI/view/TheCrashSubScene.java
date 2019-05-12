package UI.view;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class TheCrashSubScene extends SubScene {

	private final static String BACKGROUND_IMAGE = "file:res/image/yellow_panel.png";
	private boolean isHidden;
	protected static final int THE_CRASH_SUBSCENE_WIDTH = 600;
	protected static final int THE_CRASH_SUBSCENE_HEIGHT = 400;

	public TheCrashSubScene() {
		super(new AnchorPane(), THE_CRASH_SUBSCENE_WIDTH, THE_CRASH_SUBSCENE_HEIGHT);
		prefHeight(THE_CRASH_SUBSCENE_WIDTH);
		prefWidth(THE_CRASH_SUBSCENE_HEIGHT);
		BackgroundImage background = new BackgroundImage(new Image(BACKGROUND_IMAGE, THE_CRASH_SUBSCENE_WIDTH, THE_CRASH_SUBSCENE_HEIGHT, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		AnchorPane root2 = (AnchorPane) this.getRoot();
		root2.setBackground(new Background(background));
		isHidden = true;
		setLayoutX(1200);
		setLayoutY(180);
	}

	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		if (isHidden) {
			transition.setToX(-830);
			isHidden = false;
		} else {
			transition.setToX(0);
			isHidden = true;
		}

		transition.play();
	}

	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}

}
