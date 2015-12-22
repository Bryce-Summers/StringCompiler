# StringCompiler
A general translation library that allows the programmer to specify a grammar of type and inference rules.

#Author
Bryce Summers is the author of this code. Many great computer scientists, mathematiscians, computer engineers, and programmers as well as many other people probably created technology such as the invention of the computer that I used in the proccess.

#Inspiration
I was inspired to write this software while taking a course on logic, Undecidability, and Incompleteness.

#Why should I use this Project?
This project can be used for many things such as using inference rules to derive canonical strings for use in automatic grading systems. If this StringCompiler were used then automatic grading systems could be less strict in the input that the accept as correct.

#Current State:

List types and tuple types are working pretty well.

I have two perfectly working example and a predicate logic example that has two test cases that do not parse correctly.

There are still probably many bugs floating around. This project is by no means done yet.

#Usage

You can find several example programs using the String compiler in the src/examples/ folder. In particular, please look at the predicate logic example that attempts to parse all of predicate logic.

Here is a very simple example (which can also be found in the example folder) of using this program to implement a duplication rule that appends a copy of the input string to itself.
```

// Notes: 'Types' are well defined expressions that can be parsed and formally constructed from subsets of an input string.
//  - Rules are formalized descriptions of how to map one formally constructed type to another. 
//    For a given type it may be constructed as a formal translation of input data into a new form based on rules.
//  - Matchers are pattern matching expressions used in the formal description of a types.
//  - Derivers parse an input string into a single type, the power of this is that the given derived type can be specified with a 
//    formal construction from other types, so relevant transformations of the input data can be defined for specific syntactic
//    locations and patterns within the input string.
//    Beware of ambiguous grammars!!!

// Create a Universal Type that matches anything.
Type S = new type_List(Matcher.ALL);

// S --> SS
Rule rule_duplicate = new Rule(Rule.P(S), Rule.P(S, S));
Type type_duplicate = new Type(rule_duplicate);

String output;
output = Deriver.derive("Bryce Summers ", type_duplicate);
println(output);// prints "Bryce Summers Bryce Summers"
		
output = Deriver.derive("has ", type_duplicate);
println(output);// prints "has has "
		
output = Deriver.derive("achieved ", type_duplicate);
println(output);//prints "achieved achieved "
		
output = Deriver.derive("duplication ", type_duplicate);
println(output);// prints "duplication duplication "
```

#A Liscense that I am trying out.

The MIT License (MIT)

Copyright (c) 2015 Bryce Summers

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
