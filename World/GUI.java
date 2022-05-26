package World;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import World.CanvasInterface.WorldType;
import World.Gui.OrganismMenu;
import World.Organisms.Organism;

import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame{
    public static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private static final String NEW_GAME = "New game";
    private static final String LOAD = "Load";
    public static final String NEXT_TOUR = "Next tour";
    public static final String MOVE_UP = "move up";
    public static final String MOVE_DOWN = "move down";
    public static final String MOVE_LEFT = "move left";
    public static final String MOVE_RIGHT = "move right";
    public static int WIDTH;
    public static int HEIGHT;

    
    public GUI(int width, int height) {
        super("2_Java");
        WIDTH = width;
        HEIGHT = height;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width,height);
    }
    public void menu(Session session) {
        setLayout(new BorderLayout());

        JPanel worldTypePanel = new JPanel(new FlowLayout());
        JLabel worldTypeLabel = new JLabel("Rodzaj świata: ");
        ButtonGroup worldType = new ButtonGroup();
        JRadioButton worldSqr = new JRadioButton("Karta");
        worldSqr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                session.setWorldType(WorldType.SQR);
            }
        });
        JRadioButton worldHex = new JRadioButton("Hex");
        worldHex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                session.setWorldType(WorldType.HEX);
            }
        });
        worldType.add(worldSqr);
        worldType.add(worldHex);
        worldTypePanel.add(worldTypeLabel,BorderLayout.PAGE_END);
        worldTypePanel.add(worldSqr,BorderLayout.PAGE_END);
        worldTypePanel.add(worldHex,BorderLayout.PAGE_END);
        worldTypePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);


        JPanel centerPanel = new JPanel(new FlowLayout());
        JButton newGameButton = new JButton(NEW_GAME);
        newGameButton.setActionCommand(NEW_GAME);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame(session);
            }
        });
        centerPanel.add(newGameButton);

        for(int i = 1; i <= 3; i++) {
            final int index = i;
            JButton loadGameButton = new JButton(LOAD+" "+Integer.toString(i));
            loadGameButton.setActionCommand(LOAD+" "+Integer.toString(i));
            loadGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    load(session,index);
                } 
            });
            centerPanel.add(loadGameButton);
        }
        centerPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        getContentPane().add(centerPanel, BorderLayout.CENTER);
        getContentPane().add(worldTypePanel, BorderLayout.PAGE_END);
        setVisible(true);
    }
    private void newGame(Session session) {
        resetWindow();
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Label", JLabel.CENTER);
        getContentPane().add(label, BorderLayout.PAGE_START);
        
        JSlider gridWidthSlider = new JSlider(JSlider.HORIZONTAL, 1, WIDTH/2, 20);
        JLabel gridWidthLabel = new JLabel("Szerokość");
        JPanel gridWidthPanel = new JPanel(new BorderLayout());
        gridWidthSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gridWidthLabel.setText("Szerokość: " + gridWidthSlider.getValue());
            }
        });
        gridWidthPanel.add(gridWidthSlider, BorderLayout.CENTER);
        gridWidthPanel.add(gridWidthLabel, BorderLayout.PAGE_START);

        JSlider gridHeightSlider = new JSlider(JSlider.HORIZONTAL, 1, WIDTH/2, 20);
        JLabel gridHeightLabel = new JLabel("Wysokość");
        JPanel gridHeightPanel = new JPanel(new BorderLayout());
        gridHeightSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gridHeightLabel.setText("Wysokość:" + gridHeightSlider.getValue());
            }
        });
        gridHeightPanel.add(gridHeightSlider, BorderLayout.CENTER);
        gridHeightPanel.add(gridHeightLabel, BorderLayout.PAGE_START);


        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                session.newWorld(gridHeightSlider.getValue(),gridWidthSlider.getValue());
                session.manage();
            }
        });
        getContentPane().add(gridWidthPanel, BorderLayout.LINE_START);
        getContentPane().add(okButton, BorderLayout.PAGE_START);
        getContentPane().add(gridHeightPanel, BorderLayout.LINE_END);
        setVisible(true);
        
    }
    public void load(Session session, int i) {
        session.loadWorld(i);
        session.manage();
        
    }
    public void resetWindow() {
        getContentPane().removeAll();
        getContentPane().invalidate();
        getContentPane().validate();
        getContentPane().repaint();
    }
    public JPanel gameBoardPanel(Organism[][] board, int X, int Y, World world) {
        JPanel gameBoard = new JPanel(new GridLayout(X,Y,2,2));
        OrganismMenu organismMenu = new OrganismMenu(world);
        for(int i = 0; i < X; i++)
            for(int j = 0; j < Y; j++) {
                JButton grd = new JButton("");
                if(board[j][i] == null) {
                    final Position pos = new Position(i,j);
                    grd.setBackground(Color.WHITE);
                    grd.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent e) {
                            organismMenu.setPosition(pos);
                            organismMenu.show(e.getComponent(), e.getX(), e.getY());
                        }
                    });
                }
                else
                    grd.setBackground(board[j][i].getColor());
                gameBoard.add(grd);
            }
        return gameBoard;
    }
}
