package xyz.bingesurfing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
	private WebViewController controller;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("App.fxml"));
			AnchorPane root = (AnchorPane)fxmlLoader.load();
			controller = (WebViewController) fxmlLoader.getController();

			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.setTitle("Binge Surfing - " + Defaults.VERSION);
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		} // try
	}

	@Override
	public void stop() throws Exception {
		System.out.println("stop method");
		super.stop();
		controller.destroy();
	}



	public static void main(String[] args) {
		launch(args);
	}
}
