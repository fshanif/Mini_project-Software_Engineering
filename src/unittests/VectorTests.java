/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;



/**
 * Unit tests for primitives.Vector class
 * @author Elina and Shani
 */

public class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	Vector v1=new Vector(1,2,3);
	Vector v2 = new Vector(-2, -4, -6);
	@Test
	public void testAdd() {
		
        // ============ Equivalence Partitions Tests ==============
		
		//test that the function add calculate properly
	    assertEquals("function add() result is wrong ", v1.add(v2),new Vector(-1,-2,-3));
	  
        
	    // =============== Boundary Values Tests ==================
        try {
            //test that the function add calculates properly with the vector zero
	      assertEquals("function Add() result is wrong ", new Vector(Point3D.ZERO).add(v1), v1);
            fail("testAdd() This function should throw an exception, and it failed");
        } catch (Exception e) {}

	  
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() 
	{
		
		
        // ============ Equivalence Partitions Tests ==============
		Vector _v=new Vector(3,6,9);
		//tests that the function subtract calculate properly
	    assertEquals("function subtract() result is wrong ",_v , v1.subtract(v2));
	      
	    // =============== Boundary Values Tests ==================
	   
	    try {
	    	 //tests that he function subtract calculate properly with the vector zero
		    assertEquals("function subtract() result is wrong ",new Vector(2,4,6) ,new Vector(Point3D.ZERO).subtract(v2));
	    	fail("testSubtract() This function should throw an exception, and it failed");
        } catch (Exception e) {}

	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
        // ============ Equivalence Partitions Tests ==============
		

        // Test that length of cross-product is proper 
		assertEquals("function scale result is wrong ",(3)*v1.length(), (v1.scale(3)).length(),0.0001);
		
		//test that the function scale calculates properly
		assertEquals("function scale result is wrong ",new Vector(3,6,9), v1.scale(3));
		
	    // =============== Boundary Values Tests ==================

		//test that the function scale calculates properly with negative numbers
		
		assertEquals("function scale result is wrong ",new Vector(-3,-6,-9), v1.scale(-3));
		
		 try {
			//test that the function scale calculates properly with the vector zero
				
				assertEquals("function scale result is wrong ",new Vector(Point3D.ZERO), v1.scale(0));

	    		fail("testScale() This function should throw an exception, and it failed");
        } catch (Exception e) {}
	
		
		
			}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		// ============ Equivalence Partitions Tests ==============
		
		//test that the function dotProduct calculate properly
		assertEquals("function dotProduct result is wrong ",-28, v1.dotProduct(v2),0.0001);
		
		//Test for dot product function with vectors of the same direction
		assertEquals("function dotProduct result is wrong ",42,( new Vector(3,6,9)).dotProduct(v1),0.0001);
		
		//test for dot product function with vectors with Sharp Angle Vectors
		assertEquals("function dotProduct result is wrong ",10,( new Vector(1,2,3)).dotProduct(new Vector(2,1,2)),0.0001);
		
		//test for dot product function with vectors with blunt Angle Vectors
		assertEquals("function dotProduct result is wrong ",-10,( new Vector(1,2,3)).dotProduct(new Vector(-2,-1,-2)),0.0001);
		
		//test for dot product function with vectors without the same direction
		assertEquals("function dotProduct result is wrong ",-28,( new Vector(1,2,3)).dotProduct(new Vector(-2,-4,-6)),0.0001);

		
	    // =============== Boundary Values Tests ==================

		//tests that orthogonal vectors their dot vector is zero
		assertEquals("dot product of orthogonal vectors is not zero  ",0, v1.dotProduct(new Vector(-2,1,0)),0.0001);
		
		 try {
			   //test that dot product of vector and zero vector is zero
				assertEquals("dot product function is wrong  ",0, v1.dotProduct(new Vector(Point3D.ZERO)),0.0001);
		    	fail("testScale() This function should throw an exception, and it failed");
	         } 
		 catch (Exception e) {}
		
		
    	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertEquals("crossProduct() result is not orthogonal to 1st operand", vr.dotProduct(v1),0,0.0001);
        assertEquals("crossProduct() result is not orthogonal to 2nd operand", vr.dotProduct(v3),0,0.0001);
        
    	
		
		//test for cross product function with vectors with Sharp Angle Vectors
		assertEquals("function crossProduct result is wrong ",new Vector(1,4,-3),( new Vector(1,2,3)).crossProduct(new Vector(2,1,2)));
		
		//test for cross product function with vectors with blunt Angle Vectors
		assertEquals("function crossProduct result is wrong ",new Vector(-1,-4,3),( new Vector(1,2,3)).crossProduct(new Vector(-2,-1,-2)));
		
		
        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
        	//test that the function cross product calculates correctly with zero vector
    		assertEquals("function crossProduct result is wrong ",new Vector(Point3D.ZERO),v1.crossProduct(new Vector(Point3D.ZERO)));
            v1.crossProduct(v2);
            
            //test for cross product function with vectors without the same direction
		    assertEquals("function crossProduct result is wrong ",new Vector(Point3D.ZERO),( new Vector(1,2,3)).crossProduct(new Vector(-2,-4,-6)));

            //Test for cross product function with vectors of the same direction
            assertEquals("function crossProduct result is wrong ",new Vector(Point3D.ZERO),( new Vector(3,6,9)).crossProduct(v1));
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}

	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
		//test that the function lengthSquared() calculates properly
        assertEquals("function lengthSquared() result is wrong ",v1.lengthSquared(),14,0.0001);

        // =============== Boundary Values Tests ==================
        
        //test that the function lengthSquared() calculates properly negative vector
        assertEquals("function lengthSquared() result is wrong ",v2.lengthSquared(),56,0.0001);
        try
        {
        	
        //test that the function lengthSquared() calculates properly zero vector
        assertEquals("function lengthSquared() result is wrong ",new Vector(Point3D.ZERO).lengthSquared(),0,0.0001);
        fail( "lengthSquared() for vectors does not throw an exception");
        }
        catch (Exception e) {}
        
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
        // ============ Equivalence Partitions Tests ==============
		
		//test that the function length() calculates properly
        assertEquals("function length() result is wrong ",v1.length(),Math.sqrt(14),0.0001);

		
		
        // =============== Boundary Values Tests ==================
        //test that the function length() calculates properly negative vector
        assertEquals("function length() result is wrong ",v2.length(),Math.sqrt(56),0.0001);
		
		 try
	        {
			    //test that the function length() calculates properly zero vector
		        assertEquals("function length() result is wrong ",new Vector(Point3D.ZERO).length(),0,0.0001);
		        
	        	fail( "length() for parallel vectors does not throw an exception");
	        }
	        catch (Exception e) {}
		
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
        // ============ Equivalence Partitions Tests ==============

	    //test that the function normalize() calculates properly
        assertEquals("function normalize() result is wrong ",v1.normalize(),new Vector(1/Math.sqrt(14),2/Math.sqrt(14),3/Math.sqrt(14)));

	    //test that the function normalize() calculates properly by checking the length of the normalized vector
        assertEquals("function normalize() result is wrong ",v1.normalize().length(),1,0.0001);
        
        

		
        // =============== Boundary Values Tests ==================
	    //test that the function normalize() calculates properly with negative vector
        assertEquals("function normalize() result is wrong ",v2.normalize(),new Vector((-2)/Math.sqrt(56),(-4)/Math.sqrt(56),(-6)/Math.sqrt(56)));
        try
        {
		    //test that the function normalize() calculates properly zero vector
        	 assertEquals("function  normalize() result is wrong ",(new Vector(Point3D.ZERO)).normalize(),0);
	        
        	fail("ERROR: normalize() for vector does not throw an exception" );
        }
        catch (Exception e) {}

	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		 // ============ Equivalence Partitions Tests ==============

	    //test that the function normalized() calculates properly
        assertEquals("function normalized() result is wrong ",v1.normalized(),new Vector(1/Math.sqrt(14),2/Math.sqrt(14),3/Math.sqrt(14)));

	    //test that the function normalized() calculates properly by checking the length of the normalized vector
        assertEquals("function normalized() result is wrong ",v1.normalized().length(),1,0.0001);
        
        

		
        // =============== Boundary Values Tests ==================
	    //test that the function normalized() calculates properly with negative vector
        assertEquals("function normalized() result is wrong ",v2.normalized(),new Vector((-2)/Math.sqrt(56),(-4)/Math.sqrt(56),(-6)/Math.sqrt(56)));
        
        Vector _v=v1.normalized();
        //tests that the function normalized() doesn't change the vector 
        assertTrue("function normalized() result is wrong ",!(_v.equals(v1)));

        try
        {
		    //test that the function normalized() calculates properly zero vector
        	 assertEquals("function  normalized() result is wrong ",(new Vector(Point3D.ZERO)).normalized(),0);
	        
        	fail("ERROR: normalized()  vector does not throw an exception" );
        }
        catch (Exception e) {}

	
	}

}
