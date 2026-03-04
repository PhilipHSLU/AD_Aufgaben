package ch.hslu.w2_Datenstrukturen;

import java.sql.Array;

public class ArrayStack implements ArrayStackAble{
private String[] stack;

    public String[] getStack() {
        return stack;
    }

    public ArrayStack(String[] stack) {
        this.stack = stack;
    }

    public ArrayStack() {
    }

    public ArrayStack(String entry) {
        this.stack = new String[]{entry};
    }

    @Override
    public void push(String item) {

    }

    @Override
    public String pop() {
        return "";
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
