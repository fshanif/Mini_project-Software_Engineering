/**
 * 
 */
package primitives;

/**
 * Class Vector is the basic class representing a vector wich contains point3D of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Shani Fluk and Elina Hen Chulpaev
 * @param p - starting points
*/
public class Vector {
	
	
   private Point3D p;
	/**
	 * constructor with parameter(Point3D)
	 */
	public Vector(Point3D _p) throws IllegalArgumentException  {
		//try {
			if(_p.equals(Point3D.ZERO)) {//new Point3D(0,0,0)

				throw new IllegalArgumentException("cant contain the vector (0,0,0)") ;
			}
			else
				p=new Point3D(_p);
		//	}
			//catch(IllegalArgumentException e)
			//{
			//	System.out.println(e);
			//}
	}
	
	
	
	/**
	 * constructor with parameters(doubles)
	 */
	public Vector(double d1,double d2,double d3)throws IllegalArgumentException {
		p=new Point3D(new Coordinate (d1),new Coordinate (d2),new Coordinate (d3));


			if(p.equals(Point3D.ZERO))// {//new Point3D(0,0,0)
		
				throw new IllegalArgumentException("cant contain the vector (0,0,0)") ;

	}
	/**
	 * constructor with parameters
	 * @param p1- coordinate
	 * @param p2-coordinate
	 * @param p3-coordinate
	 * @throws IllegalArgumentException
	 */
	public Vector(Coordinate p1,Coordinate p2,Coordinate p3) throws IllegalArgumentException  {
		// TODO Auto-generated constructor stub
		Point3D helper=new Point3D(p1,p2,p3);
	
			if(helper.equals(Point3D.ZERO)) //new Point3D(0,0,0)
				throw new IllegalArgumentException("cant contain the vector (0,0,0)") ;
			
			else
				p=helper;
			
			
	}
	/**
	 * copy constructor
	 * @param _v
	 */
	public Vector(Vector _v) {
		// TODO Auto-generated constructor stub
		    p=new Point3D(_v.p);
		
		}
	/**
	 * get function
	 * @return
	 */
	public Point3D getP() {
		return p;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p == null) ? 0 : p.hashCode());
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
		Vector other = (Vector) obj;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		return true;
	}
	

	/**
	     * function that adds vector to the vector at which the action is performed 
	     * @param  Vector
	     * @return Vector
	 */
	 public Vector add(Vector _v)
	    {
		 return new Vector(p.add(_v));
	    }
    /**
	 * function toString 
	 */
	 @Override
	public String toString() {
		return "Vector [p=" + p + "]";
	}

	/**
		 * function subtract two Vectors 
		 * @param  Vector
	     * @return Vector
      */
	 public Vector subtract(Vector _v)	 
	 {//if(_v==this)
	//	 return new Vector(new Point)
		 return new Vector(p.subtract(_v.p));
	 }
	 
	 /**
		 * function scale  Vector with a parameter x 
		 * @param double
	     * @return Vector
      */
	 public Vector scale(double x)	 
	 {
		 return new Vector(x*(p.getpOne().get()),x*(p.getpSecond().get()),x*(p.getpThird().get()));
	 }
	 /**
		 * function dotProduct  Vector with another vector 
		 * @param Vector _v
	     * @return Vector
     */
	 public double dotProduct (Vector v)	 
	 {
		 return (v.p.getpOne().get())*(p.getpOne().get())+(v.p.getpSecond().get())*(p.getpSecond().get())+(v.p.getpThird().get())*(p.getpThird().get());
	 }
	 /**
		 * function crossProduct  Vector with another vector 
		 * @param Vector _v
	     * @return Vector
     */
	 public Vector crossProduct (Vector v)	 
	 {
		 double i=(p.getpSecond().get())*(v.p.getpThird().get())-((p.getpThird().get())*v.p.getpSecond().get());
		 double j=((-1)*(p.getpOne().get()*(v.p.getpThird().get()))+(p.getpThird().get()*v.p.getpOne().get()));
		 double k=(p.getpOne().get()*v.p.getpSecond().get())-(p.getpSecond().get()*v.p.getpOne().get());

		 return new Vector(i,j,k);
	 }
	 /**
		 * function lengthSquared  Vector with another vector 
		 * @param Vector _v
 	     * @return double
     */
	 public double lengthSquared  ()	 
	 {
		 return p.distanceSquared(Point3D.ZERO) ;
	 }
	 /**
		 * function length  Vector with another vector 
		 * @param Vector _v
	     * @return double
     */
	 public double length ()	 
	 {
		 return p.distance(Point3D.ZERO) ;
	 }
	 
	 
	 /**
		 * function normalize  Vector with another vector 
	     * @return Vector
     */
	 public Vector normalize  ()	 
	 {
		 double helper=(1.0/(p.distance(Point3D.ZERO)));
		 this.p= scale(helper).p;
		 return this ;
	 }
	 /**
		 * function normalized  Vector with another vector 
	     * @return Vector
      */
	 public Vector normalized ()	 
	 {
		 Vector v=new Vector(this);
		 return v.normalize() ;
	 }
	 
	 
}
