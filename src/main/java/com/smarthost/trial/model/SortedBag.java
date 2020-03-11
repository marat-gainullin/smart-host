package com.smarthost.trial.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Ordered data structure. Uses a comparator of elements.
 * Does not allow nulls. Allows duplicate, according to comparator, elements.
 *
 * @param <T> A type of elements.
 */
public final class SortedBag<T> implements Iterable<T> {

    private static final String REMOVE_UNSUPPORTED_MSG = SortedBag.class.getSimpleName() + " doesn't support remove operation.";
    private static final String NULL_IS_NOT_ALLOWED_MSG = SortedBag.class.getSimpleName() + " doesn't allow null elements.";

    private final class FlatIterator implements Iterator<T> {

        private final Iterator<Map.Entry<T, Collection<T>>> throughData = data.entrySet().iterator();
        private Iterator<T> throughNeighbours = Collections.emptyIterator();

        @Override
        public boolean hasNext() {
            return throughNeighbours.hasNext() || throughData.hasNext();
        }

        @Override
        public T next() {
            if (!throughNeighbours.hasNext()) {
                throughNeighbours = throughData.next().getValue().iterator();
            }
            return throughNeighbours.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(REMOVE_UNSUPPORTED_MSG);
        }
    }

    private final SortedMap<T, Collection<T>> data;
    private int added;

    public SortedBag(Comparator<T> comparator) {
        data = new TreeMap<>(comparator);
    }

    public int size() {
        return added;
    }

    public void add(T element) {
        if (element != null) {
            data.compute(element, (var newNeighbour, var neighbours) -> {
                if (neighbours == null) {
                    neighbours = new ArrayList<>(4);
                }
                neighbours.add(newNeighbour);
                return neighbours;
            });
            added++;
        } else {
            throw new IllegalArgumentException(NULL_IS_NOT_ALLOWED_MSG);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new FlatIterator();
    }
}
