/**
 * 
 */
package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Class Cylinder is the basic class representing a Cylinder which contains height of Cylinder, ray and radius of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Shani Fluk and Elina Hen Chulpaev 
*/
public class Cylinder extends Tube{

	
	private double _height;
	/**
	 * constructors with parameters
	 */
	public Cylinder(double h,Ray _a,double _r) {
		// TODO Auto-generated constructor stub
		super(_a,_r);
		_height=h;
	}
	/**
	 * @return the _height
	 */
	public double get_height() {
		return _height;
	}
	/**
	 * function toString
	 */
    public String toString()
    {
    	return this.get_axisRay().toString()+"height: "+_height;
    }
    /**
     * function that calculate the normal to the Cylinder
     */
    public Vector getNormal (Point3D point)
    {
    	return null;
    }
    /**
     * function that calculates the intersection with cylinder
     * bonus
     */
    @Override
    public List<GeoPoint> findIntsersections(Ray ray) {
        List<GeoPoint> intersections = super.findIntsersections(ray);
        List<GeoPoint> result = new LinkedList<>();
        if (intersections != null) {
            for (GeoPoint geoPoint : intersections) {
                result.add(new GeoPoint(this, geoPoint.point));
            }
            return result;
        }
        return null;
    
	}

    
}