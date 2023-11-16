package gameobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.Animation;
import util.Resource;

public class EnemiesManager {
	
	private BufferedImage cactus1;
	private BufferedImage cactus2;

	private BufferedImage bird;
	private Random rand;
	
	private List<Enemy> enemies;
	private MainCharacter mainCharacter;
	
	public EnemiesManager(MainCharacter mainCharacter) {
		rand = new Random();
		cactus1 = Resource.getResouceImage("data/cactus1.png");
		cactus2 = Resource.getResouceImage("data/cactus2.png");
		bird = Resource.getResouceImage("data/bird1.png");
		enemies = new ArrayList<Enemy>();
		this.mainCharacter = mainCharacter;
		enemies.add(createEnemy());
	}
	
	public void update() {
		for(Enemy e : enemies) {
			e.update();
		}
		Enemy enemy = enemies.get(0);
		if(enemy.isOutOfScreen()) {
			enemies.clear();
			enemies.add(createEnemy());
		}
	}
	
	public void draw(Graphics g) {
		for(Enemy e : enemies) {
			e.draw(g);
		}
	}
	
	private Enemy createEnemy() {
		// if (enemyType = getRandom)
		int type = rand.nextInt(6);
		if(type <= 1) {
			return new Cactus(mainCharacter, 800, cactus1.getWidth() - 10, cactus1.getHeight() - 10, cactus1);
		} else if(type <= 3){
			return new Cactus(mainCharacter, 800, cactus2.getWidth() - 10, cactus2.getHeight() - 10, cactus2);
		}
		else if(type == 4){
			return new Bird(mainCharacter, 800, bird.getWidth() - 10, bird.getHeight() - 10, bird, true);
		}
		else{
			return new Bird(mainCharacter, 800, bird.getWidth() - 10, bird.getHeight() - 10, bird, false);
		}
	}
	
	public boolean isCollision() {
		for(Enemy e : enemies) {
			if (mainCharacter.getBound().intersects(e.getBound())) {
				return true;
			}
		}
		return false;
	}
	
	public void reset() {
		enemies.clear();
		enemies.add(createEnemy());
	}
	
}
