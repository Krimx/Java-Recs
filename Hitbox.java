import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;

public class Hitbox {
	private double x,y,w,h,l,r,u,d;
	private boolean colRight, colLeft, colUp, colDown;
	
	public Hitbox() {
		this(0,0,1,1);
	}
	public Hitbox(double xIn, double yIn) {
		this(xIn, yIn, 1,1);
	}
	public Hitbox (double xIn, double yIn, double wIn, double hIn) {
		x = xIn;
		y = yIn;
		w = wIn;
		h = hIn;
		
		l = x - (w / 2);
		r = x + (w / 2);
		u = y - (h / 2);
		d = y + (h / 2);
		
		colRight = false;
		colLeft = false;
		colUp = false;
		colDown = false;
	}
	
	public void update (double xIn, double yIn, double wIn, double hIn) {
		x = xIn;
		y = yIn;
		w = wIn;
		h = hIn;
		
		l = x - (w / 2);
		r = x + (w / 2);
		u = y - (h / 2);
		d = y + (h / 2);
	}
	
	public void resetDirs() {
		colRight = false;
		colLeft = false;
		colDown = false;
		colUp = false;
	}
	
	public boolean isColliding (Hitbox b) {
		if (b == null) return false;
		else {
			if (l < b.getRight() && r > b.getLeft() && u < b.getBottom() && d > b.getTop()) {
				//Checking collision direction
				if (r > b.getRight()) colLeft = true;

				if (l < b.getLeft()) colRight = true;

				if (d > b.getBottom()) colUp = true;

				if (u < b.getTop()) colDown = true;
				
				return true;
			}
			
			else {
				return false;
			}
		}
	}
	
	public void render(Graphics g, int scrW, int scrH, double upscale) {
		int rX = (int)(((x * upscale) - (w / 2 * upscale)) + (scrW / 2));
		int rY = (int)(((y * upscale) - (h / 2 * upscale)) + (scrH / 2));
		
		g.setColor(Color.red);
		g.drawRect((int)(rX), (int)(rY), (int)(w * upscale), (int)(h * upscale));
	}
	
	public void render(Graphics g, int scrWidth, int scrHeight, Camera camera) {
		
		int xOffset = scrWidth / 2;
		int yOffset = scrHeight / 2;
		
		int renderX = (int) ((x + xOffset) - camera.getX() - ((double) w / 2.0));
		int renderY = (int) ((y + yOffset) - camera.getY() - ((double) h / 2.0));

		g.setColor(Color.red);
		
		g.drawRect(renderX, renderY, (int) w, (int) h);
	}
	
	public void render(Graphics g, int scrWidth, int scrHeight, int mult, Camera camera) {
		double scale = (double) (((double) (scrWidth) / (double) (mult)) * 10);
		
		int xOffset = scrWidth / 2;
		int yOffset = scrHeight / 2;
		
		int renderW = (int) (w * scale);
		int renderH = (int) (h * scale);
		
		int renderX = (int) ((x * scale) + xOffset - camera.getX() - ((double) renderW / 2.0));
		int renderY = (int) ((y * scale) + yOffset - camera.getY() - ((double) renderH / 2.0));

		g.setColor(Color.red);
		
		g.drawRect(renderX, renderY, (int) renderW, (int) renderH);
	}

	public double getLeft() {return l;}
	public double getRight() {return r;}
	public double getTop() {return u;}
	public double getBottom() {return d;}

	public boolean getColRight() {return colRight;}
	public boolean getColLeft() {return colLeft;}
	public boolean getColUp() {return colUp;}
	public boolean getColDown() {return colDown;}
}
