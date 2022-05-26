package World.Organisms.Animals;

import World.Canvas;
import World.Position;
import World.Organisms.Animal;

public class Fox extends Animal{

    public Fox(Position position, Canvas canvas) {
        super(3, 7, position, OrganismName.FOX, 1, canvas);
    }
    @Override
    public void addInstanceOf(Position pos) {
        canvas.add(pos, new Fox(pos, canvas));        
    }
    @Override
    public void move(int x, int y) {
        canvas.set(position, null);
        Position newPos = position;
        if(position.x+x >= 0 && position.x+x < canvas.getX())
            newPos.x = position.x + x;
        if(position.y+y >= 0 && position.y+y < canvas.getY())
            newPos.y = position.y + y;
        if(canvas.at(newPos) == null) {
            setPosition(newPos);
            canvas.set(newPos, this);
        }
        else if(canvas.at(newPos).getStrenth() > strength && canvas.at(newPos).getType() == Type.ANIMAL) {
            canvas.set(position, this);
            canvas.addInfoRight(getName()+" postanowił trzymać się z daleka od pola z drapieżnikiem\n");
        }
        else
            canvas.at(newPos).colision(this);
    }
    
}
