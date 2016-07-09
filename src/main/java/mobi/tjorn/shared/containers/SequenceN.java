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

import java.util.Iterator;

/**
 * A {@link Sequence} of {@code N} elements.
 * @author yuri
 * @since 6/12/16.
 */
class SequenceN<T> implements Sequence<T> {
    private final T[] elements;

    SequenceN(T[] elements) {
        this.elements = elements;
    }

    @Override
    public int length() {
        return elements == null ? 0 : elements.length;
    }

    @Override
    public T elementAt(int i) {
        if (i < 0 || length() <= i) {
            throw new IndexOutOfBoundsException("Invalid index: " + i);
        }
        return elements[i];
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementIterator();
    }

    private class ElementIterator implements Iterator<T> {
        private int position = -1;

        @Override
        public boolean hasNext() {
            return position < length() - 1;
        }

        @Override
        public T next() {
            if (! hasNext()) {
                throw new IllegalStateException("Iterator is in invalid state");
            }
            return elements[++position];
        }
    }
}
