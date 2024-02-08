package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int filledCells=0;

    public static void main(String[] args) {
        gameLogic();
    }

    public static void printGameState(String[][] grid) {
        System.out.println("---------");
        for(int row=0; row<3;row++) {
            System.out.print("| ");
            for(int column=0;column<3;column++) {
                if(grid[row][column]!=null) {
                    System.out.print(grid[row][column]+" ");
                }
                else {
                    System.out.print("  ");
                }
            }
            System.out.println("|");

        }
        System.out.println("---------");
    }

    public static Boolean isGameOver(String[][] grid) {
        /**
         * iterate over the rows
         * iterate over columns
         * iterate over diagonals
         */

        int x_counter=0;
        int o_counter=0;
        boolean is_empty_squares_present=false;

        boolean x_has_one_strike=false;
        boolean o_has_one_strike=false;

        int x_counter_column=0;
        int o_counter_column=0;

        int xcount=0;
        int ocount=0;



        for(int row=0;row<grid.length;row++) {
            x_counter_column=0;
            o_counter_column=0;
            x_counter=0;
            o_counter=0;
            for (int column=0;column<grid[row].length;column++) {

                if(grid[column][row].equalsIgnoreCase("X")) {
                    x_counter_column++;
                    o_counter_column=0;
                    xcount++;
                }
                if(grid[column][row].equalsIgnoreCase("O")) {
                    o_counter_column++;
                    x_counter_column=0;
                    ocount++;
                }

                if(grid[row][column].equalsIgnoreCase("X")){
                    x_counter++;
                    o_counter=0;
                }
                else if(grid[row][column].equalsIgnoreCase("O")){
                    o_counter++;
                    x_counter=0;
                }
                else if (grid[row][column].isBlank()){
                    is_empty_squares_present = true;
                }


            }
            if(x_counter==3||x_counter_column==3) {
                x_has_one_strike = true;
            }
            if(o_counter==3||o_counter_column==3) {
                o_has_one_strike = true;
            }
        }

        x_counter=0;
        o_counter=0;


        // diagonal processing
        for(int diagonal_pos=0;diagonal_pos<3;diagonal_pos++){
            if(grid[diagonal_pos][diagonal_pos].equalsIgnoreCase("X")){
                x_counter++;
                o_counter=0;

            }
            else if(grid[diagonal_pos][diagonal_pos].equalsIgnoreCase("O")){
                o_counter++;
                x_counter=0;

            }
        }

        if(x_counter==3) {
            x_has_one_strike = true;
        }
        if(o_counter==3) {
            o_has_one_strike = true;
        }

        x_counter=0;
        o_counter=0;

        for(int diagonal_pos_row=0,diagonal_pos_column=2;diagonal_pos_row<3;diagonal_pos_row++,diagonal_pos_column--) {
            if(grid[diagonal_pos_row][diagonal_pos_column].equalsIgnoreCase("X")){
                x_counter++;
                o_counter=0;
            }
            else if(grid[diagonal_pos_row][diagonal_pos_column].equalsIgnoreCase("O")){
                o_counter++;
                x_counter=0;
            }
        }

        if(x_counter==3) {
            x_has_one_strike = true;
        }
        if(o_counter==3) {
            o_has_one_strike = true;
        }


        if((x_has_one_strike&&o_has_one_strike)) {
            System.out.println("Impossible");
            return true;
        }

        if(x_has_one_strike) {
            System.out.println("X wins");
            return true;
        }

        if(o_has_one_strike) {
            System.out.println( "O wins");
            return true;
        }

        if( Math.abs(xcount-ocount)>1) {
            System.out.println( "Impossible");
            return true;
        }

        if(!is_empty_squares_present) {
            System.out.println( "Draw");
            return true;
        }

        return false;
    }

    public static Boolean currentGameState(String[][] grid,String player) {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int position[] = null;
        try {
            position   = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException e) {
            currentGameState(grid,player);
        }

        int x_coordinate = position[0]-1;
        int y_coordinate = position[1]-1;



        if(x_coordinate>2||y_coordinate>2||x_coordinate<0||y_coordinate<0) {
            System.out.println("Coordinates should be from 1 to 3!");
            currentGameState(grid,player);
        }
        else {
            if(!grid[x_coordinate][y_coordinate].isBlank()) {
                System.out.println("This cell is occupied! Choose another one!");
                currentGameState(grid,player);

            }
            else {
                grid[x_coordinate][y_coordinate]=player;
                filledCells++;
                printGameState(grid);
                if(filledCells==8) {
                    return  currentGameState(grid,player);
                    // return false;
                }
            }
        }
        return true;
    }

    public static void printEmpty() {
        System.out.println("---------");
        for(int row=0; row<3;row++) {
            System.out.print("| ");
            for(int column=0;column<3;column++) {
                System.out.print("  ");
            }
            System.out.println("|");

        }
        System.out.println("---------");
    }

    public static void gameLogic() {
        printEmpty();
        String[][] intial_grid = new String[3][3];
        String player="X";

        for (int row=0;row<3;row++) {
            for(int column=0;column<3;column++) {
                intial_grid[row][column]=" ";

            }        }

        while (currentGameState(intial_grid,player)) {

            switch (player)
            {
                case "X":
                    player = "O";
                    break;
                case "O":
                    player = "X";
                    break;
            }

            if(isGameOver(intial_grid)) {
                return;
            }

        }

    }



}
