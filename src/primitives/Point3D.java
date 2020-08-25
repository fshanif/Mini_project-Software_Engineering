/**
 * 
 */
package primitives;


/**
 * Class Point3D is the basic class representing a point wich contains three coordinate of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * the point contains three coordinate-pOne,pSecond,pThird
 * @author Shani Fluk and Elina Hen Chulpaev 
*/

public class Point3D {

	
public static Point3D ZERO=new Point3D(0,0,0);


private Coordinate pOne;
private Coordinate pSecond;
private Coordinate pThird;
	/**
	 * constructor with parameters
	 */
	public Point3D(Coordinate p1,Coordinate p2,Coordinate p3) {
		// TODO Auto-generated constructor stub
		pOne=p1;
		pSecond=p2;
		pThird=p3;
	}
	/**
	 * constructor that receives three double type points
	 * @param d1-coordinate
	 * @param d2-coordinate
	 * @param d3-coordinate
	 */
	public Point3D(double d1,double d2,double d3)
	{
		
		pOne=new Coordinate (d1);
		pSecond=new Coordinate (d2);
		pThird=new Coordinate (d3);

	}
	/**
	 * copy constructor
	 * @param p
	 */
	public Point3D(Point3D p)
	{
		pOne=p.pOne;
		pSecond=p.pSecond;
		pThird=p.pThird;
	}
	/**
	 * gets
	 * @return
	 */
	public Coordinate getpOne() {
		return pOne;
	}
	
	public Coordinate getpSecond() {
		return pSecond;
	}
	
	public Coordinate getpThird() {
		return pThird;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pOne == null) ? 0 : pOne.hashCode());
		result = prime * result + ((pSecond == null) ? 0 : pSecond.hashCode());
		result = prime * result + ((pThird == null) ? 0 : pThird.hashCode());
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
		Point3D other = (Point3D) obj;
		if (pOne == null) {
			if (other.pOne != null)
				return false;
		} else if (!pOne.equals(other.pOne))
			return false;
		if (pSecond == null) {
			if (other.pSecond != null)
				return false;
		} else if (!pSecond.equals(other.pSecond))
			return false;
		if (pThird == null) {
			if (other.pThird != null)
				return false;
		} else if (!pThird.equals(other.pThird))
			return false;
		return true;
	}
	/**
	 * toString
	 */
	@Override
	public String toString()
	{
		return "Coordinate :"+pOne.toString()+"Coordinate :"+pSecond.toString()+"Coordinate :"+pThird.toString();
	}
	/**
	 * Gets a second point in the parameter, returns a vector from the second point to the point at which the action is performed
	 * @param p2
	 * @return
	 */
    public Vector subtract(Point3D p2)
    {
    	Point3D _p=new Point3D(pOne._coord-p2.pOne._coord,pSecond._coord-p2.pSecond._coord,pThird._coord-p2.pThird._coord);
    	Vector v=new Vector(_p);
    	return v;
    }
    /**
     * function that adds a vector to the point at which the action is performed 
     * @param _v
     * @return
     */
    public Point3D add(Vector _v)
    {
    	return (new Point3D(pOne._coord+_v.getP().pOne._coord,pSecond._coord+_v.getP().pSecond._coord,pThird._coord+_v.getP().pThird._coord));
    }
    /**
     * function that calculate the squared distance between two points 
     * @param p2
     * @return
     */
    public double distanceSquared(Point3D p2)
    {
    	double distance=(pOne._coord-p2.pOne._coord)*(pOne._coord-p2.pOne._coord)+(pSecond._coord-p2.pSecond._coord)*(pSecond._coord-p2.pSecond._coord)+(pThird._coord-p2.pThird._coord)*(pThird._coord-p2.pThird._coord);
    	return distance;
    }
    /**
     * function that calculate the distance between two points 
     * @param p2
     * @return
     */
    public double distance(Point3D p2)
    {
    	return Math.sqrt(distanceSquared(p2));
    }
}