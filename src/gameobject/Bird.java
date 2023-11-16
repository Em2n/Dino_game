package gameobject;

import java.awt.*;
import java.awt.image.BufferedImage;
import util.Animation;
import util.Resource;

public class Bird extends Enemy {

	public int Y_LAND = 100;

	private int posX;
	private int width;
	private int height;

	private BufferedImage image;
	private Animation animation;
	private MainCharacter mainCharacter;

	private Rectangle rectBound;

	public Bird(MainCharacter mainCharacter, int posX, int width, int height, BufferedImage image, boolean fake) {
		this.posX = posX;
		this.width = width;
		this.height = height;
		this.image = image;
		this.mainCharacter = mainCharacter;
		this.animation = new Animation(200);
		this.animation.addFrame(Resource.getResouceImage("data/bird1.png"));
		this.animation.addFrame(Resource.getResouceImage("data/bird2.png"));
		Y_LAND = fake ? 100 : 80;
		rectBound = new Rectangle();
	}
	
	public void update() {
		posX -= mainCharacter.getSpeedX();
		animation.updateFrame();
	}
	
	public void draw(Graphics g) {
		g.drawImage(animation.getFrame(), posX, Y_LAND - image.getHeight(), null);
		g.setColor(Color.red);
//		Rectangle bound = getBound();
//		g.drawRect(bound.x, bound.y, bound.width, bound.height);
	}
	
	public Rectangle getBound() {
		rectBound = new Rectangle();
		rectBound.x = (int) posX + (image.getWidth() - width)/2;
		rectBound.y = Y_LAND - image.getHeight() + (image.getHeight() - height)/2;
		rectBound.width = width;
		rectBound.height = height;
		return rectBound;
	}

	@Override
	public boolean isOutOfScreen() {
		if(posX < -image.getWidth()) {
			return true;
		}
		return false;
	}
	
}
