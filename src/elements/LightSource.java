/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author shani and elina
 * 
 *Interface for common actions of light sources
 */
public interface LightSource {
	/**
	 * 
	 * @param p the lighted point
	 * @return the intensity of light source at the point received
	 */
	public Color getIntensity(Point3D p) ;
	
	/**
	 * Get normalized vector in the direction from light source
	 * @param p the lighted point
	 * @return light to point vector
	 */
	public Vector getL(Point3D p);

	/**
	 * calculates the distance from the camera to the point recieved
	 * @param point
	 * @return
	 */
	 double getDistance(Point3D point);
}

