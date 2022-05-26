package World.Organisms.Animals;

import World.Canvas;
import World.Position;
import World.Organisms.Organism;

public class CyberSheep extends Sheep {
    public CyberSheep(Position position, Canvas canvas) {
        super(position, canvas);
        this.strength = 11;
    }
    @Override
    public void addInstanceOf(Position pos) {
        canvas.add(pos, new CyberSheep(pos, canvas));
    }
    @Override
    public void action() {
        super.action();
    }
    @Override
    public void colision(Organism organism) {
        super.colision(organism);
    }

    
}
