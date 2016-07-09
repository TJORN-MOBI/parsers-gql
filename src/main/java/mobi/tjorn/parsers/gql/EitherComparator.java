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
public class EitherComparator implements Node {
    private final Token operator;

    EitherComparator(Token operator) {
        this.operator = operator;
    }

    public Token getOperator() {
        return operator;
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
        switch (operator.kind) {
            case GqlParserConstants.OP_EQ:
                return Type.EQ;
            case GqlParserConstants.OP_LE:
                return Type.LE;
            case GqlParserConstants.OP_LT:
                return Type.LT;
            case GqlParserConstants.OP_GE:
                return Type.GE;
            case GqlParserConstants.OP_GT:
                return Type.GT;
            default:
                throw new RuntimeException("Invalid operator kind: " + GqlParserConstants.tokenImage[operator.kind]);
        }
    }

    public enum Type { EQ, LE, LT, GE, GT }
}
