# Polynomial Constant Finder (Lagrange Interpolation)

## 📌 Problem Statement
Given encrypted roots of a polynomial in JSON format, determine the **constant term (c)** of the polynomial:

f(x) = a x^n + b x^(n-1) + c

Where:
- `n` = total roots provided
- `k` = minimum roots required (k = m + 1, where m is polynomial degree)
- Each root is provided as:
  - x → key in JSON
  - y → encoded in a given base

The objective is to:
1. Decode the y-values using the specified base.
2. Use **Lagrange Interpolation**.
3. Compute the constant term directly as **f(0)**.
4. Avoid hardcoding.
5. Handle large numbers safely.

---

## 🛠 Approach

- Parse JSON input dynamically (no hardcoding).
- Decode encrypted values using:
  - `BigInteger(String value, int base)`
- Use **Lagrange Interpolation** formula:
  
  f(0) = Σ yi * Π (-xj / (xi - xj))

- Use:
  - `BigInteger` for arbitrary precision integer handling.
  - `BigDecimal` for precision-safe division.

Time Complexity: O(k²)

---

## 📂 Project Structure

#### PolynomialSolver.java
#### testcase1.json
#### testcase2.json
#### .gitignore
#### README.md
---

## ▶ How to Run

Compile:
```bash
javac PolynomialSolver.java
```
Run Test Case 1:
```bash
java PolynomialSolver testcase1.json
```
Run Test Case 2:
```bash
java PolynomialSolver testcase2.json
```
## ✅ Outputs

Test Case 1:
```bash
3
```
Test Case 2:
```bash
-6290016743746469796
```

---

## 💡 Key Highlights

- No hardcoded values.
- Dynamic JSON parsing.
- Supports multiple number bases (2–36).
- Handles very large values safely.
- Computes constant term directly without constructing full polynomial.

---

## 👨‍💻 Language Used
```bash
Java
```














