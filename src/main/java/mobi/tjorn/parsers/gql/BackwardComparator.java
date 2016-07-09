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
public class BackwardComparator implements Node {
    private final EitherComparator eitherComparator;
    private final boolean inComparator;
    private final boolean hasDescendantComparator;

    BackwardComparator(EitherComparator eitherComparator, boolean inComparator, boolean hasDescendantComparator) {
        this.eitherComparator = eitherComparator;
        this.inComparator = inComparator;
        this.hasDescendantComparator = hasDescendantComparator;
    }

    public EitherComparator getEitherComparator() {
        return eitherComparator;
    }

    public boolean isInComparator() {
        return inComparator;
    }

    public boolean isHasDescendantComparator() {
        return hasDescendantComparator;
    }

    @Override
    public Sequence<Node> children() {
        switch (getType()) {
            case EITHER:
                return Containers.singletonSeries(eitherComparator);
            default:
                return Containers.emptySeries();
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type getType() {
        if (eitherComparator != null) {
            return Type.EITHER;
        }
        if (inComparator) {
            return Type.IN;
        }
        assert hasDescendantComparator;
        return Type.HAS_DESCENDANT;
    }

    public enum Type { EITHER, IN, HAS_DESCENDANT }
}
