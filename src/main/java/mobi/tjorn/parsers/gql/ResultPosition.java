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
public class ResultPosition implements Node {
    private final BindingSite bindingSite;
    private final Token intLiteral;

    ResultPosition(BindingSite bindingSite, Token intLiteral) {
        this.bindingSite = bindingSite;
        this.intLiteral = intLiteral;
    }

    public BindingSite getBindingSite() {
        return bindingSite;
    }

    public Token getIntLiteral() {
        return intLiteral;
    }

    @Override
    public Series<Node> children() {
        switch (getType()) {
            case BINDING_SITE:
                return Containers.singletonSeries(bindingSite);
            case INT:
            default:
                return Containers.emptySeries();
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type getType() {
        if (bindingSite != null) {
            return Type.BINDING_SITE;
        }
        assert intLiteral != null;
        return Type.INT;
    }

    public enum Type { BINDING_SITE, INT }
}
