package World.Organisms.Plants;

import World.Canvas;
import World.Position;
import World.Organisms.Organism;
import World.Organisms.Plant;

public class Guarana extends Plant{
    public Guarana(Position position, Canvas canvas) {
        super(0, position, OrganismName.GUARANA, canvas);
    }
    public void addInstanceOf(Position pos) {
        canvas.add(pos, new Guarana(pos, canvas));
    }
    public void colision(Organism organism) {
        organism.setStrength(organism.getStrenth()+3);
        super.colision(organism);
    }
}
