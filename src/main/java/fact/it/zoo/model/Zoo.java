package fact.it.zoo.model;

import java.util.ArrayList;

// Jeff Verheyen r0955982
public class Zoo {
    private String name;
    private int numberVisitors;
    private ArrayList<AnimalWorld> animalWorlds = new ArrayList<>();
    public Zoo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberVisitors() {
        return numberVisitors;
    }

    public ArrayList<AnimalWorld> getAnimalWorlds() {
        return animalWorlds;
    }
    public int getNumberOfAnimalWorlds(){
        return animalWorlds.size();
    }
    public void addAnimalWorld(AnimalWorld animalWorld) {
        animalWorlds.add(animalWorld);
    }
    public AnimalWorld searchAnimalWorldByName(String name) {
        for (AnimalWorld aWorld: animalWorlds) {
            if (aWorld.getName().equals(name)) {
                return aWorld;
            }
        }
        return null;
    }
    public void registerVisitor(Visitor visitor) {
        this.numberVisitors += 1;
        visitor.setPersonalCode(this.name.substring(0,2) + this.numberVisitors);
    }
}
