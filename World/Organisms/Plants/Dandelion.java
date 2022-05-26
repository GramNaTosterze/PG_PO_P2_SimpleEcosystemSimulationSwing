package World.Organisms.Plants;

import World.Canvas;
import World.Position;
import World.Organisms.Plant;

public class Dandelion extends Plant{
    public Dandelion(Position position, Canvas canvas) {
        super(0,position,OrganismName.DANDELION,canvas);
    }
    @Override
    public void addInstanceOf(Position pos) {
        canvas.add(pos, new Dandelion(pos, canvas));
    }
    @Override
    public void action() {
        for(int i = 0; i < 3; i++)
            spread();
        age++;
    }
}
