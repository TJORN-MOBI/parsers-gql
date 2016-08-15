/*
 * Copyright 2016 TJORN LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mobi.tjorn.shared.containers;

import java.lang.reflect.Array;

/**
 * Container factory.
 *
 * @author yuri
 * @since 6/12/16.
 */
public final class Containers {
    private final static Sequence0<?> instance = new Sequence0<Void>();

    private Containers() {
    }

    /**
     * Returns an empty {@link Sequence}.
     * @param <T> Element type.
     * @return An empty {@link Sequence}
     */
    @SuppressWarnings("unchecked")
    public static <T> Sequence<T> emptySequence() {
        return (Sequence<T>) instance;
    }

    /**
     * Returns a singleton {@link Sequence}.
     * @param element An element of the singleton {@link Sequence}.
     * @param <E> Base element type.
     * @param <T> Actual element type.
     * @return A singleton {@link Sequence}.
     */
    public static <E, T extends E> Sequence<E> singletonSequence(T element) {
        return new Sequence1<E>(element);
    }

    /**
     * Returns a sequence of multiple elements.  It is up to you to ensure that {@code elements} contains no {@code null}s.
     * If {@code elements} contains {@code null}s, use {@link #flexSequence(Class, Object[])} to filter {@code null}s out.
     * @param elements Elements of the {@link Sequence}.
     * @param <E> Base element type.
     * @param <T> Actual element type.
     * @return A {@link Sequence}.
     */
    @SuppressWarnings("unchecked")
    public static <E, T extends E> Sequence<E> sequence(T...elements) {
        return new SequenceN<E>(elements);
    }

    /**
     * Filters out {@code null}s and calls either {@link #emptySequence()}, {@link #singletonSequence(Object)}
     * or {@link #sequence(Object[])} depending on the count of non-{@code null} elements.
     * @param cls Element class.
     * @param elements Elements of the sequence.
     * @param <E> Base element type.
     * @param <T> Actual element types.
     * @return A {@link Sequence}.
     */
    @SuppressWarnings("unchecked")
    public static <E, T extends E> Sequence<E> flexSequence(Class<E> cls, T...elements) {
        int count = 0, last = -1;
        if (elements != null) {
            for (int i=0; i<elements.length; ++i) {
                if (elements[i] != null) {
                    ++count;
                    last = i;
                }
            }
        }
        if (count == 0) {
            return emptySequence();
        }
        if (count == 1) {
            return singletonSequence(elements[last]);
        }
        if (count == elements.length) {
            return sequence(elements);
        }
        @SuppressWarnings("unchecked")
        final E[] buf = (E[]) Array.newInstance(cls, count);
        int idx = -1;
        for (int i=0; i<elements.length; ++i) {
            final E e = elements[i];
            if (e != null) {
                buf[++idx] = e;
            }
        }
        return sequence(buf);
    }
}
