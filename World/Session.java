package World;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import World.CanvasInterface.WorldType;
import World.Organisms.OrganismInterface.OrganismName;

public class Session {
    private boolean running, created;
    private World world;
    private GUI window;
    private WorldType worldType;
    public Session() {
        worldType = WorldType.SQR;
        running = true;
        created = false;
        window = new GUI(300,300);
    }
    public void Gui() {
        window.menu(this);
    }
    public boolean sessionRunning() { return running; }
    public boolean worldCreated() { return created; }
    public void end() {
        running = false;
        created = false;
    }
    public void newWorld(int X, int Y) {
        world = new World(X, Y, 1,this,worldType);
        rand();
        created = true;
    }
    public void loadWorld(int saveSlot) {
        String directory = "slot"+Integer.toString(saveSlot)+".txt";
        try(BufferedReader fileReader = new BufferedReader(new FileReader("saves"+File.separator+directory))) {
            String board = fileReader.readLine();
            String xy[] = board.split(" ");
            world = new World(Integer.parseInt(xy[0]),Integer.parseInt(xy[1]),Integer.parseInt(xy[2]),this,WorldType.valueOf(xy[3]));
            String organism = fileReader.readLine();
            while(organism != null) {
                String organismComponents[] = organism.split(" ");
                world.create(OrganismName.find(organismComponents[0]), new Position(organismComponents[1]));
                organism = fileReader.readLine();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
           System.out.println("Nie znaleziono pliku "+Integer.toString(saveSlot));
        } catch (IOException e) {
            System.out.println("błąd jakiś");
        }
    }
    public void rand() { world.generate(); }
    public void manage() { world.turn(window); }
    public void setWorldType(WorldType worldType) { this.worldType = worldType; }
}
