import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent t) {
            	if(thread != null) {
            		System.out.println("thread is alive");
            		thread.interrupt();
//            		BlackListEditor blackListEditor = new BlackListEditor();
//            		try {
//						blackListEditor.deleteHosts();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
            	}
                Platform.exit();
            }

        });
	}
	
    @FXML
    public Button startButton;
    Thread thread;
    
	@FXML
    void handleStartCount(ActionEvent event) throws IOException {
		startButton.setDisable(true);
//		String addBlockedSite = "echo '192.0.0.1 www.facebook.com' >> /Users/Oskilla/Desktop/hello";
		//			Runtime.getRuntime().exec(addBlockedSite);
//		List<String> insertLines = Arrays.asList("192.0.0.1 www.facebook.com");
//		Path path = Paths.get("/Users/Oskilla/Desktop/hello.rtf");
//		System.out.println(path);
//		Files.write(path, insertLines);
		thread = new Thread(new ThreadRunner(this));
    	thread.start();
    }
    
	public static void main(String[] args) {
		launch(args);
	}

}
