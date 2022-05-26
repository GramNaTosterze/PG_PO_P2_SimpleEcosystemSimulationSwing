package World.Organisms.Plants;

import World.Canvas;
import World.Position;
import World.Organisms.Plant;

public class Grass extends Plant{
    public Grass(Position position, Canvas canvas) {
        super(0, position, OrganismName.GRASS, canvas);
    }
    public void addInstanceOf(Position pos) {
        canvas.add(pos, new Grass(pos, canvas));
    }
}
