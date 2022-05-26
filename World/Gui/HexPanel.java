package World.Gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import World.Position;
import World.World;
import World.Organisms.Organism;

public class HexPanel extends JPanel{
    private static final int BORDER = 20;
    private int row, col;
    private int side, t, r, height;
    private Organism[][] board;
    private Polygon hexagon (int x, int y) {
        x = x + BORDER;
        y = y + BORDER;
        int[] cx, cy;
        
        cx = new int[] {x,x+side,x+side+t,x+side,x,x-t};
        cy = new int[] {y,y,y+r,y+r+r,y+r+r,y+r};
        return new Polygon(cx,cy,6);
    }
    private void drawHex(int i, int j, Graphics2D g2) {
        int x = i * (side+t);
		int y = j * height + (i%2) * height/2;
		Polygon poly = hexagon(x,y);
		g2.setColor(Color.WHITE);
		g2.fillPolygon(poly);
		g2.setColor(Color.BLACK);
		g2.drawPolygon(poly);
    }
    private void fillHex(int i, int j, Graphics2D g2) {
		int x = i * (side+t);
		int y = j * height + (i%2) * height/2;
		if (board[i][j] == null)
			g2.setColor(Color.WHITE);
		else
			g2.setColor(board[i][j].getColor());
		g2.fillPolygon(hexagon(x,y));
		g2.setColor(Color.BLACK);
    }
    public HexPanel(int row, int col, int Hsize, Organism[][] board, World world) {
        setSide(Hsize);
        this.board = board;
        this.row = row;
        this.col = col;
        setBackground(Color.WHITE);
        OrganismMenu organismMenu = new OrganismMenu(world);
        addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Point point = new Point( pxtoHex(e.getX(), e.getY()));
                if(point.x < 0 || point.y < 0 || point.x >= row || point.y >= col)
                    return;
                organismMenu.setPosition(new Position(point.y,point.x));
                organismMenu.show(e.getComponent(), e.getX(), e.getY());
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent arg0) {}
            @Override
            public void mouseExited(java.awt.event.MouseEvent arg0) {}

            @Override
            public void mousePressed(java.awt.event.MouseEvent arg0) {}

            @Override
            public void mouseReleased(java.awt.event.MouseEvent arg0) {}

            @Override
            public void mouseDragged(java.awt.event.MouseEvent arg0) {}

            @Override
            public void mouseMoved(java.awt.event.MouseEvent arg0) {}
            
        });
    }
    @Override
    public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            super.paintComponent(g2);
            for(int i = 0; i < col; i++)
                for(int j = 0; j < row; j++) {
                    drawHex(i,j,g2);
                    fillHex(i,j,g2);
                }
    }
    public void setSide(int side) {
		this.side=side;
		t =  (int) (side*Math.sin(Math.toRadians(30)));
		r =  (int) (side *Math.cos(Math.toRadians(30)));
		height=2*r;
	}
    public Point pxtoHex(int mx, int my) {
		mx = mx - BORDER + t;
		my = my - BORDER;

		int x = (int) (mx / (side+t)); 
		int y = (int) ((my - (x%2)*r)/height);

		int dx = mx - x*(side+t);
		int dy = my - y*height;

		if (my - (x%2)*r < 0) return new Point(-1,-1);


		if (x%2==0) {
			if (dy > r) {
				if (dx * r /t < dy - r) {
					x--;
				}
			}
			if (dy < r) {
				if ((t - dx)*r/t > dy ) {
					x--;
					y--;
				}
			}
		} else {
			if (dy > height) {
				if (dx * r/t < dy - height) {
					x--;
					y++;
				}
			}
			if (dy < height) {
				if ((t - dx)*r/t > dy - r) {
					x--;
				}
			}
		}
		return new Point(x,y);
	}
}
