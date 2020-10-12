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
---

Entrega Parcial 2
-------

Se crearon clases para los tipos de personajes y de armas, para su posterior especializacion y cumplimiento del principio de unica responsabilidad. Para esta implentacion se crearon las clases abstractas e interfaces necesarias.

Se escribieron de nuevo los test para cada clase, ocupando herencia, de modo que se alcanzo un ``Coverage`` del 100% para las ``lines`` y ``branches``.
Se explicara en más detalle en la entrega de la Tarea 1.
