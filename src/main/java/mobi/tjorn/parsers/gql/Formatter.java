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

/**
 * @author yuri
 * @since 6/14/16.
 */
public class Formatter implements Visitor {
    private final StringBuilder sb = new StringBuilder();

    @Override
    public String toString() {
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return sb.hashCode();
    }

    @Override
    public void visit(BackwardComparator n) {
        switch (n.getType()) {
            case EITHER:
                n.getEitherComparator().accept(this);
                break;
            case HAS_DESCENDANT:
                sb.append("HAS DESCENDANT");
                break;
            case IN:
                sb.append("IN");
                break;
        }
    }

    @Override
    public void visit(BindingSite n) {
        sb.append("@").append(n.getName().image);
    }

    @Override
    public void visit(CompoundCondition n) {
        n.getCondition().accept(this);
        if (n.getNext() != null) {
            n.getNext().accept(this);
        }
    }

    @Override
    public void visit(Condition n) {
        switch (n.getType()) {
            case BACKWARD:
                n.getValue().accept(this);
                sb.append(" ");
                n.getBackwardComparator().accept(this);
                sb.append(" ");
                n.getPropertyName().accept(this);
                break;
            case FORWARD:
                n.getPropertyName().accept(this);
                sb.append(" ");
                n.getForwardComparator().accept(this);
                sb.append(" ");
                n.getValue().accept(this);
                break;
            case IS_NULL:
                n.getPropertyName().accept(this);
                sb.append(" IS NULL");
                break;
        }
    }

    @Override
    public void visit(DistinctOn n) {
        sb.append("DISTINCT ON (");
        n.getDistinctOn().accept(this);
        sb.append(") ");
        switch (n.getType()) {
            case FULL:
                sb.append("*");
                break;
            case PATIAL:
                n.getProjection().accept(this);
                break;
        }
    }

    @Override
    public void visit(EitherComparator n) {
        sb.append(n.getOperator().image);
    }

    @Override
    public void visit(ForwardComparator n) {
        switch (n.getType()) {
            case CONTAINS:
                sb.append("CONTAINS");
                break;
            case EITHER:
                n.getEitherComparator().accept(this);
                break;
            case HAS_ANCESTOR:
                sb.append("HAS ANCESTOR");
                break;
        }
    }

    @Override
    public void visit(KeyPathElement n) {
        n.getKind().accept(this);
        sb.append(",");
        switch (n.getType()) {
            case INT:
                sb.append(n.getIntLiteral().image);
                break;
            case STRING:
                n.getStringLiteral().accept(this);
                break;
        }

    }

    @Override
    public void visit(KeyPathElementList n) {
        n.getElement().accept(this);
        if (n.getNext() != null) {
            n.getNext().accept(this);
        }
    }

    @Override
    public void visit(Kind n) {
        sb.append(n.getName().image);
    }

    @Override
    public void visit(Limit n) {
        sb.append("LIMIT ");
        switch (n.getType()) {
            case SINGLE:
                n.getFirst().accept(this);
                break;
            case DOUBLE:
                sb.append("FIRST (");
                n.getFirst().accept(this);
                sb.append(", ");
                n.getSecond().accept(this);
                sb.append(")");
                break;
        }
    }

    @Override
    public void visit(MoreConditions n) {
        sb.append(" AND ");
        n.getCondition().accept(this);
        if (n.getNext() != null) {
            n.getNext().accept(this);
        }
    }

    @Override
    public void visit(MoreKeyPathElements n) {
        sb.append(", ");
        n.getElement().accept(this);
        if (n.getNext() != null) {
            n.getNext().accept(this);
        }
    }

    @Override
    public void visit(MoreOrderedPropertyNames n) {
        sb.append(", ");
        n.getPropertyName().accept(this);
        if(n.getNext() != null) {
            n.getNext().accept(this);
        }
    }

