package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import static com.mygdx.game.MyGdxGame.x;
import static com.mygdx.game.MyGdxGame.y;

public class Asteroid {
    static Texture texture;
    Vector2 position;
    float speed;
    float angle;
    float scale;
    Circle hitArea;

    public Asteroid () {
        if (texture == null) {
            texture = new Texture("asteroid64.png");
        }
        position = new Vector2(x+ (float) Math.random()*x, (float) Math.random()*y);
        speed = 4.0f + (float)Math.random()*4;
        angle = (float)Math.random()*360;
        scale = 0.8f + (float)Math.random();
        hitArea = new Circle();
        hitArea.setRadius(28*scale);
    }

    public void recreate () {
        position.x = x+ (float) Math.random()*x;
        position.y = (float) Math.random()*y;
        speed = 4.0f + (float)Math.random()*4;
        angle = (float)Math.random()*360;
        scale = 0.8f + (float)Math.random();
        hitArea.setRadius(28*scale);
    }

    public void render (SpriteBatch batch) {
        batch.draw(texture,position.x-32,position.y-32,32,32,64,64,scale,scale,angle,0,0,64,64,false,false);
    }
    public void update() {
        angle += speed/4.0f;
        position.x -= speed;
        if (position.x < -20 ) {
            recreate();
        }
        hitArea.setPosition(position);
    }
}
