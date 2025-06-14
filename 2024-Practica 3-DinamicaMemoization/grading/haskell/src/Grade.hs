module Grade where

import Data.MemoCombinators as Memo
import Data.Array
import Data.List

-- grading student solutions 
grade :: [Int] -> Int
grade xs = length (gradeDC (Data.List.sort xs) xs )

gradeDC :: Eq a => [a] -> [a] -> [a]
gradeDC [] ys = []
gradeDC xs [] = []
gradeDC (x:xs) (y:ys) 
  | x == y    = x : gradeDC xs ys
  | otherwise = longest (gradeDC (x:xs) ys) (gradeDC xs (y:ys))
    where
        longest xs ys = if length xs > length ys then xs else ys

--grade Optimization
gradeMemo :: Eq a => [a] -> [a] -> [a]
gradeMemo xs ys = Memo.integral gradeMemo'
  where
    gradeMemo' [] ys = []
    gradeMemo' xs [] = []
    gradeMemo' (x:xs) (y:ys)
      | x == y    = x : gradeMemo' xs ys
      | otherwise = longest (gradeMemo (x:xs) ys) (gradeMemo xs (y:ys))
      where
        longest xs ys = if length xs > length ys then xs else ys
