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
 * An empty {@link Series}.
 *
 * @author yuri
 * @since 6/12/16.
 */
class Series0<T> implements Series<T> {
    Series0() {
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public T elementAt(int i) {
        throw new IndexOutOfBoundsException("Invalid index: " + i);
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementIterator();
    }

    private class ElementIterator implements Iterator<T> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            throw new IllegalStateException("Iterator is in invalid state");
        }
    }
}
