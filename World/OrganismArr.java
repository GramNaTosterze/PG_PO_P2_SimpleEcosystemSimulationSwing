package World;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import World.Organisms.Organism;
import World.Organisms.Animals.Human;
public class OrganismArr {
    private ArrayList<Organism> array;
    private Human human;
    public OrganismArr() {
      this.array = new ArrayList<Organism>();
    }
    public Organism at(int index) { return array.get(index); }
    public Human getHuman() { return human; }
    public ArrayList<Organism> getArr() { return array; }
    public void add(Organism organism) {
        if(organism instanceof Human)
          this.human = (Human) organism;
        array.add(organism);
    }
    public void remove(Organism organism) {
        if(organism instanceof Human)
          this.human = null;
        array.remove(organism);
    }
    public void save(int saveSlot,Canvas canvas,int T) {
      String directory = "slot"+Integer.toString(saveSlot)+".txt", worldType;
      try (FileWriter fileWriter = new FileWriter("saves"+File.separator+directory)) {
        if(canvas instanceof CanvasHEX)
          worldType = "HEX";
        else
          worldType = "SQR";
        fileWriter.write(Integer.toString(canvas.getX())+" "+Integer.toString(canvas.getY())+" "+Integer.toString(T-1)+" "+worldType+"\n");
        for(int i = 0; i < array.size(); i++)
          fileWriter.write(array.get(i).toString()+"\n");
        fileWriter.close();
       } catch (IOException e) {
        System.out.println("jakiś problem");
      }
    }
    public void sort() {
      array.sort(new Comparator<Organism>() {
        @Override
        public int compare(Organism o1, Organism o2) {
          return o1.compareTo(o2);
        }
      });
    }
}
