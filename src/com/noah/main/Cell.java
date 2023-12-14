package com.noah.main;

public class Cell {
	private int column, row;
	private CellType cellType;
	
	public void Cell(int column, int row) {
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
