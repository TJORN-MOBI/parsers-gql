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

import mobi.tjorn.shared.containers.Containers;
import mobi.tjorn.shared.containers.Sequence;

/**
 * @author yuri
 * @since 6/7/16.
 */
public class MoreOrderedPropertyNames implements Node {
    private final OrderedPropertyName propertyName;
    private final MoreOrderedPropertyNames next;

    MoreOrderedPropertyNames(OrderedPropertyName propertyName, MoreOrderedPropertyNames next) {
        this.propertyName = propertyName;
        this.next = next;
    }

    public OrderedPropertyName getPropertyName() {
        return propertyName;
    }

    public MoreOrderedPropertyNames getNext() {
        return next;
    }

    @Override
    public Sequence<Node> children() {
        if (next == null) {
            return Containers.singletonSeries(propertyName);
        }
        return Containers.series(propertyName, next);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
