package gameoflife.impl;

import gameoflife.Cell;
import gameoflife.CellState;

public class StarvationRuleApplier extends AbstractRuleApplier {

	@Override
	public Cell apply(Cell cell, Iterable<Cell> neighbours) {
		return amountOfLivingCells(neighbours) < 2 ? new SimpleCell(CellState.DEAD) : cell;
	}

}