[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) 
[![Build Status](https://travis-ci.org/org-tigris-jsapar/jsapar-examples.png?branch=master)](https://travis-ci.org/org-tigris-jsapar/jsapar-examples)
[![JSaPar](https://img.shields.io/badge/JSaPar-2.0-green.svg)](https://github.com/org-tigris-jsapar/jsapar) 
[![Status](https://img.shields.io/badge/Status-Pre--Alpha-lightgrey.svg)](#Pre-Alpha)

[![Java 8](https://img.shields.io/badge/java-8-brightgreen.svg)](#java-8) 
[![Java 9-ea](https://img.shields.io/badge/java-9-brightgreen.svg)](#java-9) 
[![Java 9-ea](https://img.shields.io/badge/java-10-brightgreen.svg)](#java-10)

# JSaPar Examples
The [JSaPar](https://github.com/org-tigris-jsapar/jsapar) Example project containing all the examples on how to use the JSaPar library to its full potential.

Examples are listed in packages where each package contains one example class plus all files necessary to run that 
example. Each example class can however contain more than one example method.

# Introduction
## [a1](https://github.com/org-tigris-jsapar/jsapar-examples/tree/master/src/main/java/org/jsapar/examples/introduction/a1) Simple parse and compose
* Parsing simple unquoted CSV into internal Document structure.
* Composing simple unquoted CSV based on same schema.
# Basics
## [b1](https://github.com/org-tigris-jsapar/jsapar-examples/tree/master/src/main/java/org/jsapar/examples/basics/b1) Simple convert
* Converting CSV into fixed width format using one schema for each.
## [b2](https://github.com/org-tigris-jsapar/jsapar-examples/tree/master/src/main/java/org/jsapar/examples/basics/b2) Convert CSV to Java Beans
* Converting CSV into Java Beans using lambda.
* Converting CSV into Java Beans using RecordingBeanEventListener.
## [b3](https://github.com/org-tigris-jsapar/jsapar-examples/tree/master/src/main/java/org/jsapar/examples/basics/b3) Convert Java Beans to CSV
* Converting Java Beans one by one into CSV.
* Converting a collection of Java Beans into CSV.
## [b4](https://github.com/org-tigris-jsapar/jsapar-examples/tree/master/src/main/java/org/jsapar/examples/basics/b4) Manipulating and filtering
* Manipulating and filtering while converting CSV into fixed width format.
# Schema basics
## [c1](https://github.com/org-tigris-jsapar/jsapar-examples/tree/master/src/main/java/org/jsapar/examples/schemabasics/c1) Line condition cells
* Parsing of CSV file where first cell denotes the type of the line to be parsed.
* Parsing of fixed width file where first cell denotes the type of the line to be parsed.
* Converting from fixed width file to CSV file where first cell denotes the type of the line to be converted.
## [c2](https://github.com/org-tigris-jsapar/jsapar-examples/tree/master/src/main/java/org/jsapar/examples/schemabasics/c2) Empty cell pattern
* Parsing of CSV file where the schema of a cell has a pattern that if the content matches, the content is regarded as empty.
## [c3](https://github.com/org-tigris-jsapar/jsapar-examples/tree/master/src/main/java/org/jsapar/examples/schemabasics/c3) Quoting
* Parsing of CSV file where the schema specifies a quote character and where some of the cells are quoted.
* Composing of CSV where the schema specifies that one of the cells should always be quoted and where other cells are automatically quoted when needed.
