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

package mobi.tjorn.parsers.gql;

import mobi.tjorn.shared.containers.Sequence;

/**
 * Base node interface.
 *
 * @author yuri
 * @since 6/12/16.
 */
public interface Node {
    /**
     * Returns node children.
     *
     * @return  Node children.
     */
    Sequence<Node> children();

    /**
     * Accepts a {@link Visitor}.
     *
     * @param v A {@link Visitor}
     */
    void accept(Visitor v);
}
