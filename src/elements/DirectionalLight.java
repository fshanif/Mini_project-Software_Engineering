/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author shani and elina
 * Light source is far away (at infinity?) - like Sun
 * Intensity (I0)
 * Direction (Vector)
 * No attenuation with distance
 * 
 */
public class DirectionalLight  extends Light implements LightSource {
	
	private Vector _direction;

	/**
	 * @param _intensity
	 * @param _direction
	 */
	public DirectionalLight(Color _intensity, Vector _direction) {
		super(_intensity);
		this._direction = _direction.normalize();
	}
	/**
	 * @param p its not used since the intensity is equal everywhere
	 * @return intensity for DirectionalLight
	 */
	@Override
	public Color getIntensity(Point3D p) {
		return super.get_intensity();
	}
	 /**
	  * same as getDirection()
	  */
    @Override
    public Vector getL(Point3D p) {
    	return _direction;
    }
    /**
	 * calculates the distance from the camera to the point recieved
	 * @param point
	 * @return
	 */
    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }
}
