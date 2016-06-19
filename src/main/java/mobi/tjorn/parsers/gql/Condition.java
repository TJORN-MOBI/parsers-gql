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
import mobi.tjorn.shared.containers.Series;

/**
 * @author yuri
 * @since 6/7/16.
 */
public class Condition implements Node {
    private final PropertyName propertyName;
    private final Value value;
    private final ForwardComparator forwardComparator;
    private final BackwardComparator backwardComparator;

    Condition(PropertyName propertyName, Value value, ForwardComparator forwardComparator, BackwardComparator backwardComparator) {
        this.propertyName = propertyName;
        this.value = value;
        this.forwardComparator = forwardComparator;
        this.backwardComparator = backwardComparator;
    }

    public PropertyName getPropertyName() {
        return propertyName;
    }

    public Value getValue() {
        return value;
    }

    public ForwardComparator getForwardComparator() {
        return forwardComparator;
    }

    public BackwardComparator getBackwardComparator() {
        return backwardComparator;
    }

    @Override
    public Series<Node> children() {
        switch (getType()) {
            case FORWARD:
                return Containers.series(propertyName, forwardComparator, value);
            case BACKWARD:
                return Containers.series(value, backwardComparator, propertyName);
            case IS_NULL:
            default:
                return Containers.singletonSeries(propertyName);
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type getType() {
        if (value == null) {
            return Type.IS_NULL;
        }
        if (forwardComparator != null) {
            return Type.FORWARD;
        }
        return Type.BACKWARD;
    }

    public enum Type { IS_NULL, FORWARD, BACKWARD }
}
