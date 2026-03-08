package ch.hslu.w2_Datenstrukturen;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class StackMaschineG {

    public void execute(List<String> program) {
        Stack<Integer> stack = new Stack<>();

        System.out.println("Starte Programm-Ausführung...");

        for (String line : program) {
            String[] parts = line.split(" ");
            String command = parts[0].toUpperCase();

            switch (command) {
                case "LOAD":
                    int value = Integer.parseInt(parts[1]);
                    stack.push(value);
                    break;

                case "ADD":
                    stack.push(stack.pop() + stack.pop());
                    break;

                case "MUL":
                    stack.push(stack.pop() * stack.pop());
                    break;

                case "SUB":
                    // Wichtig: Reihenfolge beachten (Zweitunterstes - Oberstes)
                    int subtrahend = stack.pop();
                    int minuend = stack.pop();
                    stack.push(minuend - subtrahend);
                    break;

                case "DIV":
                    // Wichtig: Reihenfolge beachten (Zweitunterstes / Oberstes)
                    int divisor = stack.pop();
                    int dividend = stack.pop();
                    stack.push(dividend / divisor);
                    break;

                case "PRINT":
                    if (!stack.isEmpty()) {
                        System.out.println("Output: " + stack.pop());
                    }
                    break;

                default:
                    System.out.println("Unbekannter Befehl: " + command);
            }
        }
        System.out.println("--- Programm beendet ---\n");
    }

    public static void main(String[] args) {
        StackMaschineG machine = new StackMaschineG();

        // Test a) (2 + 3) * 4
        List<String> progA = List.of("LOAD 2", "LOAD 3", "ADD", "LOAD 4", "MUL", "PRINT");
        System.out.println("Test a):");
        machine.execute(progA);

        // Test b) (4 + 5) * (2 + 3)
        List<String> progB = List.of(
                "LOAD 4", "LOAD 5", "ADD",
                "LOAD 2", "LOAD 3", "ADD",
                "MUL", "PRINT"
        );
        System.out.println("Test b):");
        machine.execute(progB);

        // Test c) 5 * (6 / (7 - 4))
        List<String> progC = List.of(
                "LOAD 5", "LOAD 6", "LOAD 7", "LOAD 4",
                "SUB", "DIV", "MUL", "PRINT"
        );
        System.out.println("Test c):");
        machine.execute(progC);
    }
}
