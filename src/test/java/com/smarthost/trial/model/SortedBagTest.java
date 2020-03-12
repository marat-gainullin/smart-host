package com.smarthost.trial.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Iterator;

public class SortedBagTest {

    @Test
    public void singleElement() {
        SortedBag<Integer> bag = new SortedBag<>(Comparator.<Integer>naturalOrder());
        bag.add(23);
        Iterator<Integer> throughElements = bag.iterator();
        Assertions.assertTrue(throughElements.hasNext());
        Assertions.assertEquals(23, throughElements.next());
    }

    @Test
    public void differentElements() {
        SortedBag<Integer> bag = new SortedBag<>(Comparator.<Integer>naturalOrder());
        bag.add(23);
        bag.add(32);
        Iterator<Integer> throughElements = bag.iterator();
        Assertions.assertTrue(throughElements.hasNext());
        Assertions.assertEquals(23, throughElements.next());
        Assertions.assertTrue(throughElements.hasNext());
        Assertions.assertEquals(32, throughElements.next());
        Assertions.assertFalse(throughElements.hasNext());
    }

    @Test
    public void duplicatedElements() {
        SortedBag<Integer> bag = new SortedBag<>(Comparator.<Integer>naturalOrder());
        bag.add(23);
        bag.add(23);
        Iterator<Integer> throughElements = bag.iterator();
        Assertions.assertTrue(throughElements.hasNext());
        Assertions.assertEquals(23, throughElements.next());
        Assertions.assertTrue(throughElements.hasNext());
        Assertions.assertEquals(23, throughElements.next());
    }

    @Test
    public void nullElementException() {
        SortedBag<Integer> bag = new SortedBag<>(Comparator.<Integer>naturalOrder());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bag.add(null);
        });
    }

    @Test
    public void removeException() {
        SortedBag<Integer> bag = new SortedBag<>(Comparator.<Integer>naturalOrder());
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            bag.add(23);
            bag.iterator().remove();
        });
    }
}
