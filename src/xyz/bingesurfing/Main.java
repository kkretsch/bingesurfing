package xyz.bingesurfing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("App.fxml"));
			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Binge Surfing");
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}



	}

	public static void main(String[] args) {
		launch(args);
	}
}
