import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 * Write a description of class JImageDisplay here.
 * 
 * @author gcschmit
 * @version 11jul2016
 */
public class JImageDisplay extends JComponent
{
    private BufferedImage image;

    /**
     * Constructor for objects of class JImageDisplay
     */
    public JImageDisplay( int width, int height)
    {
        // initialise instance variables
        image = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB);
        
        this.setPreferredSize( new Dimension( width, height ));
    }
    
    public void clearImage()
    {
        // sets all pixels in the image data to black (RGB value 0)
        int[] rgbArray = new int[ image.getWidth() * image.getHeight() ];
        image.setRGB( 0, 0, image.getWidth(), image.getHeight(), rgbArray, 0, image.getWidth());
    }
    
    public void drawPixel( int x, int y, int rgbColor )
    {
        image.setRGB( x, y, rgbColor );
    }
    
    protected void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        
        g.drawImage( image, 0, 0, image.getWidth(), image.getHeight(), null );
    }
}
