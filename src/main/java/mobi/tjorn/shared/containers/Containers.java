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
    private final static Series0<?> instance = new Series0<Void>();

    private Containers() {
    }

    /**
     * Returns an empty {@link Series}.
     * @param <T> Element type.
     * @return An empty {@link Series}
     */
    @SuppressWarnings("unchecked")
    public static <T> Series<T> emptySeries() {
        return (Series<T>) instance;
    }

    /**
     * Returns a singleton {@link Series}.
     * @param element An element of the singleton {@link Series}.
     * @param <E> Element type.
     * @return A singleton {@link Series}.
     */
    public static <E, T extends E> Series<E> singletonSeries(T element) {
        return new Series1<E>(element);
    }

    /**
     * Returns a series of multiple elements.  It is up to you to ensure that {@code elements} contains no {@code null}s.
     * If {@code elements} contains {@code null}s, use {@link #flexSeries(Class, Object[])} to filter {@code null}s out.
     * @param elements Elements of the {@link Series}.
     * @param <E> Element type.
     * @return A {@link Series}.
     */
    @SuppressWarnings("unchecked")
    public static <E, T extends E> Series<E> series(T...elements) {
        return new SeriesN<E>(elements);
    }

    /**
     * Filters out {@code null}s and calls either {@link #emptySeries()}, {@link #singletonSeries(Object)}
     * or {@link #series(Object[])} depending on the count of non-{@code null} elements.
     * @param cls Element class.
     * @param elements Elements of the series.
     * @param <E> Element type.
     * @return A {@link Series}.
     */
    @SuppressWarnings("unchecked")
    public static <E, T extends E> Series<E> flexSeries(Class<E> cls, T...elements) {
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
            return emptySeries();
        }
        if (count == 1) {
            return singletonSeries(elements[last]);
        }
        if (count == elements.length) {
            return series(elements);
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
        return series(buf);
    }
}
