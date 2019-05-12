package UI.view;

import java.util.Random;

import UI.SmallInfoLabel;
import UI.VEHICLE;
import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import logic.Vehicle;
import logic.vehicletype.f1;
import logic.vehicletype.normal;
import logic.vehicletype.pickup;
import logic.vehicletype.tank;

public class GameViewManager {
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;

	private static final int GAME_WIDTH = 600;
	private static final int GAME_HEIGHT = 700;

	private Stage menuStage;
	private ScoreViewManager saveStage;
	private Vehicle vehicle;

	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	private int angle;
	private AnimationTimer gameTimer;

	private GridPane gridPane1;
	private GridPane gridPane2;
	private final static String BACKGROUND_IMAGE = "file:res/image/background_game.png";

	private final static String OBSTACLE_1 = "file:res/image/obstacle.png";
	private final static String OBSTACLE_2 = "file:res/image/obstacle_1.png";

	private ImageView[] obstacle1;
	private ImageView[] obstacle2;
	Random randomPositionGenerator;

	private ImageView star;
	private SmallInfoLabel pointsLabel;
	private ImageView[] vehicleLifes;
	private int vehicleLife;
	private int points;
	private final static String GOLD_STAR_IMAGE = "file:res/image/star_gold.png";

	private final static int STAR_RADIUS = 12;
	private final static int OBSTACLE_RADIUS = 30;
	private final static int VEHICLE_RADIUS = 30;
	private Thread thread;

	AudioClip soundEndGame;
	AudioClip soundGainScore;
	AudioClip soundLoseLife;

	public GameViewManager() {
		initializeStage();
		createKeyListener();
		randomPositionGenerator = new Random();

		soundEndGame = new AudioClip("file:res/sound/sfx_lose.wav");
		soundGainScore = new AudioClip("file:res/sound/sfx_gainstar.wav");
		soundLoseLife = new AudioClip("file:res/sound/sfx_corride.wav");

	}

	final Task<?> task = new Task<Object>() {

		@Override
		protected Object call() throws Exception {
			AudioClip audio = new AudioClip("file:res/sound/Race-car-sounds.wav");
			audio.setVolume(0.5f);
			audio.play();
			return null;
		}
	};

