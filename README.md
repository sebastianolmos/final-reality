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

Entrega Parcial 1
-------

Se elimino de la interfaz ``ICharacter`` y la clase abstracta ``AbstractCharacter `` los metodos ``equip(Weapon)`` y ``getEquippedWeapon()``, para implementarlas en la clase ``PlayerCharacter``, ya que estas no cumplian con el principio de Liskov al no tener un uso en la clase ``Enemy``.

Se creo el metodo ``getWeaponWeight()`` en la interfaz ICharacter y las implementa las clases ``PlayerCharacter`` y ``Enemy`` para que el metodo ``waitTurn()`` no rompa el principio de Single- responsabilty.
