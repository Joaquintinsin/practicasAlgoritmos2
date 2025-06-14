import Data.Time
import Data.Time.Calendar.Month
import Data.List (sortBy, sort)
import Data.Ord (comparing, Down (Down))

type Fecha = Int
type Prioridad = Int
-- Descripcion, Fecha, HoraInicio, HoraFin, Prioridad
type HoraInicioFin = (Int, Int)
type Descripcion = String
type DatosCita = (Descripcion, Fecha)

-- FechaDada, Descripcion (primary key), Fecha, HoraInicio, HoraFin, Prioridad
greedyCitas :: Int -> [String] -> [Int] -> [(Int, Int)] -> [Int] -> [String]
greedyCitas _ _ _ _ [] = []
greedyCitas _ [] _ _ _ = []
greedyCitas fecha (d:ds) (f:fs) _ (p:ps) = undefined
  where
    filtrarFechas = filter (== fecha) (f:fs)

    ordenarPrioridades = sortBy (comparing Data.Ord.Down) (p:ps)

