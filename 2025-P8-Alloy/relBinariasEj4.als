sig Nodo{}
sig Tree {
	raiz: Nodo,
	hijos: raiz -> Tree,
	hoja: raiz
}

pred preorder(r: Nodo -> Nodo) (
	all n: Nodo | (n -> n) in r
	all a, b, c: Nodo | (a -> b) in r and (b -> c) in r implies (a -> c) in r
}

pred esOrdenParcial(r: Nodo -> Nodo) {
	preorder[r]
	and
	all a, b: Nodo | (a -> b) in r and (b -> c) in r implies a = c
}

pred esOrdenTotal(r: Nodo -> Nodo) {
	esOrdenParcial[r]
	and
	all a, b: Nodo | a != b implies ((a -> b) in r or (b -> c) in r)
}

pred esOrdenEstricto(r: Nodo -> Nodo) {
	all n: Nodo | not((n -> n) in r)
	all a, b, c: Nodo | ((a -> b) in r and (b -> c) in r) implies (a -> c) in r
}

pred tieneMinimo(r: Nodo -> Nodo) {
	some min: Nodo | all n: Nodo | (min -> n) in r
}

pred tieneMaximo(r: Nodo -> Nodo) {
	some max: Nodo | all n: Nodo | (n -> max) in r
}

assert ordenParcialEsTotal {
	all r: Nodo -> Nodo | esOrdenParcial[r] implies esOrdenTotal[r]
}

assert ordenParcialTieneMinimo {
	all r: Nodo -> Nodo | esOrdenParcial[r] implies tieneMinimo[r]
}

assert ordenTotalTieneMinimo {
	all r: Nodo -> Nodo | esOrdenTotal[r] implies tieneMinimo[r]
}

assert minimoDistintoDeMaximo {
	all r: Nodo -> Nodo | 
		esOrdenTotal[r] and tieneMinimo [r] and tieneMaximo[r]
		implies
		all a, b: Nodo | (all c: Nodo | ((a -> c) in r and (c -> b) in r) implies a != b
}
