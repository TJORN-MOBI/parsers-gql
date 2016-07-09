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
public class StringLiteral implements Node {
    private final Token literal;

    StringLiteral(Token literal) {
        this.literal = literal;
    }

    public Token getLiteral() {
        return literal;
    }

    @Override
    public Sequence<Node> children() {
        return Containers.emptySeries();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type getType() {
        switch (literal.kind) {
            case GqlParserConstants.BLOB_LITERAL:
                return Type.BLOB;
            case GqlParserConstants.DATETIME_LITERAL:
                return Type.DATETIME;
            case GqlParserConstants.STRING_LITERAL:
                return Type.STRING;
            default:
                throw new RuntimeException("Invalid literal type: " + GqlParserConstants.tokenImage[literal.kind]);
        }
    }

    public enum Type { BLOB, DATETIME, STRING }
}
