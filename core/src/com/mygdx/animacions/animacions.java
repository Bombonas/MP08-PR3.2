package com.mygdx.animacions;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class animacions extends ApplicationAdapter {
	SpriteBatch batch;
	Texture sheet;
	float stateTime = 0;
	TextureRegion frames[] = new TextureRegion[8];
	Animation<TextureRegion> hornet;
	
	@Override
	public void create () {
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
		hornet = new Animation<TextureRegion>(0.25f,frames);
	}//1381

	@Override
	public void render () {
		ScreenUtils.clear(0.25f, 0.47f, 0.32f, 1);

		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
		TextureRegion frame = hornet.getKeyFrame(stateTime*2.8f,true);

		batch.begin();
		batch.draw(frame, 200, 100);
		// si volem invertir el sentit, ho podem fer amb el paràmetre scaleX=-1
		/*batch.draw(frame, 200, 100, 0, 0,
				frame.getRegionWidth(),frame.getRegionHeight(),-1,1,0);*/
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		sheet.dispose();
	}
}
