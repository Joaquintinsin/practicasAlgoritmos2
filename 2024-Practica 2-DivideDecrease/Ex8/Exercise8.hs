-- 8. Utilizando la técnica Decrease & Conquer, diseñe un algoritmo para encontrar los elementos mayor
-- y menor de una secuencia de n enteros positivos. Implemente su algoritmo en Haskell.

menMay :: [Int] -> (Int, Int)
menMay [x] = (x,x)
menMay [x,y] = (min x y , max x y)
menMay (x:xs) = (min x a , max x b) -- Extension
  where (a,b) = menMay xs -- Decrease
