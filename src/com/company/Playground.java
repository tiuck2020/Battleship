package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Playground {

    private Ship[][] ships;
    private int shotsFired;
    private int hitCount;
    private int shipsSunk;
    private int shipsQuantity;
    private static final Random random = new Random();
    private boolean[][] shadow;
    private Ship cruiser = new Cruiser();
    private Ship frigate1 = new Frigate();
    private Ship frigate2 = new Frigate();
    private Ship submarine1 = new Submarine();
    private Ship submarine2 = new Submarine();
    private Ship submarine3 = new Submarine();
    private Ship sailboat1 = new Sailboat();
    private Ship sailboat2 = new Sailboat();
    private Ship sailboat3 = new Sailboat();
    private Ship sailboat4 = new Sailboat();
    private ArrayList<Ship> allShips = new ArrayList();


    public Playground(int size) {

        if (size < 9) {
            shipsQuantity = 5;
            this.allShips.add(this.submarine1);
            this.allShips.add(this.submarine2);
            this.allShips.add(this.sailboat1);
            this.allShips.add(this.sailboat2);
            this.allShips.add(this.sailboat3);
        } else {
            shipsQuantity = 10;
            this.allShips.add(this.cruiser);
            this.allShips.add(this.frigate1);
            this.allShips.add(this.frigate2);
            this.allShips.add(this.submarine1);
            this.allShips.add(this.submarine2);
            this.allShips.add(this.submarine3);
            this.allShips.add(this.sailboat1);
            this.allShips.add(this.sailboat2);
            this.allShips.add(this.sailboat3);
            this.allShips.add(this.sailboat4);

        }

            this.ships = new Ship[size][size];
            this.shadow = new boolean[size][size];

            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    this.ships[i][j] = new MissedShot();
                    this.ships[i][j].setAimRow(i);
                    this.ships[i][j].setAimColumn(j);
                    this.ships[i][j].setHorizontal(true);
                    this.shadow[i][j] = false;
                }
            }

            this.shotsFired = 0;
            this.hitCount = 0;
            this.shipsSunk = 0;
        }


    public void placeAllShipsRandomly(int size) {
        Iterator iterator1 = this.allShips.iterator();

        while(iterator1.hasNext()) {
            Ship ship = (Ship)iterator1.next();
            int row = random.nextInt(size);
            int column = random.nextInt(size);
            int trueOrFalse = random.nextInt(2);
            boolean horizontal = false;
            if (trueOrFalse == 1) {
                horizontal = true;
            } else {
                horizontal = false;
            }

            while(!ship.okToPlaceShipAt(row, column, horizontal, this, size)) {
                row = random.nextInt(size);
                column = random.nextInt(size);
                trueOrFalse = random.nextInt(2);
                if (trueOrFalse == 1) {
                    horizontal = true;
                } else {
                    horizontal = false;
                }
            }

            ship.placeShipAt(row, column, horizontal, this, size);
        }

    }


        public boolean isOccupied(int row, int column) {
            return !this.ships[row][column].getShipType().equals("empty");
        }

    public boolean shootAt(int row, int column) {
        boolean hit = false;
        int sunkNum = 0;
        if (this.isOccupied(row, column) && !this.ships[row][column].isSunk()) {
            ++this.hitCount;
            hit = true;
        }

        ++this.shotsFired;
        this.ships[row][column].shootAt(row, column);
        Iterator iterator2 = this.allShips.iterator();

        while(iterator2.hasNext()) {
            Ship ship = (Ship)iterator2.next();
            if (ship.isSunk()) {
                ++sunkNum;
            }
        }

        this.shipsSunk = sunkNum;
        if (hit) {
            return true;
        } else {
            return false;
        }
    }

    public int getShotsFired() {
        return this.shotsFired;
    }

    public int getHitCount() {
        return this.hitCount;
    }

    public int getShipsSunk() {
        return this.shipsSunk;
    }

    public boolean isGameOver(int size) {
        return this.shipsSunk == shipsQuantity;
    }

    public Ship[][] getShipArray() {
        return this.ships == null ? null : (Ship[][])this.ships.clone();
    }

    public void print(int size) {
        String s = " ";

        for(int i = -1; i < size; ++i) {
            for(int j = -1; j < size; ++j) {
                if (i == -1) {
                    if (j > -1) {
                        s = s + "  " + j;
                    }
                } else if (j == -1) {
                    s = s + i + "  ";
                } else if (!this.isHit(i, j)) {
                    s = s + ".  ";
                } else {
                    s = s.concat(this.ships[i][j].toString() + "  ");
                }
            }

            s = s + "\n";
        }

        System.out.println(s);
    }


        public boolean[][] getShadow() {
            return this.shadow == null ? null : (boolean[][])this.shadow.clone();
        }

        public void setShadow(int size) {
            for(int i = 0; i < size; ++i) {
                for(int j = 0; j < size; ++j) {
                    if (this.isOccupied(i, j)) {
                        for(int k = -1; k < 2; ++k) {
                            for(int l = -1; l < 2; ++l) {
                                if (i + k >= 0 && i + k <= (size-1) && j + l >= 0 && j + l <= (size-1)) {
                                    this.shadow[i + k][j + l] = true;
                                }
                            }
                        }
                    }
                }
            }

        }


        public void placeShip(int row, int column, Ship ship, int size) {
            this.ships[row][column] = ship;
            this.setShadow(size);
        }


    public ArrayList<Ship> getAllShips() {
        return this.allShips;
    }

    public boolean isHit(int row, int column) {
        Ship ship = this.ships[row][column];
        int bowRow = ship.getAimRow();
        int bowColumn = ship.getAimColumn();
        if (ship.getShipType().equals("empty")) {
            return ship.getHitArray()[0];
        } else if (ship.isHorizontal()) {
            return ship.getHitArray()[column - bowColumn];
        } else {
            return ship.getHitArray()[row - bowRow];
        }
    }

}
