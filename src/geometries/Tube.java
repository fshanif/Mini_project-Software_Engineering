/**
 * 
 */
package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

import java.util.List;

/**
 * Class Tube is the basic class representing a Tube which contains ray and radius of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Shani Fluk and Elina Hen Chulpaev 
*/
public class Tube extends RadialGeometry {

	
	private Ray _axisRay;
	/**
	 * constructor with parameters
	 */
	public Tube(Ray _a,double _r) {
		
        this(Color.BLACK, new Material(0, 0, 0), _r, _a);

	}
	
	
	/**
	 * constructor of four parameters
	 * @param _emmission- color of tube
	 * @param _material-material of tube
	 * @param _radius-material of tube
	 */
	public Tube(Color emmission, Material material, double _radius,Ray _ray) {
	
		super(Color.BLACK, _radius);
        this._material =material;
        this._axisRay = new Ray(_ray);
	}


	/**
	 * constructor with parameters
	 * @param emissionLight -color
	 * @param _radius-radius
	 * @param _ray-ray
	 */
	public Tube(Color emissionLight, double _radius, Ray _ray) {
      

        this(emissionLight, new Material(0, 0, 0), _radius, _ray);

	}

	/**
	 * @return the _axisRay
	 */
	public Ray get_axisRay() {
		return _axisRay;
	}
	/**
	 * function toString
	 */
	public String toString()
	{
		return "radius: "+get_radius()+"Ray: "+_axisRay;
	}
	/**
	 * function that returns the normal vector to tube
	 * @param _t
	 * @return
	 */
	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		Point3D o=_axisRay.getP();
		Vector v = _axisRay.getV();
		double t =point.subtract(o).dotProduct(v);
		if(!isZero(t))
			o=o.add(v.scale(t));
		return point.subtract(o).normalize();		
	}

    /**
     * function that calculates the intersection with tube
     */
	public List<GeoPoint> findIntsersections (Ray ray){
		
		return null;
	}

}