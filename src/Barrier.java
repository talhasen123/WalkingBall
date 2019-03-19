import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Barrier. Used for WalkingBall. The objects that are used to block the ball and one segment for the pass.
 * @author Talha Şen
 * @version 30.05.2018
 * -------------------
 * Created the class.
 */

public class Barrier
{
    // Properties
    private int width;
    private int height;
    private int x;
    private int y;
    private int passWidth;
    private int passX;
    private BufferedImage barrierIcon;

    // Constructor
    public Barrier( int width, int height, int x, int y)
    {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        passX = (int) ( Math.random() * ( 19 * width / 20 ) ) + 1;
        passWidth = width / 20;
        try {
            barrierIcon = ImageIO.read( new File("C:\\Users\\USER\\IdeaProjects\\WalkingBall\\BarrierIcon.png") );
        }
        catch ( Exception e)
        {
            System.out.println( "EEROR");
        }

    }

    // Methods
    public void draw( Graphics g)
    {
        g.setColor( Color.BLACK);
        g.drawImage( barrierIcon, x, y, width, height, null);

        g.setColor( Color.WHITE);
        g.fillRect( passX, y, passWidth, height);

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPassX() {
        return passX;
    }

    public int getPassWidth() {
        return passWidth;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
