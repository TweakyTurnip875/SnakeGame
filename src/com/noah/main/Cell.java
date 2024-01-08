package com.noah.main;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Cell extends Pane {
	private int column, row;
	private CellType cellType;
	private String styleSnakeNode = "-fx-background-color: #F5F5DC;";
	private String styleEmptyNode = "-fx-background-color: #202121;";
	private String styleFoodNode = "-fx-background-color: #bc8f8f;";
	
	public Cell(int column, int row) {
		this.setPrefSize(2000, 2000);
		updateStyles();
		this.column = column;
		this.row = row;
		
	}
	public void updateStyles() {
		if(cellType == CellType.EMPTY) {
			this.setStyle(styleEmptyNode);
		} else if(cellType == CellType.SNAKE_NODE) {
			this.setStyle(styleSnakeNode);
		} else if(cellType == CellType.FOOD) {
			this.setStyle(styleFoodNode);
		}
	}
	public CellType getCellType() {
		return cellType;
	}
	public void setCellType(CellType cellType) {
		this.cellType = cellType;
		updateStyles();
	}
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
}
