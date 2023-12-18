package com.noah.main;

import javafx.scene.layout.*;

public class Cell extends Pane {
	private int column, row;
	private CellType cellType;
	
	public Cell(int column, int row) {
		this.setPrefSize(2000, 2000);
		this.column = column;
		this.row = row;
		
	}
	public CellType getCellType() {
		return cellType;
	}
	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
}
