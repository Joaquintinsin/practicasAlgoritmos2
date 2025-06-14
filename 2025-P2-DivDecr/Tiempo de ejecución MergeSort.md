# Análisis tiempo de ejecución MergeSort

Llamada recursiva con cada mitad.
Mergear dos listas es lineal, se mergea de a mitades, de la longitud inicial.

\[
T(n) = 2 * T\left(\frac{n}{2}\right) + n
\]

\[
= 2 * \left( 2 * T\left(\frac{n}{4}\right) + \frac{n}{2} \right) + n
\]

\[
= 4 * T\left(\frac{n}{4}\right) + n + n
\]

\[
= 2 * \left( 2 * \left( 2 * T\left(\frac{n}{8}\right) + \frac{n}{4} \right) + \frac{n}{2} \right) + n
\]

\[
= 8 * T\left(\frac{n}{8}\right) + 2n + n
\]

\[
= 2^i * T\left(\frac{n}{2^i}\right) + i * n
\]

### ¿Qué \( i \) consigue que \( \frac{n}{2^i} \) sea 1?
Sabemos que \( T(1) = 1 \), por lo que:

\[
\frac{n}{2^i} = 1
\]

\[
n = 2^i
\]

\[
\log_2 n = i
\]

Este \( i \) lo consigue. Luego,

\[
T(n) = 2^{\log_2 n} * T(1) + \log_2(n) * n
\]

\[
= n * 1 + \log_2(n) * n
\]

\[
= n + \log_2(n) * n
\]

Así,

\[
T(n) \in O(\log_2(n) * n), \quad O(n \log n)
\]
