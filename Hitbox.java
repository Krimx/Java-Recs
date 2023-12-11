import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;

public class Hitbox {
	private double x,y,w,h,l,r,u,d;
	
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
	
	public boolean isColliding (Hitbox b) {
		if (b == null) return false;
		else {
			if (l < b.getRight() && r > b.getLeft() && u < b.getBottom() && d > b.getTop()) return true;
			else return false;
		}
	}
	
	public void render(Graphics g, int scrW, int scrH, double upscale) {
		int rX = (int)(((x * upscale) - (w / 2 * upscale)) + (scrW / 2));
		int rY = (int)(((y * upscale) - (h / 2 * upscale)) + (scrH / 2));
		
		g.setColor(Color.red);
		g.drawRect((int)(rX), (int)(rY), (int)(w * upscale), (int)(h * upscale));
	}

	public double getLeft() {return l;}
	public double getRight() {return r;}
	public double getTop() {return u;}
	public double getBottom() {return d;}
}
