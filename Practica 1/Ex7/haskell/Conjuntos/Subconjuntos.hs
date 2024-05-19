module Conjuntos.Subconjuntos where

listToSet :: (Eq a) => [a] -> [a]
listToSet [] = []
listToSet (x:xs)
  | x `elem` xs = listToSet xs
  | otherwise   = x:(listToSet xs)

allSubSets :: (Eq a) => [a] -> [[a]]
allSubSets xs = subSets (listToSet xs)

subSets :: [a] -> [[a]]
subSets [] = [[]]
subSets (x:xs) = subSets xs ++ map (x:) (subSets xs)
