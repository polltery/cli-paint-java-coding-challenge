package main.java.cli.paint;

public enum Command {
    C('C',"Create","Create a canvas.", 2),
    L('L',"Line","Draw a line.", 4),
    R('R',"Rectangle","Draw a Rectangle.", 4),
    B('B',"Bucket fill","Fill an area with colour.", 3),
    Q('Q',"Quit","Quit program.", 0);

    private final Character command;
    private final String name;
    private final String description;
    private final int params;

    Command(Character command, String name, String description, int params){
        this.command = command;
        this.name = name;
        this.description = description;
        this.params = params;
    }

    /**
     * @param input - string input from scanner
     * @return returns the command enum
     */
    public static Command findCommand(String input) throws IllegalArgumentException {
        if(input.equalsIgnoreCase("C")){
            return Command.C;
        }else if(input.equalsIgnoreCase("L")){
            return Command.L;
        }else if(input.equalsIgnoreCase("R")){
            return Command.R;
        }else if(input.equalsIgnoreCase("B")){
            return Command.B;
        }else if(input.equalsIgnoreCase("Q")){
            return Command.Q;
        }else{
            throw new IllegalArgumentException("Command not found for given input.");
        }
    }

    public Character getCommand() {
        return command;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getParams() {
        return params;
    }
}
