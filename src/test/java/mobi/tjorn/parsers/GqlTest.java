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

package mobi.tjorn.parsers;

import mobi.tjorn.parsers.gql.*;
import mobi.tjorn.parsers.gql.Formatter;
import mobi.tjorn.parsers.gql.Node;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class GqlTest {
    private final static File MY_DIR = new File(TestConfig.ROOT_DIR, TestConfig.getPath(GqlTest.class));

    @Test
    public void nullTest() throws Exception {
        runTest("nullTest.txt", (GqlParser parser) -> {
            while (parser.getNextToken().kind != GqlParserConstants.EOF) {
                assertEquals(parser.getToken(0).kind, GqlParserConstants.NULL_LITERAL);
            }
        });
    }

    @Test
    public void boolTest() throws Exception {
        runTest("boolTest.txt", (GqlParser parser) -> {
            while (parser.getNextToken().kind != GqlParserConstants.EOF) {
                assertEquals(parser.getToken(0).kind, GqlParserConstants.BOOL_LITERAL);
            }
        });
    }

    @Test
    public void intTest() throws Exception {
        runTest("intTest.txt", (GqlParser parser) -> {
            while (parser.getNextToken().kind != GqlParserConstants.EOF) {
                assertEquals(parser.getToken(0).kind, GqlParserConstants.INT_LITERAL);
            }
        });
    }

    @Test
    public void doubleTest() throws Exception {
        runTest("doubleTest.txt", (GqlParser parser) -> {
            while (parser.getNextToken().kind != GqlParserConstants.EOF) {
                assertEquals(parser.getToken(0).kind, GqlParserConstants.DOUBLE_LITERAL);
            }
        });
    }

    @Test
    public void dateTimeTest() throws Exception {
        runTest("dateTimeTest.txt", (GqlParser parser) -> {
            while (parser.getNextToken().kind != GqlParserConstants.EOF) {
                assertEquals(parser.getToken(0).kind, GqlParserConstants.DATETIME_LITERAL);
            }
        });
    }

    @Test
    public void blobTest() throws Exception {
        runTest("blobTest.txt", (GqlParser parser) -> {
            while (parser.getNextToken().kind != GqlParserConstants.EOF) {
                assertEquals(parser.getToken(0).kind, GqlParserConstants.BLOB_LITERAL);
            }
        });
    }

    @Test
    public void stringTest() throws Exception {
        runTest("stringTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.StringLiteral(), (GqlParser p)->{
                    p.StringLiteral();
                });
            }
        });
    }

    @Test
    public void propertyNameTest() throws Exception {
        runTest("propertyNameTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.PropertyName(), (GqlParser p)->{
                    p.PropertyName();
                });
            }
        });
    }

    @Test
    public void keyPathElementTest() throws Exception {
        runTest("keyPathElementTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.KeyPathElement(), (GqlParser p)->{
                    p.KeyPathElement();
                });
            }
        });
    }

    @Test
    public void keyPathElementListTest() throws Exception {
        runTest("keyPathElementListTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.KeyPathElementList(), (GqlParser p)->{
                    p.KeyPathElementList();
                });
            }
        });
    }

    @Test
    public void syntheticLiteralTest() throws Exception {
        runTest("syntheticLiteralTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.SyntheticLiteral(), (GqlParser p)->{
                    p.SyntheticLiteral();
                });
            }
        });
    }

    @Test
    public void valueTest() throws Exception {
        runTest("valueTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.Value(), (GqlParser p)->{
                    p.Value();
                });
            }
        });
    }

    @Test
    public void eitherComparatorTest() throws Exception {
        runTest("eitherComparatorTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.EitherComparator(), (GqlParser p)->{
                    p.EitherComparator();
                });
            }
        });
    }

    @Test
    public void forwardComparatorTest() throws Exception {
        runTest("forwardComparatorTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.ForwardComparator(), (GqlParser p)->{
                    p.ForwardComparator();
                });
            }
        });
    }

    @Test
    public void backwardComparatorTest() throws Exception {
        runTest("backwardComparatorTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.BackwardComparator(), (GqlParser p)->{
                    p.BackwardComparator();
                });
            }
        });
    }

    @Test
    public void propertyNameListTest() throws Exception {
        runTest("propertyNameListTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.PropertyNameList(), (GqlParser p)->{
                    p.PropertyNameList();
                });
            }
        });
    }

    @Test
    public void conditionTest() throws Exception {
        runTest("conditionTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.Condition(), (GqlParser p)->{
                    p.Condition();
                });
            }
        });
    }

    @Test
    public void compoundConditionTest() throws Exception {
        runTest("compoundConditionTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.CompoundCondition(), (GqlParser p)->{
                    p.CompoundCondition();
                });
            }
        });
    }

    @Test
    public void orderedPropertyNameTest() throws Exception {
        runTest("orderedPropertyNameTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.OrderedPropertyName(), (GqlParser p)->{
                    p.OrderedPropertyName();
                });
            }
        });
    }

    @Test
    public void orderedPropertyNameListTest() throws Exception {
        runTest("orderedPropertyNameListTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.OrderedPropertyNameList(), (GqlParser p)->{
                    p.OrderedPropertyNameList();
                });
            }
        });
    }

    @Test
    public void bindingSiteTest() throws Exception {
        runTest("bindingSiteTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.BindingSite(), (GqlParser p)->{
                    p.BindingSite();
                });
            }
        });
    }

    @Test
    public void resultPositionTest() throws Exception {
        runTest("resultPositionTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.ResultPosition(), (GqlParser p)->{
                    p.ResultPosition();
                });
            }
        });
    }

    @Test
    public void distinctOnTest() throws Exception {
        runTest("distinctOnTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.DistinctOn(), (GqlParser p)->{
                    p.DistinctOn();
                });
            }
        });
    }

    @Test
    public void projectionTest() throws Exception {
        runTest("projectionTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.Projection(), (GqlParser p)->{
                    p.Projection();
                });
            }
        });
    }

    @Test
    public void limitTest() throws Exception {
        runTest("limitTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.Limit(), (GqlParser p)->{
                    p.Limit();
                });
            }
        });
    }

    @Test
    public void offsetTest() throws Exception {
        runTest("offsetTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.Offset(), (GqlParser p)->{
                    p.Offset();
                });
            }
        });
    }

    @Test
    public void queryTest() throws Exception {
        runTest("queryTest.txt", (GqlParser parser) -> {
            while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                testFormatter(parser.Query(), (GqlParser p)->{
                    p.Query();
                });
            }
        });
    }

    private static void testFormatter(Node n, TestRunnable<GqlParser> test) throws Exception {
        final Formatter f = new Formatter();
        n.accept(f);
        System.out.println(f);
        try (final StringReader r = new StringReader(f.toString())) {
            test.run(new GqlParser(r));
        }
    }

    private static void runTest(String fileName, TestRunnable<GqlParser> test) throws Exception {
        try (final FileReader fileReader = new FileReader(new File(MY_DIR, fileName))) {
            test.run(new GqlParser(fileReader));
        }
        assertTrue(true);
    }
}
