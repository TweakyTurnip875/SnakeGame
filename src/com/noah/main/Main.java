package com.noah.main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Main extends Application {
	private Snake snake;
	Cell[][] cells = new Cell[10][10];
	private static final int 
			DIRECTION_NONE = 0, 
			DIRECTION_UP = 1, 
			DIRECTION_DOWN = 2, 
			DIRECTION_LEFT = 3, 
			DIRECTION_RIGHT= 4;
	
	private int direction;
	
	@Override
	public void start(Stage stage) {
		setDirection(DIRECTION_RIGHT);
		GridPane board = new GridPane();
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				board.add(cells[i][j] = new Cell(i, j), i, j);
				cells[i][j].setCellType(CellType.EMPTY);
			}
		}
		snake = new Snake(cells[5][5]);
		cells[5][5].setCellType(CellType.SNAKE_NODE);
		snake.getHead().setStyle("-fx-background-color: black;");
		
		BorderPane pane = new BorderPane();
		pane.setCenter(board);
		
		Scene scene = new Scene(pane, 400, 300);
		
		stage.setTitle("Snake Game");
		stage.setScene(scene);
		stage.show();
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public void update() {
		if(direction != DIRECTION_NONE) {
			snake.getHead().setStyle("-fx-background-color: white;");
			Cell nextCell = getNextCell(snake.getHead());
			snake.move(nextCell);
		}
	}
	public Cell getNextCell(Cell currentLocation) {
		int row = currentLocation.getRow();
		int col = currentLocation.getColumn();
		
		if(direction == DIRECTION_RIGHT) {
			col++;
		} else if(direction == DIRECTION_LEFT) {
			col--;
		} else if(direction == DIRECTION_UP) {
			row--;
		} else if(direction == DIRECTION_DOWN) {
			row++;
		}
		Cell nextCell = cells[row][col];
		return nextCell;
	}
	public static void main(String[] args) {
		launch(args);
	}

}
