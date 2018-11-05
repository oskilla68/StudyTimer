import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenu extends Application {

	public boolean showWarning = false;
	
	public long totalTime = 120;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Study Timer");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
		fxmlLoader.setController(this);
		try {
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root);
            primaryStage.setScene(sc);
            primaryStage.show();
            primaryStage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
    @FXML
    public Button startButton;
    
	@FXML
    void handleStartCount(ActionEvent event) {
		startButton.setDisable(true);
		Thread thread = new Thread(new ThreadRunner(this));
    	thread.start();
    	return;
    }
    
	public static void main(String[] args) {
		launch(args);
	}

}
