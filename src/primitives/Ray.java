/**
 * 
 */
package primitives;


/**
 * Class Ray is the basic class representing a Ray wich contains point3D and vector of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Shani Fluk and Elina Hen Chulpaev 
*/
public class Ray {

	/**
	 * 
	 */
    private static final double DELTA = 0.1;
    /**
     * The point from which the ray starts.
     */
	private Point3D p;
	/**
     * The direction of the ray.
     */
	private Vector v;
	/**
	 * constructor with parameters
	 */
	public Ray(Point3D _p,Vector _v) {
		// TODO Auto-generated constructor stub
	
		        p=new Point3D(_p);
				v=new Vector(_v.normalize());
	}
	/**
	 * constructor receives point and two vectors 
	 * the point is the point + normal*dot product between normal and direction
	 * @param point
	 * @param direction
	 * @param normal- normal vector
	 */
	 public Ray(Point3D point, Vector direction, Vector normal) {
	        //head+ normal.scale(±DELTA)
	        v = new Vector(direction).normalized();

	        double nv = normal.dotProduct(direction);

	        Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
	        p = point.add(normalDelta);
	 }
	/**
	 * copy constructor
	 * @param _r
	 */
    public Ray(Ray _r)
    {
    	p=_r.p;
    	v=_r.v;
    }
	/**
	 * @return the point
	 */
	public Point3D getP() {
		return p;
	}
	/**
	 * @return the point p=p0+t*v
	 */
	public Point3D getPoint(double t)
	{
		return new Point3D (p.add(v.scale(t)));
	}
	/**
	 * @return the vector
	 */
	public Vector getV() {
		return v;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		result = prime * result + ((v == null) ? 0 : v.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		if (v == null) {
			if (other.v != null)
				return false;
		} else if (!v.equals(other.v))
			return false;
		return true;
	}
	/**
	 * function toString
	 */
	@Override
	public String toString() {
		return "Ray [p=" + p + ", v=" + v + "]";
	}
	
    
}