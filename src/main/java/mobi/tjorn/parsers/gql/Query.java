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
public class Query implements Node {
    private final Projection select;
    private final Kind from;
    private final CompoundCondition where;
    private final OrderedPropertyNameList orderBy;
    private final Limit limit;
    private final Offset offset;

    Query(Projection select, Kind from, CompoundCondition where, OrderedPropertyNameList orderBy, Limit limit, Offset offset) {
        this.select = select;
        this.from = from;
        this.where = where;
        this.orderBy = orderBy;
        this.limit = limit;
        this.offset = offset;
    }

    public Projection getSelect() {
        return select;
    }

    public Kind getFrom() {
        return from;
    }

    public CompoundCondition getWhere() {
        return where;
    }

    public OrderedPropertyNameList getOrderBy() {
        return orderBy;
    }

    public Limit getLimit() {
        return limit;
    }

    public Offset getOffset() {
        return offset;
    }

    @Override
    public Series<Node> children() {
        return Containers.flexSeries(Node.class, select, from, where, orderBy, limit, offset);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
