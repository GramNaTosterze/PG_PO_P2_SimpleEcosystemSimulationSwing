package World.Organisms.Animals;

import java.awt.event.*;
import javax.swing.*;

import World.Canvas;
import World.GUI;
import World.Position;
import World.Organisms.Animal;
import World.Position.Directions;

public class Human extends Animal {
    private static final String SPECIAL_ABILITY = "special ability";
    private Directions direction;
    private int abilityTimer;
    private class HumanDirection extends AbstractAction {
        Directions directions;
        HumanDirection(Directions d) {
            directions = d;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            direction = directions;
        }
    }
    private class HumanAbility extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            direction = Directions.STAY;
            if(abilityTimer == 0)
                calopalenie();
            else
                canvas.addInfoRight("Umiejętność nie gotowa\n");
        }
    }
    private void initActions(GUI window) {
        window.getRootPane().getInputMap(GUI.IFW).put(KeyStroke.getKeyStroke("UP"), GUI.MOVE_UP);
        window.getRootPane().getInputMap(GUI.IFW).put(KeyStroke.getKeyStroke("DOWN"), GUI.MOVE_DOWN);
        window.getRootPane().getInputMap(GUI.IFW).put(KeyStroke.getKeyStroke("LEFT"), GUI.MOVE_LEFT);
        window.getRootPane().getInputMap(GUI.IFW).put(KeyStroke.getKeyStroke("RIGHT"), GUI.MOVE_RIGHT);
        window.getRootPane().getInputMap(GUI.IFW).put(KeyStroke.getKeyStroke((char)KeyEvent.VK_SPACE), SPECIAL_ABILITY);

        window.getRootPane().getActionMap().put(GUI.MOVE_UP, new HumanDirection(Directions.UP));
        window.getRootPane().getActionMap().put(GUI.MOVE_DOWN, new HumanDirection(Directions.DOWN));
        window.getRootPane().getActionMap().put(GUI.MOVE_LEFT, new HumanDirection(Directions.LEFT));
        window.getRootPane().getActionMap().put(GUI.MOVE_RIGHT, new HumanDirection(Directions.RIGHT));
        window.getRootPane().getActionMap().put(SPECIAL_ABILITY, new HumanAbility());
    }

    public Human(Position position, Canvas canvas) {
        super(5,4,position,OrganismName.HUMAN,1,canvas);
        this.direction = Directions.STAY;
        this.abilityTimer = 0;
    }
    @Override
    public void addInstanceOf(Position pos) {
        canvas.add(pos, new Human(pos, canvas));        
    }
    public void control(GUI window) {
        initActions(window);

        switch(direction) {
            case UP:{
                canvas.addInfoDown("Kierunek: góra");
                break;
            }
            case DOWN:{
                canvas.addInfoDown("Kierunek: dół");
                break;
            }
            case LEFT:{
                canvas.addInfoDown("Kierunek: lewo");
                break;
            }
            case RIGHT:{
                canvas.addInfoDown("Kierunek: prawo");
                break;
            }
            case STAY: {
                break;
            }
        }
    }
    @Override
    public void action() {
        age++;
        switch(direction) {
            case UP: {
                move(-1,0);
                break;
            }
            case DOWN: {
                move(1,0);
                break;
            }
            case LEFT: {
                move(0,-1);
                break;
            }
            case RIGHT: {
                move(0,1);
                break;
            }
            case STAY: {
                break;
            }
        }
        if(abilityTimer > 0)
            abilityTimer--;
    }
    private void calopalenie() {
        canvas.addInfoRight(getName()+" uyżył całopalenia\n");
        abilityTimer = 5;
        int ni = position.x-1,mi = position.x+2;
        int nj = position.y-1,mj = position.y+2;
        if(position.x == 0)
            ni = 0;
        if(position.x+2 >= canvas.getX())
            mi = canvas.getX();
        if(position.y == 0)
            nj = 0;
        if(position.y+2 >= canvas.getY())
            mj = canvas.getY();
        for(int i = ni; i < mi; i++)
            for(int j = nj; j < mj; j++) {
                Position pos = new Position(i,j);
                if(!position.equals(pos) && canvas.at(pos) != null) {
                    replace(this,canvas.at(pos));
                    canvas.set(pos, null);
                }
            }
    }
}
