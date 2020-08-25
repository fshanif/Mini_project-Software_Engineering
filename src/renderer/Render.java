/**
 * 
 */
package renderer;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.*;
import elements.Camera;
import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;


//import primitives.Point3D;
import static primitives.Util.alignZero;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import static primitives.Util.isZero;
/**
 * @author shani and elina 
 * the class role is to connect the scene with the color matrix of the image
 * Scene (_scene)
 * ImageWriter (_imageWriter)
 * Delta-constant value for size of rays, transparency and reflection
 * MAX_CALC_COLOR_LEVEL-maximum repetition of the function
 * MIN_CALC_COLOR_K- the minimum color than if it less it doesn't calculates the color 
 */
public class Render {

	
	private Scene _scene;
	private ImageWriter _imageWriter;
//	private static final double DELTA = 0.1;//constant value for size of rays, transparency and reflection
	private static final int MAX_CALC_COLOR_LEVEL = 50;
	private static final double MIN_CALC_COLOR_K = 0.001;//#####
	private boolean _print = false;
	private int _threads = 1;
	private final int SPARE_THREADS = 2;
	/**
	 * Pixel is an internal helper class whose objects are associated with a Render object that
	 * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
	 * its progress.<br/>
	 * There is a main follow up object and several secondary objects - one in each thread.
	 * @author Dan
	 *
	 */
	private class Pixel {
		private long _maxRows = 0;
		private long _maxCols = 0;
		private long _pixels = 0;
		public volatile int row = 0;
		public volatile int col = -1;
		private long _counter = 0;
		private int _percents = 0;
		private long _nextCounter = 0;

		/**
		 * The constructor for initializing the main follow up Pixel object
		 * @param maxRows the amount of pixel rows
		 * @param maxCols the amount of pixel columns
		 */
		public Pixel(int maxRows, int maxCols) {
			_maxRows = maxRows;
			_maxCols = maxCols;
			_pixels = maxRows * maxCols;
			_nextCounter = _pixels / 100;
			if (Render.this._print) System.out.printf("\r %02d%%", _percents);
		}

		/**
		 *  Default constructor for secondary Pixel objects
		 */
		public Pixel() {}

		/**
		 * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
		 * critical section for all the threads, and main Pixel object data is the shared data of this critical
		 * section.<br/>
		 * The function provides next pixel number each call.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
		 * finished, any other value - the progress percentage (only when it changes)
		 */
		  private synchronized int nextP(Pixel target) {
		      ++col;
		      ++_counter;
		      if (col < _maxCols) {
		        target.row = this.row;
		        target.col = this.col;
		        if (_print && _counter == _nextCounter) {
		          ++_percents;
		          _nextCounter = _pixels * (_percents + 1) / 100;
		          return _percents;
		        }
		        return 0;
		      }
		      ++row;
		      if (row < _maxRows) {
		        col = 0;
		        target.row = this.row;
		        target.col = this.col;
		        if (_print && _counter == _nextCounter) {
		          ++_percents;
		          _nextCounter = _pixels * (_percents + 1) / 100;
		          return _percents;
		        }
		        return 0;
		      }
		      return -1;
		    }

		/**
		 * Public function for getting next pixel number into secondary Pixel object.
		 * The function prints also progress percentage in the console window.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return true if the work still in progress, -1 if it's done
		 */
		public boolean nextPixel(Pixel target) {
			int percents = nextP(target);
			if (percents > 0)
				if (Render.this._print) System.out.printf("\r %02d%%", percents);
			if (percents >= 0)
				return true;
			if (Render.this._print) System.out.printf("\r %02d%%", 100);
			return false;
		}
	}
	/**
	 * constructor with two parameters
	 * @param imageWriter-imageWriter
	 * @param scene-scene
	 */
	public Render(ImageWriter imageWriter, Scene scene) {
		_imageWriter=imageWriter;
		_scene=scene;
	}


	/**
	 * function that creates a grid before the screen 
	 * @param interval- interval of square
	 * @param color- color of grid
	 */
	public void printGrid(int interval, java.awt.Color color)	
	{
	int nx = _imageWriter.getNx();
	int ny=	_imageWriter.getNy();
	for(int j=0;j<ny;j++)
		for(int i=0;i<nx;i++)
		{
			if(i%interval ==0 || j%interval==0)
			{
				_imageWriter.writePixel(i, j, color);
			}
		}
				
	}
	
	
	/**
	 * get function
	 * @return the _scene
	 */
	public Scene get_scene() {
		return _scene;
	}
	
