package org.example;

public class TicTacToe {


    public String[][] grid = new String[3][3];

    public String[][] getGrid() {
        return grid;
    }

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }

    @Override
    public String toString() {
        return String.format(
                "---------%n" +
                        "| %s %s %s |%n"+
                        "| %s %s %s |%n"+
                        "| %s %s %s |%n"+
                        "---------%n",grid[0][0],grid[0][1],grid[0][2],grid[1][0],grid[1][1],grid[1][2],grid[2][0],grid[2][1],grid[2][2]);


    }

    /**
     * Game Validations
     *
     */
    public Boolean gameRules() {

        GameState state = checkRow();
        if(state!=GameState.GNO){
            System.out.println(state.getValue());
            return false;
        }

        state = checkColumn();
        if(state!=GameState.GNO) {
            System.out.println(state.getValue());
            return false;
        }


        state = checkForwardDiagonal();
        if(state!=GameState.GNO) {
            System.out.println(state.getValue());
            return false;
        }


        state = checkReverseDiagonal();
        if(state!=GameState.GNO) {
            System.out.println(state.getValue());
            return false;
        }

        return true;

    }

    public GameState checkRow() {

        for(int row=0;row<3;row++) {
            String rowvalue  = grid[row][0]+ grid[row][1]+ grid[row][2];
            if(rowvalue.equals("XXX")) {
                return GameState.X_WINS;

            }
            if(rowvalue.equals("OOO")) {
                return GameState.O_WINS;
            }
        }
        return  GameState.GNO;

    }

    public GameState checkColumn() {
        for(int column=0;column<3;column++) {
            String columnvalue  = grid[0][column]+ grid[1][column]+ grid[2][column];
            if(columnvalue.equals("XXX")) {
                return GameState.X_WINS;

            }
            if(columnvalue.equals("OOO")) {
                return GameState.O_WINS;

            }
        }
        return  GameState.GNO;
    }

    public GameState checkForwardDiagonal() {

        String diagonal  = grid[0][0]+ grid[1][1]+ grid[2][2];
        if(diagonal.equals("XXX")) {
            return GameState.X_WINS;
        }
        if(diagonal.equals("OOO")) {
            return GameState.O_WINS;
        }

        return  GameState.GNO;
    }

    public GameState checkReverseDiagonal() {
        String columnvalue  = grid[0][2]+ grid[1][1]+ grid[2][0];
        if(columnvalue.equals("XXX")) {
            return GameState.X_WINS;
        }
        if(columnvalue.equals("OOO")) {
            return GameState.O_WINS;
        }

        return  GameState.GNO;
    }

    public void setValue(int x_coordinate, int y_coordinate,String player) {
        this.grid[x_coordinate][y_coordinate]= player;
    }




}
