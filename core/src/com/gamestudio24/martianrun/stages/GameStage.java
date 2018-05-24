package com.gamestudio24.martianrun.stages;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gamestudio24.martianrun.utils.WorldUtils;



/**
 * Created by salim on 5/23/2018.
 */

public class GameStage extends Stage {

    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;

    private World world;
    private Body ground;

    private final float TIME_STEP = 1/300f;
    private  float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

   public GameStage(){
       this.world = WorldUtils.createWorld();
       this.ground = WorldUtils.createGround(world);
       renderer = new Box2DDebugRenderer();
       setupCamera();

    }

    private void setupCamera() {

        camera = new OrthographicCamera(VIEWPORT_WIDTH,VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight,0f);
        camera.update();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        accumulator += delta;

        while (accumulator >= delta){
            world.step(TIME_STEP,6,2);
            accumulator -= TIME_STEP;
        }
    }

    @Override
    public void draw() {
        super.draw();
        renderer.render(world,camera.combined);
    }
}
