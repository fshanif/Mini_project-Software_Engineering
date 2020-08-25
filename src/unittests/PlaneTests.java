/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import geometries.Plane;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author USER
 *
 */
public class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	
	@Test
	public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //test the function calculation of normal vector for plane
		Plane p2= new Plane(new Point3D(1,0,-1),new Point3D(2,1,-2),new Point3D(-3,5,3));
		//Vector v1=new Vector(new Point3D(1,0,-1).subtract(new Point3D(2,1,-2)));
		//Vector v2=new Vector(new Point3D(-3,5,3).subtract(new Point3D(2,1,-2)));
		
		try
		{
     		//assertEquals("function getNormal() of Plane result is wrong", new Vector(-1/Math.sqrt(2),0,-1/Math.sqrt(2)).normalize(),p2.getNormal(new Point3D(1,-5,-1)) );//(1,0,1)
     		assertEquals("function getNormal() of Plane result is wrong", new Vector(9,0,9).normalize(),p2.getNormal(new Point3D(1,-5,-1)) );//(1,0,1)

		} catch (IllegalArgumentException e) {}
		
	}
	/**
	 * Test method for {@link geometries.Plane#findIntsersections()}.
	 */
	@Test
	public void testFindIntsersections() {
        // ============ Equivalence Partitions Tests ==============
		
	    //tests the function with ray that  intersects the plane
		Plane p1=new Plane(new Point3D(0,5,0),new Vector(1,0,0));
		Ray r=new Ray(new Point3D(3,0,0),new Vector(-3,0,3));
		List<Point3D> check=new ArrayList<Point3D>();
		check.add(new Point3D(0,0,3) );
		try {
		assertEquals("function findIntsersections() of Plane result is wrong",new Point3D(0,0,3) , p1.findIntsersections(r).get(0).point);
		}
		catch(IllegalArgumentException e)
		{
			
		}
		
		//tests the function with ray that doesn't  intersects the plane
		Ray r1=new Ray(new Point3D(2,0,6),new Vector(0,0,1));
		try {
			assertEquals("function findIntsersections() of Plane result is wrong", p1.findIntsersections(r1), null);
			}
			catch(IllegalArgumentException e)
			{
				
			}
		check.clear();
	    // =============== Boundary Values Tests ==================

		//tests the function with ray that is parallel to the plane the ray is not included in the plane
		Ray r2=new Ray(new Point3D(1,1,1),new Vector(2,0,0));
		assertEquals("function findIntsersections() of Plane result is wrong", p1.findIntsersections(r2), null);
		
		//tests the function with ray that is parallel to the plane the ray is included in the plane
		Ray r3=new Ray(new Point3D(1,0,0),new Vector(1,0,0));
		assertEquals("function findIntsersections() of Plane result is wrong", p1.findIntsersections(r3), null);
		//Ray is orthogonal to the plane
		
		//test with ray that is before the plane 
		Ray r4=new Ray(new Point3D(1,0,2),new Vector(0,0,-1));
	    check=null;
	    try {
		assertEquals("function findIntsersections() of Plane result is wrong",check, p1.findIntsersections(r4));
		}
		catch(IllegalArgumentException e)
		{
			
		}
		
		//test with ray that is in the plane 
		Ray r5=new Ray(new Point3D(1,0,0),new Vector(0,0,-1));
		try 
		{
		assertEquals("function findIntsersections() of Plane result is wrong", p1.findIntsersections(r5), null);
		}
		catch(IllegalArgumentException e)
		{
					
		}
		
		//test with ray that is out of the plane 
		Ray r6=new Ray(new Point3D(1,0,-1),new Vector(0,0,-1));
		try 
		{
		assertEquals("function findIntsersections() of Plane result is wrong", p1.findIntsersections(r6), null);
		}
		catch(IllegalArgumentException e)
		{
					
		}
		
		
		//test with ray that is neither orthogonal nor parallel to and begins at the plane 

		Ray r7=new Ray(new Point3D(1,-2,0),new Vector(1,1,3));try 
		{
		assertEquals("function findIntsersections() of Plane result is wrong", p1.findIntsersections(r7), null);
		}
		catch(IllegalArgumentException e)
		{
					
		}
		//test with ray that is neither orthogonal nor parallel to the plane and begins in
		//the same point which appears as reference point in the plane 
		
		Ray r8=new Ray(p1.getP(),new Vector(1,1,3));
		try 
		{
		assertEquals("function findIntsersections() of Plane result is wrong", p1.findIntsersections(r8), null);
		}
		catch(IllegalArgumentException e)
		{
					
		}
	}
	 

}
