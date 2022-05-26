package World;

public class Position {
    public int x;
    public int y;
    public enum Directions {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        STAY,
    }
    public Position() {}
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Position(String position) {
        String pos[] = (position.substring(1, position.length()-1)).split(",");
        this.x = Integer.parseInt(pos[0]);
        this.y = Integer.parseInt(pos[1]);
    }
    public boolean equals(Position eq) {
        return this.x == eq.x && this.y == eq.y;
    }
    public String toString() {
        return "{"+Integer.toString(this.x)+","+Integer.toString(this.y)+"}";
    }
}
