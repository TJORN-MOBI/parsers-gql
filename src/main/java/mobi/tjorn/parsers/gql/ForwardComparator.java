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
public class ForwardComparator implements Node {
    private final EitherComparator eitherComparator;
    private final boolean containsComparator;
    private final boolean hasAncestorComparator;

    ForwardComparator(EitherComparator eitherComparator, boolean containsComparator, boolean hasAncestorComparator) {
        this.eitherComparator = eitherComparator;
        this.containsComparator = containsComparator;
        this.hasAncestorComparator = hasAncestorComparator;
    }

    public EitherComparator getEitherComparator() {
        return eitherComparator;
    }

    public boolean isContainsComparator() {
        return containsComparator;
    }

    public boolean isHasAncestorComparator() {
        return hasAncestorComparator;
    }

    @Override
    public Series<Node> children() {
        switch (getType()) {
            case EITHER:
                return Containers.singletonSeries(eitherComparator);
            case CONTAINS:
            case HAS_ANCESTOR:
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
        if (containsComparator) {
            return Type.CONTAINS;
        }
        assert hasAncestorComparator;
        return Type.HAS_ANCESTOR;
    }

    public enum Type {
        EITHER, CONTAINS, HAS_ANCESTOR
    }
}
