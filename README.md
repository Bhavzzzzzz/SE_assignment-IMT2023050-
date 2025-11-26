# CLI Calculator (Java)

This is a simple command-line calculator written in Java. It supports the following:

- Basic arithmetic: +, -, *, /
- Exponentiation: ^
- Parentheses: ()
- Decimal numbers
- Unary minus (negatives)

Usage
-----

Build:
```bash
javac Calculator.java
```

Run:
```bash
java Calculator
```

Examples (enter at the prompt):
```
calc> 2 + 3 * 4
14
calc> (1 + 2) * 3
9
calc> -5 + 3^2
4
calc> 1 / 0
Error: Division by zero
calc> exit
Goodbye!
```

Notes
-----
- The parser uses the shunting-yard algorithm to convert an infix expression to RPN, then evaluates it.
- The REPL prints integers without trailing .0 when the result is an integer.
- Invalid input will produce an error message.

License
-------
MIT