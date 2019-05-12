package UI.subscene;

import java.util.ArrayList;
import java.util.List;

import UI.InfoLabel;
import UI.TheCrashButton;
import UI.VEHICLE;
import UI.VehiclePicker;
import UI.view.GameViewManager;
import UI.view.TheCrashSubScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CreateVehicleChooserSubScene extends TheCrashSubScene {
	private InfoLabel chooseShipLabel;

	List<VehiclePicker> vehicleList;
	private VEHICLE chooserVehicle;
	private Stage mainStage;
	private ScoreLogPane scoreLogPane;

	public CreateVehicleChooserSubScene(VEHICLE chooserVehicle, Stage mainStage,ScoreLogPane scoreLogPane) {
		this.chooserVehicle = chooserVehicle;
		this.mainStage = mainStage;
		this.scoreLogPane = scoreLogPane;
		chooseShipLabel = new InfoLabel("CHOOSE YOUR VEHICLE");
		chooseShipLabel.setLayoutX(110);
		chooseShipLabel.setLayoutY(25);
		getPane().getChildren().add(chooseShipLabel);
		getPane().getChildren().add(createShipToChoose());
		getPane().getChildren().add(createButtonToStart());
	}

	public ScoreLogPane getScoreLogPane() {
		return this.scoreLogPane;
	}
	private HBox createShipToChoose() {
		HBox box = new HBox();
		box.setSpacing(20);
		vehicleList = new ArrayList<>();
		for (VEHICLE vehicle : VEHICLE.values()) {
			VehiclePicker vehicleToPick = new VehiclePicker(vehicle);
			box.getChildren().add(vehicleToPick);
			vehicleList.add(vehicleToPick);
			vehicleToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {

					for (VehiclePicker vehicle : vehicleList) {
						vehicle.setIsCircleChoosen(false);
					}
					vehicleToPick.setIsCircleChoosen(true);
					chooserVehicle = vehicleToPick.getVehicle();
				}

			});
		}
		box.setLayoutX(300 - (118 * 2));
		box.setLayoutY(150);
		return box;
	}

	private TheCrashButton createButtonToStart() {
		TheCrashButton startButton = new TheCrashButton("START");
		startButton.setLayoutX(350);
		startButton.setLayoutY(300);
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (chooserVehicle != null) {
					GameViewManager gameManager = new GameViewManager();
					gameManager.createNewGame(mainStage, chooserVehicle);
				}
			}

		});
		return startButton;
	}

}
