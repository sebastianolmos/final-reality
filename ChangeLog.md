ChangeLog
=========
Version 4.0
-----------
- (RC.2) Homework 1 Submission
- (B.3) Added PDF summary
- (B.2) Added the UML diagram of the project
- (B.1) Code commentated according Javadoc

Version 3.0
-----------
- (RC.2) Partial submission 2 : Test Coverage
- (B.307) 100% branch coverage reached
- (B.306) Added a test class to each type of weapon to check his construction
- (B.305) Implements the setup and construction base tests to all weapons in ``AbstractWeaponTest``
- (B.304) Added a test class to each type of character to check his construction and ``waitTurn`` method
- (B.303) Implements the ``waitTurnTest`` to enemies in ``EnemyTest`` and playable characters in ``AbstractPlayerTest``
- (B.302) Implements the setup and construction base tests to all character in ``AbstractCharacterTest``
- (B.301) Fixed and added tests classes
- (B.205) Implements each weapon type 
- (B.204) Added classes to each weapon type (axe, bow, knife, staff, sword)
- (B.203) Added ``AbstractWeapon`` that implements ``IWeapon``
- (B.202) Added ``IWeapon`` to weapons implementation
- (B.201) Removed ``Weapon`` to specialize according each class
- (B.106) Implements each character type according if they can use mana
- (B.105) Added classes to each character type (blackmage, engineer, knight, thief, whitemage)
- (B.104) Added ``AbstractMage`` to mana users playable characters
- (B.103) Added ``AbstractPlayer`` to common playable characters
- (B.102) Added ``IPlayer`` to playable characters implementation
- (B.101) Removed ``PlayerCharacter`` to specialize according each class

Version 2.0
-----------
- (RC.2) Partial submission 1 : SOLID patched
- (B.5) AbstractCharacter: Modified ``waitTurn`` with the new method that gives the weight (Single-responsabilty violation solved)
- (B.4) ICharacter: Added ``getWeaponWeight()`` and implemented in ``PlayerCharacter`` and ``Enemy``
- (B.3) PlayerCharacter: implemented ``equip(weapon)`` and ``getEquippedWeapon()``
- (B.2) Removed ``getEquippedWeapon()`` from ``ICharacter`` and ``AbstractCharacter`` (Liskov principle violation solved)
- (B.1) Removed ``equip(weapon)`` from ``ICharacter`` and ``AbstractCharacter`` (Liskov principle violation solved)

Version 1.0
-----------
- (RC.1) Implemented missing tests
- (B.5) Updated License
- (B.4) Implementation and testing of Enemy class (ensured 100% branch coverage)
- (B.3) Created .gitignore
- (B.2) Implementation of most base elements of the model
- (B.1) Created project

A note on the version naming
----------------------------
- B.n: Version ``x.y`` _beta x_, alternative to ``x.y-b.n``.
  For example: ``v1.0-b.3``.
- RC.n: Release candidate x, alternative to ``x.y-rc.n``.
  For example: ``v1.0-rc.2``.
