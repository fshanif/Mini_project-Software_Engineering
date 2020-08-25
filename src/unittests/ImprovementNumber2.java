/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * @author FLUK
 *
 */
public class ImprovementNumber2 {

	
	
	@Test
	public void mini2_number1() {
		Random r = new Random();
		
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(600);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		
		
		scene.addLights(new SpotLight(new Color(Math.random()*100,Math.random()*100,Math.random()*100),new Point3D(-10000,0,0), 1, 4E-5, 2E-7, new Vector(1, 0, 0)),
				new SpotLight(new Color(700, 400, 400),new Point3D(1500,1,200), 1, 4E-5, 2E-7, new Vector(-30, 0, 1)),
				new SpotLight(new Color(700, 400, 400),new Point3D(0,-20,0), 1, 4E-5, 2E-7, new Vector(-1000, 15, 0)),
				new PointLight(new Color(500, 300, 0), new Point3D(0, 10, 300), 1, 0.00001, 0.000001)
				
			,	new DirectionalLight(new Color(java.awt.Color.BLACK),new Vector(-10,20,30))
				,new PointLight(new Color(java.awt.Color.BLUE),new Point3D(-30, 70, 140),35, 0.5, 0.5)
				);
		
		scene.addGeometries(
				new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70), 
					15, new Point3D(0, 0, 0)),
				new Polygon(Color.BLACK, new Material(1, 1, 60),new Point3D(-560,-500,3000),new Point3D(560,-500,3000),new Point3D(0,200,-700))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70),80, new Point3D(-105, -110, 450))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.2, 0.5, 30, 0.6, 0),70, new Point3D(150, -150, 600))
			    ,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70),40, new Point3D(-20, -200, 50))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70),35, new Point3D(40, -180, 650))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70),10, new Point3D(0, 600, 50))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70),25, new Point3D(80, 0, 230))

				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70,1,0),10, new Point3D(30, 0, 60))
				,new Polygon(Color.BLACK, new Material(0.4, 0.3, 20000,0,0.4),new Point3D(-560,-500,3000),new Point3D(0,200,-700),new Point3D(-180,0,-1000))
				,new Polygon(Color.BLACK, new Material(0.4, 0.3, 20000,0,0.4),new Point3D(560,-500,3000),new Point3D(350,0,-300),new Point3D(0,200,-700))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70,0.5,0),15, new Point3D(50, 20, 50)),
				new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.2, 0.5, 30, 0.6, 0), 35, new Point3D(20, -60, 120))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.2, 0.2, 30, 0.6, 0), 10, new Point3D(65, -52, 50))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.2, 0.2, 30, 0.6, 0), 10, new Point3D(49, -25, -100))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.2, 0.2, 70, 0, 0), 13, new Point3D(95, -35, 130))

				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70, 0, 0), 20, new Point3D(-35, -25, 295))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.05, 0.5, 100), 10, new Point3D(-10, -110, 70))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70),30, new Point3D(70, -215, 150))

				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.05, 0.5, 100),27, new Point3D(-68, 8, 80))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 100),11, new Point3D(-25, 10, 0))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70, 0, 0),21, new Point3D(10, 45, -50))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70, 0, 0),11, new Point3D(10, 70, -170))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70, 0, 0),8, new Point3D(-20, 70, -190))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70, 0, 0),12, new Point3D(-25, 40, -190))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70, 0, 0),16, new Point3D(65, 45, -190))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70, 0, 0),7, new Point3D(40, 35, -190))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70, 0, 0),12, new Point3D(40, 65, -190))
				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70, 0, 0),4, new Point3D(25, 105, -130))

				,new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70, 0, 0),5, new Point3D(60, 100, -300))
		
				
				);
		            for(int i=0;i<10;i++) {
					//int result = r.nextInt(high-low) + low;
					int myX = r.nextInt(40+40) -40;
					int myY = r.nextInt(110-50) +50;
					int radius = r.nextInt(11-4) +4;

					scene.addGeometries(new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70, 1, 0), radius, new Point3D(myX,myY, -500)));
				}
		          for(int i=0;i<10;i++) {
						//int result = r.nextInt(high-low) + low;
						int myX = r.nextInt(40+40) -40;
						int myY = r.nextInt(110-50) +50;
						int radius = r.nextInt(7-4) +4;

						scene.addGeometries(new Sphere(new Color(Math.random()*100,Math.random()*100,Math.random()*100), new Material(0.5, 0.5, 70, 0, 0), radius, new Point3D(myX,myY, -300)));
					}  
				
		ImageWriter imageWriter = new ImageWriter("mini2_number1", 200, 200, 500, 500);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.impov2_renderImage();
		render.writeToImage();
		
	}
	@Test
	public void mini2_number2() {
		Random r = new Random();
		Scene scene = new Scene("Test mini2");
		scene.setCamera(new Camera(new Point3D(50, 100, -11000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(9000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(0,0,0), 0.1));
		
		Color c1=new Color(Math.random()*100,Math.random()*100,Math.random()*100);
		Color c2=new Color(Math.random()*100,Math.random()*100,Math.random()*100);
		Color c3=new Color(Math.random()*100,Math.random()*100,Math.random()*100);
		Color c4=new Color(Math.random()*100,Math.random()*100,Math.random()*100);
		Color c5=new Color(Math.random()*100,Math.random()*100,Math.random()*100);
		Color c6=new Color(Math.random()*100,Math.random()*100,Math.random()*100);
		Color c7=new Color(Math.random()*100,Math.random()*100,Math.random()*100);
		Color c8=new Color(Math.random()*100,Math.random()*100,Math.random()*100);
		Color c9=new Color(Math.random()*100,Math.random()*100,Math.random()*100);
		Color c10=new Color(Math.random()*100,Math.random()*100,Math.random()*100);
		Color c11=Color.WHITE;
		scene.addGeometries( 

				new Plane(new Color(java.awt.Color.black), new Material(0.4, 0.3, 20000,0,0.4), 
						new Point3D(1500,1000,0), new Point3D(-1500,1000,3850), new Point3D(-1500,1000,0)), 
				
				//left
				new Sphere(c9, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(-450, -1020, -600)),				
				new Sphere(c8, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(-540, -790, -600)),
				new Sphere(c7, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(-600, -560, -600)),
				new Sphere(c6, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(-600, -320, -600)),
				new Sphere(c5, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(-600, -80, -600)),
				new Sphere(c4, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(-600, 160, -600)),

				new Sphere(c3, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(-600, 400, -600)),
				new Sphere(c2, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(-600, 640, -600)),
				new Sphere(c1, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(-600, 880, -600)),

				//##################################################################33				
                //middle
				new Sphere(c9, new Material(0.8, 0.8, 200, 0.4,0.4), 120, new Point3D(-280, -1100, -600)),
				new Sphere(c10, new Material(0.8, 0.8, 200, 0.4,0.4), 120, new Point3D(-40, -1130, -600)),
				new Sphere(c10, new Material(0.8, 0.8, 200, 0.4,0.4), 120, new Point3D(200, -1130, -600)),
				new Sphere(c9, new Material(0.8, 0.8, 200, 0.4,0.4), 120, new Point3D(440, -1100, -600)),

				//##################################################################33
				new Sphere(c9, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(650, -1020, -600)),
				new Sphere(c8, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(740, -790, -600)),
				new Sphere(c7, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(800, -560, -600)),
				new Sphere(c6, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(800, -320, -600)),
				new Sphere(c5, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(800, -80, -600)),
				new Sphere(c4, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(800, 160, -600)),

				new Sphere(c3, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(800, 400, -600)),
				new Sphere(c2, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(800, 640, -600)),
				new Sphere(c1, new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(800, 880, -600)),

				//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				//Right
				//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				new Sphere(c8, new Material(0.5,0.5,500), 120, new Point3D(-300, -790, -600)),
				new Sphere(c7, new Material(0.5,0.5,500), 120, new Point3D(-360, -560, -600)),
				new Sphere(c6, new Material(0.5,0.5,500), 120, new Point3D(-360, -320, -600)),
				new Sphere(c5, new Material(0.5,0.5,500), 120, new Point3D(-360, -80, -600)),
				new Sphere(c4, new Material(0.5,0.5,500), 120, new Point3D(-360, 160, -600)),

				new Sphere(c3, new Material(0.5,0.5,500), 120, new Point3D(-360, 400, -600)),
				new Sphere(c2, new Material(0.5,0.5,500), 120, new Point3D(-360, 640, -600)),
				new Sphere(c1, new Material(0.5,0.5,500), 120, new Point3D(-360, 880, -600)),

				//##################################################################33				
                //middle
				new Sphere(c9, new Material(0.5,0.5,500), 120, new Point3D(-70, -880, -600)),
				new Sphere(c10, new Material(0.5,0.5,500), 120, new Point3D(120, -910, -600)),
				new Sphere(c9, new Material(0.5,0.5,500), 120, new Point3D(310, -880, -600)),

				//##################################################################33
				new Sphere(c8, new Material(0.5,0.5,500), 120, new Point3D(500, -790, -600)),
				new Sphere(c7, new Material(0.5,0.5,500), 120, new Point3D(560, -560, -600)),
				new Sphere(c6, new Material(0.5,0.5,500), 120, new Point3D(560, -320, -600)),
				new Sphere(c5, new Material(0.5,0.5,500), 120, new Point3D(560, -80, -600)),
				new Sphere(c4, new Material(0.5,0.5,500), 120, new Point3D(560, 160, -600)),

				new Sphere(c3, new Material(0.5,0.5,500), 120, new Point3D(560, 400, -600)),
				new Sphere(c2, new Material(0.5,0.5,500), 120, new Point3D(560, 640, -600)),
				new Sphere(c1, new Material(0.5,0.5,500), 120, new Point3D(560, 880, -600))	);
		
		for(int i=0;i<50;i++) {
			//int result = r.nextInt(high-low) + low;
			int myX = r.nextInt(1700+1700) -1700;
			int myY = r.nextInt(880+1130) -1130;
			scene.addGeometries(new Sphere(c11, new Material(0.5,0.5,70), 5, new Point3D(myX,myY, 0)));
		}



		scene.addLights(new SpotLight(new Color(700, 400, 400),
				new Point3D(0,-20,0), 1, 4E-5, 2E-7, new Vector(-1000, 15, 0)));

		scene.addLights(new PointLight(new Color(java.awt.Color.BLUE),
				new Point3D(-80, -50, -900),2.5, 0.0000005, 0.0000000005));

		scene.addLights(new SpotLight(new Color(1020, 400, 400), 
				new Point3D(0,800,-400), 1,0.00001, 0.000005, new Vector(-1,1,4).normalize()));

		scene.addLights(new SpotLight(new Color(1020, 400, 400), 
				new Point3D(1200,800,-400), 1,0.00001, 0.000005, new Vector(-1,1,4).normalize()));

		scene.addLights(new SpotLight(new Color(1020, 400, 400), 
				new Point3D(900,800,-400), 1,0.00001, 0.000005, new Vector(-1,1,4).normalize()));

		scene.addLights(new SpotLight(new Color(20, 40, 0), 
				new Point3D(800,600,-300), 1,0.00001, 0.000005, new Vector(-1,1,4)));

		scene.addLights(new SpotLight(new Color(1020, 400, 400), 
				new Point3D(-800,700,-300), 1,0.00001, 0.000005, new Vector(-1,1,4)));

		scene.addLights(new SpotLight(new Color(java.awt.Color.darkGray), 
				new Point3D(0,0,11000), 1,0.00001, 0.000005, new Vector(0,0,-1).normalize()));


		ImageWriter imageWriter = new ImageWriter("mini2_number2", 2500, 2500, 500, 500);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.impov2_renderImage();
		render.writeToImage();
	}


}
