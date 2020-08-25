/**
 * 
 */
package geometries;

import java.util.ArrayList;
import static primitives.Util.*;
import java.util.List;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Class Plane is the basic class representing a plane which contains Point and vector of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Shani Fluk and Elina Hen Chulpaev 
*/
public class Plane extends Geometry {

	
	
/**
 * _point - point in the plane
 * normal- normal to the plane
 */
	private Point3D _point;
	private Vector normal;//
	/**
	 * constructor with tree points
	 */
	public Plane(Point3D p1,Point3D p2,Point3D p3) {
		// TODO Auto-generated constructor stub
		//_point=p1;
		//Vector v1=new Vector(p3.subtract(p1));
		//Vector v2=new Vector(p2.subtract(p1));
		//normal=(v1.crossProduct(v2)).normalize();
		this(Color.BLACK,p1,p2,p3);
	}
	
  	

	/**
	 * @param _emmission-color of plane
	 * @param _material-material of plane
	 * @param p1,p2,p3-three points in the plane
	 * @param normal
	 * constructor
	 */
	public Plane(Color _emmission, Material _material, Point3D p1, Point3D p2,Point3D p3) {
		super(_emmission, _material);
		_point = new Point3D(p1);

	      Vector U = new Vector(p1.subtract(p2));
	      Vector V = new Vector(p1.subtract(p3));
	      Vector N = U.crossProduct(V);
	      N.normalize();

	      normal = N;
	}


	/**
	 * constructor with parameters
	 * @param c
	 * @param _point
	 */
	 public Plane(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
	       
		 this(emissionLight,new Material(0, 0, 0), p1, p2, p3);

	    }

	/**
	 * constructor with two parameters
	 * @param p1
	 * @param _v
	 */

	public Plane(Point3D p1,Vector _v)
	{
		
		super(Color.BLACK, new Material(0, 0, 0));

        this._point = new Point3D(p1);
        this.normal = new Vector(_v);
	}
	
	/**
	 * constructor with parameters
	 * @param c
	 * @param _point
	 * @param normal
	 */
	public Plane(Color c, Point3D _point, Vector normal) {
		this(_point,normal);
		_emmission=new Color(c.getColor());
	}




	/**
	 * @return normal vector at the point _p
	 * @param _p - received point to calculate the normal
	 */
	public Vector getNormal(Point3D _p) {
		// TODO Auto-generated method stub
		//Plane p1=new Plane(_p,v);
		return normal.normalize();
	}
	/**
	 * get function
	 * @return the p
	 */
	public Point3D getP() {
		return _point;
	}
	/**
	 * get function
	 * @return the normal
	 */
	public Vector getNormal() {
		return normal;
	}
	
    /**
     * function that calculates the intersection with plane
     * ray- the ray that comes from the camera in order to calculate the intersections points
     */

	  public List<GeoPoint> findIntsersections (Ray ray){
		
		
		//return null;
	
		Vector normal=this.getNormal(_point);	
		Vector p0q0=(this._point).subtract(ray.getP());
		if(this._point.equals(ray.getP()))
		{
			throw new IllegalArgumentException("the points cant be equal");
		}
		
		if(isZero(normal.dotProduct(ray.getV())))
			return null;
			//throw new IllegalArgumentException("cant divide by zero");
		double t=alignZero(normal.dotProduct(p0q0))/(normal.dotProduct(ray.getV()));
		if(t>0)
		{
			Point3D endP=ray.getPoint(t);
			List<GeoPoint> intersections=new ArrayList<GeoPoint>();
			intersections.add(new GeoPoint(this,endP));
			return intersections;
		}
		return null;
	}

	
}