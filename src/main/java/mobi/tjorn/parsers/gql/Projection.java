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
 * @since 6/11/16.
 */
public class Projection implements Node {
    private final PropertyNameList projection;
    private final boolean distinct;
    private final DistinctOn distinctOn;

    Projection(PropertyNameList projection, boolean distinct, DistinctOn distinctOn) {
        this.projection = projection;
        this.distinct = distinct;
        this.distinctOn = distinctOn;
    }

    public PropertyNameList getProjection() {
        return projection;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public DistinctOn getDistinctOn() {
        return distinctOn;
    }

    @Override
    public Sequence<Node> children() {
        switch (getType()) {
            case DISTINCT_ON:
                return Containers.singletonSequence(distinctOn);
            case DISTINCT:
            case PARTIAL:
                return Containers.singletonSequence(projection);
            case FULL:
            default:
                return Containers.emptySequence();
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type getType() {
        if (distinctOn != null) {
            return Type.DISTINCT_ON;
        }
        if (distinct) {
            return Type.DISTINCT;
        }
        if (projection != null) {
            return Type.PARTIAL;
        }
        return Type.FULL;
    }

    public enum Type { FULL, PARTIAL, DISTINCT, DISTINCT_ON }
}
