package main.java.cli.paint.canvas.objects;

public class Rectangle extends CanvasObject {

    private char internalColour = '.';

    public Rectangle(int x1, int y1, int x2, int y2, char internalColour, char borderColour) {
        super(x1, y1, x2, y2, borderColour);
        this.internalColour = internalColour;
    }

    public char getInternalColour() {
        return internalColour;
    }
}
