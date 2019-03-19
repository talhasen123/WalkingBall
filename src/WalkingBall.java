import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
/*
 * Created by JFormDesigner on Wed May 30 18:14:13 EET 2018
 */



/**
 * WalkingBall. A fun game.
 * @author Talha Şen
 * @version 30.05.2018
 * -------------------
 * Created the class.
 */
public class WalkingBall extends JPanel {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Talha
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    // Properties
    private TheBall ball;
    private ArrayList<Barrier> barriers;
    private Timer checkProgress;

    // Constructor
    public WalkingBall() {
        initComponents();
        initProperties();
    }

    // Methods
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Talha

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGap(0, 300, Short.MAX_VALUE)
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void initProperties() {
        setFocusable( true);
        requestFocusInWindow();
        setSize( new Dimension( 500, 500));
        setBackground( Color.WHITE);
        ball = new TheBall(  5);
        ball.setX( 300);
        ball.setY( 25);
        ball.setdX( 5);
        ball.setdY( 5);
        barriers = new ArrayList<>();
        checkProgress = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( barriers.size() == 0 )
                {
                    int answer;
                    answer = JOptionPane.showConfirmDialog( null, "Would You Like To Play Again?",
                            "You Won The Round", JOptionPane.YES_NO_OPTION);
                    if ( answer == JOptionPane.YES_OPTION )
                    {
                        ball.setX( 300);
                        ball.setY( 25);
                        fillBarriers();
                        repaint();
                    }
                    else
                    {
                        System.exit(0);
                    }
                }

                else
                {
                    Barrier b;
                    b = barriers.get( 0);

                    if (ball.getY() >= b.getY() + b.getHeight()) {
                        barriers.remove( 0);
                        repaint();
                    }
                }
            }
        });

        fillBarriers();

        this.addKeyListener( new MyKeyboardListener());
        checkProgress.start();
    }

    private void failReset()
    {
        JOptionPane.showMessageDialog(null, "You hit a barrier!");
        ball.setX(300);
        ball.setY(25);
    }

    private void fillBarriers()
    {
        for ( int i = 1; i <= 5; i++)
        {
            barriers.add( new Barrier( getWidth(), 50, 0, i * 70 ) );
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent( g);

        for ( Barrier b : barriers )
        {
            b.draw( g);
        }

        g.setColor( Color.RED);
        ball.draw( g);
    }

    private class MyKeyboardListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if ( barriers.size() != 0 ) {
                Barrier currentBarrier;
                currentBarrier = barriers.get(0);
                if (ball.getY() + 2 * ball.getRadius() < currentBarrier.getY() || ball.inPass(currentBarrier)) {
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT && ball.getX() <= getWidth() - 3 * ball.getRadius()) {
                        ball.setX(ball.getX() + ball.getdX());
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT && ball.getX() >= 5) {
                        ball.setX(ball.getX() - ball.getdX());
                    } else if (e.getKeyCode() == KeyEvent.VK_UP && ball.getY() >= 5) {
                        ball.setY(ball.getY() - ball.getdY());
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN && ball.getY() <= getHeight() - 3 * ball.getRadius()) {
                        ball.setY(ball.getY() + ball.getdY());
                    }
                } else {
                    failReset();
                }
            }


            e.consume();
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if ( barriers.size() != 0 ) {
                Barrier currentBarrier;
                currentBarrier = barriers.get(0);

                if (ball.getY() + 2 * ball.getRadius() > currentBarrier.getY() && ball.getY() < currentBarrier.getY() + currentBarrier.getHeight()) {
                    if (!ball.inPass(currentBarrier)) {
                        failReset();
                        repaint();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame game;
        game = new JFrame();

        game.add( new WalkingBall());
        game.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        game.setLocationRelativeTo( null);
        game.setSize( new Dimension( 514, 500));
        game.setBackground( Color.WHITE);
        game.setVisible( true);
    }
}
