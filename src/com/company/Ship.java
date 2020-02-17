package com.company;

public abstract class Ship {
    private int aimRow;
    private int aimColumn;
    protected int length;
    private boolean horizontal;
    protected boolean[] hit = new boolean[4];

    public Ship() {
    }

    public int getAimRow() {
        return this.aimRow;
    }

    public void setAimRow(int aimRow) {
        this.aimRow = aimRow;
    }

    public int getAimColumn() {
        return this.aimColumn;
    }

    public void setAimColumn(int aimColumn) {
        this.aimColumn = aimColumn;
    }

    public int getLength() {
        return this.length;
    }


    public boolean isHorizontal() {
        return this.horizontal;
    }
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }


    abstract String getShipType();


    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Playground playground, int size) {
        boolean okToPlace = true;
        boolean[][] shadows = playground.getShadow();
        int i;
        if (horizontal) {
            for(i = 0; i < this.getLength(); ++i) {
                if (column + i > (size-1)) {
                    okToPlace = false;
                } else if (shadows[row][column + i]) {
                    okToPlace = false;
                }
            }
        } else {
            for(i = 0; i < this.getLength(); ++i) {
                if (row + i > (size-1)) {
                    okToPlace = false;
                } else if (shadows[row + i][column]) {
                    okToPlace = false;
                }
            }
        }

        return okToPlace;
    }

    public void placeShipAt(int row, int column, boolean horizontal, Playground playground, int size) {
        this.setHorizontal(horizontal);
        this.setAimRow(row);
        this.setAimColumn(column);
        int i;
        if (!this.isHorizontal()) {
            for(i = 0; i < this.getLength(); ++i) {
                playground.placeShip(row + i, column, this, size);
            }
        } else {
            for(i = 0; i < this.getLength(); ++i) {
                playground.placeShip(row, column + i, this, size);
            }
        }

    }


    public boolean shootAt(int row, int column) {
        int i;
        if (this.isHorizontal()) {
            for(i = 0; i < this.getLength(); ++i) {
                if (this.getAimRow() == row && this.getAimColumn() + i == column) {
                    this.hit[i] = true;
                    return true;
                }
            }
        } else {
            for(i = 0; i < this.getLength(); ++i) {
                if (this.getAimRow() + i == row && this.getAimColumn() == column) {
                    this.hit[i] = true;
                    return true;
                }
            }
        }

        return false;
    }


    public boolean[] getHitArray() {
        return this.hit == null ? null : (boolean[])this.hit.clone();
    }


    public boolean isSunk() {
        boolean isSunk = true;

        for(int i = 0; i < this.getLength(); ++i) {
            isSunk = isSunk && this.hit[i];
        }

        return isSunk;
    }


    public String toString() {
        return this.isSunk() ? "x" : "c";
    }

}
