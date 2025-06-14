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

pred show[] { 
}

run show