module MaxIndex where

-- maxindex retorna la posicion del maximo elemento de la lista
-- Solucion Decrease
maxindexDec :: [Int] -> Int
maxindexDec xs = maxAux xs 0 0 (head xs)

-- lista, posActual, posMax, maxElem
maxAux :: [Int] -> Int -> Int -> Int -> Int
maxAux [] posActual posMax maxElem = posMax
maxAux (x : xs) posActual posMax maxElem
  | x > maxElem = maxAux xs (posActual + 1) posActual x
  | otherwise = maxAux xs (posActual + 1) posMax maxElem

-- Solucion fuerza bruta
maxindexFB :: [Int] -> Int
maxindexFB xs = posicion xs (findMaxElem xs)

findMaxElem :: [Int] -> Int
findMaxElem [x] = x
findMaxElem (x : y : xs)
  | x > y = findMaxElem (x : xs)
  | otherwise = findMaxElem (y : xs)

posicion :: [Int] -> Int -> Int
posicion [x] e
  | x == e = 0
  | otherwise = error "NotFound!!"
posicion (x : xs) e
  | x == e = 0
  | otherwise = 1 + posicion xs e

-- minElem retorna el elemento minimo de una lista
-- Solucion decrease
minElemDec :: [Int] -> Int
minElemDec [x] = x
minElemDec (x : y : xs)
  | x < y = minElemDec (x : xs)
  | otherwise = minElemDec (y : xs)

-- Solucion fuerza bruta
minElemFB :: [Int] -> Int
minElemFB = findMinElem

findMinElem :: [Int] -> Int
findMinElem [x] = x
findMinElem (x : y : xs)
  | x > y = findMinElem (y : xs)
  | otherwise = findMinElem (x : xs)
