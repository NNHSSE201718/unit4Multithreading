import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;


/**
 * Write a description of class FractalExplorer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FractalExplorer
{
    // instance variables - replace the example below with your own
    private int displaySize;
    private JImageDisplay display;
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double range;

    /**
     * Constructor for objects of class FractalExplorer
     */
    public FractalExplorer( int displaySize )
    {
        // initialise instance variables
        this.displaySize = displaySize;
        this.fractalGenerator = new Mandelbrot();
        this.range = new Rectangle2D.Double();
        this.fractalGenerator.getInitialRange( this.range );
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void createAndShowGUI()
    {
        this.display = new JImageDisplay( this.displaySize, this.displaySize );
        
        JButton resetButton = new JButton( "Reset Display" );
        resetButton.addActionListener( new ClickListener());
        
        JFrame frame = new JFrame();
        frame.setTitle( "Fractal Explorer" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );        
        frame.add( display, BorderLayout.CENTER );
        frame.add( resetButton, BorderLayout.SOUTH );
        frame.addMouseListener( new ZoomMouseListener());
        
        frame.pack();
        frame.setVisible( true );
        frame.setResizable( false );
    }
    
    private void drawFractal()
    {
        for( int x = 0; x < this.displaySize; x++ )
        {
            for( int y = 0; y < this.displaySize; y++ )
            {
                // x is the pixel-coordinate; xCoord is the coordinate in the fractal's space
                double xCoord = FractalGenerator.getCoord( this.range.x, this.range.x + this.range.width,
                        displaySize, x );
                double yCoord = FractalGenerator.getCoord( this.range.y, this.range.y + this.range.height,
                        displaySize, y );
                int numIterations = this.fractalGenerator.numIterations( xCoord, yCoord );
                
                // If the number of iterations is -1 (i.e. the point doesn't escape, se the pixel's color
                //      to black (rgb value 0)
                int rgbColor = 0;
                if( numIterations != -1 )
                {
                    float hue = 0.7f + (float) numIterations / 200f;
                    rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                }
                
                this.display.drawPixel( x, y, rgbColor );
            }
        }
        
        this.display.repaint();
    }
    
    class ClickListener implements ActionListener
    {
        public void actionPerformed( ActionEvent event )
        {
            display.clearImage();
            drawFractal();
        }
    }
    
    class ZoomMouseListener extends MouseAdapter
    {
        public void mouseClicked( MouseEvent event )
        {
            int x = event.getX();
            int y = event.getY();
            
            // map the click pixel-coordinates into the area of the fractal that is being displayed
            double centerX = FractalGenerator.getCoord( range.x, range.x + range.width, displaySize, x );
            double centerY = FractalGenerator.getCoord( range.y, range.y + range.height, displaySize, y ); 
            
            fractalGenerator.recenterAndZoomRange( range, centerX, centerY, 0.5);
            drawFractal();
        }
    }
    
    public static void main( String[] args )
    {
        FractalExplorer fractalExplorer = new FractalExplorer( 500 );
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }
}
