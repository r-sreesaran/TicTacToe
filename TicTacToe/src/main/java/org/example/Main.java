package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    TicTacToe ticTacToe = new TicTacToe();

    public static void main(String[] args) {
        Main main = new Main();
        main.startGame();
    }


    public  Boolean gameLogic(String player) {

        Scanner sc = new Scanner(System.in);

        int row = 0;
        int column = 0;
        try {
            row = sc.nextInt()-1;
            column = sc.nextInt()-1;
        } catch (NumberFormatException e) {
            gameLogic(player);
        }

        if(row>2||column>2||row<0||column<0) {
            System.out.println("Coordinates should be from 1 to 3!");
            gameLogic(player);
        }
        else {
            String[][] grid = ticTacToe.getGrid();
            if(!grid[row][column].equals(" ")) {
                System.out.println("This cell is occupied! Choose another one!");
                gameLogic(player);

            }
            else {
                ticTacToe.setValue(row,column,player);
                System.out.println(ticTacToe);
            }
        }
        return true;
    }

    public  void startGame() {
        String player="X";

        for (int row=0;row<3;row++) {
            for(int column=0;column<3;column++) {
                ticTacToe.setValue(row,column," ");
            }
        }

        System.out.println(ticTacToe);

        while (gameLogic(player)) {

            if(!ticTacToe.gameRules()) {
                return;
            }

            switch (player)
            {
                case "X":
                    player = "O";
                    break;
                case "O":
                    player = "X";
                    break;
            }
        }

    }



}
