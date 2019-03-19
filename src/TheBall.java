import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * TheBall. Used in WalkingBall, the primary object that user moves to pass the level.
 * @author Talha Şen
 * @version 30.05.2018
 * -------------------
 * Created the class.
 */

public class TheBall
{
    // Properties
    private int radius;
    private int x;
    private int y;
    private int dX;
    private int dY;
    private BufferedImage image;

    // Constructor
    public TheBall( int radius) {
        this.radius = radius;
        x = 0;
        y = 0;
        dX = 0;
        dY = 0;
        try {
            image = ImageIO.read( new File("C:\\Users\\USER\\IdeaProjects\\WalkingBall\\BasketBall.png") );
        }
        catch ( Exception e)
        {
            System.out.println( "ERROR");
        }

    }

    // Methods
    public void draw( Graphics g)
    {
        g.drawImage( image, x, y, 2 * radius, 2 * radius, null);
    }

    public boolean inPass( Barrier barrier)
    {
        if ( x >= barrier.getPassX() && x + 2 * radius <= barrier.getPassX() + barrier.getPassWidth() )
        {
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getdX() {
        return dX;
    }

    public int getdY() {
        return dY;
    }

    public int getRadius() {
        return radius;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setdX(int dX) {
        this.dX = dX;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
