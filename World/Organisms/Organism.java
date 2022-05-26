package World.Organisms;
import World.Canvas;
import World.Position;
import java.awt.Color;

public abstract class Organism implements OrganismInterface{
    protected Type type;
    protected int strength;
    protected int initiative;
    protected int age;
    protected Position position;
    protected OrganismName name;
    protected Canvas canvas;
    Organism(int strength, int initiative, Position position, OrganismName name, Type type, Canvas canvas) {
        this.strength = strength;
        this.initiative = initiative;
        this.position = position;
        this.name = name;
        this.type = type;
        this.canvas = canvas;
    }
    public void scan(String[] statistics) {
        this.strength = Integer.parseInt(statistics[2]);
        this.initiative = Integer.parseInt(statistics[3]);
        this.age = Integer.parseInt(statistics[4]);
    }
    public void replace(Organism first, Organism second) {
        canvas.set(position, first);
        first.setPosition(position);
        canvas.addInfoRight(first.getName()+" zabija stworzenie "+second.getName()+"\n");
        canvas.orgArr().remove(second);
    }
    public void setStrength(int s) { this.strength = s; }
    public int getStrenth() { return strength; }
    public int getInitiative() { return initiative; }
    public int getAge() { return age; }
    public Position getPosition() { return position; }
    public String getName() { return name.toString(); }
    public Color getColor() { return name.col(); }
    public Type getType() { return type; }
    public void setPosition(Position position) { this.position = position; }
    public int compareTo(Organism other) {
        if(initiative > other.getInitiative())
            return -1;
        else if(initiative < other.getInitiative())
            return 1;
        else if(age > other.getAge())
            return -1;
        else if(age < other.getAge())
            return 1;
        else
            return 0;
       /* if(initiative == other.getInitiative() && age > other.getAge())
            return 1;
        if(age == other.getAge())
            return 0;
        else
            return -1; */
        
    }
    public String toString() {
        return name+" "+position.toString()+" "+Integer.toString(strength)+" "+Integer.toString(initiative)+" "+Integer.toString(age);
    }
}
