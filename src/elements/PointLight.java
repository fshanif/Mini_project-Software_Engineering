/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author shani and elina
 *Models omni-directional point source (such as a bulb)
 * Intensity (I0)
 * Position (PL)
 * Factors (kc, kl, kq) for attenuation with distance (d)
 */
public class PointLight extends Light implements LightSource {

	 protected Point3D _position;
	 protected double _kC; // Constant attenuation
	 protected double _kL; // Linear attenuation
	 protected double _kQ; // Quadratic attenuation
	/**
	 * @param _intensity
	 * @param _position
	 * @param _kC
	 * @param _kL
	 * @param _kQ
	 */
	public PointLight(Color _intensity, Point3D _position, double _kC, double _kL, double _kQ) {
		super(_intensity);
		this._position = _position;
		this._kC = _kC;
		this._kL = _kL;
		this._kQ = _kQ;
	}

	/**
	 * @param p point to calculate the intensity
	 * @return the intensity of light source at the point received
	 */
	@Override
	public Color getIntensity(Point3D p) {
		double d=p.distance(_position);
		double dSquared=p.distanceSquared(_position);
		return (this._intensity.reduce(_kC+_kL*d+_kQ*dSquared));
	}

     /**
      * light vector
      */
	@Override
	public Vector getL(Point3D p) {
		 if (p.equals(_position)) {
	            return null;
	        }
	        return p.subtract(_position).normalize();
	}
	 
	 
	/**
	 * calculates the distance from the camera to the point received
	 * @param point
	 * @return
	 */
	@Override
    public double getDistance(Point3D point) {
        return _position.distance(point);
    }
	 

}
