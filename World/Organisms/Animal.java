package World.Organisms;

import World.Position;
import World.Position.Directions;
import java.util.Random;
import World.Canvas;

public abstract class Animal extends Organism{
    int speed;
    protected Animal(int strength,int initiative, Position position, OrganismName name, int speed, Canvas canvas) {
        super(strength, initiative, position, name, Type.ANIMAL, canvas);
        this.speed = speed;
        this.canvas.set(position, this);
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public void action() {
        age++;
        Directions direction = Directions.values()[new Random().nextInt(Directions.values().length)];
        switch(direction) {
            case UP : {
                move(0,-1);
                return;
            }
            case DOWN: {
                move(0,1);
                return;
            }
            case LEFT: {
                move(-1,0);
                return;
            }
            case RIGHT: {
                move(1,0);
                return;
            }
            default: {
                return;
            }
        }
    }
    public void reproduce() {
        if(age > 0 && new Random().nextInt(4) == 1) {
            Position position = canvas.nextPos(this.position);
            if(position != null)
                if(canvas.at(position) == null) {
                    addInstanceOf(position);
                    canvas.addInfoDown(name+" rozmnożył się");
                }
        }
    }
    public void move(int x, int y) {
        canvas.set(position, null);
        Position newPosition = position;
        x = x*speed;
        y = y*speed;
        if(position.x+x >= 0 && position.x+x < canvas.getX())
            newPosition.x = position.x + x;
        if(position.y+y >= 0 && position.y+y < canvas.getY())
            newPosition.y = position.y + y;

        if(canvas.at(newPosition) == null) {
            setPosition(newPosition);
            canvas.set(newPosition, this);
        }
        else
            canvas.at(newPosition).colision(this);
    }
    public void colision(Organism organism) {
        if(organism instanceof Animal) {
            if(name.toString().equals(organism.getName()))
                reproduce();
            else if (this.strength >= organism.getStrenth())
                replace(this,organism);
            else
                replace(organism,this);
        }
        else
            replace(this,organism);
    }
}
