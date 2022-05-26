package World;

import java.awt.BorderLayout;

public class CanvasSQR extends Canvas {
    public CanvasSQR(int x, int y) {
        super(x, y);
    }
    public void gameBoard(GUI window, World world) {
        window.getContentPane().add(window.gameBoardPanel(board, X, Y, world), BorderLayout.CENTER);
    }
}
