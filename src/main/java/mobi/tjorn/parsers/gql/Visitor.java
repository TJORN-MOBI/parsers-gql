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
 * Declares Visitor pattern over {@link Node}s.
 *
 * @author yuri
 * @since 6/12/16.
 */
public interface Visitor {
    void visit(BackwardComparator n);
    void visit(BindingSite n);
    void visit(CompoundCondition n);
    void visit(Condition n);
    void visit(DistinctOn n);
    void visit(EitherComparator n);
    void visit(ForwardComparator n);
    void visit(KeyPathElement n);
    void visit(KeyPathElementList n);
    void visit(Kind n);
    void visit(Limit n);
    void visit(MoreConditions n);
    void visit(MoreKeyPathElements n);
    void visit(MoreOrderedPropertyNames n);
    void visit(MorePropertyNames n);
    void visit(MorePropertyNameSteps n);
    void visit(Offset n);
    void visit(OrderedPropertyName n);
    void visit(OrderedPropertyNameList n);
    void visit(Projection n);
    void visit(PropertyName n);
    void visit(PropertyNameList n);
    void visit(Query n);
    void visit(ResultPosition n);
    void visit(StringLiteral n);
    void visit(SyntheticLiteral n);
    void visit(Value n);
}
