open util/ordering[House] as Ord

enum Color {Red, White, Blue, Green, Yellow}

enum Pet {Birds, Cats, Dogs, Fish, Horses}

enum Cigar {Blend, BlueMaster, Dunhill, PallMall, Prince}

enum Beverage {Beer, Coffee, Milk, Tea, Water}

sig House {
        color: disj Color
}

abstract sig Owner {
        house: disj House,
        pet: disj Pet,
        cigar: disj Cigar,
        beverage: disj Beverage
}

one sig Brit extends Owner {}
one sig Swede extends Owner {}
one sig Dane extends Owner {}
one sig Norwegian extends Owner {}
one sig German extends Owner {}

pred atTheCenter(h: House) {
	h = Ord.middle
}

pred firstHouse(h: House) {
	h = Ord.first
}

pred neighbors[o: Owner, o2: Owner] {
	let h = o.house, h2 = o2.house | h2 = Ord.next[h] or h2 = Ord.prev[h]
}

pred livesNextToTheBlueHouse(o: Owner) {
	some h: House | (o.house = Ord.prev[h] or o.house = Ord.next[h]) and h.color = Blue
}

fact constraints {
// 1.  The Englishman lives in the red house.
	one b: Brit | b.house.color = Red
// 2.  The Swede keeps dogs.
	one s: Swede | s.pet = Dogs
// 3.  The Dane drinks tea.
	one d: Dane | d.beverage = Tea

// ? 4.  The green house is just to the left of the white one.
	all h: House - last | h.color = Green implies next[h].color = White

// 5.  The owner of the green house drinks coffee.
	one o: Owner | o.house.color = Green implies o.beverage = Coffee
// 6.  The Pall Mall smoker keeps birds.
	one o1: Owner | o1.cigar = PallMall implies o.pet = Birds
// 7.  The owner of the yellow house smokes Dunhills.
	one o2: Owner | o2.house.color = Yellow implies o2.cigar = Dunhill
// ? 8.  The man in the center house drinks milk.
	one o3: Owner | atTheCenter[o3.house] implies o3.beverage = Milk
// ? 9.  The Norwegian lives in the first house.
	one n: Norwegian | firstHouse[n.house]
// ? 10. The Blend smoker has a neighbor who keeps cats.
	one o4, oNeighbor: Owner | o4.cigar = Blend and neighbors[o4, oNeighbor] and oNeighbor.pet = Cats
// 11. The man who smokes Blue Masters drinks beer.
	one o5: Owner | o5.cigar = BlueMaster implies o5.beverage = Beer
// 12. The man who keeps horses lives next to the Dunhill smoker.
	one o6, o7: Owner | (o6 != o7 and o6.pet = Horses) implies o7 in neighbors(o6) and o7.cigar = Dunhill
// 13. The German smokes Prince.
	one g: German | g.cigar = Prince
// 14. The Norwegian lives next to the blue house.
	one n1: Norwegian | livesNextToTheBlueHouse[n1]
// 15. The Blend smoker has a neighbor who drinks water.
	one o8, o9: Owner | (o8 != o9 and o8.cigar = Blend) implies o9 in neighbors(o8) and o9.beverage = Water

	#House = 5
}

run {} for 5

// https://udel.edu/~os/riddle.html
