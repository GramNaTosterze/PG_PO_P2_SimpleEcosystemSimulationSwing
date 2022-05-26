package World.Organisms.Animals;

import World.Canvas;
import World.Position;
import World.Organisms.Animal;
import World.Organisms.Organism;

public class Turtle extends Animal {

    public Turtle(Position position, Canvas canvas) {
        super(2, 1, position, OrganismName.TURTLE, 1, canvas);
        
    }
    @Override
    public void addInstanceOf(Position pos) {
        canvas.add(pos, new Turtle(pos, canvas));        
    }
    @Override
    public void colision(Organism organism) {
        if(organism.getStrenth() < 5) {
            canvas.set(organism.getPosition(), organism);
            canvas.addInfoRight(getName()+ " obronił się przed atakiem stworzenia "+organism.getName()+"\n");
        }
        else
            super.colision(organism);
    }
    
}
