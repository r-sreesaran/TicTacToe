package org.example;


public enum GameState {


    DRAW("Draw"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    GNO("Not Over");
    private   String value;
    GameState(String value) {
        this.value =value;
    }
    public String getValue() {
        return value;
    }

}

