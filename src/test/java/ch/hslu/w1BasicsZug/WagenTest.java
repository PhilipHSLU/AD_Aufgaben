package ch.hslu.w1BasicsZug;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WagenTest {
    @Test
    void testEqualsAndHash_Class() {
        EqualsVerifier.forClass(Wagen.class)
                .withOnlyTheseFields("id")
                //.withIgnoredFields("LOG")
                .suppress(Warning.NONFINAL_FIELDS)
//                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

//    @Test
//    void testMultipleWagons_two(){
//        Wagen zug1 = new Wagen("W1", 10,
//                new Wagen("w2", 22));
//    }

    public static void main(String[] args) {
        Wagen zug1 = new Wagen("W1", 10,
                new Wagen("w2", 22));

        System.out.println(zug1.getCapacity());
    }
}