package World.Organisms;

import java.util.Random;

import World.Canvas;
import World.Position;

public abstract class Plant extends Organism{
    public Plant(int strength, Position position, OrganismName name, Canvas canvas) {
        super(strength, 0, position, name, Type.PLANT, canvas);
    }
    public void setPosition(Position position) { this.position = position; }
    public void action() {
        spread();
        age++;
    }
    protected void spread() {
        if(new Random().nextInt(30) == 1) {
            Position pos = canvas.nextPos(position);
            if(pos != null) {
                if(canvas.at(pos) == null)
                    addInstanceOf(pos);
            }
        }
    }
    public void colision(Organism organism) {
        canvas.set(position, organism);
        organism.setPosition(position);
        canvas.addInfoRight(organism.getName()+ "zjadł roślinę "+getName()+"\n");
        canvas.orgArr().remove(this);
    }
}
