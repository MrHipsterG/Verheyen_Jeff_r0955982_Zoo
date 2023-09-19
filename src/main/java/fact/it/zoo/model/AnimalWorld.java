package fact.it.zoo.model;
// Jeff Verheyen r0955982
public class AnimalWorld {
    private String name;
    private String photo;
    private int numberOfAnimals;
    private Staff responsible;

    public AnimalWorld() {
    }

    public AnimalWorld(String name) {
        this.name = name;
    }

    public AnimalWorld(String name, int numberOfAnimals) {
        this.name = name;
        this.numberOfAnimals = numberOfAnimals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getNumberOfAnimals() {
        return numberOfAnimals;
    }

    public void setNumberOfAnimals(int numberOfAnimals) {
        this.numberOfAnimals = numberOfAnimals;
    }

    public Staff getResponsible() {
        return responsible;
    }

    public void setResponsible(Staff responsible) {
        this.responsible = responsible;
    }
}