	private void createKeyListener() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if (event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = true;
				} else if (event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = true;
				}
			}

		});
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = false;
				} else if (event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = false;
				}
			}

		});

	}

	private void initializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setResizable(false);
		gameStage.setScene(gameScene);

	}

	public void createNewGame(Stage menuStage, VEHICLE choosenVehicle) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBackground();
		createVehicle(choosenVehicle);
		thread = new Thread(task);
		thread.start();
		createGameElements(choosenVehicle);
		createGameLoop();
		gameStage.show();
	}

	private void createGameElements(VEHICLE choosenVehicle) {
		vehicleLife = vehicle.getHitpoint() - 1;
		star = new ImageView(GOLD_STAR_IMAGE);
		setNewElementPosition(star);
		gamePane.getChildren().add(star);
		pointsLabel = new SmallInfoLabel("POINTS : 00");
		pointsLabel.setLayoutX(460);
		pointsLabel.setLayoutY(20);
		gamePane.getChildren().add(pointsLabel);
		vehicleLifes = new ImageView[vehicleLife + 1];
		for (int i = 0; i < vehicleLifes.length; i++) {
			vehicleLifes[i] = new ImageView(choosenVehicle.getUrlLife());
			vehicleLifes[i].setFitHeight(40);
			vehicleLifes[i].setFitWidth(45);
			vehicleLifes[i].setLayoutX(555 - (i * 50));
			vehicleLifes[i].setLayoutY(80);
			gamePane.getChildren().add(vehicleLifes[i]);
		}

		obstacle1 = new ImageView[7];
		for (int i = 0; i < obstacle1.length; i++) {
			ImageView ob1 = new ImageView(OBSTACLE_1);
			ob1.setFitHeight(100);
			ob1.setFitWidth(100);
			obstacle1[i] = ob1;
			setNewElementPosition(obstacle1[i]);
			gamePane.getChildren().add(obstacle1[i]);
		}
		obstacle2 = new ImageView[7];
		for (int i = 0; i < obstacle2.length; i++) {
			ImageView ob2 = new ImageView(OBSTACLE_2);
			ob2.setFitHeight(80);
			ob2.setFitWidth(80);
			obstacle2[i] = ob2;
			setNewElementPosition(obstacle2[i]);
			gamePane.getChildren().add(obstacle2[i]);
		}
	}

	private void moveGameElements() {
		star.setLayoutY(star.getLayoutY() + 5);
		for (int i = 0; i < obstacle1.length; i++) {
			obstacle1[i].setLayoutY(obstacle1[i].getLayoutY() + vehicle.getVelocity());
		}
		for (int i = 0; i < obstacle2.length; i++) {
			obstacle2[i].setLayoutY(obstacle2[i].getLayoutY() + vehicle.getVelocity());
		}
	}

	private void checkIfElementsAreBehideTheShipAndRelocate() {
		if (star.getLayoutY() > 1200) {
			setNewElementPosition(star);
		}
		for (int i = 0; i < obstacle1.length; i++) {
			if (obstacle1[i].getLayoutY() > 900) {
				setNewElementPosition(obstacle1[i]);
			}

		}
		for (int i = 0; i < obstacle2.length; i++) {
			if (obstacle2[i].getLayoutY() > 900) {
				setNewElementPosition(obstacle2[i]);
			}
		}
	}

	private void setNewElementPosition(ImageView image) {
		image.setLayoutX(randomPositionGenerator.nextInt(370));
		image.setLayoutY(-(randomPositionGenerator.nextInt(3200) + 600));
	}

	private void createVehicle(VEHICLE choosenVehicle) {
		String type = choosenVehicle.gettypeVehicle();
		if (type.equals("F1")) {
			vehicle = new f1(choosenVehicle.geturl());
		} else if (type.equals("TANK")) {
			vehicle = new tank(choosenVehicle.geturl());
		} else if (type.equals("NORMAL")) {
			vehicle = new normal(choosenVehicle.geturl());
		} else if (type.equals("PICKUP")) {
			vehicle = new pickup(choosenVehicle.geturl());
		}
		gamePane.getChildren().add(vehicle);
	}

	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				moveBackground();
				moveGameElements();
				checkIfElementCorride();
				checkIfElementsAreBehideTheShipAndRelocate();
				moveShip();

			}

		};
		gameTimer.start();
	}

	private void moveShip() {
		if (isLeftKeyPressed && !isRightKeyPressed) {
			if (angle > -30) {
				angle -= 5;
			}
			vehicle.setRotate(angle);
			if (vehicle.getLayoutX() > -20) {
				vehicle.setLayoutX(vehicle.getLayoutX() - 3);
			}
		}
		if (isRightKeyPressed && !isLeftKeyPressed) {
			if (angle < 30) {
				angle += 5;
			}
			vehicle.setRotate(angle);
			if (vehicle.getLayoutX() < 522) {
				vehicle.setLayoutX(vehicle.getLayoutX() + 3);
			}
		}
		if (isLeftKeyPressed && isRightKeyPressed) {
			if (angle < 0) {
				angle += 5;
			} else if (angle > 0) {
				angle -= 5;
			}
			vehicle.setRotate(angle);
		}
		if (!isLeftKeyPressed && !isRightKeyPressed) {
			if (angle < 0) {
				angle += 5;
			} else if (angle > 0) {
				angle -= 5;
			}
			vehicle.setRotate(angle);
		}
	}

	private void createBackground() {
		gridPane1 = new GridPane();
		gridPane2 = new GridPane();

		for (int i = 0; i < 12; i++) {
			ImageView BackgroundImage1 = new ImageView(BACKGROUND_IMAGE);
			ImageView BackgroundImage2 = new ImageView(BACKGROUND_IMAGE);
			BackgroundImage1.setFitHeight(GAME_HEIGHT);
			BackgroundImage1.setFitWidth(GAME_WIDTH);
			BackgroundImage2.setFitHeight(GAME_HEIGHT);
			BackgroundImage2.setFitWidth(GAME_WIDTH);
			GridPane.setConstraints(BackgroundImage1, i % 3, i / 3);
			GridPane.setConstraints(BackgroundImage2, i % 3, i / 3);
			gridPane1.getChildren().add(BackgroundImage1);
			gridPane2.getChildren().add(BackgroundImage2);
		}
		gridPane2.setLayoutY(-1024);
		gamePane.getChildren().addAll(gridPane1, gridPane2);
	}

	private void moveBackground() {
		gridPane1.setLayoutY(gridPane1.getLayoutY() + vehicle.getVelocity());
		gridPane2.setLayoutY(gridPane2.getLayoutY() + vehicle.getVelocity());
		if (gridPane1.getLayoutY() >= 1024) {
			gridPane1.setLayoutY(-1024);
		}
		if (gridPane2.getLayoutY() >= 1024) {
			gridPane2.setLayoutY(-1024);
		}
	}

	private void checkIfElementCorride() {
		if (VEHICLE_RADIUS + STAR_RADIUS > calculateDistance(vehicle.getLayoutX() + 49, star.getLayoutX() + 15,
				vehicle.getLayoutY() + 37, star.getLayoutY() + 15)) {
			setNewElementPosition(star);
			points += 10;
			increaseLife();
			soundGainScore.play();
			String textToSet = "POINTS: ";
			if (points < 10) {
				textToSet = textToSet + "0";
			}
			pointsLabel.setText(textToSet + points);
		}
		for (int i = 0; i < obstacle1.length; i++) {
			if (VEHICLE_RADIUS + OBSTACLE_RADIUS > calculateDistance(vehicle.getLayoutX() + 10,
					obstacle1[i].getLayoutX() + 20, vehicle.getLayoutY() + 37, obstacle1[i].getLayoutY() + 20)) {
				removeLife();
				setNewElementPosition(obstacle1[i]);
				soundLoseLife.play();
			}
		}
		for (int i = 0; i < obstacle2.length; i++) {
			if (VEHICLE_RADIUS + OBSTACLE_RADIUS > calculateDistance(vehicle.getLayoutX() + 10,
					obstacle2[i].getLayoutX() + 20, vehicle.getLayoutY() + 37, obstacle2[i].getLayoutY() + 20)) {
				removeLife();
				setNewElementPosition(obstacle2[i]);
				soundLoseLife.play();
			}
		}
	}

	private void removeLife() {
		gamePane.getChildren().remove(vehicleLifes[vehicleLife]);
		vehicleLife--;
		if (vehicleLife < 0) {
			soundEndGame.play();
			saveStage = new ScoreViewManager();
			saveStage.createSaveScore(menuStage);
			
//			gameStage.close();
//			gameTimer.stop();
//			menuStage.show();
		}
	}

	private void increaseLife() {
		if (vehicleLife < vehicleLifes.length - 1) {
			vehicleLife++;
			gamePane.getChildren().add(vehicleLifes[vehicleLife]);
		}

	}

	private double calculateDistance(Double x1, Double x2, Double y1, Double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
}