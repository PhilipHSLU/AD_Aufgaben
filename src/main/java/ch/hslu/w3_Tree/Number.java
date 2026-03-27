package ch.hslu.w3_Tree;

import java.util.ArrayList;
import java.util.List;

// Blattknoten: Repräsentiert eine reine Zahl
class Number implements Nodeable {
    private int value;
    public Number(int value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return String.valueOf(value); // Wandelt int in String um
    }
    @Override
    public int eval() {
        return value; // Der Wert einer Zahl ist einfach die Zahl selbst
    }
    @Override
    public List<String> compile() {
        List<String> instructions = new ArrayList<>();
        instructions.add("LOAD " + value); // Lade die Zahl auf den Stack
        return instructions;
    }
}

