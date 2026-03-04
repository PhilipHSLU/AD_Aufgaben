package ch.hslu.w2_Datenstrukturen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class ArrayStackTest {

    @Test
    void isEmpty() {
        ArrayStack stack = new ArrayStack();
        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    void isNotEmpty() {
        ArrayStack stack = new ArrayStack();
        stack.push("sali");

        assertThat(stack.isEmpty()).isFalse();
    }

    @Test
    void isFull() {
        String[] array1 = new String[1];

        ArrayStack stack = new ArrayStack(array1);
        assertThat(stack.isFull()).isTrue();
    }
}