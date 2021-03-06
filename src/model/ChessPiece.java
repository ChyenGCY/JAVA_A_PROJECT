package model;

import java.awt.*;

public enum ChessPiece {
    BLACK(-1, Color.BLACK), WHITE(1, Color.WHITE), GRAY(0, new Color(173, 216, 230, 150));

    private final Color color;
    private int type;

    ChessPiece(int type, Color color) {
        this.color = color;
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public int getType() {
        return type;
    }
}
