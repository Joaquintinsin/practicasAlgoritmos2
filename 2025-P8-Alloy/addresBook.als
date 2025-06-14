module addressBook

sig Name{}
sig Address{}

sig AddressBook{
	names: set Name, 
	addr:names -> Address
}

fact  domAddrIsNames{
	all b:AddressBook | b.addr.Address = b.names
}

fact addrIsFuncional{
	all b: AddressBook | all n: b.names | one n.(b.addr)
}

pred addPersonToAddressBook(n: Name, a: Address, b: AddressBook, b': AddressBook) {
	n in b'.names and not(n in b.names)
		and
	(n->a) in b'.addr and not((n->a) in b.addr)
		and
	b.names in b'.names
		and
	b.addr in b'.addr
}

pred removePersonFromAddressBook(n: Name, a: Address, b: AddressBook, b': AddressBook) {
	n in b.names and not(n in b'.names)
		and
	(n->a) in b.addr and not((n->a) in b'.addr)
		and
	b'.names in b.names
		and
	b'.addr in b.addr
}

pred addAndRemovePersonKeepsTheSameAddressBook(n: Name, a: Address, b: AddressBook, b': AddressBook) {
	addPersonToAddressBook[n, a, b, b'] and removePersonFromAddressBook[n, a, b, b'] implies b = b'
}

pred show[] {}

// run addPersonToAddressBook for 2
run removePersonFromAddressBook for 2

run show
