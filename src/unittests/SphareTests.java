/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import geometries.Sphere;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author USER
 *
 */
public class SphareTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(geometries.Sphere)}.
	 */
	@Test
	public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        //test the function calculation of normal vector for Sphare
		Point3D c=new Point3D(1,2,3);
		Point3D p1=new Point3D(1,2,8);
		Sphere s=new Sphere(c,5);
		try
		{
			assertEquals("function getNormal() of Sphere result is wrong", (p1.subtract(c)).normalize(), s.getNormal(p1));
			
		} catch (IllegalArgumentException e) {}
		
		Sphere s2=new Sphere(new Point3D(3,6,2),4.2313);
		Point3D p2=new Point3D(3,6,1);
		Vector v2=new Vector(p2.subtract(s2.getCenter()));
		try
		{
			assertEquals(v2,s2.getNormal(p2) );
			
		} catch (IllegalArgumentException e) {}
		
		
	}
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals("Ray's line out of sphere", null,
                        sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> points = new ArrayList<>();
        List<Point3D> points2 = new ArrayList<>();

        List<GeoPoint> result = sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0),
                                                                new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        for (Intersectable.GeoPoint geo : result) 
        	points.add(geo.point);
        if ((result.get(0).point.getpOne()).get()-   result.get(1).point.getpOne().get()>0)
        { 
        	result = List.of(result.get(1), result.get(0));
        	points = List.of(points.get(1), points.get(0));
        }
        
        assertEquals("Ray crosses sphere", List.of(p1, p2), points);

        // TC03: Ray starts inside the sphere (1 point)
        
        Sphere s1=new Sphere(new Point3D(0,-1,0),2);
        Ray r1=new Ray(new Point3D(-1,0,0),new Vector(3,-1,0));
        List<Intersectable.GeoPoint> result03=s1.findIntsersections(r1);
        for (Intersectable.GeoPoint geo : result03) {
        	points2.add(geo.point);
        }
        List<Point3D>check=new ArrayList<Point3D>();
        check.add(new Point3D(2,-1,0));
        assertEquals("function findIntsersections() of sphare result is wrong", points2, check);

        
        // TC04: Ray starts after the sphere (0 points)
        
        Ray r2=new Ray(new Point3D(-3,0,0),new Vector(3,2,0));
        assertEquals("function findIntsersections() of sphare result is wrong", s1.findIntsersections(r2), null);

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        Ray r3=new Ray(new Point3D(0,-3,0),new Vector(0,2,2));
        check.clear();
        check.add(new Point3D(0,-1,2));
        try
		{
		     assertEquals("function findIntsersections() of sphare result is wrong", s1.findIntsersections(r3), check);

		} catch (IllegalArgumentException e) {}
		

        // TC12: Ray starts at sphere and goes outside (0 points)
        try
		{
        Ray r4=new Ray(new Point3D(0,-3,0),new Vector(5,-2,6));
	     assertEquals("function findIntsersections() of sphare result is wrong", s1.findIntsersections(r4), null);
		} catch (IllegalArgumentException e) {}

        
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        try
		{
        Ray r5=new Ray(new Point3D(0,-4,0),new Vector(0,7,0));
        check.clear();
        check.add(new Point3D(0,1,0));
        check.add(new Point3D(0,-3,0));
        List<Intersectable.GeoPoint> result13=s1.findIntsersections(r5);
	    if(points2!=null)
	    	points2.clear();
	    for (Intersectable.GeoPoint geo : result13) {
            points2.add(geo.point);
        }
        assertEquals("function findIntsersections() of sphare result is wrong", points2, check);
		} catch (IllegalArgumentException e) {}
        // TC14: Ray starts at sphere and goes inside (1 points)
        Ray r6=new Ray(new Point3D(0,1,0),new Vector(0,-4,0));
        check.clear();
        check.add(new Point3D(0,-3,0));
        try
		{
		     assertEquals("function findIntsersections() of sphare result is wrong", s1.findIntsersections(r6), check);

		} catch (IllegalArgumentException e) {}
		
        // TC15: Ray starts inside (1 points)
        Ray r7=new Ray(new Point3D(0,-2,0),new Vector(0,4,0));
        List<Intersectable.GeoPoint> result15=s1.findIntsersections(r7);
        if(points2!=null)
	    	points2.clear();
	    for (Intersectable.GeoPoint geo : result15) {
            points2.add(geo.point);
        }
        check.clear();
        check.add(new Point3D(0,1,0));
        try
		{
		     assertEquals("function findIntsersections() of sphare result is wrong", points2, check);

		} catch (IllegalArgumentException e) {}
		
        // TC16: Ray starts at the center (1 points)
       try
		{
        Ray r8=new Ray(new Point3D(0,-1,0),new Vector(0,2,0));
        check.clear();
        check.add(new Point3D(0,1,0));
        List<Intersectable.GeoPoint> result16=s1.findIntsersections(r8);
        if(points2!=null)
	    	points2.clear();
	    for (Intersectable.GeoPoint geo : result16) {
            points2.add(geo.point);
        }
		assertEquals("function findIntsersections() of sphare result is wrong", points2, check);

		} catch (IllegalArgumentException e) {}
		
        // TC17: Ray starts at sphere and goes outside (0 points)
       try
		{
       Ray r9=new Ray(new Point3D(0,1,0),new Vector(0,1,0));
       check.clear();
		assertEquals("function findIntsersections() of sphare result is wrong", s1.findIntsersections(r9), null);

		} catch (IllegalArgumentException e) {}
		
        // TC18: Ray starts after sphere (0 points)
       
       try
		{
         Ray r10=new Ray(new Point3D(0,2,0),new Vector(0,1,0));
         check.clear();
		 assertEquals("function findIntsersections() of sphare result is wrong", s1.findIntsersections(r10), null);

		} catch (IllegalArgumentException e) {}
		
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
       try
		{
        Ray r11=new Ray(new Point3D(-1,1,0),new Vector(3,0,0));
		 assertEquals("function findIntsersections() of sphare result is wrong", s1.findIntsersections(r11), null);

		} catch (IllegalArgumentException e) {}
		
        // TC20: Ray starts at the tangent point
       try
		{
       Ray r12=new Ray(new Point3D(0,1,0),new Vector(2,0,0));
		 assertEquals("function findIntsersections() of sphare result is wrong", s1.findIntsersections(r12), null);

		} catch (IllegalArgumentException e) {}
		
        // TC21: Ray starts after the tangent point
       try
		{
         Ray r13=new Ray(new Point3D(1,1,0),new Vector(1,0,0));
		 assertEquals("function findIntsersections() of sphare result is wrong", s1.findIntsersections(r13), null);

		} catch (IllegalArgumentException e) {}
		
        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
       try
		{
        Ray r14=new Ray(new Point3D(0,2,0),new Vector(2,0,0));
		 assertEquals("function findIntsersections() of sphare result is wrong", s1.findIntsersections(r14), null);

		} catch (IllegalArgumentException e) {}
		

    }



}
