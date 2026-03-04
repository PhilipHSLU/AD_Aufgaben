package ch.hslu.w2_Datenstrukturen;

import java.sql.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayStack implements ArrayStackAble{

private final String[] stack;
private int size = 0;

private static final Logger LOG = LoggerFactory.getLogger(ArrayStack.class);



    public ArrayStack(String[] stack) {
        this.stack = stack;
    }

    public ArrayStack(String entry) {
        this.stack = new String[]{entry};
    }

    public String[] getStack() {
        return stack;
    }

    @Override
    public void push(String item) {
        if (isFull()){
            throw new IllegalStateException("stack voll");
        }
        stack[this.size] = item;
        size++;

    }

    @Override
    public String pop() {
        return stack[size];
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isFull() {
        if (this.size >= this.stack.length){return true;}
        LOG.info("size = {} | length = {}", this.size, this.stack.length);
        return false;
    }

    @Override
    public boolean isEmpty() {
         if (this.size <= 0){return true;}
        return false;
    }
}
