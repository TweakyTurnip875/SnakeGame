package com.noah.main;

import javafx.application.Application;
import java.util.concurrent.*; 
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Main extends Application {
	private boolean isRunning = true;
	private float interval = 200.0f;
	private boolean isFood = false;
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
		GridPane board = new GridPane();
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				board.add(cells[i][j] = new Cell(i, j), i, j);
				cells[i][j].setCellType(CellType.EMPTY);
			}
		}
		snake = new Snake(cells[5][5]);
		
		BorderPane pane = new BorderPane();
		pane.setCenter(board);

		updateThread.start();
		
		Scene scene = new Scene(pane, 400, 300);
		
		scene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.UP) {
				setDirection(DIRECTION_UP);
			} else if(e.getCode() == KeyCode.DOWN) {
				setDirection(DIRECTION_DOWN);
			} else if(e.getCode() == KeyCode.LEFT) {
				setDirection(DIRECTION_LEFT);
			} else if(e.getCode() == KeyCode.RIGHT) {
				setDirection(DIRECTION_RIGHT);
			}
		});
		
		stage.setTitle("Snake Game");
		stage.setScene(scene);
		stage.show();
	}
	Thread updateThread = new Thread(() -> {
		while(isRunning) {
			float time = System.currentTimeMillis();
			update();
				
			time = System.currentTimeMillis() - time;
				
			if(time < interval) {
				try {
					Thread.sleep((long)(interval - time));
				} catch(InterruptedException ex) {}
			}	
		}
	});

	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public void update() {
		if(direction != DIRECTION_NONE) {
			Cell nextCell = getNextCell(snake.getHead());
			
			if(nextCell.getCellType() == CellType.FOOD) {
				snake.grow();
				isFood = false;
			} 
			snake.move(nextCell);
		}
		int row = (int)(Math.random() * 9);
		int col = (int)(Math.random() * 9);

		if(!isFood && cells[col][row].getCellType() == CellType.EMPTY) {
			cells[col][row].setCellType(CellType.FOOD);
			isFood = true;
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
		Cell nextCell = cells[col][row];
		return nextCell;
	}
	public static void main(String[] args) {
		launch(args);
	}

}
