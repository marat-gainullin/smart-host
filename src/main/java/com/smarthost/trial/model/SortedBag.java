package com.smarthost.trial.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Supplier;

/**
 * Ordered data structure with O(log(n)) complexity of additions. Uses a comparator of elements.
 * Does not allow nulls. Allows duplicate, according to comparator, elements.
 * So, if a comparator uses a field of an element and values of the field will be the same among different elements, all of them will be added.
 * It is an iterable, allowing a client code to iterate over added elements in order, stated by the comparator.
 * <p>
 * This data structure is never thread safe. Client code should take care about synchronization or about only sequenced use.
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
    private final Supplier<Collection<T>> bucketFactory;
    private int added;

    /**
     * Constructor with a comparator.
     *
     * @param comparator A {@link Comparator} instance to be used while ordering elements.
     */
    public SortedBag(Comparator<T> comparator) {
        this(comparator, ArrayList::new);
    }

    /**
     * Constructor with a comparator and bucket collection factory
     *
     * @param comparator     A {@link Comparator} instance to be used while ordering elements.
     * @param aBucketFactory A {@link Collection} factory used to store all duplicated, according to the comparator, elements.
     *                       A client code may use this parameter, for example to avoid really same elements by using a {@link java.util.Set}, or with other purpose.
     */
    public SortedBag(Comparator<T> comparator, Supplier<Collection<T>> aBucketFactory) {
        data = new TreeMap<>(comparator);
        bucketFactory = aBucketFactory;
    }

    public int size() {
        return added;
    }

    public void add(T element) {
        if (element != null) {
            data.compute(element, (var newNeighbour, var neighbours) -> {
                if (neighbours == null) {
                    neighbours = bucketFactory.get();
                }
                neighbours.add(newNeighbour);
                return neighbours;
            });
            added++;
        } else {
            throw new IllegalArgumentException(NULL_IS_NOT_ALLOWED_MSG);
        }
    }

    /**
     * @return An {@link Iterator} instance, allowing client code to iterate over added elements in order, stated by the comparator.
     */
    @Override
    public Iterator<T> iterator() {
        return new FlatIterator();
    }
}
