module Main where

import MaxIndex

main :: IO ()
main = do
  let x = [0, 3, 42, 12, 7]

  -- Call the functions.
  let a = maxindexDec x

  let b = maxindexFB x

  let c = minElemFB x

  let d = minElemDec x

  putStrLn ("The index of max element in: " ++ show x)
  putStrLn (" made by decrease function is : " ++ show a)
  putStrLn (" made by BF function is: " ++ show b)
  putStrLn ("Minimum element with BF: " ++ show c)
  putStrLn ("Minimum element with Dec: " ++ show d)
