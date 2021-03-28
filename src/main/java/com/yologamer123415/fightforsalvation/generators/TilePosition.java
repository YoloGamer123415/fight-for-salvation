package com.yologamer123415.fightforsalvation.generators;

public class TilePosition {
	private final int row;
	private final int line;

	public TilePosition(int row, int line) {
		this.row = row;
		this.line = line;
	}

	public int getRow() {
		return row;
	}

	public int getLine() {
		return line;
	}
}