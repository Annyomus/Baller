package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;

public class MathGame extends ApplicationAdapter {

	World world;
	ShapeRenderer renderer;

	Ball ball;
	Ball[] balls;
	PolygonShape polygonShape;
	Body groundBody;
	BodyDef groundBodyDef;
;
	MicrophoneListener microphoneListener;

	@Override
	public void create () {
		world = new World(new Vector2(0, -50), true);

		renderer = new ShapeRenderer();
		renderer.setAutoShapeType(true);

		balls = new Ball[550];
		for (int i = 0; i < balls.length; i++) {
			balls[i] = new Ball(world);
		}
		groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(0, 0));
		groundBody = world.createBody(groundBodyDef);

		groundBody.setType(BodyDef.BodyType.StaticBody);
		polygonShape = new PolygonShape();
		polygonShape.setAsBox(1980, 40);
		groundBody.createFixture(polygonShape, 10);
		microphoneListener = new MicrophoneListener();
	}

	@Override
	public void render () {
		world.step(1/60f, 6, 2);
		float volume = microphoneListener.getVolume();

        for (Ball ball : balls) {

			System.out.println(volume);
			if (volume > ball.threshold) {
				Vector2 force = new Vector2(0, volume * ball.forceMultiplier);
				ball.circleBody.applyForceToCenter(force, true);
			}


			if (Gdx.input.isButtonPressed(com.badlogic.gdx.Input.Buttons.LEFT)) {
				float mouseX = Gdx.input.getX();
				float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
				Vector2 ballPosition = ball.circleBody.getPosition();
				float radius = ball.ballShape.getRadius();

				if (ballPosition.dst(mouseX, mouseY) <= radius + 80) {
					Vector2 direction = new Vector2(mouseX - ballPosition.x, mouseY - ballPosition.y).nor();
					ball.circleBody.applyForceToCenter(direction.scl(-11815000), true);
				}
			}
			if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
				float mouseX = Gdx.input.getX();
				float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
				Vector2 ballPosition = ball.circleBody.getPosition();
				float radius = ball.ballShape.getRadius();

				if (ballPosition.dst(mouseX, mouseY) <= radius + 80) {
					Vector2 direction = new Vector2(mouseX - ballPosition.x, mouseY - ballPosition.y).nor();
					ball.circleBody.applyForceToCenter(direction.scl(11815000), true);
				}
			}
		}

			ScreenUtils.clear(0, 0, 0.2f, 1);
		for (Ball ball : balls) {

			renderer.begin(ShapeRenderer.ShapeType.Filled);
			renderer.setColor( ball.color.r, ball.color.g, ball.color.b, 1);
			renderer.circle(ball.circleBody.getPosition().x, ball.circleBody.getPosition().y, ball.ballShape.getRadius());
			renderer.setColor(0,1,0,1);
			renderer.rect(0, 0, 1980, 40);
			renderer.end();

		}

	}

	@Override
	public void dispose () {
		for (Ball ball : balls) {
			ball.ballShape.dispose();
		}
		polygonShape.dispose();
		renderer.dispose();
	}
}
