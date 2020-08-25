/**
 * 
 */
package unittests;

import org.junit.Test;

import elements.*;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 *
 */
public class ReflectionRefractionTests {

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
		scene.set_numOfRays(1);


		scene.addGeometries(
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0), 50,
						new Point3D(0, 0, 50)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), 25, new Point3D(0, 0, 50)));

		scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), 1,0.0004, 0.0000006, new Vector(-1, 1, 2)));

		ImageWriter imageWriter = new ImageWriter("twoSpheresAfter1", 150, 150, 500, 500);
		Render render = new Render(imageWriter, scene);

		render._renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(10000);
		scene.setBackground(Color.BLACK);
		scene.set_numOfRays(1);

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.addGeometries(
				new Sphere(new Color(0, 0, 100), new Material(0.25, 0.25, 20, 0.5, 0), 400, new Point3D(-950, 900, 1000)),
				new Sphere(new Color(100, 20, 20), new Material(0.25, 0.25, 20), 200, new Point3D(-950, 900, 1000)),
				new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1500, 1500, 1500),
						new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
				new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 0.5), new Point3D(1500, 1500, 1500),
						new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000))
				);

		scene.addLights(new SpotLight(new Color(1020, 400, 400),  new Point3D(-750, 750, 150), 1, 0.00001, 0.000005,new Vector(-1, 1, 4)));

		ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored", 2500, 2500, 500, 500);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render._renderImage();
		render.writeToImage();
	}
	
	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
	 *  producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		scene.set_numOfRays(1);


		scene.addGeometries( //
				new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
				new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
						30, new Point3D(60, -50, 50)));

		scene.addLights(new SpotLight(new Color(700, 400, 400),new Point3D(60, -50, 0), 1, 4E-5, 2E-7, new Vector(0, 0, 1)));

		ImageWriter imageWriter = new ImageWriter("shadow with transparency", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.impov2_renderImage();
		render.writeToImage();
	}
	/**
	 * Produce a picture of a three spheres and triangle an d plane lighted by a spot light 
	 */
	@Test
	public void picture() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(-1000, -5, 0), new Vector(1, 0, 0), new Vector(0, 0, 1)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		scene.set_numOfRays(50);

		
		scene.addGeometries( //
				new Triangle(new Color(51,25,0), new Material(0.5, 0.5, 60), //
						new Point3D(0,50,25), new Point3D(-70,0,40), new Point3D(0,-60,25)), //
		
				new Sphere(new Color(0,102,0), new Material(0.4, 0.4, 0, 0.3, 0.2), // )
						20, new Point3D(0,-3,0)),
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.4, 0, 0.2, 0.2), // )
						15, new Point3D(0,-20,0)),
				new Sphere( new Color(76,0,153), new Material(0.4, 0.4, 0, 0, 0.2), // )
					    10, new Point3D(0,-35,0))
				,new Plane( Color.BLACK, new Material(0.4, 0.4, 0, 0, 1),new Point3D(1,0,-55), new Point3D(0,1,-55), new Point3D(0,0,-55)));

		scene.addLights(new SpotLight(new Color(700, 400, 400),new Point3D(-10000,0,0), 1, 4E-5, 2E-7, new Vector(1, 0, 0)),new SpotLight(new Color(700, 400, 400),new Point3D(0,-20,0), 1, 4E-5, 2E-7, new Vector(-1000, 15, 0)),new PointLight(new Color(500, 300, 0), new Point3D(0, 10, 300), 1, 0.00001, 0.000001));

		ImageWriter imageWriter = new ImageWriter("ourPicafter1", 100, 100, 500, 500);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render._renderImage();
		render.writeToImage();
	

	}
	@Test
	public void picture1() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(-1000, -5, 0), new Vector(1, 0, 0), new Vector(0, 0, 1)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		scene.set_numOfRays(50);

		
		scene.addGeometries( //
				new Triangle(new Color(51,25,0), new Material(0.5, 0.5, 60), //
						new Point3D(0,50,25), new Point3D(-70,0,40), new Point3D(0,-60,25)), //
		
				new Sphere(new Color(0,102,0), new Material(0.4, 0.4, 0, 0.3, 0.2), // )
						20, new Point3D(0,-3,0)),
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.4, 0, 0.2, 0.2), // )
						15, new Point3D(0,-20,0)),
				new Sphere( new Color(76,0,153), new Material(0.4, 0.4, 0, 0, 0.2), // )
					    10, new Point3D(0,-35,0))
				,new Plane( Color.BLACK, new Material(0.4, 0.4, 0, 0, 1),new Point3D(1,0,-55), new Point3D(0,1,-55), new Point3D(0,0,-55)));

		scene.addLights(new SpotLight(new Color(700, 400, 400),new Point3D(-10000,0,0), 1, 4E-5, 2E-7, new Vector(1, 0, 0)),new SpotLight(new Color(700, 400, 400),new Point3D(0,-20,0), 1, 4E-5, 2E-7, new Vector(-1000, 15, 0)),new PointLight(new Color(500, 300, 0), new Point3D(0, 10, 300), 1, 0.00001, 0.000001));

		ImageWriter imageWriter = new ImageWriter("ourPicafter", 100, 100, 500, 500);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.impov2_renderImage();
		render.writeToImage();
	}
}

