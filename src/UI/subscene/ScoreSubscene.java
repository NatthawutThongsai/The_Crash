package UI.subscene;

import UI.view.TheCrashSubScene;

public class ScoreSubscene extends TheCrashSubScene{
	private ScoreLogPane scorePane;
	public ScoreSubscene() {
		scorePane = new ScoreLogPane();
		scorePane.setPrefWidth(THE_CRASH_SUBSCENE_WIDTH-100);
		scorePane.setPrefHeight(THE_CRASH_SUBSCENE_HEIGHT-100);
		scorePane.setLayoutX(50);
		scorePane.setLayoutY(50);
		getPane().getChildren().add(scorePane);
	}
	public ScoreLogPane getScoreLogPane() {
		return this.scorePane;
	}

}
