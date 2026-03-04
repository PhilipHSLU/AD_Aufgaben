package ch.hslu.w2_Datenstrukturen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class ArrayStackTest {

    @Test
    void isEmpty() {
        String[] arrempty = new String[0];
        ArrayStack stack = new ArrayStack(arrempty);
        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    void isNotEmpty() {
        String[] arr2 = new String[2];
//        arr2[0] = "lol";

        ArrayStack stack = new ArrayStack(arr2);
        stack.push("sali");

        assertThat(stack.isEmpty()).isFalse();
    }

    @Test
    void isFull() {
        String[] array1 = new String[1];
//        array1[0] = "element";
        ArrayStack stack = new ArrayStack(array1);

        stack.push("lol");
        assertThat(stack.isFull()).isTrue();
    }
}