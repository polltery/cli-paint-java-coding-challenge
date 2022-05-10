package main.java.cli.paint;

import java.util.Scanner;

public class Main {

    private static final String ERR_PREFIX = "ERROR: ";

    public static void main(String[] args) {

        // initialize
        CanvasBoardImpl canvasBoard = null;
        Validator validator = new Validator();
        String[] command = new String[]{"C"};
        Scanner in = new Scanner(System.in);
        boolean illegal;

        // start the program
        System.out.println("Welcome to the CLI paint tool!");
        System.out.println("let's start by creating a canvas first.");
        System.out.println("Use command 'C' to create followed by width and height, for example: C 20 5");
        try{
            command = validator.readInput(in);
            illegal = false;
        }catch (IllegalArgumentException e){
            System.out.println(ERR_PREFIX + e.getMessage());
            illegal = true;
        }

        // enter main loop
        while(!Command.Q.getCommand().toString().equalsIgnoreCase(command[0])){

            // process input
            if(!Command.C.getCommand().toString().equalsIgnoreCase(command[0])
                    && canvasBoard == null
                    && !illegal){
                System.out.println(ERR_PREFIX + "No canvas found, please create a canvas first using 'C W H' command.");
            }else if(Command.C.getCommand().toString().equalsIgnoreCase(command[0])
                    && canvasBoard != null
                    && !illegal){
                System.out.println(ERR_PREFIX + "Please use the existing canvas.");
            }else if(canvasBoard == null
                    && !illegal) {
                int canvasWidth = Integer.parseInt(command[1]);
                int canvasHeight = Integer.parseInt(command[2]);
                canvasBoard = new CanvasBoardImpl(canvasWidth, canvasHeight, 1);
                canvasBoard.render();
                System.out.println("Canvas successfully initalised!");
                System.out.println("You may now use the following commands:");
                System.out.println("Draw a line using: L x1 y1 x2 y2");
                System.out.println("Draw a rectangle using: R x1 y1 x2 y2");
                System.out.println("Bucket fill an area to 'o' using: B x1 y1 o");
                System.out.println("Quit: Q");

            }else if(Command.L.getCommand().toString().equalsIgnoreCase(command[0]) && !illegal){
                int x1 = Integer.parseInt(command[1]);
                int y1 = Integer.parseInt(command[2]);
                int x2 = Integer.parseInt(command[3]);
                int y2 = Integer.parseInt(command[4]);
                try{
                    canvasBoard.insertLine(x1, y1, x2, y2, true, 'x');
                    canvasBoard.render();
                }catch (IllegalArgumentException e){
                    System.out.println(ERR_PREFIX + e.getMessage());
                }
            }else if(Command.R.getCommand().toString().equalsIgnoreCase(command[0]) && !illegal){
                int x1 = Integer.parseInt(command[1]);
                int y1 = Integer.parseInt(command[2]);
                int x2 = Integer.parseInt(command[3]);
                int y2 = Integer.parseInt(command[4]);
                try{
                    canvasBoard.insertRectangle(x1, y1, x2, y2, '.', 'x');
                    canvasBoard.render();
                }catch (IllegalArgumentException e){
                    System.out.println(ERR_PREFIX + e.getMessage());
                }
            }else if(Command.B.getCommand().toString().equalsIgnoreCase(command[0]) && !illegal){
                int x = Integer.parseInt(command[1]);
                int y = Integer.parseInt(command[2]);
                char c = command[3].charAt(0);
                try{
                    canvasBoard.bucketFill(x, y, c);
                    canvasBoard.render();
                }catch (IllegalArgumentException e){
                    System.out.println(ERR_PREFIX + e.getMessage());
                }
            }

            // read input
            try{
                command = validator.readInput(in);
                illegal = false;
            }catch (IllegalArgumentException e){
                System.out.println(ERR_PREFIX + e.getMessage());
                illegal = true;
            }
        }

        System.out.println("Quitting..");
    }
}
