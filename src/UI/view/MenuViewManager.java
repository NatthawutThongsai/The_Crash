package UI.view;

import java.util.ArrayList;
import java.util.List;

import UI.TheCrashButton;
import UI.VEHICLE;
import UI.subscene.CreateVehicleChooserSubScene;
import UI.subscene.CreditSubScene;
import UI.subscene.HelpSubScene;
import UI.subscene.ScoreLogPane;
import UI.subscene.ScoreSubscene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

public class MenuViewManager {

	private static final int HEIGHT = 650;
	private static final int WIDTH = 1024;

	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;

	private TheCrashSubScene creditsSubScene;
	private TheCrashSubScene helpSubScene;
	private TheCrashSubScene scoreSubScene;
	private TheCrashSubScene vehicleChooserScene;
	private TheCrashSubScene screenToHide;
	
	private ScoreLogPane scoreLogPane;

	private final static int MENU_BUTTONS_START_X = 100;
	private final static int MENU_BUTTONS_START_Y = 150;

	List<TheCrashButton> menuButtons;
	private VEHICLE chooserVehicle;

	public MenuViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		CreateSubScene();
		createButton();
		createBackground();
		mainStage.setScene(mainScene);
		mainStage.setResizable(false);
		mainStage.setTitle("THE CRASH!!");
		mainStage.getIcons().add(new Image("file:res/image/vehicle/red_car.png"));

	}

	private void CreateSubScene() {
		creditsSubScene = new CreditSubScene();
		helpSubScene = new HelpSubScene();
		scoreSubScene = new ScoreSubscene();
		scoreLogPane = ((ScoreSubscene) scoreSubScene).getScoreLogPane();
		vehicleChooserScene = new CreateVehicleChooserSubScene(chooserVehicle,mainStage,scoreLogPane);
		mainPane.getChildren().addAll(vehicleChooserScene,creditsSubScene,helpSubScene,scoreSubScene);
	

		
	}

	private void createButton() {
		createStartButton();
		createScoresButton();
		createHelpButton();
		creatCreditsButton();
		createExitButton();

	}

	private void showSubScene(TheCrashSubScene subScene) {
		if (screenToHide != null) {
			screenToHide.moveSubScene();
		}
		subScene.moveSubScene();
		screenToHide = subScene;
	}

	private void createStartButton() {
		TheCrashButton startButton = new TheCrashButton("PLAY");
		AddMenuButton(startButton);

		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(vehicleChooserScene);

			}

		});
	}

	private void createScoresButton() {
		TheCrashButton scoreButton = new TheCrashButton("SCORES");
		AddMenuButton(scoreButton);

		scoreButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				showSubScene(scoreSubScene);
			}

		});
	}

	private void createHelpButton() {
		TheCrashButton helpButton = new TheCrashButton("HELP");
		AddMenuButton(helpButton);
		helpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				showSubScene(helpSubScene);
			}

		});
	}

	private void creatCreditsButton() {
		TheCrashButton creditsButton = new TheCrashButton("CREDITS");
		AddMenuButton(creditsButton);

		creditsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				showSubScene(creditsSubScene);

			}

		});
	}

	private void createExitButton() {
		TheCrashButton exitButton = new TheCrashButton("EXIT");
		AddMenuButton(exitButton);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
			}

		});
	}

	private void AddMenuButton(TheCrashButton button) {
		button.setLayoutX(MENU_BUTTONS_START_X);
		button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}

	public Stage getMainStage() {
		return mainStage;

	}

	private void createBackground() {
		Image backgroundImage = new Image("file:res/image/background.png", 1024, 650, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}

}
