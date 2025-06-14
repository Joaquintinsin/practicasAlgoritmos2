module Assignment where

import Data.List (sort)
import Conjuntos.Subconjuntos
import Listas.Sublistas
import Listas.Permutaciones

-- 1) Decidir si dos secuencias son anagramas.
sonAnagramas :: (Eq a) => [a] -> [a] -> Bool
sonAnagramas [] [] = True
sonAnagramas (x:xs) (y:ys) = longs && x `elem` (y:ys) && y `elem` (x:xs)
  where longs = (length xs == length ys)

-- 2) Dado un conjunto s y un valor n, decidir si existe un subconjunto de s cuya suma sea n.
--    {existe subConjunto ys | (sumatoria ys)==n}
--    or[sum(ys) == n | ys <- allSubSets givenSet]
existeSumaSubconjunto :: (Num a, Eq a) => [a] -> a -> Bool
existeSumaSubconjunto [] n = n == 0
existeSumaSubconjunto (x:xs) n = or[sum ys == n | ys <- allSubSets givenSet] 
  where givenSet = listToSet (x:xs)

-- 3) Dadas dos cadenas p y s, decida si p es subcadena de s.
esSubCadena :: String -> String -> Bool
esSubCadena p s
  | length p > length s = False
  |           otherwise = sort p `elem` map sort (subLists s)

-- 4) Dadas dos cadenas p y s, decida si p es subsecuencia de s (los elementos no necesariamente tienen que aparecer contiguos en s).
esSubSecuencia :: String -> String -> Bool
esSubSecuencia p s
  | length p > length s = False
  |           otherwise = sort p `elem` map sort (subSets s)

{- 
  Notar que para subCadena los elementos tienen que aparecer contiguos, mientras que para subSecuencia no necesariamente
  Es por eso que uno se trata con subListas (obligando que aparezcan contiguos)
  Y el otro se trata con subSets (pudiendo no estar contiguos)
-}