    @Override
    public void visit(MorePropertyNames n) {
        sb.append(", ");
        n.getPropertyName().accept(this);
        if (n.getNext() != null) {
            n.getNext().accept(this);
        }
    }

    @Override
    public void visit(MorePropertyNameSteps n) {
        sb.append(".").append(n.getName().image);
        if (n.getNext() != null) {
            n.getNext().accept(this);
        }
    }

    @Override
    public void visit(Offset n) {
        sb.append("OFFSET ");
        n.getFirst().accept(this);
        if (n.getSecond() != null) {
            sb.append("+");
            n.getSecond().accept(this);
        }
    }

    @Override
    public void visit(OrderedPropertyName n) {
        n.getPropertyName().accept(this);
        switch (n.getType()) {
            case ASC:
                sb.append(" ASC");
                break;
            case DESC:
                sb.append(" DESC");
                break;
        }
    }

    @Override
    public void visit(OrderedPropertyNameList n) {
        n.getPropertyName().accept(this);
        if (n.getNext() != null) {
            n.getNext().accept(this);
        }
    }

    @Override
    public void visit(Projection n) {
        sb.append("SELECT ");
        switch (n.getType()) {
            case DISTINCT:
                sb.append("DISTINCT ");
                n.getProjection().accept(this);
                break;
            case DISTINCT_ON:
                n.getDistinctOn().accept(this);
                break;
            case FULL:
                sb.append("*");
                break;
            case PARTIAL:
                n.getProjection().accept(this);
                break;
        }
    }

    @Override
    public void visit(PropertyName n) {
        sb.append(n.getName().image);
        if (n.getNext() != null) {
            n.getNext().accept(this);
        }
    }

    @Override
    public void visit(PropertyNameList n) {
        for (final Node child : n.children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(Query n) {
        n.getSelect().accept(this);
        if (n.getFrom() != null) {
            sb.append(" FROM ");
            n.getFrom().accept(this);
        }
        if (n.getWhere() != null) {
            sb.append(" WHERE ");
            n.getWhere().accept(this);
        }
        if (n.getOrderBy() != null) {
            sb.append(" ORDER BY ");
            n.getOrderBy().accept(this);
        }
        if (n.getLimit() != null) {
            sb.append(" ");
            n.getLimit().accept(this);
        }
        if (n.getOffset() != null) {
            sb.append(" ");
            n.getOffset().accept(this);
        }
    }

    @Override
    public void visit(ResultPosition n) {
        switch (n.getType()) {
            case BINDING_SITE:
                n.getBindingSite().accept(this);
                break;
            case INT:
                sb.append(n.getIntLiteral().image);
                break;
        }
    }

    @Override
    public void visit(StringLiteral n) {
        sb.append(n.getLiteral().image);
    }

    @Override
    public void visit(SyntheticLiteral n) {
        switch (n.getType()) {
            case KEY:
                sb.append("KEY(");
                if (n.getProject() != null) {
                    sb.append("PROJECT(");
                    n.getProject().accept(this);
                    sb.append("), ");
                }
                if (n.getNamespace() != null) {
                    sb.append("NAMESPACE(");
                    n.getNamespace().accept(this);
                    sb.append("), ");
                }
                n.getElementList().accept(this);
                sb.append(")");
                break;
            case BLOB:
                sb.append("BLOB(");
                sb.append(n.getLiteral().image);
                sb.append(")");
                break;
            case DATETIME:
                sb.append("DATETIME(");
                sb.append(n.getLiteral().image);
                sb.append(")");
                break;
        }
    }

    @Override
    public void visit(Value n) {
        switch (n.getType()) {
            case BINDING_SITE:
                n.getBindingSite().accept(this);
                break;
            case SYNTHETIC:
                n.getSyntheticLiteral().accept(this);
                break;
            case STRING:
                n.getStringLiteral().accept(this);
                break;
            default:
                sb.append(n.getLiteral().image);
                break;
        }
    }
}
