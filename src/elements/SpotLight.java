/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

/**
 * @author shani and elina
 *  Models point light source with direction (such as a luxo lamp)
 * Intensity (_intensity)
 * Position (_position)
 * Direction _direction (Vector) - normalized
 * Attenuation factors 
 *
 */
public class SpotLight extends PointLight{

	private Vector _direction;

	/**
	 * constructor with all parameters
	 * @param _intensity
	 * @param _position
	 * @param _kC
	 * @param _kL
	 * @param _kQ
	 * @param _direction
	 */
	public SpotLight(Color _intensity, Point3D _position, double _kC, double _kL, double _kQ, Vector _direction) {
		super(_intensity, _position, _kC, _kL, _kQ);
		this._direction = _direction.normalize();
	}

	/**
	 * @param _p point to calculate the intensity
	 * @return the intensity of light source at the point received
	 */
	@Override
	public Color getIntensity(Point3D _p) {
		double dirL = _direction.dotProduct(getL(_p));

        if (Util.isZero(dirL)) {
            return Color.BLACK;
        }
        double maxi = Math.max(0, dirL);
        Color pli = super.getIntensity(_p);
        return (pli.scale(maxi));
	}
	/**
	 * light vector
	 */
    @Override
    public Vector getL(Point3D p) {
    	return super.getL(p);
    }
	
}