	/**
	 * get function
	 * @return the _imageWriter
	 */
	public ImageWriter get_imageWriter() {
		return _imageWriter;
	}
	/**
	 * without improvement
	 * function that fills the buffer according to the geometries that are in the scene.
	 */
	
	/*public void renderImage()
	{
		Camera camera = _scene.get_camera();
		Intersectable geometries = _scene.get_geometries();
		java.awt.Color background = _scene.get_background().getColor();
		int nX = _imageWriter.getNx();
		int nY = _imageWriter.getNy();

        for (int row = 0; row < nY; ++row)  // i is pixel row number and j is pixel in the row number
		{
            for (int column = 0; column < nX; ++column) {
            	 Ray ray = camera.constructRayThroughPixel(nX, nY, column, row, _scene.get_distance(), (int) _imageWriter.getWidth(), (int) _imageWriter.getHeight());
		         //List<GeoPoint> intersectionPoints = geometries.findIntsersections(ray);
		         GeoPoint closestPoint = findClosestIntersection(ray);

		         if (closestPoint == null) {
		             _imageWriter.writePixel(column, row, background);
		         } else {
		             java.awt.Color pixelColor = calcColor(closestPoint,ray).getColor();
		             _imageWriter.writePixel(column, row, pixelColor);
		         }
		     }
		}
		}
	*/
	/**
	 * function that fills the buffer according to the geometries that are in the scene.
	 */
        public void _renderImage()
    	{
        	int[] HWRays= this.getNumOfrays(_scene.get_numOfRays());
        	int raysToHeight=HWRays[0];
        	int raysToWidth=HWRays[1];
    		Camera camera = _scene.get_camera();
    		//java.awt.Color background = _scene.get_background().getColor();
    		int nX = _imageWriter.getNx();
    		int nY = _imageWriter.getNy();
    		
    		final Pixel thePixel = new Pixel(nY, nX);

    		// Generate threads
    		Thread[] threads = new Thread[_threads];
    		for (int i = _threads - 1; i >= 0; --i) {
    			threads[i] = new Thread(() -> {
    				Pixel pixel = new Pixel();
    				while (thePixel.nextPixel(pixel)) {
    					// for (int row = 0; row < nY; ++row)  // i is pixel row number and j is pixel in the row number
    					//{
    					//  for (int column = 0; column < nX; ++column) {
    					ArrayList<Ray> allRays = camera._constructRayThroughPixel(raysToHeight, raysToWidth, nX, nY, pixel.col, pixel.row, _scene.get_distance(), (int) _imageWriter.getWidth(), (int) _imageWriter.getHeight());
    					//List<GeoPoint> intersectionPoints = geometries.findIntsersections(ray);

    					java.awt.Color pixelColor =raysColor(allRays,raysToHeight,raysToWidth);
    					_imageWriter.writePixel(pixel.col, pixel.row, pixelColor);
    				}
    			});
    		}
    			// Start threads
    			for (Thread thread : threads) thread.start();

    			// Wait for all threads to finish
    			for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
    			if (_print) System.out.printf("\r100%%\n");
    		}

//
//    		         if (closestPoint == null) {
//    		             _imageWriter.writePixel(column, row, background);
//    		         } else {
//    		             java.awt.Color pixelColor = calcColor(closestPoint,ray).getColor();
//    		             _imageWriter.writePixel(column, row, pixelColor);
//    		         }
    		   //  }

    		//}	
		
	
       
        
        
       //################################################################################################################## 
        
        
        
        
        
        
        
