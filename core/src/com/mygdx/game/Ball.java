package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.Random;

public class Ball {
    BodyDef bodyDef;
    CircleShape ballShape;
    ShapeRenderer renderer;
    FixtureDef fixtureDef;
    Fixture fixture;
    Body circleBody;
    Color color;
    float threshold;
    float forceMultiplier;

    Ball(World world){
        Random random = new Random();

        bodyDef = new BodyDef();
        ballShape = new CircleShape();
        ballShape.setRadius(11f);
        bodyDef.position.set(new Vector2(random.nextInt(1980), random.nextInt( 700) + 40));
        circleBody = world.createBody(bodyDef);
        circleBody.setType(BodyDef.BodyType.DynamicBody);
        fixtureDef = new FixtureDef();
        fixtureDef.restitution = 0.909f;
        fixtureDef.friction = 13.3f;
        fixtureDef.density = 3.4f;
        fixtureDef.shape = ballShape;
        fixture = circleBody.createFixture(fixtureDef);
        color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), 1);
        threshold = random.nextInt(500) + 500;
        forceMultiplier = threshold / 2;


    }
}
