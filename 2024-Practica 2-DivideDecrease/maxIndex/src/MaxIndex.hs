-- ~ 9. Utilizando la técnica Decrease & Conquer, diseñe un algoritmo para encontrar el índice del mayor
-- ~ elemento de una secuencia de n enteros positivos. Piense en una alternativa a este algoritmo diseñado
-- ~ utilizando Fuerza Bruta, y compare implementaciones para estos dos algoritmos en Haskell.
-- ~ Realice además el análisis correspondiente para calcular cuántas comparaciones de elementos son
-- ~ realizadas por ambos algoritmos en el peor caso.

module MaxIndex where

-- maxindex retorna la posicion del maximo elemento de la lista
-- Solucion fuerza bruta
maxindexFB :: [Int] -> Int
maxindexFB xs = indexOf xs (maximum xs) 0

-- -- Solucion Decrease and Conquer
maxindexDec :: [Int] -> Int
maxindexDec xs = snd (indiceMayorElementoDaC xs)

-- fst == ElemMax , snd == Indice
indiceMayorElementoDaC :: [Int] -> (Int, Int)
indiceMayorElementoDaC [x] = (x,0)
indiceMayorElementoDaC (x:xs) = if (x > a) then (x,0) else (a, indexOf xs a 1)
  where a = fst (indiceMayorElementoDaC xs)

-- Lista original , elemento a ver el indice , variable acumulador
indexOf :: [Int] -> Int -> Int -> Int
indexOf [x] n r = r
indexOf (x:xs) n r = if (x == n) then r else indexOf xs n (r+1)

{- Análisis de comparaciones de elementos en el peor caso
  Fuerza bruta:
    maximum xs
      realiza n comparaciones en su peor caso (el elemento máximo está al último o no está)
    indexOf xs n a
      realiza n comparaciones en su peor caso (el elemento a buscar es el último)
    maxindexFB xs
      tiempo n*n (cuadrático)
  
  Decrease and Conquer:
    indexOf xs n a
      realiza n comparaciones en su peor caso (el elemento a buscar es el último)
    indiceMayorElementoDaC xs
      en su peor caso, entra siempre al indexOf hasta el último elemento, que ya vimos que es lineal
      por lo tanto;
    maxindexDec xs
      tiempo n (lineal)
-}
