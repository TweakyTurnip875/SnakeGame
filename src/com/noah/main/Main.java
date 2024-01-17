package com.noah.main;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class Main extends Application {
	private boolean isRunning = true;
	private float interval = 100.0f;
	private boolean isFood = false;
	private Snake snake;
	Cell[][] cells = new Cell[20][20];
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
		
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				board.add(cells[i][j] = new Cell(i, j), i, j);
				cells[i][j].setCellType(CellType.EMPTY);
			}
		}
		snake = new Snake(cells[0][0]);
		
		BorderPane pane = new BorderPane();
		pane.setCenter(board);

		updateThread.start();
		
		Scene scene = new Scene(pane, 400, 350);
		
		scene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.UP && getDirection() != DIRECTION_DOWN) {
				setDirection(DIRECTION_UP);
			} else if(e.getCode() == KeyCode.DOWN && getDirection() != DIRECTION_UP) {
				setDirection(DIRECTION_DOWN);
			} else if(e.getCode() == KeyCode.LEFT && getDirection() != DIRECTION_RIGHT) {
				setDirection(DIRECTION_LEFT);
			} else if(e.getCode() == KeyCode.RIGHT && getDirection() != DIRECTION_LEFT) {
				setDirection(DIRECTION_RIGHT);
			}
		});
		
		setDirection(DIRECTION_RIGHT);
		
		stage.setTitle("Snake Game");
		stage.setScene(scene);
		stage.setResizable(false);
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
		try {
			if(direction != DIRECTION_NONE) {
				Cell nextCell = getNextCell(snake.getHead());
				
				if(nextCell.getCellType() == CellType.FOOD) {
					snake.grow();
					nextCell.setCellType(CellType.SNAKE_NODE);
					isFood = false;
				} else if(nextCell.getCellType() == CellType.SNAKE_NODE) {
					isRunning = false;
					snake.getHead().setStyle("-fx-background-color:red;");
				}
				snake.move(nextCell);
			}
			int row = (int)(Math.random() * 19);
			int col = (int)(Math.random() * 19);
	
			if(!isFood && cells[col][row].getCellType() == CellType.EMPTY) {
				cells[col][row].setCellType(CellType.FOOD);
				isFood = true;
			}
		}  catch(ArrayIndexOutOfBoundsException ex) {
			isRunning = false;
			snake.getHead().setStyle("-fx-background-color: red;");
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
