package UI.subscene;

import static javafx.scene.layout.BorderStrokeStyle.SOLID;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class ScoreLogPane extends StackPane {

	private ObservableList<Label> logDataList = FXCollections.observableArrayList();
	private ListView<Label> logListView;
	private int count = 1;

	public ScoreLogPane() {

		setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		logListView = new ListView<Label>(logDataList);
		logListView.setPrefWidth(400);
		logListView.setFocusTraversable(false);
		logListView.setPlaceholder(new Label("No Logs"));

		getChildren().add(logListView);
	}

	public void addData(String name, int score) {
		Label newLabel = new Label("Log #" + count + ": " + name + "    " + score);
		count++;
		logDataList.add(newLabel);
		logListView.scrollTo(newLabel);
	}

}
