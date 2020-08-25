/**
 * 
 */
package geometries;

import primitives.Color;
import primitives.Material;

/**
 * @author shani and elina
 *_radius- radius of radial geometry
 */
public abstract class RadialGeometry extends Geometry{
	
	private double _radius;
	
	
	
	/**
	 * constructor with parameter
	 */
	public RadialGeometry(double r) {
		// TODO Auto-generated constructor stub
		 if (r==0 || (r < 0.0))
	            throw new IllegalArgumentException("radius " + r + " is not valid");
		_radius=r;
	}
	
	/**
	 * @param _emmission- color of radial geometry
	 * @param _material-material of radial geometry
	 * @param _radius-radius of radial geometry
	 */
	public RadialGeometry(Color _emmission, Material _material, double _radius) {
		super(_emmission, _material);
		this._radius = _radius;
	}

	/**
	 * constructor with parameters
	 * @param c
	 * @param _radius
	 */
	public RadialGeometry(Color emissionLight, double radius) {
        super(emissionLight);
        if (radius==0 || (radius < 0.0))
            throw new IllegalArgumentException("radius " + radius + " is not valid");
	    _radius=radius;
    }


	/**
	 * copy constructor
	 */
	public RadialGeometry(RadialGeometry x) {
		// TODO Auto-generated constructor stub
		_radius=x._radius;
	}
	/**
	 * get function
	 * @return the _radius
	 */
	public double get_radius() {
		return _radius;
	}
	/**
	 * @return string information for class radial geometry
	 */
	@Override
	public String toString() {
		return "RadialGeometry [_radius=" + _radius + "]";
	}
	

}
