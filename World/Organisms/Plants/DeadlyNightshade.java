package World.Organisms.Plants;

import World.Canvas;
import World.Position;
import World.Organisms.Organism;
import World.Organisms.Plant;

public class DeadlyNightshade extends Plant{
    public DeadlyNightshade(Position position, Canvas canvas) {
        super(99,position,OrganismName.DEADLY_NIGHTSHADE, canvas);
    }
    public void addInstanceOf(Position pos) {
        canvas.add(pos, new DeadlyNightshade(pos, canvas));
    }
    public void colision(Organism organism) {
        canvas.addInfoRight(getName()+" zabiły stworzenie "+organism.getName()+"\n");
        canvas.orgArr().remove(organism);
    }
}
