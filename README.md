# CLI Paint Coding Challenge

# Challenge

You're given the task of writing a simple console version of a drawing program.
At this time, the functionality of the program is quite limited but this might change in the future.
In a nutshell, the program should work as follows:
1. Create a new canvas
2. Start drawing on the canvas by issuing various commands
3. Quit

Check `challenge.txt` for full description of required commands.

## Pre-requisite

- Java 11 or higher
- `junit-4.12.jar` (for unit tests)
- `hamcrest-core-1.3.jar` (for unit tests)

## Compiling and running the project

Open the root folder in command prompt and run the following commands to compile and run the project

```cmd
> cd src
> javac main/java/cli/paint/Main.java
> java main.java.cli.paint.Main
...
```

Compiling the tests (given you are in the `src` directory)

```cmd
> javac -cp ../junit-4.12.jar;. test/java/CanvasBoardUnitTest.java
...
```

Running the tests (given you are in the `src` directory)
```cmd
> java -cp ../junit-4.12.jar;../hamcrest-core-1.3.jar;. org.junit.runner.JUnitCore test.java.CanvasBoardUnitTest
...
...
Tests run: 5,  Failures: 1
```

Out of the 5 tests, 1 one of them should fail (check **special case # 3**).


## How does it work?

Note: You may quit the program at any given time by entering `Q` command.

1. Create a canvas: `C {width} {height}`

```cmd
Welcome to the CLI paint tool!
let's start by creating a canvas first.
Use command 'C' to create followed by width and height, for example: C 20 5
> C 20 4
Command received: C
Parameter 1 : 20
Parameter 2 : 4
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------
Canvas successfully initalised!
You may now use the following commands:
Draw a line using: L x1 y1 x2 y2
Draw a rectangle using: R x1 y1 x2 y2
Bucket fill an area to 'o' or 'x' using: B x1 y1 o
Quit: Q
```

2. Follow the given instructions to draw further.

## Special Cases

1. Bucket fill only accepts either `o` or `x` colour.
2. Rectangles get inner fill of `.` colour.
3. Bucket fill spills over boundaries to colour all possible spaces it found at `x, y`. It is not implemented as required simply to save time. The unit test tries to test the requirement logic given in the task, but it fails intentionally.

