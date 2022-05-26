package World.Organisms;

import World.Position;
import java.awt.Color;

public interface OrganismInterface {
    public enum Type{
        ANIMAL,
        PLANT
    }
    public enum OrganismName {
        HUMAN("Człowiek",Color.BLACK),
        WOLF("Wilk",Color.DARK_GRAY),
        SHEEP("Owca",Color.CYAN),
        FOX("Lis",Color.ORANGE),
        TURTLE("Żółw",Color.GREEN),
        ANTELOPE("Antylopa", Color.MAGENTA),
        CYBER_SHEEP("CyberOwca", Color.BLUE),

        GRASS("Trawa", Color.PINK),
        DANDELION("Mlecz", Color.YELLOW),
        GUARANA("Guarana", Color.GRAY),
        DEADLY_NIGHTSHADE("WilczeJagody", Color.RED),
        SOSNOWSKYS_HOGWEED("BarszczSosnowskiego", Color.RED);
        
        private final String name;
        private final Color color;
        private OrganismName(String name, Color color) {
            this.name = name;
            this.color = color;
        }
        public String toString() { return name; }
        public Color col() { return color; }
        public static OrganismName find(String str) {
            for (OrganismName e : values()) {
                if (e.name.equals(str)) {
                    return e;
                }
            }
            return null;
        }
    }
    void action();
    void colision(Organism organism);
    void addInstanceOf(Position pos);
    void replace(Organism first,Organism second); 
}
