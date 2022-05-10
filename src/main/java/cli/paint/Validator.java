package main.java.cli.paint;

import java.util.Scanner;

public class Validator {

    /**
     * @param in - scanner
     * @return returns command array, where the first item is the command followed by any parameters.
     */
    public String[] readInput(Scanner in) throws IllegalArgumentException{
        String input = in.nextLine();
        this.validateInputString(input);
        String [] command = input.split("\\s");
        this.validateCommand(command);
        System.out.println("Command received: " + command[0]);
        for(int i = 1; i < command.length; i++){
            System.out.println("Parameter " + i + " : " + command[i]);
        }
        return command;
    }

    /**
     * @param input raw input text from scanner
     */
    private void validateInputString(String input){
        if(!input.matches("^(C\\s[0-9]{1,2}\\s[0-9]{1,2}|(L|R)\\s[0-9]{1,2}\\s[0-9]{1,2}\\s[0-9]{1,2}\\s[0-9]{1,2}|B\\s[0-9]{1,2}\\s[0-9]{1,2}\\s[ox]|Q)")){
            throw new IllegalArgumentException("Invalid input, please follow the guidelines.");
        }
    }

    /**
     * A simple method to valid the commands.
     * !!! Always run this.validateInputString(input) before calling this method.
     *
     * @param inputs - array of input from readInput method, the input must be first validated and separated using regex.
     */
    private void validateCommand(String [] inputs) throws IllegalArgumentException{
        Command command = Command.findCommand(inputs[0]);
        if(inputs.length-1 != command.getParams()){
            throw new IllegalArgumentException("Invalid amount of parameters for the given command.");
        }
        String inputCommand = inputs[0];
        if(!inputCommand.matches("[CLRBQ]")){
            throw new IllegalArgumentException("Invalid command.");
        }
    }

}
