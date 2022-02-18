# Assertj-json - Fluent JSON comparisons for Java

[![GitHub license](https://img.shields.io/github/license/kwakeroni/assertj-json.svg)](https://github.com/kwakeroni/assertj-json/blob/main/LICENSE)
[![Supported Java version](https://img.shields.io/badge/Java-8-green)](https://openjdk.java.net/projects/jdk8/)
[![Maven Build](https://github.com/kwakeroni/assertj-json/actions/workflows/build-maven.yaml/badge.svg)](https://github.com/kwakeroni/assertj-json/actions/workflows/build-maven.yaml)

Convenience test library providing JSONAssert comparisons in the fluent AssertJ style.

## Introduction
**Assertj-json** leverages two other test libraries and integrates them to easily compare JSON content in unit tests:
* [JSONAssert](https://github.com/skyscreamer/JSONassert) is a test library providing JSON comparisons based on the actual data, so as not to depend on whitespace and formatting.
* [AssertJ](https://github.com/assertj) is a test library providing a rich set of strongly-typed assertions for unit testing

The creator of Assertj-json is not affiliated with either of these two libraries.

## Usage
### Matching JSON content
The following snippet shows how to assert that an actual JSON String value (`response`)
matches the expected JSON value.

The example leverages _text blocks_ introduced in Java 15, but works just as well with traditional String literals.

```java
JSONAssertions.assertThatJSON(response).isEqualTo("""
    {
        "title": "Effective Java",
        "author": "Joshua Bloch",
        "edition": 3
    }
    """);
```

### Matching partial JSON content
The following snippet shows how to assert that an actual JSON String value (`response`)
matches _at least_ the given JSON value, but may contain more.

```java
JSONAssertions.assertThatJSON(response).contains("""
    {
        "title": "Effective Java"
    }
    """);
```

### Array order
By default the order of elements in JSON arrays is matched strictly.
The following snippet show how to turn of this strict matching using `ignoringArrayOrder`.

```java
JSONAssertions.assertThatJSON(response)
        .ignoringArrayOrder()
        .contains("""
                  {
                      "chapters": ["Classes and Interfaces", "Enums and Annotations", "Generics"]
                  }
                  """);
```

## JSONAssert Comparison Modes
Users of JSONAssert are probably familiar with the `JSONCompareMode` class that specifies how the comparison is done.
The table below links each `JSONCompareMode` to the corresponding Assertj-json functionality .

| JSONCompareMode | Extensible     | Array Ordering | Assertj-json                                        |
|-----------------|----------------|----------------|-----------------------------------------------------|
| STRICT          | Not extensible | Strict         | `assertThatJSON().isEqualTo()`                      |
| LENIENT         | Extensible     | Non-strict     | `assertThatJSON().ignoringArrayOrder().contains()`  |
| NON_EXTENSIBLE  | Not extensible | Non-strict     | `assertThatJSON().ignoringArrayOrder().isEqualTo()` |
| STRICT_ORDER    | Extensible     | Strict         | `assertThatJSON().contains()`                       |

