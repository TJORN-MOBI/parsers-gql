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
public class KeyPathElement implements Node {
    private final Kind kind;
    private final Token intLiteral;
    private final StringLiteral stringLiteral;

    KeyPathElement(Kind kind, Token intLiteral, StringLiteral stringLiteral) {
        this.kind = kind;
        this.intLiteral = intLiteral;
        this.stringLiteral = stringLiteral;
    }

    public Kind getKind() {
        return kind;
    }

    public Token getIntLiteral() {
        return intLiteral;
    }

    public StringLiteral getStringLiteral() {
        return stringLiteral;
    }

    @Override
    public Sequence<Node> children() {
        switch (getType()) {
            case STRING:
                return Containers.sequence(kind, stringLiteral);
            case INT:
            default:
                return Containers.singletonSequence(kind);
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type getType() {
        return intLiteral != null ? Type.INT : Type.STRING;
    }

    public enum Type { INT, STRING }
}
