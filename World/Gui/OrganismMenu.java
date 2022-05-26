package World.Gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import World.Position;
import World.World;
import World.Organisms.OrganismInterface.OrganismName;

public class OrganismMenu extends JPopupMenu {
    private World world;
    private Position position;
    public OrganismMenu(World world) {
        this.world = world;
        for (OrganismName on : OrganismName.values()) {
            JMenuItem organismItem = new JMenuItem(new AbstractAction(on.toString()) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    world.create(on,position);
                    world.getCanvas().addInfoRight("na "+position.toString()+" pojawił się "+on.toString()+"\n");
                }
            });
            add(organismItem);
        }
    }
    public void setPosition(Position position) {
        this.position = position;
    }
}