    	/**
    	 * function that fills the buffer according to the geometries that are in the scene.
    	 */
          public void impov2_renderImage()
        	{
            	
        		Camera camera = _scene.get_camera();
        		//java.awt.Color background = _scene.get_background().getColor();
        		int nX = _imageWriter.getNx();
        		int nY = _imageWriter.getNy();
        		double hp=_imageWriter.getHeight()/nY;//hight of pixel
        		double wp=_imageWriter.getWidth()/nX;//width of pixel
        		
        		final Pixel thePixel = new Pixel(nY, nX);

        		// Generate threads
        		Thread[] threads = new Thread[_threads];
        		for (int i = _threads - 1; i >= 0; --i) {
        			threads[i] = new Thread(() -> {
        				Pixel pixel = new Pixel();
        				while (thePixel.nextPixel(pixel)) {
        	            	 //Ray ray = camera.constructRayThroughPixel(nX, nY, pixel.col, pixel.row, _scene.get_distance(), (int) _imageWriter.getWidth(), (int) _imageWriter.getHeight());
        					
        	            	 Point3D Pij;

        	                 double Yi=(pixel.row-nY/2d)*hp+(hp/2d);
        	                 double Xj=(pixel.col-nX/2d)*wp+(wp/2d); 

        	                 Pij=camera.get_p0().add(camera.get_vTo().scale(_scene.get_distance()));
        	                 if (Xj != 0) 
        	              	   Pij = Pij.add(camera.get_vRight().scale(Xj));
        	                 if (Yi != 0) 
        	              	   Pij = Pij.add(camera.get_vUp().scale(-Yi));
        	            	 Color myColor = rec(0, hp,wp,Pij);
        				
        					java.awt.Color pixelColor=myColor.getColor();
        					//java.awt.Color pixelColor =raysColor(allRays,raysToHeight,raysToWidth);
        					_imageWriter.writePixel(pixel.col, pixel.row, pixelColor);
        				}
        			});
        		}
        			// Start threads
        			for (Thread thread : threads) thread.start();

        			// Wait for all threads to finish
        			for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
        			if (_print) System.out.printf("\r100%%\n");
        		}
        

         /**
          * 
          * @param levelrec- level of recursion
          * @param hp- height of pixel
          * @param wp-weight of pixel
          * @param center- middle of the pixel
          * @param nX-number of pixel to x axis
          * @param nY-number of pixel to y axis
          * @param j
          * @param i
          * @param screenDistance
          * @param screenWidth
          * @param screenHeight
          * @return
          * recursive function that receives the middle point to the pixel and calculate the edges of the pixel if the color of the adges are not the same
          * it does the function again 4 time to each sub pixel 
          */
            public Color rec(int levelrec,double hp,double wp, Point3D center) {
            	
            	ArrayList<Point3D> points=fromcenterto5(center,hp,wp);
            	ArrayList<Ray> rays=new ArrayList<Ray>();
            	rays.add(pointTOray(points.get(0)));//left up
            	rays.add(pointTOray(points.get(1)));//left down
            	rays.add(pointTOray(points.get(2)));//right down
            	rays.add(pointTOray(points.get(3)));//right up
            	rays.add(pointTOray(points.get(4)));// middle
            	ArrayList<Color> c=calcPixelVertexColor(rays);
            	int r=0;
            	int b=0;
            	int g=0;
            	r=c.get(0).getColor().getRed()+c.get(1).getColor().getRed()+c.get(2).getColor().getRed()+c.get(3).getColor().getRed();
        		b=c.get(0).getColor().getBlue()+c.get(1).getColor().getBlue()+c.get(2).getColor().getBlue()+c.get(3).getColor().getBlue();
        		g=c.get(0).getColor().getGreen()+c.get(1).getColor().getGreen()+c.get(2).getColor().getGreen()+c.get(3).getColor().getGreen();
        		if(levelrec<=4) {
        		if(isSame(c.get(0), c.get(1))&&isSame(c.get(0), c.get(2))&&isSame(c.get(0), c.get(3))&&isSame(c.get(1), c.get(2))&&isSame(c.get(1), c.get(2))&&isSame(c.get(2), c.get(3)))
            	{
            		//return new Color(r/4,g/4,b/4);
        			return new Color(c.get(4));
        			//return new Color(c.get(0));
            	}
            	else {
            		Point3D p1=new Point3D(center.getpOne().get(),center.getpSecond().get()+wp/4,center.getpThird().get()+hp/4);//left up
            		Point3D p2=new Point3D(center.getpOne().get(),center.getpSecond().get()+wp/4,center.getpThird().get()-hp/4);//left down
            		Point3D p3=new Point3D(center.getpOne().get(),center.getpSecond().get()-wp/4,center.getpThird().get()-hp/4);//right down
            		Point3D p4=new Point3D(center.getpOne().get(),center.getpSecond().get()-wp/4,center.getpThird().get()+hp/4);//right up
            	
            		Color c1=rec(levelrec+1,hp/2,wp/2,p1);
            		Color c2=rec(levelrec+1,hp/2,wp/2,p2);
            		Color c3=rec(levelrec+1,hp/2,wp/2,p3);
            		Color c4=rec(levelrec+1,hp/2,wp/2,p4);
            		Color coloNOW= new Color(r/4,g/4,b/4);
            		r=0;
                	b=0;
                	g=0;
                	r=c1.getColor().getRed()+c2.getColor().getRed()+c3.getColor().getRed()+c4.getColor().getRed()+coloNOW.getColor().getRed();
                	b=c1.getColor().getBlue()+c2.getColor().getBlue()+c3.getColor().getBlue()+c4.getColor().getBlue()+coloNOW.getColor().getBlue();
                	g=c1.getColor().getGreen()+c2.getColor().getGreen()+c3.getColor().getGreen()+c4.getColor().getGreen()+coloNOW.getColor().getGreen();
            		Color totalColor =new Color(r/5,g/5,b/5) ;
            		return totalColor;
            	}
            	}
            	else return new Color(r/4,g/4,b/4);
            }

