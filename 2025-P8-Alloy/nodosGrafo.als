sig Nodo { } -- signatura para nodos de grafos
sig Grafo {
    nodos: set Nodo,
    arcos: nodos -> nodos -- arco es una relación ternaria 
}

pred dag(g: Grafo) { -- g es un parámetro, los arcos de g
    all n : g.nodos | n !in n.^(g.arcos)
  // no existe ningun camino dirigido que regrese al mismo nodo n
}

pred completo(g: Grafo) {
	all n1, n2: g.nodos | n1 != n2 implies (n1 -> n2) in g.arcos
}

pred conexo(g: Grafo) {
	all n, m: g.nodos | (n->m) in g.arcos and (m->n) in g.arcos
}

pred grafoEsArbol(g: Grafo) {
	all n, m: g.nodos | (n->m) in g.arcos and not((m->n) in g.arcos)
}

pred show () {}

run dag for 4 but exactly 4 Nodo, 1 Grafo

run completo for 4 but exactly 4 Nodo, 1 Grafo

run conexo for 4 but exactly 4 Nodo, 1 Grafo

run grafoEsArbol for 4 but exactly 4 Nodo, 1 Grafo

run show for 4 but exactly 4 Nodo, 1 Grafo
