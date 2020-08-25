package unittests;


import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class ImprovementNumberOne {



	@Test
	//in order to see the improvement we need to change the number of rays if we don't want to have the improvement the number of rays equal to one 
	public void finalMini1() {
		Scene scene = new Scene("Test scene");
		//scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));

		scene.setCamera(new Camera(new Point3D(-1000, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, 1)));
		scene.setDistance(1000);
		scene.setBackground(new Color(0,0,102));
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		scene.set_numOfRays(50);

		
		scene.addGeometries( //
				
				new Sphere(Color.WHITE, new Material(0.25, 0.25, 20, 0.5, 0), // )
						33, new Point3D(0,0,7)),
				new Sphere(Color.WHITE, new Material(0.25, 0.25, 20, 0.5, 0), // )
						20, new Point3D(0,0,57))
				,new Sphere(Color.WHITE, new Material(0, 0, 0, 0, 0), // )
						0.6, new Point3D(-55,6,60))
				,new Sphere(Color.WHITE, new Material(0, 0, 0, 0, 0), // )
						0.6, new Point3D(-55,4,60))
				,new Sphere(Color.WHITE, new Material(0, 0, 0, 0, 0), // )
						0.6, new Point3D(-55,5,58.8))
				,new Sphere(Color.WHITE, new Material(0, 0, 0, 0, 0), // )
						0.6, new Point3D(-55,-6,60))
				,new Sphere(Color.WHITE, new Material(0, 0, 0, 0, 0), // )
						0.6, new Point3D(-55,-4,60))
				,new Sphere(Color.WHITE, new Material(0, 0, 0, 0, 0), // )
						0.6, new Point3D(-55,-5,58.8))
				,new Sphere(Color.BLACK, new Material(0, 0, 0, 0, 0), // )
						3, new Point3D(-50,5,60))
				,new Sphere(Color.BLACK, new Material(0, 0, 0, 0, 0), // )
						3, new Point3D(-50,-5,60))
				,new Plane(new Color(0, 255, 255), new Material(0, 0, 0, 0, 1),new Point3D(38,0,-30), new Point3D(10,12,-30), new Point3D(0,0,-30)),
				new Triangle(new Color(102,51,0), new Material(0, 0, 20, 0, 0), new Point3D(-20,-5,57), new Point3D(-20,5,57) ,  new Point3D(-20,0,46))
				,new Polygon(new Color(76,0,153),new Material(0, 0, 20, 0, 0),new Point3D(-20,-12,37),new Point3D(-20,-12,40),new Point3D(-20,12,40),new Point3D(-20,12,37) ),
				new Polygon(new Color(java.awt.Color.BLUE),new Material(0, 0, 20, 0, 0),new Point3D(-34,-4,22),new Point3D(-34,-2,26),new Point3D(-34,2,26),new Point3D(-34,4,22),new Point3D(-34,2,18),new Point3D(-34,-2,18) ),
				new Polygon(new Color(java.awt.Color.BLUE),new Material(0, 0, 20, 0, 0),new Point3D(-34,-4,7),new Point3D(-34,-2,11),new Point3D(-34,2,11),new Point3D(-34,4,7),new Point3D(-34,2,3),new Point3D(-34,-2,3) ),
				new Polygon(new Color(java.awt.Color.BLUE),new Material(0, 0, 20, 0, 0),new Point3D(-34,-4,-8),new Point3D(-34,-2,-4),new Point3D(-34,2,-4),new Point3D(-34,4,-8),new Point3D(-34,2,-12),new Point3D(-34,-2,-12) ),
		        new Polygon(new Color(51,25,0),new Material(0, 0, 20, 0, 0),new Point3D(33,25,23),new Point3D(33,29,25),new Point3D(33,46,-3),new Point3D(33,42,-5) ),
                new Polygon(new Color(51,25,0),new Material(0, 0, 20, 0, 0),new Point3D(33,-25,23),new Point3D(33,-29,25),new Point3D(33,-46,-3),new Point3D(33,-42,-5) ));

		scene.addLights(new SpotLight(new Color(1024, 400, 400),new Point3D(-10000,0,0), 1, 4E-5, 2E-7, new Vector(1, 0, 0)),new SpotLight(new Color(700, 400, 400),new Point3D(1500,1,200), 1, 4E-5, 2E-7, new Vector(-30, 0, 1)),new SpotLight(new Color(700, 400, 400),new Point3D(0,-20,0), 1, 4E-5, 2E-7, new Vector(-1000, 15, 0)),new PointLight(new Color(500, 300, 0), new Point3D(0, 10, 300), 1, 0.00001, 0.000001));

		ImageWriter imageWriter = new ImageWriter("mini1", 200, 200, 500, 500);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render._renderImage();
		render.writeToImage();
		


	}
	
	@Test
	//in order to see the improvement we need to change the number of rays if we don't want to have the improvement the number of rays equal to one 
	public void picture2BeforeImp1() {
		Scene scene = new Scene("Test scene");
		//scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));

		scene.setCamera(new Camera(new Point3D(-1000, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, 1)));
		scene.setDistance(1000);
		scene.setBackground(new Color(0,0,102));
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		scene.set_numOfRays(1);

		
		scene.addGeometries( //
				
				new Sphere(Color.WHITE, new Material(0.25, 0.25, 20, 0.5, 0), // )
						33, new Point3D(0,0,7)),
				new Sphere(Color.WHITE, new Material(0.25, 0.25, 20, 0.5, 0), // )
						20, new Point3D(0,0,57))
				,new Sphere(Color.WHITE, new Material(0, 0, 0, 0, 0), // )
						0.6, new Point3D(-55,6,60))
				,new Sphere(Color.WHITE, new Material(0, 0, 0, 0, 0), // )
						0.6, new Point3D(-55,4,60))
				,new Sphere(Color.WHITE, new Material(0, 0, 0, 0, 0), // )
						0.6, new Point3D(-55,5,58.8))
				,new Sphere(Color.WHITE, new Material(0, 0, 0, 0, 0), // )
						0.6, new Point3D(-55,-6,60))
				,new Sphere(Color.WHITE, new Material(0, 0, 0, 0, 0), // )
						0.6, new Point3D(-55,-4,60))
				,new Sphere(Color.WHITE, new Material(0, 0, 0, 0, 0), // )
						0.6, new Point3D(-55,-5,58.8))
				,new Sphere(Color.BLACK, new Material(0, 0, 0, 0, 0), // )
						3, new Point3D(-50,5,60))
				,new Sphere(Color.BLACK, new Material(0, 0, 0, 0, 0), // )
						3, new Point3D(-50,-5,60))
				,new Plane(new Color(0, 255, 255), new Material(0, 0, 0, 0, 1),new Point3D(38,0,-30), new Point3D(10,12,-30), new Point3D(0,0,-30)),
				new Triangle(new Color(102,51,0), new Material(0, 0, 20, 0, 0), new Point3D(-20,-5,57), new Point3D(-20,5,57) ,  new Point3D(-20,0,46))
				,new Polygon(new Color(76,0,153),new Material(0, 0, 20, 0, 0),new Point3D(-20,-12,37),new Point3D(-20,-12,40),new Point3D(-20,12,40),new Point3D(-20,12,37) ),
				new Polygon(new Color(java.awt.Color.BLUE),new Material(0, 0, 20, 0, 0),new Point3D(-34,-4,22),new Point3D(-34,-2,26),new Point3D(-34,2,26),new Point3D(-34,4,22),new Point3D(-34,2,18),new Point3D(-34,-2,18) ),
				new Polygon(new Color(java.awt.Color.BLUE),new Material(0, 0, 20, 0, 0),new Point3D(-34,-4,7),new Point3D(-34,-2,11),new Point3D(-34,2,11),new Point3D(-34,4,7),new Point3D(-34,2,3),new Point3D(-34,-2,3) ),
				new Polygon(new Color(java.awt.Color.BLUE),new Material(0, 0, 20, 0, 0),new Point3D(-34,-4,-8),new Point3D(-34,-2,-4),new Point3D(-34,2,-4),new Point3D(-34,4,-8),new Point3D(-34,2,-12),new Point3D(-34,-2,-12) ),
		        new Polygon(new Color(51,25,0),new Material(0, 0, 20, 0, 0),new Point3D(33,25,23),new Point3D(33,29,25),new Point3D(33,46,-3),new Point3D(33,42,-5) ),
                new Polygon(new Color(51,25,0),new Material(0, 0, 20, 0, 0),new Point3D(33,-25,23),new Point3D(33,-29,25),new Point3D(33,-46,-3),new Point3D(33,-42,-5) ));

		scene.addLights(new SpotLight(new Color(1024, 400, 400),new Point3D(-10000,0,0), 1, 4E-5, 2E-7, new Vector(1, 0, 0)),new SpotLight(new Color(700, 400, 400),new Point3D(1500,1,200), 1, 4E-5, 2E-7, new Vector(-30, 0, 1)),new SpotLight(new Color(700, 400, 400),new Point3D(0,-20,0), 1, 4E-5, 2E-7, new Vector(-1000, 15, 0)),new PointLight(new Color(500, 300, 0), new Point3D(0, 10, 300), 1, 0.00001, 0.000001));

		ImageWriter imageWriter = new ImageWriter("beforeMini1", 200, 200, 500, 500);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render._renderImage();
		render.writeToImage();
		


	}
	
}

