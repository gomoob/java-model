# java-model

> Gomoob Java Data Model library.

[![Build Status](https://img.shields.io/travis/gomoob/java-model.svg?style=flat)](https://travis-ci.org/gomoob/java-model)

The Gomoob Data Model library contains utility interfaces, abstract classes and classes used to create Java Data Models
/ Domain Models quickly and using best practices.

## Setup

### Maven

Add the following dependency to you `pom.xml` file.

```xml
<dependency>
    <groupId>org.gomoob</groupId>
    <artifactId>model</artifactId>
    <version>1.0</version>
</dependency>
```

## Usage

Read the JavaDoc (we're currently preparing a more user friendly documentation with tutorials) and use what you need,
for example to create a simple `User` entity having an `Integer` identifier simply extends the `AbstractEntity`.

```java
package com.mycompany.myproject.model.user;

import com.mycompany.myproject.model.IUser;

import org.gomoob.model.AbstractEntity

class User extends AbstractEntity<Integer> implements IUser {

}
```

## Changelog

All notable changes to this project will be documented in the
[CHANGELOG.md](https://github.com/gomoob/java-model/blob/master/CHANGELOG.md) file.

The format is based on [Keep a Changelog](http://keepachangelog.com/) and this project adheres to
[Semantic Versioning](http://semver.org/).

## About Gomoob

At [Gomoob](https://www.gomoob.com) we build high quality software with awesome Open Source frameworks everyday. Would
you like to start your next project with us? That's great! Give us a call or send us an email and we will get back to
you as soon as possible !

You can contact us by email at [contact@gomoob.com](mailto:contact@gomoob.com) or by phone number
[(+33) 6 85 12 81 26](tel:+33685128126) or [(+33) 6 28 35 04 49](tel:+33685128126).

Visit also http://gomoob.github.io to discover more Open Source softwares we develop.
