package test.java;

import main.java.cli.paint.CanvasBoardImpl;
import main.java.cli.paint.canvas.objects.CanvasObject;
import org.junit.Assert;
import org.junit.Test;

public class CanvasBoardUnitTest {
    
    private static final int CANVAS_BOARD_HEIGHT = 4;
    private static final int CANVAS_BOARD_WIDTH = 20;
    private static final int CANVAS_BOARD_BORDER = 1;
    private static final String INCORRECT_COLOUR_ERR_MSG = "Incorrect colour at position ";

    private static final CanvasBoardImpl canvasBoard = new CanvasBoardImpl(CANVAS_BOARD_WIDTH, CANVAS_BOARD_HEIGHT, CANVAS_BOARD_BORDER);
    private static final char[][] canvas = canvasBoard.getCanvas();

    /**
     * Test canvas creation
     */
    @Test
    public void testCanvas(){
        int canvasArrayHeight = CANVAS_BOARD_HEIGHT + canvasBoard.getBorderSize() * 2;
        int canvasArrayWidth = CANVAS_BOARD_WIDTH + canvasBoard.getBorderSize() * 2;

        Assert.assertEquals(canvasBoard.getWidth(), CANVAS_BOARD_WIDTH);
        Assert.assertEquals(canvasBoard.getHeight(), CANVAS_BOARD_HEIGHT);
        Assert.assertEquals(canvasBoard.getCanvas().length, canvasArrayHeight);
        Assert.assertEquals(canvasBoard.getCanvas()[0].length, canvasArrayWidth);
    }

