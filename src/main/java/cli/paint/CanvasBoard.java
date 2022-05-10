package main.java.cli.paint;

public interface CanvasBoard {
    /**
     * Adds a line to the canvas
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    void insertLine(int x1, int y1, int x2, int y2);


    /**
     * Adds a rectangle on the canvas
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    void insertRectangle(int x1, int y1, int x2, int y2);


    /**
     * Fills the given area at x1, y1 with the given colour
     *
     * @param x1
     * @param y1
     * @param colour
     */
    void bucketFill(int x1, int y1, char colour);

}
