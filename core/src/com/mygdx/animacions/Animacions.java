package com.mygdx.animacions;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class Animacions implements Screen {
	SpriteBatch batch;
	Texture sheet;
	float stateTime = 0;
	TextureRegion frames[] = new TextureRegion[8];

	TextureRegion idleFrames[] = new TextureRegion[6];
	Animation<TextureRegion> hornet;

	Animation<TextureRegion> idleHornet;

	float x = 400;

	int VELOCITY = 10;
	Main game;

	boolean toLeft = true;
	Rectangle left, right;

	public Animacions(final Main game){
		this.game = game;
		batch = new SpriteBatch();
		sheet = new Texture("hornet.png");

		frames[0] = new TextureRegion(sheet,3,1191,159,190);
		frames[1] = new TextureRegion(sheet,165,1191,159,190);
		frames[2] = new TextureRegion(sheet,327,1191,159,190);
		frames[3] = new TextureRegion(sheet,489,1191,159,190);
		frames[4] = new TextureRegion(sheet,651,1191,159,190);
		frames[5] = new TextureRegion(sheet,813,1191,159,190);
		frames[6] = new TextureRegion(sheet,975,1191,159,190);
		frames[7] = new TextureRegion(sheet,1137,1191,159,190);

		idleFrames[0] = new TextureRegion(sheet,3,953,184,216);
		idleFrames[1] = new TextureRegion(sheet,190,953,184,216);
		idleFrames[2] = new TextureRegion(sheet,377,953,184,216);
		idleFrames[3] = new TextureRegion(sheet,564,953,184,216);
		idleFrames[4] = new TextureRegion(sheet,751,953,184,216);
		idleFrames[5] = new TextureRegion(sheet,938,953,184,216);

		idleHornet = new Animation<TextureRegion>(0.25f, idleFrames);
		hornet = new Animation<TextureRegion>(0.25f,frames);

		left = new Rectangle(0, 0, 800/2, 480);
		right = new Rectangle(800/2, 0, 800/2, 480);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0.25f, 0.47f, 0.32f, 1);

		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
		TextureRegion idleFrame = idleHornet.getKeyFrame(stateTime*2.85f,true);
		TextureRegion frame = hornet.getKeyFrame(stateTime*2.85f,true);

		batch.begin();

		int dir = virtual_joystick_control();

		switch (dir){
			case 0:
				if(toLeft){
					batch.draw(idleFrame, x, 100);
				}else{
					batch.draw(idleFrame, x+idleFrame.getRegionWidth(), 100, 0, 0,
						idleFrame.getRegionWidth(),idleFrame.getRegionHeight(),-1,1,0);
				}
				break;
			case 1:
				toLeft = true;
				x -= VELOCITY;
				batch.draw(frame, x, 100);
				break;
			case 2:
				toLeft = false;
				x += VELOCITY;
				batch.draw(frame, x+frame.getRegionWidth(), 100, 0, 0,
					frame.getRegionWidth(),frame.getRegionHeight(),-1,1,0);
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose () {
		batch.dispose();
		sheet.dispose();
	}

	protected int virtual_joystick_control() {
		// iterar per multitouch
		// cada "i" és un possible "touch" d'un dit a la pantalla
		for(int i=0;i<10;i++)
			if (Gdx.input.isTouched(i)) {
				Vector3 touchPos = new Vector3();
				touchPos.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);
				// traducció de coordenades reals (depen del dispositiu) a 800x480
				game.camera.unproject(touchPos);
				if (left.contains(touchPos.x, touchPos.y)) {
					return 1;
				} else if (right.contains(touchPos.x, touchPos.y)) {
					return 2;
				}
			}
		return 0;
	}
}
