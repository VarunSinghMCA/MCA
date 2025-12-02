# Genshin Impact - Java Interface & Package Implementation

This Java project demonstrates the use of **interfaces**, **packages**, **encapsulation**, **constructors**, and **polymorphism** through a **Genshin Impact**-themed simulation.

It models different types of characters (`Pyro`, `Hydro`, `Electro`) — each implementing common behaviors defined by an interface. The project is neatly organized into packages and tested using a main driver class.

## Project Structure

genshinimpact/
│
├── interfaces/
│   └── CharacterActions.java
│
├── implementations/
│   ├── PyroCharacter.java
│   ├── HydroCharacter.java
│   └── ElectroCharacter.java
│
└── main/
    └── GenshinTest.java

## Features

- *Interface Usage* — Defines common actions (`attack()`, `useElementalSkill()`, `useElementalBurst()`, `displayStats()`).
- *Encapsulation* — Character data (name, level, stats) kept private and initialized via constructors.
- *Polymorphism* — Interface references invoke different behaviors at runtime.
- *Package Organization* — Clear separation between interfaces, implementations, and main driver class.
- *Real-World Context* — Inspired by Genshin Impact's elemental combat system.

## Concepts Demonstrated

| Concept                     | Description                                                  |
| --------------------------- | ------------------------------------------------------------ |
| **Interface**         | Common structure for character actions.                      |
| **Encapsulation**     | Private attributes with constructors for initialization.     |
| **Abstraction**       | Interface hides implementation details.                      |
| **Polymorphism**      | One interface reference, multiple implementations.           |
| **Packages**          | Organized and modular project structure.                     |
| **Method Overriding** | Each class provides its unique version of interface methods. |

---

## Implementation Overview

### Interface (`CharacterActions`)

Defines four essential methods that all Genshin characters must implement:

```java
void attack();
void useElementalSkill();
void useElementalBurst();
void displayStats();
```

### PyroCharacter

Represents fire-type characters (e.g., Diluc).
Implements fiery attacks and explosions.

### HydroCharacter

Represents water-type characters (e.g., Barbara).
Focuses on healing and water-based defense.

### ElectroCharacter

Represents lightning-type characters (e.g., Lisa).
Uses electric attacks and speed boosts.

---

## Execution

### **1. Compile the files**

Navigate to the directory containing the `genshinimpact` folder and run:

```bash
javac genshinimpact/interfaces/*.java genshinimpact/implementations/*.java genshinimpact/main/GenshinTest.java
```

### **2. Run the main class**

```bash
java genshinimpact.main.GenshinTest
```

---

## Sample Output

```
===== Genshin Impact Character Simulation =====

--- Character Demonstrations ---
Pyro Character: Diluc
Level: 80 | Power: 2500
Actions:
Diluc performs a blazing sword strike!
Diluc casts 'Flame Vortex' — burning nearby enemies!
Diluc unleashes 'Inferno Explosion' — massive Pyro damage!
--------------------------------------

Hydro Character: Barbara
Level: 70 | Healing Power: 1800
Actions:
Barbara attacks with a water blade!
Barbara summons 'Aqua Shield' for protection!
Barbara releases 'Ocean Embrace' — heals and damages enemies!
--------------------------------------

Electro Character: Lisa
Level: 75 | Energy: 2000
Actions:
Lisa delivers a shocking spear thrust!
Lisa activates 'Thunder Pulse' — boosts attack speed!
Lisa channels 'Lightning Storm' — electro damage to all foes!
--------------------------------------

===== Simulation Complete =====
```

---

## Key Learnings

* How to **design and implement interfaces** in Java.
* How to **structure a multi-package project** effectively.
* How **runtime polymorphism** makes code flexible and extensible.
* How to represent **real-world systems (Genshin Impact)** in OOP.

---

## Future Enhancements

* Add **Weapon** and **Artifact** packages.
* Implement **Inheritance** with a `BaseCharacter` class.
* Add **user input** to select and simulate characters dynamically.
* Include **combat simulation** with damage and energy systems.

---

## Author

**Varun Singh**


[Compile]
>>>> javac genshinimpact/interfaces/*.java genshinimpact/implementations/*.java genshinimpact/main/GenshinTest.java
[or: Compile] go to the parent folder and write this
>>>> javac *.java
[Run]
>>>> java genshinimpact.main.GenshinTest
