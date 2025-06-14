module Listas.Permutaciones where

permutate :: (Eq a) => [a] -> [[a]]
permutate [] = [[]]
permutate xs = [a:x | a <- xs, x <- (permutate $ filter (\x -> x /= a) xs)]

permutations :: [a] -> [[a]]
permutations [] = [[]]
permutations xs = [y:ps | (y,ys) <- selections xs, ps <- permutations ys]

selections :: [a] -> [(a,[a])]
selections [] = []
selections (x:xs) = (x,xs) : [(y,x:ys) | (y,ys) <- selections xs]
