# Polynomial Constant Finder

## Problem
Given k encrypted roots in JSON format, compute the constant term of the polynomial using Lagrange Interpolation.

## Approach
- Parse JSON input
- Decode y values using given base
- Use Lagrange interpolation
- Compute f(0) directly

## Technologies
- Java
- BigInteger
- BigDecimal

## How to Run
javac PolynomialSolver.java
java PolynomialSolver
