factorialDivideAndConquer :: Integer -> Integer
factorialDivideAndConquer 0 = 1
factorialDivideAndConquer n = divide 1 n

divide :: Integer -> Integer -> Integer
divide n m
  | n == m     = n
  | otherwise  = let mid = (n + m) `div` 2
                 in divide n mid * divide (mid + 1) m

factorialDecreaseAndConquer :: Integer -> Integer
factorialDecreaseAndConquer 0 = 1
factorialDecreaseAndConquer n = decrease 1 n

decrease :: Integer -> Integer -> Integer
decrease acum 1 = acum
decrease acum n = decrease (acum * n) (n - 1)

main :: IO ()
main = do
    putStrLn "Factorial usando Divide and Conquer:"
    print $ factorialDivideAndConquer 5
    putStrLn "Factorial usando Decrease and Conquer:"
    print $ factorialDecreaseAndConquer 5
