package genshinimpact.implementations;

import genshinimpact.interfaces.CharacterActions;

public class PyroCharacter implements CharacterActions {
    private String name;
    private int level;
    private int power;

    // Constructor
    public PyroCharacter(String name, int level, int power) {
        this.name = name;
        this.level = level;
        this.power = power;
    }

    @Override
    public void attack() {
        System.out.println(name + " performs a blazing sword strike!");
    }

    @Override
    public void useElementalSkill() {
        System.out.println(name + " casts 'Flame Vortex' - burning nearby enemies!");
    }

    @Override
    public void useElementalBurst() {
        System.out.println(name + " unleashes 'Inferno Explosion' - massive Pyro damage!");
    }

    @Override
    public void displayStats() {
        System.out.println("Pyro Character: " + name);
        System.out.println("Level: " + level + " | Power: " + power);
    }
}
