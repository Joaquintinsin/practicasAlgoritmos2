module Lcs where

import Data.Function
import Data.List

-- Get subsequences
subseq :: [a] -> [[a]]
subseq xs = sortBy (compare `on` length) $ subsequences xs

-- Longest common subsequence using brute force
lcsBf :: (Eq a) => [a] -> [a] -> [a]
lcsBf xs ys = maximumBy (compare `on` length) (filter (\x -> x `elem` subseq ys) (subseq xs))

-- Longest common subsequence using divide and Conquer
lcsDc :: (Eq a) => [a] -> [a] -> [a]
lcsDc [] _ = []
lcsDc _ [] = []
lcsDc (x : xs) (y : ys)
  | x == y = x : lcsDc xs ys
  | otherwise = longest (lcsDc (x : xs) ys) (lcsDc xs (y : ys))

longest :: (Eq a) => [a] -> [a] -> [a]
longest xs ys = if length xs > length ys then xs else ys
