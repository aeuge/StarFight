package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import static com.mygdx.game.MyGdxGame.x;
import static com.mygdx.game.MyGdxGame.y;

public class Hero {
    Texture texture;
    Vector2 position;
    float speed;
    int fireRate;
    int fireCounter;

    public Hero () {
        texture = new Texture("ship64.png");
        position = new Vector2(100,100);
        speed = 10f;
        fireCounter = 0;
        fireRate = 8;
    }
    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            System.out.println("L pressed");
            fireCounter++;
            if (fireCounter>=fireRate) {
                fireCounter = 0;
                fire();
           }
        }

        if (!Gdx.input.isPeripheralAvailable(Input.Peripheral.HardwareKeyboard)) {
            System.out.println("no keyboard");

        }

        if (Gdx.input.isTouched()) {
            for (int j = 0; j < 10 ; j++) {
                if (Gdx.input.isTouched(j)) {
                    if ((Gdx.input.getX(j)>x-100) & (Gdx.input.getY(j)>y-100)) {
                        fireCounter++;
                        if (fireCounter>=fireRate) {
                            fireCounter = 0;
                            fire();
                        }
                    } else {
                        if (Gdx.input.getX(j) < position.x + 32) {
                            position.x -= speed;
                        }
                        if (Gdx.input.getX(j) > position.x + 32) {
                            position.x += speed;
                        }
                        if (y - Gdx.input.getY(j) < position.y + 32) {
                            position.y -= speed;
                        }
                        if (y - Gdx.input.getY(j) > position.y + 32) {
                            position.y += speed;
                        }
                    }
                }
            }

        }

        if (position.x < 0 ) {
            position.x = 0;
        }
        if (position.x >x-60) {
            position.x = x-60;
        }
        if (position.y < 0 ) {
            position.y = 0;
        }
        if (position.y >y-60) {
            position.y = y-60;
        }
    }
    public void fire () {
        for (int i = 0; i < MyGdxGame.bullets.length;i++) {
            if (!MyGdxGame.bullets[i].active) {
                MyGdxGame.bullets[i].activate((int)position.x+48,(int)position.y+32);
                break;
            }

        }
    }
}
