package com.noah.main;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		GridPane board = new GridPane();
		
		Scene scene = new Scene(board, 400, 300);
		
		stage.setTitle("Snake Game");
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}

}
