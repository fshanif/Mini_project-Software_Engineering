/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import elements.Camera;
import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
//import primitives.Ray;
import primitives.Vector;

/**
 * @author USER
 *
 */
public class IntegrationTests {

	@Test
	public void test() {
		
		List<GeoPoint>intersection=new ArrayList<GeoPoint>();
		Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Camera camera2 = new Camera(new Point3D(0,0,-0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
		int count=0;
        
				//tests the amount of intersections when the sphere is big
				Sphere s1=new Sphere(new Point3D(0,0,3),1);
				count=0;
				intersection.clear();
				for (int i = 0; i < 3; i++) 
				{
					for (int j = 0; j < 3; j++) 
					{
						intersection=s1.findIntsersections(camera._constructRayThroughPixel(1,1,3, 3, j, i, 1, 3, 3).get(0));
						if(intersection!=null)
							count+=intersection.size();
					}
				}
				assertEquals("the amount of intersections is not correct ", 2, count);			
				
				//test case number two, intersection with big sphere(18)
				Sphere s2=new Sphere(new Point3D(0,0,2.5),2.5);
				count=0;
				if(intersection!=null)
				{
				    intersection.clear();
				}
				for (int i = 0; i < 3; i++) 
				{
					for (int j = 0; j < 3; j++) 
					{
						intersection=s2.findIntsersections(camera2._constructRayThroughPixel(1,1,3, 3, j, i, 1, 3, 3).get(0));
						if(intersection!=null)
							count+=intersection.size();
					}
				}
				assertEquals("the amount of intersections is not correct ", 18, count);	
				
				//test case number three, 10 intersection with sphere
				Sphere s3=new Sphere(new Point3D(0,0,2),2);
				count=0;
				if(intersection!=null)
				{
				    intersection.clear();
				}
				for (int i = 0; i < 3; i++) 
				{
					for (int j = 0; j < 3; j++) 
					{
						intersection=s3.findIntsersections(camera2._constructRayThroughPixel(1,1,3, 3, j, i, 1, 3, 3).get(0));
						if(intersection!=null)
							count+=intersection.size();
					}
				}
				assertEquals("the amount of intersections is not correct ", 10, count);
				
				//test case number four, 9 intersection with sphere
				Sphere s4=new Sphere(new Point3D(0,0,2),4);
				count=0;
				if(intersection!=null)
				{
				    intersection.clear();
				}
				for (int i = 0; i < 3; i++) 
				{
					for (int j = 0; j < 3; j++) 
					{
						intersection=s4.findIntsersections(camera._constructRayThroughPixel(1,1,3, 3, j, i, 1, 3, 3).get(0));
						if(intersection!=null)
							count+=intersection.size();
					}
				}
				assertEquals("the amount of intersections is not correct ", 9, count);		
				
				//test case number five, 0 intersection with sphere
				Sphere s5=new Sphere(new Point3D(0,0,-1),0.5);
				count=0;
				if(intersection!=null)
				{
				    intersection.clear();
				}
				for (int i = 0; i < 3; i++) 
				{
					for (int j = 0; j < 3; j++) 
					{
						intersection=s5.findIntsersections(camera._constructRayThroughPixel(1,1,3, 3, j, i, 1, 3, 3).get(0));
						if(intersection!=null)
							count+=intersection.size();
					}
				}
				assertEquals("the amount of intersections is not correct ", 0, count);	
				 //triangle tests
				//tests the amount of intersections when the triangle is small
				Triangle t1=new Triangle(new Point3D (0, -1, 2),new Point3D (1, 1, 2),new Point3D  (-1, 1, 2));
				 count=0;
				 if(intersection!=null)
					{
					    intersection.clear();
					}
				 for (int i = 0; i < 3; i++) 
				   {
					for (int j = 0; j < 3; j++) 
					{
						intersection=t1.findIntsersections(camera._constructRayThroughPixel(1,1,3, 3, j, i, 1, 3, 3).get(0));
						if(intersection!=null)
							count+=intersection.size();
					}
				   }
				assertEquals("the amount of intersections is not correct ", 1, count);			
					
				//tests the amount of intersections when the triangle is big
				Triangle t2=new Triangle(new Point3D (0, -20, 2),new Point3D (1, 1, 2),new Point3D  (-1, 1, 2));
				count=0;
				if(intersection!=null)
				{
				    intersection.clear();
				}
				for (int i = 0; i < 3; i++) 
				{
					for (int j = 0; j < 3; j++) 
					{
						intersection=t2.findIntsersections(camera._constructRayThroughPixel(1,1,3, 3, j, i, 1, 3, 3).get(0));
						if(intersection!=null)
							count+=intersection.size();
					}
				}
				assertEquals("the amount of intersections is not correct ", 2, count);			
					
				 // plane tests:
				//tests the amount of intersections when the plane is parallel to the view plane
				
		       Plane p1=new Plane(new Point3D(0,0,4),new Vector(0,0, 1));
		       count=0;
		       if(intersection!=null)
				{
				    intersection.clear();
				}
				for (int i = 0; i < 3; i++) 
				{
					for (int j = 0; j < 3; j++) 
					{
						intersection=p1.findIntsersections(camera._constructRayThroughPixel(1,1,3, 3, j, i, 1, 3, 3).get(0));
						if(intersection!=null)
							count+=intersection.size();
					}
				}
				assertEquals("the amount of intersections is not correct ", 9, count);
				
				//tests the amount of intersections when the plane interacts the view plane
				        count=0;
				        if(intersection!=null)
						{
						    intersection.clear();
						}
				        Plane p2=new Plane(new Point3D(0, -1, 2),new Point3D(1, 1, 3),new Point3D(-1,1,3));
						for (int i = 0; i < 3; i++) 
						{
							for (int j = 0; j < 3; j++) 
							{
								intersection=p2.findIntsersections(camera._constructRayThroughPixel(1,1,3, 3, j, i, 1, 3, 3).get(0));
								if(intersection!=null)
									count+=intersection.size();
							}
						}
						assertEquals("the amount of intersections is not correct ", 9, count);	
						
						
				      //tests the amount of intersections when the plane interacts the view plane
						count=0;
						if(intersection!=null)
						{
						    intersection.clear();
						}
						Plane p3=new Plane(new Point3D(0, -1, 2),new Point3D(1, 1, 3000),new Point3D(-1,1,3000));
						for (int i = 0; i < 3; i++) 
						{
							for (int j = 0; j < 3; j++) 
							{
								intersection=p3.findIntsersections(camera._constructRayThroughPixel(1,1,3, 3, j, i, 1, 3, 3).get(0));
								if(intersection!=null)
									count+=intersection.size();
							}
						}
						assertEquals("the amount of intersections is not correct ", 6, count);			
							
		         
	}

}
