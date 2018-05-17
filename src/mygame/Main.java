package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

/**
 *
 * @authors Emanuel(150329) e Daniel(132118) *
 *
 */
public class Main extends SimpleApplication implements ActionListener, PhysicsCollisionListener{

    private PlayerCameraNode player;
    private boolean up = false, down = false, left = false, right = false;
    private BulletAppState state;
    private Node cars = new Node();
    private int totalCars = 0, totalItens=0;

    //0 -> Livre
    //1 -> Bloco com corpo rígido
    //2 -> Item
    //3 -> Player
    int mat[][] = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 3, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

    };

    public static void main(String[] args) {
        Main app = new Main();
        app.setShowSettings(false);
        app.start();

    }
    

    @Override
    public void simpleInitApp() {
        rootNode.attachChild(cars);
        createPhisics();
        createFloor();
        createLight();
        createWalls();
        initKeys();

    }

    @Override
    public void simpleUpdate(float tpf) {
        player.upDateKeys(tpf, up, down, left, right);
        
        for(Spatial d : cars.getChildren())
            d.rotate(0, tpf, 0);
  
        if(totalItens == totalCars){
            restartGame();
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
   
    }

    private void createFloor() {

        Box boxMesh = new Box(mat.length, 0.1f, mat[0].length);
        Geometry boxGeo = new Geometry("A Textured Box", boxMesh);
        Material boxMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture monkeyTex = assetManager.loadTexture("Textures/grama.jpg");
        boxMat.setTexture("ColorMap", monkeyTex);
        boxGeo.setMaterial(boxMat);
        rootNode.attachChild(boxGeo);

        RigidBodyControl r = new RigidBodyControl(0);
        boxGeo.addControl(r);

        state.getPhysicsSpace().add(r);

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
        player = new PlayerCameraNode("player", assetManager, state, cam,posicao);
        rootNode.attachChild(player);
        flyCam.setEnabled(true);
    }

    private void createPhisics() {
        state = new BulletAppState();
        stateManager.attach(state);
        state.getPhysicsSpace().addCollisionListener(this);
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

                    boxGeo.move(-40+i*2,1,-40+j*2);
                    r.setPhysicsLocation(boxGeo.getLocalTranslation());

                    state.getPhysicsSpace().add(r);
                }
                else
                {
                    if(mat[i][j] == 2){
                       createPrize(new Vector3f(-40+i*2,1,-40+j*2));
                       totalCars++;
                    }
                    if(mat[i][j]==3){
                       createPlayer(new Vector3f(-40+i*2,1,-40+j*2));
                    }
                }
            }
        }
        

    }

    private void createPrize(Vector3f posicao) {
        Spatial car = assetManager.loadModel("Models/Car.mesh.xml");
        car.setName("car");
        car.setLocalTranslation(posicao);
   
        createWheels(posicao);
        cars.attachChild(car);
        
        RigidBodyControl r = new RigidBodyControl(0);
        car.addControl(r);
        state.getPhysicsSpace().add(r);               
    }
    
    private void createWheels(Vector3f posicao) {
        
        float x = -0.42f;
        //Material defaultMat = new Material( assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        Material defaultMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        defaultMat.setBoolean("UseMaterialColors", true);
        defaultMat.setColor("Ambient", ColorRGBA.Black);
        defaultMat.setColor("Diffuse", ColorRGBA.Black);
        
        
        posicao.add(10f, 0f, 0f);
        Spatial wheel1 = assetManager.loadModel("Models/WheelBackLeft.mesh.xml");
        wheel1.setName("wheel1");
        wheel1.setLocalTranslation(posicao);
        wheel1.rotate(0, x, 0);
        wheel1.setMaterial(defaultMat);
        cars.attachChild(wheel1);
        
        posicao.add(0f, 0.5f, 0f);
        Spatial wheel2 = assetManager.loadModel("Models/WheelBackRight.mesh.xml");
        wheel2.setName("wheel2");
        wheel2.setLocalTranslation(posicao);
        wheel2.rotate(0, x, 0);
        wheel2.setMaterial(defaultMat);
        cars.attachChild(wheel2);
        
        posicao.add(-10f, 0f, 0f);
        Spatial wheel3 = assetManager.loadModel("Models/WheelFrontLeft.mesh.xml");
        wheel3.setName("wheel3");
        wheel3.setLocalTranslation(posicao);
        wheel3.rotate(0, x, 0);
        wheel3.setMaterial(defaultMat);
        cars.attachChild(wheel3);
        
        posicao.add(0f, -0.5f, 0f);
        Spatial wheel4 = assetManager.loadModel("Models/WheelFrontRight.mesh.xml");
        wheel4.setName("wheel4");
        wheel4.setLocalTranslation(posicao);
        wheel4.rotate(0, x, 0);
        wheel4.setMaterial(defaultMat);
        cars.attachChild(wheel4);

           
    }

    private void restartGame() {
       totalItens=0;
       totalCars=0;
       
        
       
       for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {

                    if(mat[i][j] == 2){
                       createPrize(new Vector3f(-40+i*2,1,-40+j*2));
                       totalCars++;
                    }
                  
                    
            }
        }
    }
    
    private void createLight() {
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-10.5f, -15f, -10.5f)).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

        DirectionalLight sun2 = new DirectionalLight();
        sun2.setDirection((new Vector3f(10.5f, -15f, 10.5f)).normalizeLocal());
        sun2.setColor(ColorRGBA.White);
        rootNode.addLight(sun2);

    }
    @Override
    public void collision(PhysicsCollisionEvent event) {
        Spatial nodeA = event.getNodeA();
        Spatial nodeB = event.getNodeB();
              
        if(nodeA.getName().equals("car"))
        {
            if(cars.getChildIndex(nodeA) != -1){
             state.getPhysicsSpace().remove(nodeA);
             cars.detachChild(nodeA);
             totalItens++;
            
            }
        }
        else{
         if(nodeB.getName().equals("car")){
             if(cars.getChildIndex(nodeB) != -1){
             state.getPhysicsSpace().remove(nodeB);
             cars.detachChild(nodeB);
             totalItens++;
             }
         }
        }
        
    }

}
