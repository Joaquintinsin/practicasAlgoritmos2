import qualified Data.List
import MaxIndex
import Test.Hspec

main :: IO ()
main = hspec spec

spec :: Spec
spec = do
  describe "maxindexDec" $ do
    it "Test maxindexDec with descending order " $ do
      maxindexDec [5, 4, 3, 2, 1] `shouldBe` ((0) :: (Int))
    it "Test maxindexDec ascending order" $ do
      maxindexDec [1, 2, 3, 4, 5] `shouldBe` ((4) :: (Int))
    it "Test maxindexDec with random order" $ do
      maxindexDec [2, 3, 1, 4] `shouldBe` ((3) :: (Int))

  describe "maxindexFB" $ do
    it "Test maxindexFB with descending order " $ do
      maxindexFB [5, 4, 3, 2, 1] `shouldBe` ((0) :: (Int))
    it "Test maxindexFB ascending order" $ do
      maxindexFB [1, 2, 3, 4, 5] `shouldBe` ((4) :: (Int))
    it "Test maxindexFb with random order" $ do
      maxindexFB [2, 3, 1, 4] `shouldBe` ((3) :: (Int))

  describe "minElemFB" $ do
    it "Test minElemFB with descending order " $ do
      minElemFB [5, 4, 3, 2, 1] `shouldBe` ((1) :: (Int))
    it "Test minElemFB ascending order" $ do
      minElemFB [1, 2, 3, 4, 5] `shouldBe` ((1) :: (Int))
    it "Test minElemFB with random order" $ do
      minElemFB [2, 3, 1, 4] `shouldBe` ((1) :: (Int))

  describe "minElemDec" $ do
    it "Test minElemDec with descending order " $ do
      minElemDec [5, 4, 3, 2, 1] `shouldBe` ((1) :: (Int))
    it "Test minElemDec ascending order" $ do
      minElemDec [1, 2, 3, 4, 5] `shouldBe` ((1) :: (Int))
    it "Test minElemDec with random order" $ do
      minElemDec [2, 3, 1, 4] `shouldBe` ((1) :: (Int))
