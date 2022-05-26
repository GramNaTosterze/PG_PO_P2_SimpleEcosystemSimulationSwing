package World;

import World.Gui.HexPanel;
import java.awt.BorderLayout;

public class CanvasHEX extends Canvas{
    public CanvasHEX(int X, int Y) { super(X, Y); }

    @Override
    public void gameBoard(GUI window, World world) {
        window.getContentPane().add(new HexPanel(X, Y, X < Y ? X : Y, board,world), BorderLayout.CENTER);
    }
}
