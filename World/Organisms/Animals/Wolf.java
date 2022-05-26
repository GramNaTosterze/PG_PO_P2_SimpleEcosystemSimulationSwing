package World.Organisms.Animals;

import World.Canvas;
import World.Position;
import World.Organisms.Animal;

public class Wolf extends Animal{

    public Wolf(Position position, Canvas canvas) {
        super(9, 5, position, OrganismName.WOLF, 1, canvas);
    }
    @Override
    public void addInstanceOf(Position pos) {
        canvas.add(pos, new Wolf(pos, canvas));
    }
    
}
