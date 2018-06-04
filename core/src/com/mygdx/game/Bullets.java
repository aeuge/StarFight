package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Bullets {
    Vector2 position;
    float speed;
    boolean active;

    public Bullets () {
        position = new Vector2( 1, 1);
        speed = 12.0f;
        active = false;
    }
    public void activate (int x, int y) {
        position.x = x;
        position.y = y;
        active = true;
    }

    public void deactivate () {
        active = false;
    }
    public void update() {
            position.x += speed;
            if (position.x > 1300) {
                deactivate();
            }
    }

}
