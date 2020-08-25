/**
 * 
 */
package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

/**
 * Class Triangle is the basic class representing a Triangle 
 * @author Shani Fluk and Elina Hen Chulpaev 
*/
public class Triangle extends Polygon {

	/**
	 *constructor with three parameters 
	 */
	public Triangle(Point3D p1,Point3D p2,Point3D p3) {
		 super(p1,p2,p3);
	}
	
    /**
     * onstructor with four parameters 
	 * @param c-color of triangle
	 * @param p1,p2,p3-verticals of triangle
	 */
	public Triangle(Color c, Point3D p1,Point3D p2,Point3D p3) {
		
		super(c,p1,p2,p3);
	}
	
    /**
     * constructor with all parameters
	 * @param emissionLight-color of triangle
	 * @param material-material of triangle
	 * @param p1,p2,p3-verticals of triangle
	 */
	public Triangle(Color emissionLight, Material material,  Point3D p1, Point3D p2, Point3D p3) {
		super(emissionLight, material, p1,p2,p3);
	}

	/**
     * function that calculates the intersection with triangle
     * @param ray- ray from the camera to the wanted pixel
     */
	   @Override
	    public List<GeoPoint> findIntsersections(Ray ray) {
	        List<GeoPoint> planeIntersections = _plane.findIntsersections(ray);
	        if (planeIntersections == null) return null;

	        Point3D p0 = ray.getP();
	        Vector v = ray.getV();

	        Vector v1 = _vertices.get(0).subtract(p0);
	        Vector v2 = _vertices.get(1).subtract(p0);
	        Vector v3 = _vertices.get(2).subtract(p0);

	        double s1 = v.dotProduct(v1.crossProduct(v2).normalize());
	        if (isZero(s1)) return null;
	        double s2 = v.dotProduct(v2.crossProduct(v3).normalize());
	        if (isZero(s2)) return null;
	        double s3 = v.dotProduct(v3.crossProduct(v1).normalize());
	        if (isZero(s3)) return null;

	        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) {
	            //for GeoPoint
	            List<GeoPoint> result = new LinkedList<>();
	            for (GeoPoint geo : planeIntersections) {
	                result.add(new GeoPoint(this, geo.point));
	            }
	            return result;
	        }

	        return null;

	    }
}
