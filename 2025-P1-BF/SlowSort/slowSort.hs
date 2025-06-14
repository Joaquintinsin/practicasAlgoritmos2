slowSort :: Ord a => [a] -> [a]
slowSort xs = head (filter isSorted (permutaciones xs))

isSorted :: Ord a => [a] -> Bool
isSorted [] = True
isSorted [_] = True
isSorted (x:y:xs) = x <= y && isSorted (y:xs)

permutaciones :: [a] -> [[a]]
permutaciones [] = [[]]
permutaciones (x:xs) = [y | ys <- permutaciones xs, y <- insertar x ys]
  where
    insertar :: a -> [a] -> [[a]]
    insertar x [] = [[x]]
    insertar x (y:ys) = (x:y:ys) : [y:zs | zs <- insertar x ys]
