rmvDup :: Eq a => [a] -> [a]
rmvDup [] = []
rmvDup (x:xs) = x : rmvDup (filter (/= x) xs)