    /**
     * Test insert line method
     */
    @Test
    public void testInsertLine(){
        int x1 = 1;
        int y1 = 2;
        int x2 = 6;
        int y2 = 2;
        int nx = x1;
        char expectedColour = 'x';
        canvasBoard.insertLine(x1, y1, x2, y2);

        // check line in history
        this.assertCanvasObjectInHistory(x1, y1, x2, y2, 0);

        // Do a manual assertion for each coordinate
        String incorrectColourAtPositionMsg =  + nx + ", " + y1;
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[y1][nx++]);
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[y1][nx++]);
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[y1][nx++]);
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[y1][nx++]);
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[y1][nx++]);
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[y1][nx]);

        x1 = 6;
        y1 = 3;
        x2 = 6;
        y2 = 4;
        int ny = y1;
        canvasBoard.insertLine(x1, y1, x2, y2);

        // check line in history
        this.assertCanvasObjectInHistory(x1, y1, x2, y2, 1);

        // Do a manual assertion for each coordinate
        incorrectColourAtPositionMsg = INCORRECT_COLOUR_ERR_MSG + x1 + ", " + ny;
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[ny++][x1]);
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[ny][x1]);
    }

    /**
     * Assert if the line was added to the canvas history correctly.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param index
     */
    private void assertCanvasObjectInHistory(int x1, int y1, int x2, int y2, int index){
        CanvasObject canvasObject = canvasBoard.getHistory().get(index);
        Assert.assertNotNull("No Line object was saved in history", canvasObject);
        Assert.assertEquals("Incorrect parameter X1 for CanvasObject", x1, canvasObject.getX1());
        Assert.assertEquals("Incorrect parameter Y1 for CanvasObject", y1, canvasObject.getY1());
        Assert.assertEquals("Incorrect parameter X2 for CanvasObject", x2, canvasObject.getX2());
        Assert.assertEquals("Incorrect parameter Y2 for CanvasObject", y2, canvasObject.getY2());
    }

    /**
     * Test insert rectangle method in the canvas
     */
    @Test
    public void testInsertRectangle(){
        int x1 = 14;
        int y1 = 1;
        int x2 = 18;
        int y2 = 3;
        int nx = x1;
        int ny = y1;
        char expectedColour = 'x';
        canvasBoard.insertRectangle(x1, y1, x2, y2);

        // check rectangle in history
        this.assertCanvasObjectInHistory(x1, y1, x2, y2, 2);

        // Do a manual assertion for each coordinate
        String incorrectColourAtPositionMsg = INCORRECT_COLOUR_ERR_MSG + x1 + ", " + ny;
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[ny++][x1]);
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[ny++][x1]);
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[ny][x1]);
        incorrectColourAtPositionMsg = INCORRECT_COLOUR_ERR_MSG + x2 + ", " + ny;
        ny = y1;
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[ny++][x2]);
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[ny++][x2]);
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[ny][x2]);
        incorrectColourAtPositionMsg = INCORRECT_COLOUR_ERR_MSG + nx + ", " + y1;
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[y1][nx++]);
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[y1][nx++]);
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[y1][nx++]);
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[y1][nx]);
        incorrectColourAtPositionMsg = INCORRECT_COLOUR_ERR_MSG + nx + ", " + y2;
        nx = x1;
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[y2][nx++]);
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[y2][nx++]);
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[y2][nx++]);
        Assert.assertEquals(incorrectColourAtPositionMsg, expectedColour, canvas[y2][nx]);
    }

    /**
     * Test basic bucket fill method to make sure background is changed to the given colour.
     */
    @Test
    public void testEasyBucketFill(){
        int x = 10;
        int y = 3;
        char colour = 'o';

        // do the bucket fill
        CanvasBoardImpl blankCanvasBoard = new CanvasBoardImpl(CANVAS_BOARD_WIDTH, CANVAS_BOARD_HEIGHT, CANVAS_BOARD_BORDER);
        char [][] blankCanvas = blankCanvasBoard.getCanvas();
        blankCanvasBoard.bucketFill(x, y, colour);

        // assert the fill (j = x, i = y)
        for (int i = blankCanvasBoard.getBorderSize(); i < blankCanvas.length - blankCanvasBoard.getBorderSize(); i++) {
            for (int j = blankCanvasBoard.getBorderSize(); j < blankCanvas[i].length - blankCanvasBoard.getBorderSize(); j++) {
                Assert.assertEquals(
                        INCORRECT_COLOUR_ERR_MSG + j + ", " + i + " - expected fill",
                        colour,
                        blankCanvas[i][j]
                );
            }
        }
    }

    /**
     * test bucket fill method with complex shapes already inside the canvas
     */
    @Test
    public void testComplexBucketFill(){
        int x = 10;
        int y = 3;
        char colour = 'o';
        boolean [][] fillException = new boolean[canvas.length][canvas[0].length];

        // add horizontal line
        int x1 = 1;
        int y1 = 2;
        int x2 = 6;
        int y2 = 2;
        int nx = x1;
        canvasBoard.insertLine(x1, y1, x2, y2);
        fillException[y1][nx++] = true;
        fillException[y1][nx++] = true;
        fillException[y1][nx++] = true;
        fillException[y1][nx++] = true;
        fillException[y1++][nx] = true;

        // add a vertical line, block off the bottom left space
        x1 = 6;
        y1 = 3;
        x2 = 6;
        y2 = 4;
        nx = 1;
        canvasBoard.insertLine(x1, y1, x2, y2);
        fillException[y1][nx++] = true;
        fillException[y1][nx++] = true;
        fillException[y1][nx++] = true;
        fillException[y1][nx++] = true;
        fillException[y1++][nx] = true;
        nx = 1;
        fillException[y1][nx++] = true;
        fillException[y1][nx++] = true;
        fillException[y1][nx++] = true;
        fillException[y1][nx++] = true;
        fillException[y1][nx] = true;

        // add a rectangle, block off the internal space
        x1 = 14;
        y1 = 1;
        x2 = 18;
        y2 = 3;
        nx = x1;
        canvasBoard.insertRectangle(x1, y1, x2, y2);
        fillException[y1][nx++] = true;
        fillException[y1][nx++] = true;
        fillException[y1][nx++] = true;
        fillException[y1++][nx] = true;
        nx = x1;
        fillException[y1][nx++] = true;
        fillException[y1][nx++] = true;
        fillException[y1][nx++] = true;
        fillException[y1++][nx] = true;
        nx = x1;
        fillException[y1][nx++] = true;
        fillException[y1][nx++] = true;
        fillException[y1][nx++] = true;
        fillException[y1][nx] = true;

        // do the bucket fill
        canvasBoard.bucketFill(x, y, colour);

        // assert the fill (j = x, i = y)
        for (int i = canvasBoard.getBorderSize(); i < canvas.length - canvasBoard.getBorderSize(); i++) {
            for (int j = canvasBoard.getBorderSize(); j < canvas[i].length - canvasBoard.getBorderSize(); j++) {
                if(fillException[i][j]){
                    Assert.assertNotEquals(
                            INCORRECT_COLOUR_ERR_MSG + j + ", " + i + " - expected no fill",
                            colour,
                            canvas[i][j]
                    );
                }else{
                    Assert.assertEquals(
                            INCORRECT_COLOUR_ERR_MSG + j + ", " + i + " - expected fill",
                            colour,
                            canvas[i][j]
                    );
                }
            }
        }
    }
}
