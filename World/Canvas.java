package World;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import World.Organisms.Organism;
import World.Position.Directions;
public abstract class Canvas implements CanvasInterface{
    private String infoUp, infoDown, infoRight;
    private OrganismArr organisms;

    protected int X,Y;
    protected Organism board[][];

    public Canvas(int x, int y) {
        infoUp = new String();
        infoDown = new String();
        infoRight = new String();
        board = new Organism[y][x];
        organisms = new OrganismArr();
        this.X = x;
        this.Y = y;
    }
    public void addInfoUp(String str) {
        this.infoUp = this.infoUp + str;
    }
    public void addInfoDown(String str) {
        this.infoDown = this.infoDown + str;
    }
    public void addInfoRight(String str) {
        this.infoRight = this.infoRight + str;
    }
    public void clearInfo() {
        this.infoUp = "";
        this.infoDown = "";
    }
    public OrganismArr orgArr() { return organisms; }
    public int getX() { return X; }
    public int getY() { return Y; }
    public OrganismArr getArr() { return organisms; }
    public void add(Position pos, Organism organism) {
        set(pos,organism);
        organisms.add(organism);
    }
    public void set(Position pos, Organism organism) {board[pos.y][pos.x] = organism;}
    public Organism at(int index) { return organisms.at(index); }
    public Organism at(Position position) { return board[position.y][position.x]; }
    public Position freeSpace() {
        Position fs = new Position(ThreadLocalRandom.current().nextInt(0, this.X),ThreadLocalRandom.current().nextInt(0, this.Y));
        if(board[fs.y][fs.x] == null)
            return fs;
        else
            return freeSpace();
    }
    public Position nextPos(Position pos) {
        Directions directions = Directions.values()[new Random().nextInt(Directions.values().length)];
        switch (directions) {
            case UP: {
                if(pos.y+1 != Y && board[pos.y+1][pos.x] == null)
                    return new Position(pos.x,pos.y+1);
            }
            case DOWN: {
                if(pos.y-1 != -1 && board[pos.y-1][pos.x] == null)
                    return new Position(pos.x,pos.y-1);
            }
            case LEFT: {
                if(pos.x+1 != X && board[pos.y][pos.x+1] == null)
                    return new Position(pos.x+1,pos.y);
            }
            case RIGHT: {
                if(pos.x-1 != -1 && board[pos.y][pos.x-1] == null)
                    return new Position(pos.x-1,pos.y);
            }
            default: {
                if(pos.y+1 != Y && board[pos.y+1][pos.x] == null)
                    return new Position(pos.x,pos.y+1);
            }
        }
        return null;
    }
    public void draw(GUI window, World world) {
        window.resetWindow();
        window.setLayout(new BorderLayout());
        
        JPanel bottomPanel = new JPanel(new BorderLayout()); 
        JButton nextTourButton = new JButton(GUI.NEXT_TOUR);
        nextTourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.turn(window);
            }
        });

        JMenuBar menu = new JMenuBar();
        JMenu saveMenu = new JMenu("Save");
        for(int i = 1; i <= 3; i++) {
            final int index = i;
            JMenuItem saveItem = new JMenuItem("Slot "+Integer.toString(i));
            saveItem.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    orgArr().save(index,world.getCanvas(),world.getTurn());
                }
            });
            saveMenu.add(saveItem);
        }
        menu.add(saveMenu);
        JMenu loadMenu = new JMenu("Load");
        menu.add(loadMenu);
        for(int i = 1; i <= 3; i++) {
            final int index = i;
            JMenuItem loadItem = new JMenuItem("Slot "+Integer.toString(i));
            loadItem.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    window.load(world.getSession(), index);
                }
            });
            loadMenu.add(loadItem);
        }
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel(infoUp, JLabel.CENTER),BorderLayout.CENTER);
        topPanel.add(menu, BorderLayout.LINE_START);
        bottomPanel.add(nextTourButton,BorderLayout.WEST);
        bottomPanel.add(new JLabel(infoDown,JLabel.CENTER), BorderLayout.CENTER);
        gameBoard(window,world);
        window.getContentPane().add(bottomPanel, BorderLayout.PAGE_END);
        window.getContentPane().add(new JTextArea(infoRight,GUI.HEIGHT,GUI.WIDTH/15), BorderLayout.EAST);
        window.getContentPane().add(topPanel,BorderLayout.PAGE_START);
        window.setVisible(true);
    }
}
