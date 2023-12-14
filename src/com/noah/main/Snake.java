package com.noah.main;

import java.util.LinkedList;

public class Snake {
	LinkedList<Cell> snake = new LinkedList<>();
	private Cell head;
	
	public Snake(Cell startPosition) {
		head = startPosition;
		snake.add(head);
		head.setCellType(CellType.SNAKE_NODE);
	}
}
