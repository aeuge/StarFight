package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import static com.mygdx.game.MyGdxGame.x;
import static com.mygdx.game.MyGdxGame.y;

public class BackGround {
    class Star {
        Vector2 position;
        float speed;

        public Star () {
            position = new Vector2( (float) Math.random()*x, (float) Math.random()*y);

            speed = 1.0f + (float)Math.random()*4;
        }
        public void update() {
            position.x -= speed;
            if (position.x < -20 ) {
                position.x = x;
                position.y = (float) Math.random()*y;
            }
        }
    }

    Texture Texture;
    Texture TextureStar;
    Star[] stars;

    public void setTexture(Texture t) {
        Texture = t;
    }

    public Texture getTexture() {
        return Texture;
    }

    public BackGround () {
        Texture = new Texture("space.jpg");
        TextureStar = new Texture("star12.tga");
        stars = new Star[250];
        for (int i=0;i<stars.length;i++) {
            stars [i] = new Star ();
        }
    }

    public void render (SpriteBatch batch, int x,int y) {
        float x1 = (float) x/1280;
        float x2 = (float) y/720;
  //      System.out.println(x1);
        update();
        batch.draw(Texture,0,0,(int)x/2,(int)y/2,x,y,x1,x2,0,0,0,1280,720,false,false);
        for (int i=0;i<stars.length;i++) {
            batch.draw(TextureStar,stars[i].position.x,stars[i].position.y);
        }
    }

    public void update (){
        for (int i=0;i<stars.length;i++) {
            stars [i].update();
        }
    }
}
