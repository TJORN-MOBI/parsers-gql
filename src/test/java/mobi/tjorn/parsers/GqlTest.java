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
        runTest("nullTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getNextToken().kind != GqlParserConstants.EOF) {
                    assertEquals(parser.getToken(0).kind, GqlParserConstants.NULL_LITERAL);
                }
            }
        });
    }

    @Test
    public void boolTest() throws Exception {
        runTest("boolTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getNextToken().kind != GqlParserConstants.EOF) {
                    assertEquals(parser.getToken(0).kind, GqlParserConstants.BOOL_LITERAL);
                }
            }
        });
    }

    @Test
    public void intTest() throws Exception {
        runTest("intTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getNextToken().kind != GqlParserConstants.EOF) {
                    assertEquals(parser.getToken(0).kind, GqlParserConstants.INT_LITERAL);
                }
            }
        });
    }

    @Test
    public void doubleTest() throws Exception {
        runTest("doubleTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getNextToken().kind != GqlParserConstants.EOF) {
                    assertEquals(parser.getToken(0).kind, GqlParserConstants.DOUBLE_LITERAL);
                }
            }
        });
    }

    @Test
    public void dateTimeTest() throws Exception {
        runTest("dateTimeTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getNextToken().kind != GqlParserConstants.EOF) {
                    assertEquals(parser.getToken(0).kind, GqlParserConstants.DATETIME_LITERAL);
                }
            }
        });
    }

    @Test
    public void blobTest() throws Exception {
        runTest("blobTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getNextToken().kind != GqlParserConstants.EOF) {
                    assertEquals(parser.getToken(0).kind, GqlParserConstants.BLOB_LITERAL);
                }
            }
        });
    }

    @Test
    public void stringTest() throws Exception {
        runTest("stringTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.StringLiteral(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.StringLiteral();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void propertyNameTest() throws Exception {
        runTest("propertyNameTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.PropertyName(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.PropertyName();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void keyPathElementTest() throws Exception {
        runTest("keyPathElementTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.KeyPathElement(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.KeyPathElement();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void keyPathElementListTest() throws Exception {
        runTest("keyPathElementListTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.KeyPathElementList(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.KeyPathElementList();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void syntheticLiteralTest() throws Exception {
        runTest("syntheticLiteralTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.SyntheticLiteral(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.SyntheticLiteral();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void valueTest() throws Exception {
        runTest("valueTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.Value(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.Value();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void eitherComparatorTest() throws Exception {
        runTest("eitherComparatorTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.EitherComparator(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.EitherComparator();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void forwardComparatorTest() throws Exception {
        runTest("forwardComparatorTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.ForwardComparator(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.ForwardComparator();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void backwardComparatorTest() throws Exception {
        runTest("backwardComparatorTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.BackwardComparator(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.BackwardComparator();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void propertyNameListTest() throws Exception {
        runTest("propertyNameListTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.PropertyNameList(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.PropertyNameList();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void conditionTest() throws Exception {
        runTest("conditionTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.Condition(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.Condition();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void compoundConditionTest() throws Exception {
        runTest("compoundConditionTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.CompoundCondition(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.CompoundCondition();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void orderedPropertyNameTest() throws Exception {
        runTest("orderedPropertyNameTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.OrderedPropertyName(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.OrderedPropertyName();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void orderedPropertyNameListTest() throws Exception {
        runTest("orderedPropertyNameListTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.OrderedPropertyNameList(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.OrderedPropertyNameList();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void bindingSiteTest() throws Exception {
        runTest("bindingSiteTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.BindingSite(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.BindingSite();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void resultPositionTest() throws Exception {
        runTest("resultPositionTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.ResultPosition(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.ResultPosition();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void distinctOnTest() throws Exception {
        runTest("distinctOnTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.DistinctOn(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.DistinctOn();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void projectionTest() throws Exception {
        runTest("projectionTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.Projection(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.Projection();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void limitTest() throws Exception {
        runTest("limitTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.Limit(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.Limit();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void offsetTest() throws Exception {
        runTest("offsetTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.Offset(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.Offset();
                        }
                    });
                }
            }
        });
    }

    @Test
    public void queryTest() throws Exception {
        runTest("queryTest.txt", new TestRunnable<GqlParser>() {
            @Override
            public void run(GqlParser parser) throws Exception {
                while (parser.getToken(1).kind != GqlParserConstants.EOF) {
                    testFormatter(parser.Query(), new TestRunnable<GqlParser>() {
                        @Override
                        public void run(GqlParser p) throws Exception {
                            p.Query();
                        }
                    });
                }
            }
        });
    }

    private static void testFormatter(Node n, TestRunnable<GqlParser> test) throws Exception {
        final Formatter f = new Formatter();
        n.accept(f);
        System.out.println(f);
        final StringReader r = new StringReader(f.toString());
        try {
            test.run(new GqlParser(r));
        } finally {
            r.close();
        }
    }

    private static void runTest(String fileName, TestRunnable<GqlParser> test) throws Exception {
        final FileReader fileReader = new FileReader(new File(MY_DIR, fileName));
        try {
            test.run(new GqlParser(fileReader));
        } finally {
            fileReader.close();
        }
        assertTrue(true);
    }
}