        /**
         * 
         * @param c-center of pixel
         * @param h-height of this level of pixel
         * @param w-weight of this level of pixel
         * @return
         * calculates the adages of pixel
         */
            public ArrayList<Point3D> fromcenterto5(Point3D c,double h,double w){
            	Point3D p1=new Point3D(c.getpOne().get(),c.getpSecond().get()+w/2,c.getpThird().get()+h/2);//left up
            	Point3D p2=new Point3D(c.getpOne().get(),c.getpSecond().get()+w/2,c.getpThird().get()-h/2);//left down
            	Point3D p3=new Point3D(c.getpOne().get(),c.getpSecond().get()-w/2,c.getpThird().get()-h/2);//right down
            	Point3D p4=new Point3D(c.getpOne().get(),c.getpSecond().get()-w/2,c.getpThird().get()+h/2);//right up
            	ArrayList<Point3D> points=new ArrayList<Point3D>();
            	points.add(p1);
            	points.add(p2);
            	points.add(p3);
            	points.add(p4);
            	points.add(c);
            	return points;
            	
            }
         
            /**
             * 
             * @param myPoint
             * @return
             * Receives a point and change it to a ray
             */
            public Ray pointTOray(Point3D myPoint) {//recieves a point and return a ray to the point from the camera
            	double X=myPoint.getpOne().get();
            	double Y=myPoint.getpSecond().get();
            	
            	Camera c=_scene.get_camera();
            	Point3D Pij;
            	Pij=c.get_p0().add(c.get_vTo().scale(_scene.get_distance()));
            	if (X != 0) 
            		Pij = Pij.add(c.get_vRight().scale(X));
            	if (Y != 0) 
            		Pij = Pij.add(c.get_vUp().scale(-Y));
            	return(new Ray(c.get_p0(),Pij.subtract(c.get_p0())));
            }

           /**
            * 
            * @param rays- list if rays
            * @return 
            * recieves 5 rays and return a list of the colors
            */
           public ArrayList<Color> calcPixelVertexColor(ArrayList<Ray> rays)
           {
        	 
        	 ArrayList<Color> c=new ArrayList<Color>();
        	 c.add(rayColor(rays.get(0)));//left up
        	 c.add(rayColor(rays.get(1)));//left down
        	 c.add(rayColor(rays.get(2)));//right down
        	 c.add(rayColor(rays.get(3)));//right up
        	 c.add(rayColor(rays.get(4)));// middle
        	 return c;
        	
           }
           /**
            * 
            * @param ray
            * @return the color of the ray received
            */
           public Color rayColor(Ray ray)
           {
        	   java.awt.Color background = _scene.get_background().getColor();
        	   GeoPoint closestPoint = findClosestIntersection(ray);
        	   if(closestPoint==null) {
        		   return new Color(background.getRed(),background.getGreen(),background.getBlue()); 

        	   }else {
        		   java.awt.Color myPixelColor = calcColor(closestPoint,ray).getColor(); 
        		   return new Color(myPixelColor.getRed(),myPixelColor.getGreen(),myPixelColor.getBlue()); 

        	   }
           }
        /**
         * 
         * @param c1-color
         * @param c2-color
         * @return
         * return true if the colors are the same
         */
            public boolean isSame(Color c1, Color c2)
            {
            	if(Math.abs(c1.getColor().getBlue()-c2.getColor().getBlue())>=5)
            		return false;
            	if(Math.abs(c1.getColor().getGreen()-c2.getColor().getGreen())>=5)
            		return false;
            	if(Math.abs(c1.getColor().getRed()-c2.getColor().getRed())>=5)
            		return false;
            	return true;
            }


        
        
        
        
        
        
        
        
