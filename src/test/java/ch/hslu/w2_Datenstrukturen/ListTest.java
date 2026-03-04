package ch.hslu.w2_Datenstrukturen;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ListTest {
    private static final Logger LOG = LoggerFactory.getLogger(ListTest.class);

    @Test
    void testSize() {
        List list = new List(null);
        list.addFirst(3);
        list.addFirst(22);

        assertThat(list.getSize()).isEqualTo(2);

    }

    @Test
    void testHasValue() {
        List list = new List(null);
        list.addFirst(50);

        assertThat(list.hasValue(50)).isEqualTo(true);
    }

    @Test
    void testRemoveValueLast() {
        List list = new List(null);
        list.addFirst(50);
        list.addFirst(4);

        list.removeValue(4);

        assertThat(list.getSize()).isEqualTo(1);
    }

 @Test
    void testRemoveValueMiddle() {
        List list = new List(null);
        list.addFirst(50);
        list.addFirst(4);
        list.addFirst(10);

        list.removeValue(4);

        assertThat(list.getSize()).isEqualTo(2);
        assertThat(list.getHead().getWert()).isEqualTo(10);
        assertThat(list.getHead().getNext().getWert()).isEqualTo(50);


    }

    @Test
    void testRemoveValueNotFoundOrNull() {
        List list = new List(null);

         assertThatThrownBy(() -> {
                list.removeValue(4);
                 })
                         .isExactlyInstanceOf(RuntimeException.class)
                         .hasMessage("wert gibts nicht");
    }



}