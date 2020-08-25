/**
 * 
 */
package geometries;
import static primitives.Util.*;

import java.util.ArrayList;
import java.util.List;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Class Sphere is the basic class representing a Sphere which contains Point and radius of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Shani Fluk and Elina Hen Chulpaev 
*/
public class Sphere extends RadialGeometry {

	
	private Point3D _center;
	/**
	 * constructor with parameters
	 */
	public Sphere(Point3D _c,double _r) {
         
        this(Color.BLACK,new Material(0,0,0),_r,_c);

	}


	/**
	 * constructor with all parameters
	 * @param emissionLight- color of sphere
	 * @param material-material of sphere
	 * @param radius-radius of sphere
	 * @param center-the point  of the spheres center
	 */
	 public Sphere(Color emissionLight, Material material, double radius, Point3D center) {
	        super( emissionLight, material,radius);
	        this._center = new Point3D(center);
	    }


	 /**
	  * constructor with parameters
	  * @param emissionLight -color of sphere
	  * @param radius-radius of sphere
	  * @param center-the point  of the spheres center
	  */
	public Sphere(Color emissionLight, double radius, Point3D center) {
     
	       this(emissionLight,new Material(0,0,0),radius,center);

    }


	/**
	 * get function
	 * @return the center
	 */
	public Point3D getCenter() {
		return _center;
	}
	/**
	 * function to String
	 */
	public String toString()
	{
		return "radius: "+get_radius()+"center: "+_center;
	}

	/**
	 * get function
	 * function getNormal
	 */
	public Vector getNormal (Point3D _p)
	{
		return (_p.subtract(_center)).normalize();

	}
    /**
     * function that calculates the intersection with sphere
     */
	public List<GeoPoint> findIntsersections (Ray ray) {
		Vector u;
		double tm;
		double d;
		double th;
		if(!_center.equals(ray.getP()))
		{
		  u=_center.subtract(ray.getP());	
		 tm=alignZero(ray.getV().dotProduct(u));
		 d=alignZero(Math.sqrt(u.lengthSquared()-tm*tm));
		if(d>get_radius())
			return null;
		 th=alignZero(Math.sqrt(get_radius()*get_radius()-d*d));
		}
		else
		{
			 u=new Vector(ray.getP());
			 tm=0;
			 d=0;
			 th=alignZero(Math.sqrt(get_radius()*get_radius()-d*d));	
		}
		Point3D p1=new Point3D(ray.getPoint(tm+th));
		Point3D p2=new Point3D(ray.getPoint(tm-th));
		List<GeoPoint> intersection=new ArrayList<GeoPoint>();
		double o1=p1.subtract(_center).dotProduct(ray.getV());
		double o2=p2.subtract(_center).dotProduct(ray.getV());
		if(tm+th>0&& !isZero(o1))
		  intersection.add(new GeoPoint(this,p1));
		if(tm-th>0&&!isZero(o2))
			  intersection.add(new GeoPoint(this,p2));
		if(isZero(intersection.size()))
			return null;
		return intersection;
		

	}
}