package com.pixnbgames.tiled.map_layers;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Simple example about how to render a tiled map with libGDX.
 * 
 * @author pixnbgames
 */
public class MyGdxTiledGame extends ApplicationAdapter {
	
	// Assets manager
	private AssetManager manager;
	
	// Map
	private TiledMap map;
	private TiledMapTileLayer terrainLayer;
	private int[] decorationLayersIndices;
	

	// Map properties
	private int tileWidth, tileHeight,
	            mapWidthInTiles, mapHeightInTiles,
	            mapWidthInPixels, mapHeightInPixels;
	
	// Camera and render
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer renderer;
	
	@Override
	public void create () {
		// Map loading
		manager = new AssetManager();
		manager.setLoader(TiledMap.class, new TmxMapLoader());
		manager.load("maps/level.tmx", TiledMap.class);
		manager.finishLoading();
		map = manager.get("maps/level.tmx", TiledMap.class);
		
		// Reading map layers
		MapLayers mapLayers = map.getLayers();
		terrainLayer = (TiledMapTileLayer) mapLayers.get("terrain");
		decorationLayersIndices = new int[] {
				mapLayers.getIndex("decoration"),
				mapLayers.getIndex("background")
		};
		
		// Read properties
		MapProperties properties = map.getProperties();
		tileWidth         = properties.get("tilewidth", Integer.class);
		tileHeight        = properties.get("tileheight", Integer.class);
		mapWidthInTiles   = properties.get("width", Integer.class);
		mapHeightInTiles  = properties.get("height", Integer.class);
		mapWidthInPixels  = mapWidthInTiles  * tileWidth;
		mapHeightInPixels = mapHeightInTiles * tileHeight;
		
		// Set up the camera
		camera = new OrthographicCamera(320.f, 180.f);
		camera.position.x = mapWidthInPixels * .5f;
		camera.position.y = mapHeightInPixels * .35f;
		
		// Instantiation of the render for the map object
		renderer = new OrthogonalTiledMapRenderer(map);
	}

	@Override
	public void render () {
		// Clear screen
		Gdx.gl.glClearColor(.5f, .7f, .9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Update the camera
		camera.update();
		renderer.setView(camera);
		
		// Rendering
		renderer.render(decorationLayersIndices);
		renderer.getBatch().begin();
		renderer.renderTileLayer(terrainLayer);
		renderer.getBatch().end();
	}
	
	@Override
	public void dispose () {
		// Free resources
		manager.dispose();
	}
}
