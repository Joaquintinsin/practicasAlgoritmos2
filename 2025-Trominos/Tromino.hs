-- Incompletisimo, no usar jejee!! =)

type Pos = (Int, Int)
type Tromino = (Pos, Pos, Pos)

trominoTiling :: Int -> Pos -> Int -> Pos -> [Tromino]
trominoTiling 1 (x, y) size (offsetX, offsetY)
  | cuadraditoSuperiorIzq (x, y) = [((x + 1, y), (x, y - 1), (x + 1, y + 1))]
  | cuadraditoSuperiorDer (x, y) = [((x - 1, y), (x, y + 1), (x - 1, y - 1))]
  | cuadraditoInferiorDer (x, y) = [((x - 1, y), (x, y - 1), (x - 1, y - 1))]
  | cuadraditoInferiorIzq (x, y) = [((x + 1, y), (x, y - 1), (x + 1, y - 1))]
  | otherwise = error "Posición no válida"
trominoTiling n (x, y) size (offsetX, offsetY)
  | cuadraditoPrimerCuadrante size (x, y) = error "TODO"
  | otherwise = error "TODO"


-- La idea es que tomas el size, la posicion del cuadradito y el offset que estas
-- Revisas cuadrantes y devolves la nueva posicion del cuadradito segun el cuadrante que este
-- Habria que ver tambien en que llamada recursiva estas, para saber de que manera moverse con el offset
posicionRelativa :: Int -> Pos -> Pos -> Pos
posicionRelativa size (x, y) (offsetX, offsetY)
  | cuadraditoPrimerCuadrante size (x, y) = (x - offsetX, y - offsetY)
  | cuadraditoSegundoCuadrante size (x, y) = (x - offsetX - size `div` 2, y - offsetY)
  | cuadraditoTercerCuadrante size (x, y) = (x - offsetX, y - offsetY - size `div` 2)
  | cuadraditoCuartoCuadrante size (x, y) = (x - offsetX - size `div` 2, y - offsetY - size `div` 2)
  | otherwise = error "Posición no válida"


-- Auxiliares cuadradito
cuadraditoPrimerCuadrante :: Int -> Pos -> Bool
cuadraditoPrimerCuadrante size (pX, pY) = (pX >= 0 && pX < size `div` 2) && (pY >= 0 && pY < size `div` 2)

cuadraditoSegundoCuadrante :: Int -> Pos -> Bool
cuadraditoSegundoCuadrante size (pX, pY) = (pX >= size `div` 2 && pX < size) && (pY >= 0 && pY < size `div` 2)

cuadraditoTercerCuadrante :: Int -> Pos -> Bool
cuadraditoTercerCuadrante size (pX, pY) = (pX >= 0 && pX < size `div` 2) && (pY >= size `div` 2 && pY < size)

cuadraditoCuartoCuadrante :: Int -> Pos -> Bool
cuadraditoCuartoCuadrante size (pX, pY) = (pX >= size `div` 2 && pX < size) && (pY >= size `div` 2 && pY < size)

cuadraditoSuperiorIzq :: Pos -> Bool
cuadraditoSuperiorIzq (x, y) = x == 0 && y == 0

cuadraditoSuperiorDer :: Pos -> Bool
cuadraditoSuperiorDer (x, y) = x == 1 && y == 0

cuadraditoInferiorDer :: Pos -> Bool
cuadraditoInferiorDer (x, y) = x == 1 && y == 1

cuadraditoInferiorIzq :: Pos -> Bool
cuadraditoInferiorIzq (x, y) = x == 0 && y == 1
