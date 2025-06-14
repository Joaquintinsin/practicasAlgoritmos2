sig Interprete {}
sig Cancion {}
sig Catalogo {
	canciones: set Cancion,
	interpretes : set Interprete,
	interpretaciones: canciones -> interpretes
}
// un catalogo es consistente si todas las canciones del catalogo
// estan registradas por algun interprete y todo interprete
// del catalogo tiene registrada alguna cancion

fact allSongsAreRegisteredBySomeInterprete {
	all cc: Catalogo.canciones | cc in Interprete
}

fact allInterpreteOfTheCatalogoHaveSomeSongRegistered {
	all ci: Catalogo.interpretes | some c: Cancion | c in ci
}

// predicados

// dado un catalogo y una cancion con su interprete, devuelva
// un nuevo catalogo igual al primero pero con su interpretacion
// agregada

pred addInterprete(can: Cancion, cat: Catalogo, cat': Catalogo, i: Interprete) {
	(can -> i) in cat'.interpretaciones
	and
	cat'.interpretaciones in cat.interpretaciones
	and
	can in cat'.canciones and cat'.canciones in cat.canciones
	and
	i in cat'.interpretes and cat'.interpretes in cat.interpretes
}

// dado un catalogo y una cancion con su interprete, devuelva un nuevo
// catalogo igual al primero pero eliminando esa interpretacion
pred removeInterpretacionFromCatalogo(can:Cancion, inter: Interprete, cat: Catalogo, cat': Catalogo) {
	cat'.canciones = cat.canciones - can
	cat'.interpretes = cat.interpretes - inter
	cat'.interpretaciones = cat.interpretaciones - (can -> inter)
}



run removeInterpretacionFromCatalogo{}
