import java.awt.geom.Rectangle2D;


/**
 * Write a description of class Mandelbrot here.
 * 
 * @author gcschmit
 * @version 11jul2016
 */
public class Mandelbrot extends FractalGenerator
{
    public static final int MAX_ITERATIONS = 2000;

    /**
     * Sets the specified rectangle to contain the initial range suitable for
     *      the fractal being generated.
     * 
     * @param  range   a sample parameter for a method
     */
    public void getInitialRange(Rectangle2D.Double range)
    {
        // The Mandelbrot implementation of this method should set the initial range to (-2 - 1.5i) - (1 + 1.5i).
        // That is, the x and y values will be -2 and -1.5 respectively, and the width and height will both be 3.
        range.setRect( -2, -1.5, 3, 3 );
    }
    
    /**
     * Given a coordinate <em>x</em> + <em>iy</em> in the complex plane,
     * computes and returns the number of iterations before the fractal
     * function escapes the bounding area for that point.  A point that
     * doesn't escape before the iteration limit is reached is indicated
     * with a result of -1.
     */
    public int numIterations(double x, double y)
    {
        double re = 0;
        double im = 0;
        
        for( int i = 0; i < MAX_ITERATIONS; i++ )
        {
            double nextRe = re*re - im*im + x;
            double nextIm = 2*re*im + y;

            re = nextRe;
            im = nextIm;
            
            if( re*re + im*im > 4.0 )
            {
                return i;
            }
        }
        
        return -1;
    }
}
