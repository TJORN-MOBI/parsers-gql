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
public class DistinctOn implements Node {
    private final PropertyNameList distinctOn;
    private final PropertyNameList projection;

    DistinctOn(PropertyNameList distinctOn, PropertyNameList projection) {
        this.distinctOn = distinctOn;
        this.projection = projection;
    }

    public PropertyNameList getDistinctOn() {
        return distinctOn;
    }

    public PropertyNameList getProjection() {
        return projection;
    }

    @Override
    public Sequence<Node> children() {
        switch (getType()) {
            case FULL:
                return Containers.singletonSequence(distinctOn);
            case PATIAL:
            default:
                return Containers.sequence(distinctOn, projection);
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type getType() {
        return projection == null ? Type.FULL : Type.PATIAL;
    }

    public enum Type { FULL, PATIAL }
}
