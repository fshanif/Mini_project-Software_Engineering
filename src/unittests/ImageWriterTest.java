/**
 * 
 */
package unittests;

//import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Color;
import renderer.ImageWriter;

/**
 * @author USER
 *
 */
public class ImageWriterTest {

	/**
	 * Test method for {@link renderer.ImageWriter#ImageWriter(java.lang.String, double, double, int, int)}.
	 */
	@Test
	public void testImageWriter() 
	{
		Color c= new Color(255 ,191 ,191);
		String imagename = "hii";
        int width = 1600;            
        int height = 1000;
        int nx =800;   
        int ny =500;
        ImageWriter imageWriter = new ImageWriter(imagename, width, height, nx, ny);
        for (int col = 0; col < ny; col++) {
            for (int row = 0; row < nx; row++) {
                if (col % 50==0 || row % 50== 0) {
                    imageWriter.writePixel(row, col, Color.WHITE.getColor());
                }
                else
                	imageWriter.writePixel(row, col, c.getColor());
            }
        }
                                                                                                                    
        imageWriter.writeToImage();
    
	}

}
