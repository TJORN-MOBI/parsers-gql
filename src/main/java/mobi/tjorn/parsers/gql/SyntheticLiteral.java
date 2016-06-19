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
public class SyntheticLiteral implements Node {
    private final StringLiteral project;
    private final StringLiteral namespace;
    private final KeyPathElementList elementList;
    private final Token literal;

    SyntheticLiteral(StringLiteral project, StringLiteral namespace, KeyPathElementList elementList, Token literal) {
        this.project = project;
        this.namespace = namespace;
        this.elementList = elementList;
        this.literal = literal;
    }

    public StringLiteral getProject() {
        return project;
    }

    public StringLiteral getNamespace() {
        return namespace;
    }

    public KeyPathElementList getElementList() {
        return elementList;
    }

    public Token getLiteral() {
        return literal;
    }

    @Override
    public Series<Node> children() {
        switch (getType()) {
            case KEY:
                if (project == null && namespace == null) {
                    return Containers.singletonSeries(elementList);
                }
                if (namespace == null) {
                    return Containers.series(project, elementList);
                }
                if (project == null) {
                    return Containers.series(namespace, elementList);
                }
                return Containers.series(project, namespace, elementList);
            case BLOB:
            case DATETIME:
            default:
                return Containers.emptySeries();
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type getType() {
        if (literal == null) {
            return Type.KEY;
        }
        if (literal.kind == GqlParserConstants.BLOB_LITERAL) {
            return Type.BLOB;
        }
        return Type.DATETIME;
    }

    public enum Type { BLOB, DATETIME, KEY }
}
