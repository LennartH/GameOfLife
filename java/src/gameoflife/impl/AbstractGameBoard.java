package gameoflife.impl;

import gameoflife.Cell;
import gameoflife.GameBoard;
import gameoflife.InitialGenerationCreator;
import gameoflife.RuleApplier;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractGameBoard implements GameBoard {

    private Cell[][] board;
    private RuleApplier ruleApplier;

    public AbstractGameBoard(RuleApplier ruleApplier, InitialGenerationCreator initialGenerationCreator) {
        board = initialGenerationCreator.createInitialGeneration();
        this.ruleApplier = ruleApplier;
    }
    
    @Override
    public void evolve() {
        Cell[][] nextGeneration = new Cell[getHeight()][getWidth()];
        for (int heightIndex = 0; heightIndex < board.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < board[heightIndex].length; widthIndex++) {
                nextGeneration[heightIndex][widthIndex] = ruleApplier.apply(getCell(widthIndex, heightIndex), getNeighbours(widthIndex, heightIndex));
            }
        }
        board = nextGeneration;
    }

    protected Set<Cell> getNeighbours(int widthIndex, int heightIndex) {
        Set<Cell> neighbours = new HashSet<>();
        for (int h = heightIndex - 1; h <= heightIndex + 1; h++) {
            for (int w = widthIndex - 1; w <= widthIndex + 1; w++) {
                if (w == widthIndex && h == heightIndex) {
                    continue;
                }

                if (indicesAreOutOfBound(w, h)) {
                    neighbours.add(getCellOutOfBounds(w, h));
                    continue;
                }

                neighbours.add(getCell(w, h));
            }
        }
        return neighbours;
    }

    private boolean indicesAreOutOfBound(int widthIndex, int heightIndex) {
        return widthIndex >= getWidth() || widthIndex < 0 || heightIndex >= getHeight() || heightIndex < 0;
    }

    private Cell getCell(int widthIndex, int heightIndex) {
        return board[heightIndex][widthIndex];
    }

    protected abstract Cell getCellOutOfBounds(int w, int h);

    public Cell[][] getCells() {
        return board;
    }

    @Override
    public int getHeight() {
        return board.length;
    }

    @Override
    public int getWidth() {
        return board[0].length;
    }

}
