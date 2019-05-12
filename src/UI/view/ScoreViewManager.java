package UI.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScoreViewManager {
	private VBox vbox;
	private Stage saveScoreStage;
	private Stage menuStage;
	private Scene scene;
	private static final int GAME_WIDTH = 600;
	private static final int GAME_HEIGHT = 700;
	private TextField textField;
	private Label label;

	private Button saveButton;
	
	
	public ScoreViewManager() {
		textField = new TextField();
		textField.setPromptText("INPUT YOUR NAME");
		label = new Label("SAVE YOUR SCORE");
		
		vbox.getChildren().addAll(label, textField,saveButton);
		
		saveScoreStage = new Stage();
		vbox = new VBox();
		scene = new Scene(vbox, GAME_WIDTH, GAME_HEIGHT);
		saveScoreStage.setScene(scene);
	}
	

	public void createSaveScore(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		saveScoreStage.show();
	}
	public void createButtonSave() {
		saveButton = new Button("SAVE");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String name = textField.getText().trim();
				
			}
			
		});
	}
}
