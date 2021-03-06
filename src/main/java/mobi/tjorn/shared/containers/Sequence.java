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

/**
 * A light-wight extension of {@link Iterable}.
 *
 * @author yuri
 * @since 6/12/16.
 */
public interface Sequence<E> extends Iterable<E> {
    /**
     * Returns the length of the sequence.
     *
     * @return The length of the sequence.
     */
    int length();

    /**
     * Gets an element of the sequence at position {@code i}.
     *
     * @param i Position of the element in the sequence.
     * @return An element of the sequence at position {@code i}.
     */
    E elementAt(int i);
}
