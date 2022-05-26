package World;

import java.util.Random;

import World.CanvasInterface.WorldType;
import World.Organisms.Animals.Antelope;
import World.Organisms.Animals.CyberSheep;
import World.Organisms.Animals.Fox;
import World.Organisms.Animals.Human;
import World.Organisms.Animals.Sheep;
import World.Organisms.Animals.Turtle;
import World.Organisms.Animals.Wolf;
import World.Organisms.OrganismInterface.OrganismName;
import World.Organisms.Plants.Dandelion;
import World.Organisms.Plants.DeadlyNightshade;
import World.Organisms.Plants.Grass;
import World.Organisms.Plants.Guarana;
import World.Organisms.Plants.SosnowskysHogweed;

public class World {
    private Canvas canvas;
    private int currentTurn;
    private Session session;
    private OrganismName randAnimal() {
        OrganismName newOne[] = {OrganismName.ANTELOPE,OrganismName.CYBER_SHEEP,OrganismName.FOX,OrganismName.SHEEP,OrganismName.TURTLE,OrganismName.WOLF};
        int r = new Random().nextInt(newOne.length);
        return newOne[r];
    }
    private OrganismName randPlant() {
        OrganismName newOne[] = {OrganismName.DANDELION,OrganismName.DEADLY_NIGHTSHADE,OrganismName.GRASS,OrganismName.GUARANA,OrganismName.SOSNOWSKYS_HOGWEED};
        int r = new Random().nextInt(newOne.length);
        return newOne[r];
    }
    public World(int X, int Y, int T, Session session, WorldType worldType) {
        switch(worldType) {
            case SQR: { canvas = new CanvasSQR(X,Y); break;}
            case HEX: { canvas = new CanvasHEX(X,Y); break; }
        }
        currentTurn = T-1;
        this.session = session;
    }
    public void create(OrganismName oName, Position position) {
        switch(oName) {
            case HUMAN: {
                canvas.add(position ,new Human(position, canvas));
                return;
            }
            case ANTELOPE: {
                canvas.add(position ,new Antelope(position, canvas));
                return;
            }
            case CYBER_SHEEP: {
                canvas.add(position ,new CyberSheep(position, canvas));
                return;
            }
            case FOX: {
                canvas.add(position ,new Fox(position, canvas));
                return;
            }
            case SHEEP: {
                canvas.add(position ,new Sheep(position, canvas));
                return;
            }
            case TURTLE: {
                canvas.add(position ,new Turtle(position, canvas));
                return;
            }
            case WOLF: {
                canvas.add(position ,new Wolf(position, canvas));
                return;
            }
            case DEADLY_NIGHTSHADE: {
                canvas.add(position ,new DeadlyNightshade(position, canvas));
                return;
            }
            case DANDELION: {
                canvas.add(position ,new Dandelion(position, canvas));
                return;
            }
            case GRASS: {
                canvas.add(position ,new Grass(position, canvas));
                return;
            }
            case GUARANA: {
                canvas.add(position ,new Guarana(position, canvas));
                return;
            }
            case SOSNOWSKYS_HOGWEED: {
                canvas.add(position ,new SosnowskysHogweed(position, canvas));
                return;
            }
        }
    }
    void generate() {
        int animals = canvas.getX()/3;
        int plants = canvas.getY()/3;
        create(OrganismName.HUMAN, new Position(canvas.getX()/2, canvas.getY()/2));
        for(int i = 0; i < animals; i++)
            create(randAnimal(),canvas.freeSpace());
        for(int i = 0; i < plants; i++)
            create(randPlant(), canvas.freeSpace());
    }
    void turn(GUI window) {
        canvas.getArr().sort();
        canvas.clearInfo();
        currentTurn++;
        canvas.addInfoUp("Tura "+Integer.toString(currentTurn));
        Human human = canvas.getArr().getHuman();
        if(human != null) {
            canvas.addInfoUp(" kontrola - strzałki \n");
            human.control(window);
        }
        for(int i = 0; i < canvas.orgArr().getArr().size(); i++)
            canvas.orgArr().at(i).action();
        canvas.draw(window,this);
        canvas.getArr().sort();
    }
    public Canvas getCanvas() { return canvas; }
    public Session getSession() { return session; }
    public int getTurn() { return currentTurn; }
}
