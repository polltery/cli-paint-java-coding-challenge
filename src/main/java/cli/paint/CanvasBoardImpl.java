package main.java.cli.paint;


import main.java.cli.paint.canvas.objects.CanvasObject;
import main.java.cli.paint.canvas.objects.Line;
import main.java.cli.paint.canvas.objects.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class CanvasBoardImpl implements CanvasBoard {

    private final int width;
    private final int height;
    private final int borderSize;
    private final char [][] canvas;
    private List<CanvasObject> history = new ArrayList<>(10);

    /**
     * Creates a canvas object with given width and height.
     *
     * @param width of the canvas
     * @param height of the canvas
     * @param borderSize for the canvas
     */
    public CanvasBoardImpl(int width, int height, int borderSize) {
        this.width = width;
        this.height = height;
        this.borderSize = borderSize;
        this.canvas = new char[height+(borderSize*2)][width+(borderSize*2)];
        for (int i = 0; i < this.canvas.length; i++) {
            for(int j = 0; j < this.canvas[i].length; j++){
                if(i == 0 || i == this.canvas.length-1){
                    this.canvas[i][j] = '-';
                }else if(j == 0 || j == this.canvas[i].length-1){
                    this.canvas[i][j] = '|';
                }else{
                    this.canvas[i][j] = ' ';
                }
            }
        }
    }

    public char[][] getCanvas() {
        return canvas;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Insert line into the canvas object as specified by the coordinates.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param colour
     * @throws IllegalArgumentException
     */
    public void insertLine(int x1, int y1, int x2, int y2, boolean saveObject, char colour) throws IllegalArgumentException{
        this.validateBounds(x1, y1, x2, y2);
        if(y1 == y2){
            int start = Math.min(x1, x2) - 1 + this.borderSize;
            int end = Math.max(x1, x2) + this.borderSize;
            for(int i = start; i < end; i++){
                this.canvas[y1][i] = colour;
            }
        }else if(x1 == x2){
            int start = Math.min(y1, y2) - 1 + this.borderSize;
            int end = Math.max(y1, y2) + this.borderSize;
            for(int i = start; i < end; i++){
                this.canvas[i][x1] = colour;
            }
        }else{
            throw new IllegalArgumentException("Only vertical or horizontal lines are accepted.");
        }
        if(saveObject){
            history.add(new Line(x1, y1, x2, y2, colour));
        }
    }

    /**
     * Adds a line with default colour 'x' and saves the object in history.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @throws IllegalArgumentException
     */
    public void insertLine(int x1, int y1, int x2, int y2) throws IllegalArgumentException{
        this.insertLine(x1, y1, x2, y2, true, 'x');
    }

    /**
     * Adds 4 lines to the canvas to create a rectangle and then saves the object in the list.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param internalColour
     * @param borderColour
     */
    public void insertRectangle(int x1, int y1, int x2, int y2, char internalColour, char borderColour){
        this.insertLine(x1, y1, x2, y1, false, borderColour);
        this.insertLine(x1, y1, x1, y2, false, borderColour);
        this.insertLine(x2, y1, x2, y2, false, borderColour);
        this.insertLine(x1, y2, x2, y2, false, borderColour);

        int internalWidth = Math.abs((x2-1) - x1);
        if(internalWidth > 0){
            int internalHeight = Math.abs((y2-1) - y1);
            for(int i = 0; i < internalHeight; i++){
                this.insertLine(x1+1+i, y1+1+i, x2-1-i, y2-1-i, false, internalColour);
            }
        }

        history.add(new Rectangle(x1, y1, x2, y2, internalColour, borderColour));
    }

    /**
     * Same as insert rectangle but with default options.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void insertRectangle(int x1, int y1, int x2, int y2){
        this.insertRectangle(x1, y1, x2, y2, '.', 'x');
    }

    /**
     * Validate the bounds to make sure parameters are with in limits.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @throws IllegalArgumentException
     */
    private void validateBounds(int x1, int y1, int x2, int y2) throws IllegalArgumentException{
        if(x1 < 1 || x1 > this.width
                || x2 < 1 || x2 > this.width
                || y1 < 1 || y1 > this.height
                || y2 < 1 || y2 > this.height){
            throw new IllegalArgumentException("Parameter position is out of canvas.");
        }
    }

    /**
     * Changes a given target colour with the given colour, ignores the boundaries
     *
     * @param x
     * @param y
     * @param colour
     */
    public void bucketFill(int x, int y, char colour){
        this.validateBounds(x, y, 1, 1);
        char target = this.canvas[y][x];
        for (int i = 0; i < this.canvas.length; i++) {
            for (int j = 0; j < this.canvas[i].length; j++) {
                if(this.canvas[i][j] == target){
                    this.canvas[i][j] = colour;
                }
            }
        }
    }

    /**
     * Draws the canvas on the console.
     */
    public void render(){
        for (char[] canva : this.canvas) {
            for (char c : canva) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public int getBorderSize() {
        return this.borderSize;
    }

    public List<CanvasObject> getHistory() {
        return history;
    }
}
