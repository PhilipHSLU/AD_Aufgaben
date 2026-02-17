package ch.hslu.w1BasicsZug;
import static org.assertj.core.api.Assertions.assertThat;


import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WagenTest {
    @Test
    void testEqualsAndHash_Class() {
        Wagen wA = new Wagen("A", 100);
        Wagen wB = new Wagen("B", 200);
        EqualsVerifier.forClass(Wagen.class)
                .withOnlyTheseFields("id")
//                .withIgnoredFields("capacity", "nachfolger")
                .suppress(Warning.NONFINAL_FIELDS)
                .withPrefabValues(Wagen.class, wA, wB)
//                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    void testGetterAndConstructor(){
        Wagen w1 = new Wagen("1", 30);
        assertThat(w1.getId()).isEqualTo("1");
        assertThat(w1.getCapacity()).isEqualTo(30);
    }
       @Test
    void testChain(){
        Wagen w1 = new Wagen("1", 30);
        Wagen w2 = new Wagen("2", 10);

        w1.setNachfolger(w2);
        assertThat(w1.getNachfolger()).isEqualTo(w2);
    }


}