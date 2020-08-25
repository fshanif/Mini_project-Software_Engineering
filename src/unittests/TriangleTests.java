/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author USER
 *
 */
public class TriangleTests {

	/**
	 * Test method for {@link geometries.Triangle#findIntsersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntsersections() {
        // ============ Equivalence Partitions Tests ==============

		//tests the function with ray that  intersects the Triangle
		Triangle t1=new Triangle(new Point3D(0,-2,0),new Point3D(2,0,0),new Point3D(3,-3,0));
		Ray r1=new Ray(new Point3D(0,0,2),new Vector(2,-2,-2));
		ArrayList<Point3D> check=new ArrayList<Point3D>();
		check.add(new Point3D(2,-2,0));
		try
		{
		assertEquals("function findIntsersections() of triangle result is wrong", t1.findIntsersections(r1).get(0).point, check.get(0));
		}
		catch(IllegalArgumentException e)
		{
			
		}
		
		//tests the function with ray that doesn't  intersects the Triangle(against edge)
		Ray r2=new Ray(new Point3D(0,0,2),new Vector(-1,2,-2));
		check.clear();
		try
		{
		assertEquals("function findIntsersections() of triangle result is wrong", t1.findIntsersections(r2), null);
		}
		catch(IllegalArgumentException e)
		{
			
		}

		//tests the function with ray that doesn't  intersects the Triangle(against vertex)
		Ray r3=new Ray(new Point3D(0,0,2),new Vector(2,1,-2));
		check.clear();
		try
		{
		assertEquals("function findIntsersections() of triangle result is wrong", t1.findIntsersections(r3), null);
		}
		catch(IllegalArgumentException e)
		{
					
		}
	    // =============== Boundary Values Tests ==================

		//tests the function with ray that intersects the Triangle on the vertex
		Ray r4=new Ray(new Point3D(0,0,2),new Vector(2,0,-2));
		try
		{
		assertEquals("function findIntsersections() of triangle result is wrong", t1.findIntsersections(r4), null);
		}
		catch(IllegalArgumentException e)
		{
					
		}
		//tests the function with ray that intersects the Triangle on the egde
		Ray r5=new Ray(new Point3D(0,0,2),new Vector(1,-1,-2));
		try
		{
		assertEquals("function findIntsersections() of triangle result is wrong", t1.findIntsersections(r5), null);
		}
		catch(IllegalArgumentException e)
		{
							
		}
		//tests the function with ray that intersects the Triangle on the egde
		Ray r6=new Ray(new Point3D(0,0,2),new Vector(1,3,-2));
		try
		{
		assertEquals("function findIntsersections() of triangle result is wrong", t1.findIntsersections(r6), null);
		}
		catch(IllegalArgumentException e)
		{
							
		}


		

	}

}
