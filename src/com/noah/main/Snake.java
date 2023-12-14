package com.noah.main;

import java.util.LinkedList;

public class Snake {
	private LinkedList<Cell> snake = new LinkedList<>();
	private Cell head;
	
	public Snake(Cell startPosition) {
		head = startPosition;
		snake.add(head);
		head.setCellType(CellType.SNAKE_NODE);
	}
	public void grow() {
		snake.add(head);
	}
	public void move(Cell next) {
		Cell tail = snake.removeLast();
		tail.setCellType(CellType.EMPTY);
		
		head = next;
		head.setCellType(CellType.SNAKE_NODE);
		snake.addFirst(head);
	}
}
