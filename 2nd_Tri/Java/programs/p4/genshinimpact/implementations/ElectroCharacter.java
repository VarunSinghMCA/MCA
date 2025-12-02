package genshinimpact.implementations;

import genshinimpact.interfaces.CharacterActions;

public class ElectroCharacter implements CharacterActions {
    private String name;
    private int level;
    private int energy;

    public ElectroCharacter(String name, int level, int energy) {
        this.name = name;
        this.level = level;
        this.energy = energy;
    }

    @Override
    public void attack() {
        System.out.println(name + " delivers a shocking spear thrust!");
    }

    @Override
    public void useElementalSkill() {
        System.out.println(name + " activates 'Thunder Pulse' - boosts attack speed!");
    }

    @Override
    public void useElementalBurst() {
        System.out.println(name + " channels 'Lightning Storm' - electro damage to all foes!");
    }

    @Override
    public void displayStats() {
        System.out.println("Electro Character: " + name);
        System.out.println("Level: " + level + " | Energy: " + energy);
    }
}
