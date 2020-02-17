package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private static int size;
    private boolean[][] availableSpot;
    private Playground playground = new Playground(size);
    private Scanner sc;



    public Game(int size) {
        availableSpot = new boolean[size][size];
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                this.availableSpot[i][j] = true;
            }
        }

    }



    public void print(int select) {
        String info;
        switch (select) {
            case 0:
                info = "Wprowadz rozmiar boku planszy:";
                break;
            case 1:
                info = "Niepoprawna wartość!, minimalny rozmiar planszy to 5x5, maksymalny 10x10.";
                break;
            case 2:
                info = "Wprowadź współrzędne do strzału (pionowo poziomo):";
                break;
            case 3:
                info = "Strzały: " + this.playground.getShotsFired() + ", Zatopione statki: " + this.playground.getShipsSunk();
                break;
            case 4:
                info = "Gratulacje, wygrałeś!";
                break;
            case 5:
                info = "Niepoprawna wartość, wprowadź ponownie:";
                break;
            case 6:
                info = "--------------------------------------------";
                break;
            case 7:
                info = "\n============================================";
                break;
            default:
                info = "Błąd";
        }

        System.out.println(info);
    }

    public boolean checkValidInput(String input) {
        ArrayList<String> numList = new ArrayList();

        for(int i = 0; i < 10; ++i) {
            numList.add("" + i);
        }

        String[] coordinates = input.split(" ");
        if (coordinates.length != 2) {
            return false;
        } else {
            String[] coordinate = coordinates;
            int coordinatesLenght = coordinates.length;

            int column;
            for(column = 0; column < coordinatesLenght; ++column) {
                String str = coordinate[column];
                if (!numList.contains(str)) {
                    return false;
                }
            }

            int row = Integer.parseInt(coordinates[0]);
            column = Integer.parseInt(coordinates[1]);
            return this.availableSpot[row][column];
        }
    }


        public int[] getCoordinates(String input){
            int[] coordinates = new int[2];
            String[] strList = input.split(" ");
            int row = Integer.parseInt(strList[0]);
            int column = Integer.parseInt(strList[1]);
            coordinates[0] = row;
            coordinates[1] = column;
            return coordinates;
        }


    public void play(int size) {
        this.print(7);
        this.playground.placeAllShipsRandomly(size);
        boolean isGameOver = this.playground.isGameOver(size);
        this.sc = new Scanner(System.in);
        this.playground.print(size);
        this.print(3);

        while(!isGameOver) {
            this.print(2);

            String input;
            for(input = this.sc.nextLine(); !this.checkValidInput(input); input = this.sc.nextLine()) {
                this.print(5);
            }

            int[] coordinates = this.getCoordinates(input);
            int row = coordinates[0];
            int column = coordinates[1];
            this.playground.shootAt(row, column);
            this.availableSpot[row][column] = false;
            isGameOver = this.playground.isGameOver(size);
            this.playground.print(size);
            this.print(3);
            this.print(6);
        }

        this.print(4);

    }


        public static void sizeSetting(){
            System.out.println("Wprowadz rozmiar boku planszy:");
            Scanner sc = new Scanner(System.in);
            size = sc.nextInt();
            
            if (size < 5 || size > 10) {
                System.out.println("Niepoprawna wartość!, minimalny rozmiar planszy to 5x5, maksymalny 12x12.");
                sizeSetting();
            }
            
        }

    public static void main(String[] args) {

            sizeSetting();
            Game battleshipGame = new Game(size);
            battleshipGame.play(size);


    }
}
