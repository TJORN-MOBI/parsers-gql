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
 * @since 6/11/16.
 */
public class Offset implements Node {
    private final ResultPosition first;
    private final ResultPosition second;

    Offset(ResultPosition first, ResultPosition second) {
        this.first = first;
        this.second = second;
    }

    public ResultPosition getFirst() {
        return first;
    }

    public ResultPosition getSecond() {
        return second;
    }

    @Override
    public Series<Node> children() {
        switch (getType()) {
            case SINGLE:
                return Containers.singletonSeries(first);
            case DOUBLE:
            default:
                return Containers.series(first, second);
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Limit.Type getType() {
        return second == null ? Limit.Type.SINGLE : Limit.Type.DOUBLE;
    }

    public enum Type { SINGLE, DOUBLE }
}
