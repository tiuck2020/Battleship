package com.company;

public class MissedShot extends Ship {
    public MissedShot() {
        this.length = 1;
    }

    public boolean shootAt(int row, int column) {
        this.hit[0] = true;
        return false;
    }

    public boolean isSunk() {
        return false;
    }

    public String toString() {
        return "-";
    }

    String getShipType() {
        return "empty";
    }
}

