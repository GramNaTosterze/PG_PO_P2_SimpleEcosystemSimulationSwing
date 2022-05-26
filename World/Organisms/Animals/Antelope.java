package World.Organisms.Animals;

import java.util.Random;

import World.Canvas;
import World.Position;
import World.Organisms.Animal;
import World.Organisms.Organism;

public class Antelope extends Animal {
    
    public Antelope(Position position, Canvas canvas) {
        super(4, 4, position, OrganismName.ANTELOPE, 2, canvas);
    }

    @Override
    public void addInstanceOf(Position pos) {
        canvas.add(pos, new Antelope(pos, canvas));        
    }
    @Override
    public void colision(Organism organism) {
        if(organism instanceof Animal) {
            if(new Random().nextInt(2) == 1) {
                canvas.set(position,null);
                canvas.set(canvas.nextPos(position),this);
                canvas.addInfoRight(getName()+ "ucieka na inne pole\n");
            }
            else
                super.colision(organism);
        }
        else
            replace(this,organism);
    }
    
}
