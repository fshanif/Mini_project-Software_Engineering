package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import geometries.Cylinder;
import geometries.Geometries;
import geometries.Intersectable;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class GeometriesTests {

	@Test
	public void testFindIntsersections() {
        // ============ Equivalence Partitions Tests ==============
		
		//tests the function findIntsersections() with ray that has  intersections with some of the geometries
		  List<Point3D> check=new ArrayList<Point3D>();
		  Intersectable s1=new Sphere(new Point3D(0,-1,0),2);
		  Intersectable t1=new Triangle(new Point3D(0,-2,0),new Point3D(2,0,0),new Point3D(3,-3,0));
		  Intersectable c1=new Cylinder(4.0, new Ray(new Point3D(0,1,-2),new Vector(0,0,4)), 1.0);
		  Intersectable p1=new Plane(new Point3D(0,5,0),new Vector(0,0,1));
		  Intersectable pol1=new Polygon(new Point3D(4,0,0),new Point3D(4,-4,0),new Point3D(0,-4,0),new Point3D(-3,0,0));
		  Intersectable tube1= new Tube(new Ray(new Point3D(0,-1,0),new Vector(0,1,0)),1);
       
		Intersectable s2=new Sphere(new Point3D(0,-1,0),3);
		Geometries g2=new Geometries(s2,t1,c1,p1,pol1,tube1);
		check.clear();
		check.add(new Point3D(0,-4,0));
		try {
		 assertEquals("function findIntsersections() is wrong", check, g2.findIntsersections(new Ray(new Point3D(0,-1,-3),new Vector(0,-3,3))));
		}
		catch(IllegalArgumentException e)
		{}
		
        // =============== Boundary Values Tests ==================

		//tests the function findIntsersections() on empty list
		Geometries g=new Geometries();
        Ray r1=new Ray(new Point3D(-1,0,0),new Vector(3,-1,0));
        try {
		assertEquals("function getNormal() of Plane result is wrong", g.findIntsersections(r1), null);
        }
		catch(IllegalArgumentException e)
		{}
		
		//tests the function findIntsersections() with ray that has no intersections with the geometries
		 Geometries g1=new Geometries(s1,t1,c1,p1,pol1,tube1);
        try
        {
       assertEquals("function findIntsersections()  wrong",null, g1.findIntsersections(new Ray(new Point3D(0,0,3),new Vector(0,0,2))));
        }
        catch(IllegalArgumentException e)
		{}
	
		//tests the function findIntsersections() with ray that has  intersections only with one of the geometries
        check.clear();
		check.add(new Point3D(6,0,0));
        assertEquals("function findIntsersections()  is wrong", check.size(), g1.findIntsersections(new Ray(new Point3D(6,0,-1),new Vector(0,0,2))).size());
        
      //tests the function findIntsersections() with ray that has intersections with all the geometries
      		Intersectable s3=new Sphere(new Point3D(0,-1,0),2);
      		Intersectable t2=new Triangle(new Point3D(0,-2,0),new Point3D(2,0,0),new Point3D(-1,-7,0));
      		Intersectable p2=new Plane(new Point3D(0,5,0),new Vector(0,0,1));
      		Intersectable pol2=new Polygon(new Point3D(4,0,0),new Point3D(4,-4,0),new Point3D(-1,-7,0),new Point3D(-3,0,0));
              check.clear();
              check.add(new Point3D(0,-4,0));
      		Geometries g3=new Geometries(s3,t2,c1,p2,pol2,tube1);
              try
              {
              assertEquals("function findIntsersections()  wrong", 3, g3.findIntsersections(new Ray(new Point3D(0,-1,-3),new Vector(0,-3,3))).size());
              }
              catch(IllegalArgumentException e)
      		{}
		

		
		
	}

}
