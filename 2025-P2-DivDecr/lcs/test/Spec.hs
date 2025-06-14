import qualified Data.List
import Lcs
import Test.Hspec
import Test.QuickCheck

propLcs :: String -> String -> Bool
propLcs xs ys = length (lcsBf xs ys) == length (lcsDc xs ys)

main :: IO ()
main = hspec spec

spec :: Spec
spec = do
  describe "Longest Common Subsequence" $ do
    it "Test lcsBf with one empty list" $ do
      lcsBf [] "ACDBAC" `shouldBe` ([] :: String)
      quickCheckWith stdArgs {maxSize = 15} propLcs
    it "Test lcsBf inputs with no element in common " $ do
      lcsBf "BCDAACD" "FGHHFG" `shouldBe` ([] :: String)
      quickCheckWith stdArgs {maxSize = 15} propLcs
    it "Test lcsBf with equal list" $ do
      lcsBf "BCDAACD" "BCDAACD" `shouldBe` ("BCDAACD" :: String)
      quickCheckWith stdArgs {maxSize = 15} propLcs

    it "Test to compare lcsBf and lcsDC results 1" $ do
      lcsDc "BCDAACD" "BCDAACD" `shouldBe` ("BCDAACD" :: String)
      quickCheckWith stdArgs {maxSize = 15} propLcs
    it "Test to compare lcsBf and lcsDC results 2" $ do
      lcsDc "BCDAACD" "FGHHFG" `shouldBe` ([] :: String)
      quickCheckWith stdArgs {maxSize = 15} propLcs
    it "Test to compare lcsBf and lcsDC results 3" $ do
      lcsDc [] "ACDBAC" `shouldBe` ([] :: String)
      quickCheckWith stdArgs {maxSize = 15} propLcs

-- use verboseCheckWith to see the actual values generated
