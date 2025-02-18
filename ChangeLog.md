ChangeLog
=========

Version 8.0
-----------
- (RC.8) Homework 3 Submission
- (B.501) Added PDF summary
- (B.401) Added the UML diagram of the project
- (B.311) Implements some null object of gui elements to fix some test errors
- (B.310) Add methods to the controller that needs the gui
- (B.309) Implements a default 5vs5 battle, and a 3 v2 1 boss battle
- (B.308) Implements scenes for the start menu, the battle maker, the battle game, and the battle result
- (B.307) Implements a scene hierarchy with parent classes that manages scene with an audio and timers
- (B.306) Add a class that manage the enemy target menu in the battle
- (B.305) Add a class that manage the inventory menu in the battle
- (B.304) Add classes that manages the figure and portrait menu to each enemy character 
- (B.303) Add classes that manages the figure and portrait menu to each playable character type
- (B.302) Add a random background for the battle
- (B.301) Start with the gui implementation
- (B.203) Fix the timer in the method that simulate the turn of an enemy with a schedule executor, and the wait time value 
to the controller
- (B.202) Fix the test that simulates a basic battle
- (B.201) Add test classes to test the management of the phases from te controller
- (B.103) Add getters and setters to the controller that only works when the game is in the correct phase
- (B.102) Add methods to the controller that manage the phases
- (B.101) Add phases classes to the controller package

Version 7.0
-----------
- (RC.7) Homework 2 Submission
- (B.4) Added PDF summary
- (B.3) Added the UML diagram of the project
- (B.2) Updated README
- (B.1) Refactor the ``equals`` method of the playable characters and add some test

Version 6.0
-----------
- (RC.6) Partial submission 5 : Basic Controller implementation.
- (B.401) Add methods for check the winner condition in the controller.
- (B.301) Refactor the attack method in the player characters to implements in ``AbstractCharacters``
- (B.204) Add methods for turn management in the controller.
- (B.203) Add the listener subscribe methods in the controller.
- (B.202) Add the listener methods to characters classes.
- (B.201) Add ``IEventHandler`` interface, and the extended classes to implement the observer pattern.
- (B.105) Add method to make an attack interaction between characters.
- (B.105) Add getters for the selected character or character on turn
- (B.104) Add methods for the inventory management.
- (B.103) Add getters for the party and enemy characters receiving an index
- (B.102) Add methods to create characters and weapons in the controller
- (B.101) Add ``GameController`` class to control the interaction with the user

Version 5.0
-----------
- (RC.5) Partial submission 3 : DD, equip weapons and attack between characters.
- (B.304) 100% branch coverage reached
- (B.303) Add methods in ``BattleTest`` to check the successful (and not) attacks between a player and an enemy (with/without weapons and dead/alive)
- (B.302) Add ``BattleTest`` test class to check the attacks.
- (B.301) Fix equip weapons tests, Adding methods to test supported an unsupported weapons to each character with each type of weapon using inheritance.
- (B.203) Implements the attack between characters with ``receiveAttackOf()`` and ``attack()`` to the enemy and player characters
- (B.202) Add ``itsAlive()`` to ``AbstractCharacter`` to use in the attacks and equip weapons methods.
- (B.201) Add the attributes ``defense``, ``health`` to ``AbstractCharacter`` and ``damage`` to ``Enemy`` and its getters, setters methods.
- (B.103) Move ``equip()`` to each player characters that implements its equipOn method with Double Dispatch.
- (B.102) Add to ``AbstractWeapon`` and its interface methods to be equipped on each player character (equipOnBlackMage, equipOnKnight, etc)
- (B.101) Add ``setEquippedWeapon()`` to ``AbstractPlayer``

Version 4.0
-----------
- (RC.4) Homework 1 Submission
- (B.3) Added PDF summary
- (B.2) Added the UML diagram of the project
- (B.1) Code commentated according Javadoc

Version 3.0
-----------
- (RC.3) Partial submission 2 : Test Coverage
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
