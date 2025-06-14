-- Brute Force Anagramas

permutaciones :: [a] -> [[a]]
permutaciones [] = [[]]
permutaciones (x:xs) = [y | ys <- permutaciones xs, y <- insertar x ys]
  where
    insertar :: a -> [a] -> [[a]]
    insertar x [] = [[x]]
    insertar x (y:ys) = (x:y:ys) : [y:zs | zs <- insertar x ys]

anagramas :: String -> String -> Bool
anagramas _ [] = False
anagramas [] _ = False
anagramas xs ys = or[p == s | p <- permutaciones xs, s <- permutaciones ys]

--Exists p,s : p.belongs(permutaciones xs) ^ s.belongs(permutaciones ys) : p == s
