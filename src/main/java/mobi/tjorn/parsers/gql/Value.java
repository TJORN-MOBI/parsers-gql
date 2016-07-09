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
public class Value implements Node {
    private final BindingSite bindingSite;
    private final SyntheticLiteral syntheticLiteral;
    private final StringLiteral stringLiteral;
    private final Token literal;

    Value(BindingSite bindingSite, SyntheticLiteral syntheticLiteral, StringLiteral stringLiteral, Token literal) {
        this.bindingSite = bindingSite;
        this.syntheticLiteral = syntheticLiteral;
        this.stringLiteral = stringLiteral;
        this.literal = literal;
    }

    public BindingSite getBindingSite() {
        return bindingSite;
    }

    public SyntheticLiteral getSyntheticLiteral() {
        return syntheticLiteral;
    }

    public StringLiteral getStringLiteral() {
        return stringLiteral;
    }

    public Token getLiteral() {
        return literal;
    }

    @Override
    public Sequence<Node> children() {
        switch (getType()) {
            case BINDING_SITE:
                return Containers.singletonSeries(bindingSite);
            case SYNTHETIC:
                return Containers.singletonSeries(syntheticLiteral);
            case STRING:
                return Containers.singletonSeries(stringLiteral);
            default:
                return Containers.emptySeries();
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type getType() {
        if (literal != null) {
            switch (literal.kind) {
                case GqlParserConstants.INT_LITERAL:
                    return Type.INT;
                case GqlParserConstants.DOUBLE_LITERAL:
                    return Type.DOUBLE;
                case GqlParserConstants.BOOL_LITERAL:
                    return Type.BOOL;
                case GqlParserConstants.NULL_LITERAL:
                default:
                    return Type.NULL;
            }
        }
        if (stringLiteral != null) {
            return Type.STRING;
        }
        if (bindingSite != null) {
            return Type.BINDING_SITE;
        }
        return Type.SYNTHETIC;
    }

    public enum Type {
        BINDING_SITE, SYNTHETIC, STRING, INT, DOUBLE, BOOL, NULL
    }
}
