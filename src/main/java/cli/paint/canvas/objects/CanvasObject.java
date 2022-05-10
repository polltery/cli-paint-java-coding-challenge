package main.java.cli.paint.canvas.objects;

/**
 * CanvasObject is the parent class for all the objects that would be drawn on the canvas.
 */
public abstract class CanvasObject {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private char borderColour = 'x';

    public CanvasObject(int x1, int y1, int x2, int y2, char borderColour) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.borderColour = borderColour;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
}
