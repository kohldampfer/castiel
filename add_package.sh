#!/bin/bash

set -x

sed -i "s/import org.antlr.runtime.\*;/package org.homeunix.wap.php.parser;\n\nimport org.antlr.runtime.*;/g" "$(pwd)/target/generated-sources/antlr3/PhpLexer.java"