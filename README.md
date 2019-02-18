# Wildcard pattern matcher

Program that verifies if the second string is a substring of the first string.
Each occurrence of * in the second substring means that it can be a match for zero or more characters of the first string.

Consider the example:<br>
Input string 1: abcd<br>
Input string 2: a*c<br>
Program should evaluate that the string 2 is a substring of the string 1.

Additionally asterisk (*) may be considered as a regular character, if it is preceded by a backslash (\\). 

## Getting Started

Generate jar file:
```
$ cd "Wildcard Matcher"
$ mvn package
```

Run executable jar from the command line (assuming project name is "Wildcard Matcher"):
```
$ cd "Wildcard Matcher\target"
$ java -jar wildcard-matcher-1.0-SNAPSHOT.jar text pattern
```
Program requires 2 string arguments as an input:
* text - string to search for pattern within, ex. abcd
* pattern - string to find in the text, ex. a*c

## Authors

* **Piotr Niewiński**