   //#########################################################################################################################     
        
        
        
        
        
        
        
  
        /**
         * function that helps to calculate the colors for all rays after improvement one
         * @param rays - array of rays 
         * @param HWRays -raysToHeight
         * @param HWRays2- raysToWidth
         * @return
         */
        public java.awt.Color raysColor(ArrayList<Ray> rays,int HWRays,int HWRays2)
        {
        	int raysToHeight=HWRays;
        	int raysToWidth=HWRays2;
    		java.awt.Color background = _scene.get_background().getColor();
        	int red=0;
        	int green=0;
        	int blue=0;
        	for (Ray ray : rays) {
        		GeoPoint closestPoint = findClosestIntersection(ray);
        		if(closestPoint==null) {
        			red+=background.getRed();
        			green+=background.getGreen();
        			blue+=background.getBlue();
        		}else {
        			java.awt.Color myPixelColor = calcColor(closestPoint,ray).getColor(); 
        			red+=myPixelColor.getRed();
        			green+=myPixelColor.getGreen();
        			blue+=myPixelColor.getBlue();
        		}

        	}
        	java.awt.Color pixelColor = new java.awt.Color((int)(red/(raysToHeight*raysToWidth)),(int)(green/(raysToHeight*raysToWidth)),(int)(blue/(raysToHeight*raysToWidth)));
        	return pixelColor;
        }
        /**
    	 * Set multithreading <br>
    	 * - if the parameter is 0 - number of coress less 2 is taken
    	 * 
    	 * @param threads number of threads
    	 * @return the Render object itself
    	 */
    	public Render setMultithreading(int threads) {
    		if (threads < 0)
    			throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
    		if (threads != 0)
    			_threads = threads;
    		else {
    			int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
    			if (cores <= 2)
    				_threads = 1;
    			else
    				_threads = cores;
    		}
    		return this;
    	}
    	/**
    	 * Set debug printing on
    	 * 
    	 * @return the Render object itself
    	 */
    	public Render setDebugPrint() {
    		_print = true;
    		return this;
    	}
        /**
         * 
         * @param rays - number of rays to a pixel
         * @return array that contains number of rays in pixel to x axis and y axis
         */
	public int[] getNumOfrays(int rays) {
		if(rays==0)
			rays=1;
		int i;
		for (i=0;i<rays;i++) {
			if(i*i>=rays)
				break;
		}
		int[] valueToReturn= {i,i};
		return valueToReturn;

	}
		
	
	/**
	 * function that calculates the color in the point
	 * @param geopoint- point to calculate the color at
	 * @param inRay -ray from light source to the point
	 * @param level - of the recursion
	 * @param k
	 * @return
	 */
	private Color calcColor(GeoPoint geopoint, Ray inRay, int level, double k) {
		if (k < MIN_CALC_COLOR_K) {
            return Color.BLACK;
        }
		Color color = geopoint.geometry.getEmmission();
	
		Vector v = geopoint.point.subtract(_scene.get_camera().get_p0()).normalize();
		Vector n = geopoint.geometry.getNormal(geopoint.point);
		
		Material material =geopoint.geometry.get_material();
		int nShininess = material.get_nShininess();
		double kd = material.get_kD();
		double ks = material.get_kS();
		double kr = geopoint.geometry.get_material().get_kr();
        double kkr = k * kr;
		double kt = geopoint.geometry.get_material().get_kT();
		double kkt = k * kt;

	
		color=color.add(getLightSourcesColors(geopoint, k, color, v, n, alignZero(n.dotProduct(v)), nShininess, kd, ks));

		if (level == 1 || k < MIN_CALC_COLOR_K) {
            return color;
        }

		 if (kkr > MIN_CALC_COLOR_K) {
	            Ray reflectedRay = constructReflectedRay(geopoint.point, inRay, n);
	            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
	            if (reflectedPoint != null) {
	                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
	            }
	        }
	        if (kkt > MIN_CALC_COLOR_K) {
	            Ray refractedRay = constructRefractedRay(geopoint.point, inRay, n);
	            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
	            if (refractedPoint != null) {
	            	color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
	            }
	        }
		return color;
		}
	/**
	 * part of function calcolor this part calculates the color according the light source
	 * @param geoPoint- point to calculate the color at
	 * :data from function calcolor
	 * @param k 
	 * @param color
	 * @param v
	 * @param n
	 * @param nv - d
	 * @param nShininess
	 * @param kd
	 * @param ks
	 * @return
	 */
    private Color getLightSourcesColors(GeoPoint geoPoint, double k, Color color, Vector v, Vector n, double nv, int nShininess, double kd, double ks) 
    {
    	if(_scene.get_lights()!= null)
		{
		for (LightSource lightSource : _scene.get_lights())
		{
		
			 Vector l = lightSource.getL(geoPoint.point);
			 if(alignZero(n.dotProduct(l))*alignZero(n.dotProduct(v))>0)
			 {
				 double ktr = transparency(lightSource, l, n, geoPoint);
				 if (ktr * k > MIN_CALC_COLOR_K)
				 {//if (unshaded(lightSource,l, n, intersection)) {
		          Color lightIntensity = lightSource.getIntensity(geoPoint.point).scale(ktr);;
		          color = color.add(calcDiffusive(kd, alignZero(n.dotProduct(l)), lightIntensity),
		          calcSpecular(ks, l, n,alignZero(n.dotProduct(l)), v, nShininess, lightIntensity));
				 }
				 
				 
			 }
		}}
		
		return color;
    }
	/**
	 * function named calcColor that call to another calcColor function with 4 parameters
	 * it receives ray and point and calculates the color at point recieved
	 * @param geopoint 
	 * @param inRay
	 * @return
	 */
	private Color calcColor(GeoPoint geopoint, Ray inRay) {
		Color color = calcColor(geopoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0);
        color = color.add(_scene.get_ambientLight().get_intensity());
        return color;		}
	
	
	
    
	/**
	 * 
	 * @param ks
	 * @param l
	 * @param n
	 * @param nDPl
	 * @param v
	 * @param nShininess
	 * @param ip
	 * @return  specular component light effect at the point
	 * * Finally, the Phong model has a provision for a highlight, or specular, component, which reflects light in a
     * shiny way. This is defined by [rs,gs,bs](-V.R)^p, where R is the mirror reflection direction vector we discussed
     * in class (and also used for ray tracing), and where p is a specular power. The higher the value of p, the shinier
     * the surface.
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, double nDPl, Vector v, int nShininess,Color ip) {

	        Vector R = l.add(n.scale(-2 * nDPl)); // nDPl must not be zero!
	        double minusVR = -alignZero(R.dotProduct(v));
	        if (minusVR <= 0) {
	            return Color.BLACK; // view from direction opposite to r vector
	        }
	        return ip.scale(ks * Math.pow(minusVR, nShininess));
	}


	/**
	 * The diffuse component is that dot product n•L that we discussed in class. It approximates light, originally
     * from light source L, reflecting from a surface which is diffuse, or non-glossy. One example of a non-glossy
     * surface is paper. In general, you'll also want this to have a non-gray color value, so this term would in general
     * be a color defined as: [rd,gd,bd](n•L)
     * @param kd
	 * @param nl- n dot product l
	 * @param ip
	 * @return diffusive component of light reflection
	 */
	private Color calcDiffusive(double kd, double nl, Color ip) {
		if (nl < 0) nl = -nl;
        return ip.scale(nl * kd);
	}


