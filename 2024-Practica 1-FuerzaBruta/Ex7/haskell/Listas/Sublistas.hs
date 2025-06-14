module Listas.Sublistas where

subLists :: [a] -> [[a]]
subLists [] = [[]]
subLists (x:xs) = [x:sublist | sublist <- subLists xs] ++ subLists xs
