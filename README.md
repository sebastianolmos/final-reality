Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.

---

First Submission
-------
The goal of the first submission was refactor the project code committed and fixed the tests. In order to make this, it was removed the methods 
relative to weapon handling from the abstract class ``AbstractCharacter`` and his implemented interface. Then was added a method ``getWeaponWeight()`` 
for both enemy and character, so that the method ``waitTurn`` can work properly without breaking the single responsibility principle.

For the next I supposed that in the future when the project need to implement the battle mechanics, every type of character and weapon will have
 several methods according each type, so in order to maintain the single responsibility principle the playable characters and weapons were specialized 
 according to their type. To achieve this, it were implemented some abstract classes to the common code, and it was added the mana attribute to the mage 
 character (with value zero), to prepare their eventually specialization.

At least for the tests, in the same way of specialization for the classes, the tests were also separated according their type, implementing a base test 
of the abstract class for each one. The waitTurn tests were replaced to one without lists because it was using only the elements of 0 indexes, so for each 
character type was implemented a waitTurn test, and a construction test, while for the weapon was implemented a construction test. It was reached a 
100% branch and lines coverage.

---

Second Submission
-------
For the third partial submission the goal was implements the equip functionality to the playable characters. First according to the correction in the forum 
I will assume that the ``Thief`` character can equip Sword, Knife and Bow weapons (No Staff). Then I implement this using double dispatch.

For the attack functionality it was implemented assuming that any character can attack any character, because is more extensible to add new functionalities 
(confused state, etc) so it was only needed modify the abstract classes.

At least it was implemented the new test to check the equipment to each weapon using inheritance, and tests to check the attacks in the ``BattleTest`` class
where only are tested a few characters, because the attack functionality was implemented in the abstract class, non in each type of character.

For the fifth partial submission the goal was implements a basic controller that will interact with the user and play the game. Then was created 
the ``GameController`` class and was added several methods to satisfy the requirements. First was implemented methods to create all the different types 
of characters and weapons, then add them to arrays in the controller. For the getters of the characters' information was implemented so that the user have to pass
and index. For the inventory, was supposed that first the user have to add a weapon to the inventory and then equip on the character from the inventory passing
and index. Also, was supposed that if a character already has a weapon equipped, the equip method swap the weapons, removing from the inventory the old weapon 
and adding the weapon that was used by the character. In the case of the attack methods, for the moment receive character's objects, its need more indication to implements attack 
methods in more cases, so the user doesn't need to know the objects.

The controller needs to know when start a turn so was implemented the ``CharacterTurnHandler`` that is used to implement an observer on the character to notify 
when a character waits for a turn, and the turns queue isn't empty. Also notify when a character was added to the queue and is the only one. The was implemented another
 handler for the enemy and the playable character to notify when each one is deafeted, so the controller needs update the remaining character counts and check if the user win 
 or lose.
 
At least for the tests, was implemented for the management of the playable characters, enemies and inventory, the for the turns was implemented tests that simulates 
a simple run of turns that test the correct behaviour of the methods related. For the handlers, and listeners methods was supposed that is not required methods that 
only test its methods because only make sense use these methods with both the subscriber and publisher implemented, so these methods were tested with the turns.
 It was reached a 100% branch and lines coverage.


To execute the project, run the test package with coverage.