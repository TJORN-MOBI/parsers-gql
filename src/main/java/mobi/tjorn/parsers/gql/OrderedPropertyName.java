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
public class OrderedPropertyName implements Node {
    private final PropertyName propertyName;
    private final Token sortOrder;

    OrderedPropertyName(PropertyName propertyName, Token sortOrder) {
        this.propertyName = propertyName;
        this.sortOrder = sortOrder;
    }

    public PropertyName getPropertyName() {
        return propertyName;
    }

    public Token getSortOrder() {
        return sortOrder;
    }

    @Override
    public Sequence<Node> children() {
        return Containers.singletonSeries(propertyName);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type getType() {
        if (sortOrder == null) {
            return Type.UNSPECIFIED;
        }
        switch (sortOrder.kind) {
            case GqlParserConstants.K_ASC:
                return Type.ASC;
            case GqlParserConstants.K_DESC:
                return Type.DESC;
            default:
                throw new IllegalStateException("Invalid sort order: " + GqlParserConstants.tokenImage[sortOrder.kind]);
        }
    }

    public enum Type { ASC, DESC, UNSPECIFIED }
}
