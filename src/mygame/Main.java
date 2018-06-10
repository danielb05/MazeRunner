package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @authors Emanuel(150329) e Daniel(132118) *
 *
 */
public class Main extends SimpleApplication implements ActionListener, PhysicsCollisionListener {

    private PlayerCameraNode player;
    private boolean up = false, down = false, left = false, right = false, replay = false;
    private BulletAppState state;
    private Prize prize;
    private Vector3f player_pos;
    private int timer = 0;
    DirectionalLight sun;
    DirectionalLight sun2;
    int mat[][];
    
    public static void main(String[] args) {
        Main app = new Main();
        app.setShowSettings(false);
        app.setDisplayFps(false);
        app.setDisplayStatView(false);
        app.start();

    }    

    @Override
    public void simpleInitApp() {
        
        if(!replay)
        {
            initKeys();  
            createLight();
        }
        mat = generateMaze(40);

        createPhisics();
        createFloor();
        createWalls();          
       
    }
    
    private int[][] generateMaze(int size)
    {
        int cont = 0;
                
        Maze maze = new Maze();
        
        //testano se os labirintos possuem solução
        if(!replay)
        {
            System.out.println("Por favor, aguarde um momento, estamos gerando o Labirinto");
            for(cont = 0; cont<3; cont++)
            {
                maze.generate(cont);
                if(maze.breadthFirstSearch(1, 2))
                {
                    System.out.println("Tudo OK!!! Labirinto: " + cont + " possui solução!");
                }
                else
                {
                    System.out.println("Erro!!! Labirinto: " + cont + " não possui solução!");
                }
            }
        }
        
        Random r = new Random();
        
        maze.generate(Math.abs(r.nextInt() % 3));
        
        return maze.getMaze();

    }

    @Override
    public void simpleUpdate(float tpf) {
        player.upDateKeys(tpf, up, down, left, right);
        
        if (timer++ == 100) {
            guiNode.getChildren().clear();
        }
    }

    @Override
    public void onAction(String binding, boolean value, float tpf) {
        switch (binding) {
            case "CharLeft":
                if (value) {
                    left = true;
                } else {
                    left = false;
                }
                break;
            case "CharRight":
                if (value) {
                    right = true;
                } else {
                    right = false;
                }
                break;
        }
        switch (binding) {
            case "CharForward":
                if (value) {
                    up = true;
                } else {
                    up = false;
                }
                break;
            case "CharBackward":
                if (value) {
                    down = true;
                } else {
                    down = false;
                }
                break;
        }

    }

    private void createPlayer(Vector3f posicao) {
        player = new PlayerCameraNode("player", assetManager, state, cam, posicao);
        rootNode.attachChild(player);
        flyCam.setEnabled(true);
    }

    private void initKeys() {
        inputManager.addMapping("CharLeft", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("CharRight", new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("CharForward", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("CharBackward", new KeyTrigger(KeyInput.KEY_DOWN));

        inputManager.addListener(this, "CharLeft", "CharRight");
        inputManager.addListener(this, "CharForward", "CharBackward");

    }

    private void createWalls() {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {

                if (mat[i][j] == 1) {
                    Box boxMesh = new Box(1f, 2f, 1f);
                    Geometry boxGeo = new Geometry("Bloco", boxMesh);
                    Material boxMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                    Texture monkeyTex = assetManager.loadTexture("Textures/wall.jpg");
                    boxMat.setTexture("ColorMap", monkeyTex);
                    boxGeo.setMaterial(boxMat);
                    rootNode.attachChild(boxGeo);

                    RigidBodyControl r = new RigidBodyControl(0);
                    boxGeo.addControl(r);

                    boxGeo.move(-40 + i * 2, 1, -40 + j * 2);
                    r.setPhysicsLocation(boxGeo.getLocalTranslation());

                    state.getPhysicsSpace().add(r);
                } else {
                    if (mat[i][j] == 2) {
                        createPrize(new Vector3f(-40 + i * 2, 1, -40 + j * 2));
                    }
                    if (mat[i][j] == 3) {
                        player_pos = new Vector3f(-40 + i * 2, 1, -40 + j * 2);
                        createPlayer(new Vector3f(-40 + i * 2, 1, -40 + j * 2));
                    }
                }
            }
        }

    }

    private void createPrize(Vector3f posicao) {
        prize = new Prize("car", assetManager, posicao);

        RigidBodyControl r = new RigidBodyControl(0);
        prize.getCarNode().getChild("car").addControl(r);
        state.getPhysicsSpace().add(r);

        rootNode.attachChild(prize.getCarNode());
    }

    private void restartGame() {
        
        guiNode.detachAllChildren();
        replay = true;
        showMessage();
        rootNode.getChildren().clear();
        rootNode.detachAllChildren();
        timer = 0;
        player = null;
        simpleInitApp();
        
    }

    private void showMessage(){
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText helloText = new BitmapText(guiFont, false);
        helloText.setSize(guiFont.getCharSet().getRenderedSize() * 3);
        helloText.setText("Você ganhou!");
        helloText.setLocalTranslation(this.settings.getWidth() / 2 - helloText.getSize() * 2.5f, 
        helloText.getLineHeight() + this.settings.getHeight() / 2, 0);
        guiNode.attachChild(helloText);
    }
    
    private void createPhisics() {
        state = new BulletAppState();
        stateManager.attach(state);
        state.getPhysicsSpace().addCollisionListener(this);
    }

    private void createLight() {
        sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-10.5f, -15f, -10.5f)).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

        sun2 = new DirectionalLight();
        sun2.setDirection((new Vector3f(10.5f, -15f, 10.5f)).normalizeLocal());
        sun2.setColor(ColorRGBA.White);
        rootNode.addLight(sun2);

    }

    @Override
    public void simpleRender(RenderManager rm) {

    }

    private void createFloor() {

        Box boxMesh = new Box(mat.length, 0.1f, mat[0].length);
        Geometry boxGeo = new Geometry("A Textured Box", boxMesh);
        Material boxMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture monkeyTex = assetManager.loadTexture("Textures/Floor.jpg");
        boxMat.setTexture("ColorMap", monkeyTex);
        boxGeo.setMaterial(boxMat);
        rootNode.attachChild(boxGeo);

        RigidBodyControl r = new RigidBodyControl(0);
        boxGeo.addControl(r);

        state.getPhysicsSpace().add(r);

    }

    @Override
    public void collision(PhysicsCollisionEvent event) {
        Spatial nodeA = event.getNodeA();
        Spatial nodeB = event.getNodeB();

        if (nodeA.getName().equals("player") && nodeB.getName().equals("car")) {
            rootNode.detachChildNamed("carNode");
            state.getPhysicsSpace().remove(nodeB);
            restartGame();

        } else if (nodeB.getName().equals("player") && nodeA.getName().equals("car")) {
            rootNode.detachChildNamed("carNode");
            state.getPhysicsSpace().remove(nodeA);
            restartGame();
        }

    }

}
