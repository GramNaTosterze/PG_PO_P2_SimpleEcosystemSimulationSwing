package World.Organisms.Plants;

import World.Canvas;
import World.Position;
import World.Organisms.Animal;
import World.Organisms.Organism;
import World.Organisms.Plant;
import World.Organisms.Animals.CyberSheep;

public class SosnowskysHogweed extends Plant{
    public SosnowskysHogweed(Position position, Canvas canvas) {
        super(0, position, OrganismName.SOSNOWSKYS_HOGWEED, canvas);
    }
    @Override
    public void addInstanceOf(Position pos) {
        canvas.add(pos, new SosnowskysHogweed(pos, canvas));;
    }
    @Override
    public void action() {
        int ni = position.x-1,mi = position.x+2;
        int nj = position.y-1,mj = position.y+2;
        if(position.x <= 0)
            ni = 0;
        if(position.x+2 >= canvas.getX())
            mi = canvas.getX();
        if(position.y <= 0)
            nj = 0;
        if(position.y+2 >= canvas.getY())
            mj = canvas.getY();
        for(int i = ni; i < mi; i++)
            for(int j = nj; j < mj; j++) {
                Position pos = new Position(i,j);
                if(!position.equals(pos) && canvas.at(pos) != null && canvas.at(pos) instanceof Animal) {
                    replace(this,canvas.at(pos));
                    canvas.set(pos, null);
                }
            }
        spread();
        age++;
    }
    @Override
    public void colision(Organism organism) {
        if(organism instanceof CyberSheep) {
            organism.colision(this);
            return;
        }
        canvas.addInfoRight(organism.getName()+" padl ofiara barszczu\n");
        canvas.orgArr().remove(organism);
    }
}
