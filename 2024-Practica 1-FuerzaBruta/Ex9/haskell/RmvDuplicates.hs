removeDuplicates :: (Eq a) => [a] -> [a]
removeDuplicates [] = []
removeDuplicates (x:xs)
  | x `elem` xs = removeDuplicates xs
  |   otherwise = x:removeDuplicates xs

{-
  Complejidad en el peor caso (que tenga todos sus elementos duplicados hasta el final de la lista)
  Sea elem de orden lineal (n)
  T(0) = 1
  T(n) = n+T(n-1)
       = n+(n+T(n-2))
       = n+(n+(n+T(n-3)))
       = ...
       Sean k pasos tal que T(n-k)
       Caso base en n-k = 0
                    n = k
       = Sumatoria (i=1 , n) (n+T(n-k))
       = Sumatoria (i=1 , n) (n+1)
       = (n+1) * n
  T(n) de Orden (n*n + n = n^2 + n)
  T(n) pertenece Sigma(n^2) : orden cuadrático en el peor caso
-}
