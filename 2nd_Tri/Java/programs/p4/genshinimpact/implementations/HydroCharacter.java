package genshinimpact.implementations;

import genshinimpact.interfaces.CharacterActions;

public class HydroCharacter implements CharacterActions {
    private String name;
    private int level;
    private int healingPower;

    public HydroCharacter(String name, int level, int healingPower) {
        this.name = name;
        this.level = level;
        this.healingPower = healingPower;
    }

    @Override
    public void attack() {
        System.out.println(name + " attacks with a water blade!");
    }

    @Override
    public void useElementalSkill() {
        System.out.println(name + " summons 'Aqua Shield' for protection!");
    }

    @Override
    public void useElementalBurst() {
        System.out.println(name + " releases 'Ocean Embrace' - heals and damages enemies!");
    }

    @Override
    public void displayStats() {
        System.out.println("Hydro Character: " + name);
        System.out.println("Level: " + level + " | Healing Power: " + healingPower);
    }
}
