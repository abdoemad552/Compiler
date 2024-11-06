# Compiler Design Project

## A small compiler for a subset of the C language
This project is a part of the university course "Compilers Design" as an application of all the learned concepts and theories.

We are using Java programming language (**JDK 17**) to implement the phases of this compiler and the main IDE we are using Intellij.

## Project Phases
The phases include building:
- Scanner
- Parser

## Language specification

In this project, we are trying to build a compiler for a subset of C programming language.

In this section, we are going to show specification of this subset.

Our language support the following constructs:
- Keywords
- Identifiers
- Numeric literals (Integers and floating point)
- String literals
- Characters literals
- Preprocessors
- Comments
- Operators
- Special characters
- White spaces (Tabs, line feed, space, and carriage return)

## Scanner
The **scanner** (also known as the **lexical analyzer**) is _the first phase_ in the compilation process of a programming language. Its main purpose is to read the source code, break it down into meaningful units called **_tokens_**, and pass these tokens to the next phase of the compiler (the parser).

We used regular expressions to define scanning rules. For each one of the constructs mentioned in language specification, we created a regular expression that detects this construct.
- Keywords
  - `\b(void|char|short|int|long|float|double|const|if|else|switch|case|default|for|while|break|continue|return|sizeof|static|unsigned)\b`
  - These are the keywords supported in our language.
- Identifiers
  - `\b([a-zA-Z_][a-zA-Z0-9_]*)\b`
  - Identifier names should begin with an alphabet character or underscore, and after that any alphanumeric character or underscores.
- Numeric literals
  - `\b((\d*\.\d+|\d+\.\d*|\d+)([Ee][+-]?\d+)?)\b`
  - This regular expression is used to detect both integers and floating point numbers.
  - Floating point number can be written in scientific notation as well.
    - `2.5E-5`
    - `2.3e+3`
    - `2.50`
- String literals
  - `"([^"\\]|\\[\s\S])*"`
- Character literals
  - `'([^'\\]|\\.)'`
- Preprocessors
  - `^# *include *(<\w+\.h>|"\w+\.h")$`
  - In our language, there is no support for using any preprocessor other than `#include`.
- Comments
  - `//.*?$|/\*[\s\S]*?\*/`
  - In our language, we support single and multiline comments.
- Operators
  - `&&|(\|\|)|(\+\+)|--|(<<|>>|[-+*/%=!<>&^|~])=?`
  - We support using the following operators:
    - Arithmetic operators `+, -, *, /, %`
    - Relational operators `==, !=, <, >, <=, >=`
    - Logical operators `||, &&, !`
    - Bitwise operators `&, |, ^, ~, <<, >>`
    - Assignment operators `=, +=, -=, *=, /=, %=, &=, |=, ^=, <<=, >>=`
    - Increment and Decrement operators `++, --`
- Special characters
  - `[;{}\[\](),.:]`
- White spaces
  - `\s+`

Anything other than these constructs is going to be recognized as `unknown`.
