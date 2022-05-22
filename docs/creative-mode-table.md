# Requirement 4

**Title**:
_Forbidden Magic_

**Description**:

###### RUINS:
- A structure on the original game map with high difficulty walls to jump over.
- 2 Guard Koopas patrol the doorway.
- There are 2 ways in – jump over the walls (hard) or kill the guards.
- Inside is a new item, a necromancy weapon (N).

###### NECROMANCY WEAPON:
- This weapon can bring a dead enemy back to life to fight for the player
- If the necromancy weapon is in the players inventory, the corpses of fallen enemies will remain on the map for 5 turns, during which time they can be turned into a Zombie
- Zombie will seek out and attack other enemies on the map. There is only one type of reanimated enemy (zombie). Zombies have a 'z' icon and deal 10 damage with a 50% hit rate. Zombies will not try and attack domant koopas. 
- Reanimated enemies are active for 20 turns, after which their body deteriorates, and they die forever (removed from the map).
- Weapon starts off with 10 damage and 30% hit rate. Every time something is killed with the weapon, the damage increased by 5 and the hitrate increases by 5%


**Explanation why it adheres to SOLID principles** (WHY):

- This will show the open-closed principle because it will extend functionality without needing to modify existing classes
- This shows the dependency inversion principle because although we are creating lower-level modules, they will remain abstracted from other high-level modules. For example, the new enemy type, Zombie, extends the Enemy abstract class, which extends the Actor abstract class. Because this class will not violate the Liskov substitution principle, high-level modules will be able to interact with the Actor class to interact with Zombies – so they will not depend on the lower-level modules. The NecromancyWeapon also does this by extanding the WeaponItem class
- Please note that further explanation of adherence to SOLID principles is available in our design rationale

| Requirements                                                                                                            | Features (HOW) / Your Approach / Answer                                                                                                                               |
| ----------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Must use at least two (2) classes from the engine package                                                               | _We use 3 classes from the engine pakcage: NecromancyWeapon extends WeaponItem, Zombies extend Actor, and ReanimateAction extends Action._ |
| Must use/re-use at least one(1) existing feature (either from assignment 2 and/or fixed requirements from assignment 3) | _RuinWall extends Wall which extends HigherGround, a class implemented for REQ2 of assignment 2. The Enemy abstraction created for REQ3 of assignment 2 is used, because the Zombie class extends this. This REQ also uses the Koopa created in assignment 2 REQ3 by placing Guard Koopas at the entrance to the Ruins._                                                                                                                                                                      |
| Must use existing or create new abstractions (e.g., abstract or interface, apart from the engine code)                  | _This REQ uses existing abstractions such as Enemy, WeaponItem, and HigherGround._                                                                                                                                                                      |
| Must use existing or create new capabilities                                                                            | _This REQ creates a new capability, CAN_REANIMATE. This capability is given to the NecromanyWeapon to allow it to bring fallen enemies back to life. It also creates a new capability, KILLER, which is used in the process of increasing the NecromancyWeapon's damage output._                                                                                                                                                                      |

---

# Requirement 5

**Title**:
The Dangers of Greed

**Description**:

###### SHOVEL
- This is an item that you can buy from Toad
- It allows you to dig at ‘diggable’ grounds 

###### SPORES
- Spores are a type of ground
- It can be walked on like normal dirt 
- By stepping on Spores ground, you step into the cloud of spores. For each turn that you end in the spores, you take 5 points of damage

###### SOFT GROUND
- Soft ground is a type of ground
- It can be walked on like normal dirt
- When an actor is on soft ground - and has a shovel in their inventory - they have a Dig Action available to them. 

###### DIG ACTION
- The player must have a shovel in order to be able to take the dig action while on soft ground. 
- When you dig, you have a 75% chance of digging up a $50 Coin (which sits on the ground and can be picked up), and a 25% chance of digging up a ‘clump of spores’, which immediately covers a 3*3 area centred on the place you dug, and lasts for 10 turns

**Explanation why it adheres to SOLID principles** (WHY):

- This will show the open-closed principle because it will extend functionality without needing to modify existing classes
- This will show the single responsibility principle because, for example, the SporeGround class will manage the effects of spores, and the DigAction will merely create the instance of the SporeGround. So each class has its own responsibility, and does not need to manage any of the aspects of a different class.
- In a similar manner to the previous REQ, this will also maintain the dependency inversion principle through its implementation of Shovel, DigAction, SoftGround, and SporeGround – all of which are abstracted by parent abstract classes.
- Please note that further explanation of adherence to SOLID principles is available in our design rationale

| Requirements                                                                                                            | Features (HOW) / Your Approach / Answer                                                                                                                               |
| ----------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Must use at least two (2) classes from the engine package                                                               | _We use three classes from the engine package: Item, Ground, and Action. Shovel extends Item, Spores and SoftGround extend Ground, and DigAction extends Action._ |
| Must use/re-use at least one(1) existing feature (either from assignment 2 and/or fixed requirements from assignment 3) | _This REQ uses REQ5(Trading) from assignment 2. The Shovel item can be bought from Toad._                                                                                                                                                                      |
| Must use existing or create new abstractions (e.g., abstract or interface, apart from the engine code)                  | _This REQ uses the existing abstractions from the engine code of Item, Ground, and Action. All of the classes that this REQ implements extends these abstractions. This also creates a new abstraction, Buyable (interface), to ensure that only Buyable Items can be bought with a BuyAction. This is applied to all Items deemed 'buyable' in assignment 2, as well as to the newly implemented Shovel._                                                                                                                                                                      |
| Must use existing or create new capabilities                                                                            | _This REQ introduces the new capability CAN_DIG. This is a capability that is given to the Shovel. This capability is checked by the SoftGround class to determine whether or not to add a DigAction to the ActionList of the Player when they are on a SoftGround._                                                                                                                                                                      |
