package World.Organisms.Animals;

import World.Canvas;
import World.Position;
import World.Organisms.Animal;

public class Sheep extends Animal{
    public Sheep(Position position, Canvas canvas) {
        super(4,4,position,OrganismName.SHEEP,1,canvas);
    }
    @Override
    public void addInstanceOf(Position pos) {
        canvas.add(pos, new Sheep(pos, canvas));        
    }
}
