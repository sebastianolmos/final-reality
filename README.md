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

-------
For the fifth partial submission was implement a basic first view of the controller, adding its tests and the javadoc.

To execute the project, run the test package with coverage.