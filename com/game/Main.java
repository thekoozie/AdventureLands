package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import items.Item;
import items.ItemHandler;
import managers.*;

public class Main extends Game {
	public static long currentTimeMillis;

	SpriteBatch batch;

	Stage stageGui;
	public Stage stageWorld;

	Viewport viewport;

	float width;
	float height;
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;

	int levelPixelWidth;
	int levelPixelHeight;

	GuiManager guiManager = new GuiManager();

	@Override
	public void create () {
		SoundManager.playMusic("music.ogg", 0.25f);
		batch = new SpriteBatch();

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.update();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        tiledMap = new TmxMapLoader().load("map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        //Calculate the width height of level in pixels
        MapProperties mapProperties = tiledMap.getProperties();
        int levelWidth = mapProperties.get("width", Integer.class);
        int levelHeight = mapProperties.get("height", Integer.class);
        int tilePixelWidth = mapProperties.get("tilewidth", Integer.class);
        int tilePixelHeight = mapProperties.get("tileheight", Integer.class);
        levelPixelWidth = levelWidth * tilePixelWidth;
        levelPixelHeight = levelHeight * tilePixelHeight;


        stageWorld = new Stage(new ScreenViewport(camera));
        stageGui = new Stage(new ScreenViewport());

        Constants.setWorldStage(stageWorld);

        Gdx.input.setInputProcessor(stageGui);
        Gdx.input.setInputProcessor(stageWorld);

		TextManager.setSpriteBatch(batch, camera);
		PlayerManager.add(new Player(new Texture("character.png"), 50, 50, 2, 1, 0, 100,
                20, 20, 0, 3, "David", 1));
		String monster = Constants.getRandomMonster();
		EntityManager.add(new Entity(new Texture("monsters/"+monster+".png"), 460, 470, 100, monster, Constants.getRandomMonsterLevel(PlayerManager.getPlayer().level),
				Constants.getRandomMonsterHealth(PlayerManager.getPlayer().level),
                Constants.getRandomMonsterExp(PlayerManager.getPlayer().level),
                150, Constants.getRandomMonsterAttackSpeed(),
                Constants.getRandomMonsterArmor(PlayerManager.getPlayer().level),
                Constants.getRandomMonsterDamage(PlayerManager.getPlayer().level)));

		//ItemHandler.add(new Item(570,570, "item.png", "Dagger", stageWorld, 1, 2, 0, 0, "Weapon"));


        guiManager.create(stageGui);
	}

	@Override
	public void render () {
		currentTimeMillis = System.currentTimeMillis();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		GameInput.update();
		camera.position.x = Math.min(Math.max(PlayerManager.getPlayer().getX(), width/2), levelPixelWidth - (width/2));
		camera.position.y = Math.min(Math.max(PlayerManager.getPlayer().getY(), height/2), levelPixelHeight - (height/2));
        camera.update();

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        batch.begin();
        EntityManager.update();
		PlayerManager.update();
        IndicatorTextManager.update();
        batch.setProjectionMatrix(camera.combined);
		PlayerManager.draw(batch);
        EntityManager.draw(batch);
        IndicatorTextManager.draw(batch);

		batch.end();
        ItemHandler.update();
        ItemHandler.draw();
        guiManager.render();

        stageWorld.act(Gdx.graphics.getDeltaTime());
        stageWorld.draw();
        stageGui.act(Gdx.graphics.getDeltaTime());
        stageGui.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		guiManager.dispose();
	}
}
