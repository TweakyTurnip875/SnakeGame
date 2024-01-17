<h1>SnakeGame</h1>
<h2>Synopsis</h2>
A snake game written in Java. The user plays as a snake that eats apples. The snake grows with the amount of apples it eats. The snake cannot crash into itself or a wall, otherwise as game over will occur.
<h2>Install Guide</h2>
You will need the Java programming language (https://www.java.com/en/) installed and the JavaFX (https://openjfx.io/) library added to your build path.

Here is a guide to installing JavaFX: https://docs.oracle.com/javafx/2/installation/jfxpub-installation.htm
<h2>Code Example</h2>
The following code snippet is responsible for updating the game state, spawning food for the snake to eat, and checking for a game over.

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
