package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	BackGround background;
	Hero hero;
	Asteroid[] asteroids;
	Texture textureBullet;
	static Bullets[] bullets;
	static int x;
	static int y;

	public void create () {
		x = Gdx.graphics.getWidth();
		y = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		background = new BackGround();
        hero = new Hero();
		asteroids = new Asteroid[25];
		textureBullet = new Texture("bullet64x32.png");
		for (int i=0;i<asteroids.length;i++) {
			asteroids [i] = new Asteroid();
		}
		bullets = new Bullets[200];
		for (int i=0;i<bullets.length;i++) {
			bullets [i] = new Bullets();
		}
	}

	public void render () {
		update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//System.out.println(x);
		batch.begin();
		background.render(batch, x, y);
		hero.render(batch);
		for (int i=0;i<asteroids.length;i++) {
			asteroids[i].render(batch);
		}
		for (int i=0;i<bullets.length;i++) {
			if (bullets[i].active) {
				batch.draw(textureBullet, bullets[i].position.x - 32, bullets[i].position.y - 16);
			}
		}
		batch.end();
	}

	public void update () {
		x = Gdx.graphics.getWidth();
		y = Gdx.graphics.getHeight();
		Random r = new Random();
		//System.out.println(r.nextInt());
		r.nextInt();
		background.update();
        hero.update();
		for (int i=0;i<asteroids.length;i++) {
			asteroids [i].update();
		}
		for (int i=0;i<bullets.length;i++) {
			if (bullets[i].active) {
				bullets[i].update();
				for (int j = 0; j < asteroids.length; j++) {
					if (asteroids[j].hitArea.contains(bullets[i].position)){
						asteroids[j].recreate();
						bullets[i].deactivate();
						break;
					}

				}
			}
		}

	}

	public void dispose () {
		batch.dispose();
	}
}
