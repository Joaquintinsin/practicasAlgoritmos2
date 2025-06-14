# ~ Ilustración

import random

real_coin = 1
fake_coin = 0

#Shuffles the coin so that we stimulate the environment that we don't know #the fake coin
def randomize_coins(n):  
    """A shuffled list of (n-1) real coins and 1 fake coin."""
    global coins
    coins= [real_coin] * (n - 1) + [fake_coin] * (1)# Generate an array of coins
    random.shuffle(coins)
    print(coins)
    return coins
def testing_fake_one(n):
    for i in range(1,n):
        if coins[0]==coins[i]:
            pass
        elif coins[0]<coins[i]:
            print(1,"th coin is the fake one")
        else:
            print(i, "th coin is the fake one")
n=int(input("Enter the number of coins:"))
randomize_coins(n)
testing_fake_one(n)
