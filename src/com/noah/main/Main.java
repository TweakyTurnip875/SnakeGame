package com.noah.main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	Cell[][] cell = new Cell[10][10];
	
	@Override
	public void start(Stage stage) {
		GridPane board = new GridPane();
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				board.add(cell[i][j] = new Cell(i, j), i, j);
				cell[i][j].setStyle("-fx-border-color: black;");
			}
		}
		
		Scene scene = new Scene(board, 400, 300);
		
		stage.setTitle("Snake Game");
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}

}
