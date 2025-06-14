subListas :: [a] -> [[a]]
subListas [] = [[]]
subListas (x:xs) = [x : sbl | sbl <- subListas xs] ++ subListas xs
