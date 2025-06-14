module Lcs where

import Data.List
import Data.Function

--Get subsequences
subseq :: [a] -> [[a]]
subseq xs = sortBy (compare `on` length) $ subsequences xs

--Longest common subsequence using brute force
lcsBf :: Eq a => [a] -> [a] -> [a]
lcsBf xs ys = maximumBy (compare `on` length) (filter (\x -> x `elem` subseq ys) (subseq xs))

--Longest common subsequence using divide and Conquer
lcsDc :: Eq a => [a] -> [a] -> [a]
lcsDc [] _ = []
lcsDc xs [] = []
lcsDc (x:xs) (y:ys)
  | x == y    = x : lcsDc xs ys
  | otherwise = let l1 = lcsDc (x:xs) ys
                    l2 = lcsDc xs (y:ys)
                in longest l1 l2
  where longest xs ys = if length xs > length ys then xs else ys