	/**
	 * 
	 * @param value
	 * @return true if the value was given is positive and false if its not
	 */
     private boolean sign(double value) {
    	 return value >0;
       		
	}


/**
 * function that returns the closest point
 * @param intersectionPoints
 * @return
 */
	private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints)
	{
		GeoPoint p;
		Camera camera = _scene.get_camera();
		if(intersectionPoints!=null)
		{
			p=new GeoPoint(intersectionPoints.get(0).geometry,intersectionPoints.get(0).point);
			double minD=camera.get_p0().distance(intersectionPoints.get(0).point);
		      for (int i =1 ; i < intersectionPoints.size(); i++) {
				if(camera.get_p0().distance(intersectionPoints.get(i).point)<minD) {
					minD=camera.get_p0().distance(intersectionPoints.get(i).point);
				    p=new GeoPoint(intersectionPoints.get(i).geometry,intersectionPoints.get(i).point);
				}
			}
		      return p;
		}
		return null;
	}
	/**
	 * function that calls the function writeToImage for this render
	 */
	public void writeToImage() {
        _imageWriter.writeToImage();
    }

	/**
	 * checks if  an object exists between the light and the geometry
	 * @param light
	 * @param l
	 * @param n
	 * @param geopoint
	 * @return
	 */
	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
		Vector lightDirection = l.scale(-1); // from point to light source
		//Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
		//Point3D point = geopoint.point.add(delta);
		Ray lightRay = new Ray(geopoint.point, lightDirection,n);
		List<GeoPoint> intersections = _scene.get_geometries().findIntsersections(lightRay);
		if (intersections == null)
			return true;
		double lightDistance = light.getDistance(geopoint.point);
		for (GeoPoint gp : intersections) {
            double temp = gp.point.distance(geopoint.point) - lightDistance;
            if (alignZero(temp) <= 0&& gp.geometry.get_material().get_kT() == 0)
                return false;
            
		}
		return true;
	}
	/**
	 * 
	 * function that creates Partial shading in case the body or bodies that block the light source from the point have transparency at some level or another
	 * @param light 
	 * @param l -the vector from the light to the point 
	 * @param n- normal vector to the point at the geometry
	 * @param geopoint- the point in the geometry
	 * @return
	 */
	private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
		Vector lightDirection = l.scale(-1); // from point to light source
		//Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
		//Point3D point = geopoint.point.add(delta);
		Ray lightRay = new Ray(geopoint.point, lightDirection,n);
		List<GeoPoint> intersections = _scene.get_geometries().findIntsersections(lightRay);
		if (intersections == null)
			return 1.0;
		double lightDistance = light.getDistance(geopoint.point);
		double ktr = 1.0;
		for (GeoPoint gp : intersections) {
            double temp = gp.point.distance(geopoint.point) - lightDistance;
            if (alignZero(temp) <= 0)
            {
            	ktr *= gp.geometry.get_material().get_kT();
            	if (ktr < MIN_CALC_COLOR_K) return 0.0;
            	}

            
		}
		return ktr;
    }
    
	/**
	 * creates RefractedRay by sending to the constructor ray with proper details
	 * @param pointGeo
	 * @param inRay
	 * @param n
	 * @return
	 */
	private Ray constructRefractedRay(Point3D pointGeo, Ray inRay, Vector n) {
        return new Ray(pointGeo, inRay.getV(), n);
    }

	/**
	 * creates ReflectedRay by sending to the constructor ray with proper details
	 * @param pointGeo
	 * @param inRay
	 * @param n
	 * @return
	 */
    private Ray constructReflectedRay(Point3D pointGeo, Ray inRay, Vector n) {
        //r=v-2*v*n*n
        Vector v = inRay.getV();
        double vn = alignZero(v.dotProduct(n));
        if (isZero(vn)){
            return null;
        }
        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(pointGeo, r, n);
    }
    /**
     * Find intersections of a ray with the scene geometries and get the
     * intersection point that is closest to the ray head. If there are no
     * intersections, null will be returned.
     *
     * @param ray intersecting the scene
     * @return the closest point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        if (ray == null) {
            return null;
        }
        GeoPoint closestPoint = null;
        double closestDistance = Double.MAX_VALUE;
        Point3D ray_p0 = ray.getP();

        List<GeoPoint> intersections = _scene.get_geometries().findIntsersections(ray);
        if (intersections == null)
            return null;

        for (GeoPoint geoPoint : intersections) {
            double distance = ray_p0.distance(geoPoint.point);
            if (distance < closestDistance) {
                closestPoint = geoPoint;
                closestDistance = distance;
            }
        }
        return closestPoint;
    }


}
