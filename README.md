# GQL Parser

### What is this?
A parser that implements [Google Query Language](https://cloud.google.com/datastore/docs/apis/gql/gql_reference)

### Introduction
If you are interested in doing more with your query strings than passing them over 
to [Google Cloud Datastore](https://cloud.google.com/datastore/), you may find this project useful.

GqlParser converts a GQL query string to a parse tree. 
The parser can parse any query sub-string representing a valid production. 
Full GQL grammar can be found at [qgl.jj](src/main/javacc/mobi/tjorn/parsers/gql/gql.jj) file.
[GqlTest](src/test/java/mobi/tjorn/parsers/GqlTest.java) provides examples of parsing most GQL productions.

All parse tree nodes implement [Node](src/main/java/mobi/tjorn/parsers/gql/Node.java) interface.
[Node](src/main/java/mobi/tjorn/parsers/gql/Node.java) 
and [Visitor](src/main/java/mobi/tjorn/parsers/gql/Visitor.java) interfaces support visitor pattern.
[Formatter](src/main/java/mobi/tjorn/parsers/gql/Formatter.java) class implements visitor pattern.

### Using in [Android Studio](https://developer.android.com/studio/index.html) or [IntelliJ IDEA](https://www.jetbrains.com/idea/)

```
buildscript {
    repositories {
        jcenter()
    }
}
```

```
dependencies {
    implementation 'mobi.tjorn.parsers:gql:1.2.0'
}
```

### An example

```
final Reader reader = ...
final GqlParser parser = new GqlParser(reader);
final Query query = parser.Query();
final Formatter formatter = new Formatter();
query.accept(formatter);
System.out.println(formatter.toString());
```

### A note on using JJTree
I favored hand-crafted syntax tree over JJTree-generated one.
JJTree's children list is too generic and makes visitor code slightly messy.
I wanted to write visitor code closely matching syntax-directed translation rules. 
Generic prefix or postfix traversal over children becomes irrelevant 
when implementing translation schemes.
Even though [Node](src/main/java/mobi/tjorn/parsers/gql/Node.java) interface 
declares children() method, it is not used in practice.
For example, [Formatter](src/main/java/mobi/tjorn/parsers/gql/Formatter.java) 
uses it only once when visiting 
[PropertyNameList](src/main/java/mobi/tjorn/parsers/gql/PropertyNameList.java) node.

Nodes are optimized so that [Node](src/main/java/mobi/tjorn/parsers/gql/Node.java) implementations 
do not keep the list of children.
They only have fields that match non-terminals on the right-hand side of their productions. 
The list of children is generated only when Node.children() is called.
