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

Third Submission
-------
For the last submission the goal was implement the game phases, and the gui to let the user play the game.
For the first goal it was implemented phases classes using the state pattern, then add classes to the controller that move in these 
phases and depending on the phase where is the game, let the user do some actions. It was refactor the method that simulate ehe enemy turn 
using a schedule executor instead of sleep the thread. Then was fixed some test classes that use the new phase implementation and 
create the final tests to the remaining methods added to the controller.

For the last goal it was implemented the gui with several classes. First was implemented the classes that generate and manage the sprites
which represents the figure of the characters on the battlefield and the menu with the data for each playable character and the buttons that let 
the user chose the character action in its turn.
Then was implemented the classes that generate and manage the inventory menu, and the menu that let select an enemy to attack. Then was 
implemented other scenes of the application like the start menu, the battle result, and the battle maker that let user to add the 
characters and weapons with desired attributes.

Finally, was implemented sounds for each scene.

To execute the test, run the test package with coverage not including the gui package.

GUI instructions
-------
The application begins in the start scene where the user can:
- Press the ``Make your Battle`` button to go to the scene that let the user create the characters
- Press the ``Default Battle`` button to go to the game scene with a default 5 vs 5 battle
- Press the ``Boss Battle`` button to go to the game scene with a 3 vs 1 boss battle

In the ``Make your Game`` scene the user can:
- Fill the party character fields and then press one of the buttons bellow the fields to add the respective class
- Fill the enemy character fields and then press the ``Add Enemy`` button to add an enemy.
- Fill the weapon fields and then press one of the buttons bellow the fields to add the respective weapon type in the inventory
- Press the ``Back`` button to go to the start scene
- Press the ``Make and Play`` button to go to start the battle with the added characters and weapons.

It's important to consider that:
- To add a character or a weapon, all their respective fields must be filled
- Only the ``Name`` fields can accept characters other than numbers, otherwise the field will be clear
- The input text filled in the ``Name`` will be cut at length o 12, for the text to fit in the menu.
- The user can create a maximum of 5 playable characters, 5 enemies and 27 weapons.
- To start a battle the application needs at least 1 playable character, 1 enemy and 1 weapon.
- The battle will start with the playable characters without equipped weapons, the user must equip in their respective turn 
to defeat the enemies

In the ``Battle`` scene the user can:
- Manage the turn of each playable character when it's the character's turn
- Press the ``Attack`` button to show the target enemy menu
- Press the ``Inventory`` button to show the inventory menu
- In the ``Select an enemy to attack`` menu, the user can press the button of each remaining enemy to display in the top panel
its information and target the enemy, press the ``Back`` button to do other action, or press the ``Attack`` button to attack the targeted
 enemy, this button also ends the character turn.
- In the ``Inventory`` menu, the user can press the button of each weapon in the inventory to display in the ``Selected`` panel
its information and select the weapon, in the ``Equipped`` panel its shows the information of the current weapon that is equipped in the character.
Press the ``Back``button to do other action, or press the ``Equip`` to equip the selected weapon, Consider that the weapon will be equipped only if 
its compatible with the character class (see the weapon compatibility bellow).
- The battle last until all the playable characters die or until all the enemies die. Changing to the result scene.

In the ``Result`` scene its displayed the result of the battle, and the user can:
- Press the ``Back to Menu`` button to go to the start menu scene
- Press the ``Close Game`` button to close the application.

Enjoy the Game!

Weapons per character classes assumptions
-------

I will assume that the ``Thief`` character can equip Sword, Knife and Bow weapons (No Staff). 
So the weapons type that can equip each playable character are the next:

|            | Sword | Axe | Knife | Staff | Bow |
|:----------:|:-----:|:---:|:-----:|:-----:|:---:|
|   Knight   |  Yes  | Yes |  Yes  |   No  |  No |
|  Engineer  |   No  | Yes |   No  |   No  | Yes |
|    Thief   |  Yes  |  No |  Yes  |   No  | Yes |
| Black Mage |   No  |  No |  Yes  |  Yes  |  No |
| White Mage |   No  |  No |   No  |  Yes  |  No |

Sample video
-------
Click to see a sample video that show the application
[![Alt text for your video](https://img.youtube.com/vi/kwJvuvLzh-g/0.jpg)](https://youtu.be/kwJvuvLzh-g)

Asset credits
-------

- Music by JP Soundworks (https://www.youtube.com/c/JPSoundworks/)
- Playable characters sprites were created by Ocean's Dream (https://oceansdream.itch.io/)
- Victory Background Photo by Dawid Zawila on Unsplash           
                                 
