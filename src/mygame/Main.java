package mygame;

import com.jme3.app.SimpleApplication;
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
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @authors Emanuel(150329) e Daniel(132118) *
 *
 */
public class Main extends SimpleApplication implements ActionListener, PhysicsCollisionListener{

    private PlayerCameraNode player;
    private boolean up = false, down = false, left = false, right = false;
    private BulletAppState state;
    private Node ducks = new Node();
    private int totalDucks = 0, totalItens=0;

    //0 -> Livre
    //1 -> Bloco com corpo rígido
    //2 -> Item
    //3 -> Player
    int mat[][] = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                   {1,0,3,0,1,0,1,1,0,0,1,0,1,0,0,1,0,0,1,0,0,1,1,1,0,0,0,1,0,1,0,1,0,1,1,0,0,1,0,1},
                   {1,0,1,0,0,0,0,1,0,0,1,1,1,1,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,1,0,0,1,0,0,1,1,0,0,1},
                   {1,0,1,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0,0,1,0,0,1,0,0,1,1,0,0,1,0,1,0,0,1,1,1,0,1,1},
                   {1,0,1,0,1,1,1,0,0,0,0,1,1,1,1,0,0,0,1,0,0,1,1,0,1,0,1,1,0,0,1,1,0,1,0,1,0,1,0,1},
                   {1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,1,0,1,1,1,0,1},
                   {1,0,1,1,1,1,1,1,0,1,0,1,1,0,1,0,1,1,0,1,0,0,0,0,0,0,1,0,0,0,1,0,1,1,0,1,0,1,1,1},
                   {1,0,1,1,0,1,0,1,1,0,0,1,1,0,0,0,1,1,0,0,0,1,0,0,0,1,1,1,1,0,1,0,1,0,1,0,1,1,1,1},
                   {1,0,0,1,1,0,0,0,0,1,0,0,1,0,0,0,1,1,1,1,0,0,1,1,1,1,0,1,0,1,1,0,0,1,0,1,0,0,0,1},
                   {1,0,0,0,1,0,0,0,0,0,0,0,1,0,1,1,0,0,0,0,0,1,0,1,0,1,1,0,1,1,1,1,0,1,0,0,0,0,0,1},
                   {1,0,1,0,0,0,1,0,1,0,0,0,0,1,1,1,1,1,1,0,0,0,1,0,1,0,0,1,1,0,0,0,0,0,1,0,0,1,0,1},
                   {1,0,1,1,1,0,1,1,0,1,0,1,1,1,1,1,1,0,0,0,1,0,1,0,1,1,0,0,0,0,0,0,1,1,1,1,1,0,0,1},
                   {1,0,0,0,1,0,1,0,1,0,0,0,1,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,0,0,0,0,1},
                   {1,0,0,0,1,1,0,0,0,1,0,0,0,1,1,0,0,1,0,0,1,0,1,0,0,0,0,1,0,0,1,0,1,0,1,0,0,1,0,1},
                   {1,0,1,0,0,0,1,0,1,1,0,1,0,0,0,0,0,0,0,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                   {1,0,1,0,0,0,0,1,1,0,1,0,0,1,1,1,1,0,1,0,1,1,0,1,0,0,0,1,0,0,1,1,1,1,1,1,1,1,0,1},
                   {1,2,0,0,1,0,0,0,1,0,1,1,0,0,1,1,1,1,0,1,0,0,1,0,0,1,0,1,0,0,0,1,0,1,1,1,1,1,0,1},
                   {1,0,0,0,1,1,0,1,1,1,0,0,1,0,0,0,1,1,1,1,0,0,0,1,0,0,0,1,0,0,1,1,1,0,1,1,1,1,1,1},
                   {1,1,1,1,1,1,0,1,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,1,0,1,0,1,1,0,0,0,0,1,1},
                   {1,0,1,1,1,1,0,0,0,0,0,1,0,0,0,0,1,0,1,0,1,0,0,1,1,1,0,0,0,1,0,0,1,1,0,0,0,0,1,1},
                   {1,0,0,0,1,1,0,0,1,1,1,0,0,1,1,0,1,0,1,0,0,0,0,1,0,0,1,1,1,0,0,0,1,0,1,0,1,1,0,1},
                   {1,0,0,0,1,1,1,1,1,1,0,1,0,0,1,0,1,1,1,1,0,0,0,1,1,1,0,0,1,0,0,1,1,1,0,0,1,1,0,1},
                   {1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,0,0,0,1,0,1,0,0,1,1,1,1,1,1,0,0,1},
                   {1,0,1,1,1,1,0,0,1,0,0,1,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,1,1,0,1,1,0,1,0,1,0,1,1,1},
                   {1,0,1,1,1,1,0,1,1,0,0,1,0,0,1,1,1,1,1,0,1,0,1,0,1,0,0,1,1,0,0,0,0,1,0,0,1,0,0,1},
                   {1,0,1,1,0,1,0,0,0,0,1,1,0,0,1,1,1,0,0,0,0,1,0,0,0,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1},
                   {1,0,0,1,0,0,1,0,0,1,1,1,1,1,1,0,1,0,1,1,0,1,1,0,1,1,0,0,1,0,0,0,0,0,1,0,0,1,1,1},
                   {1,1,0,0,0,0,1,1,1,1,0,0,0,1,0,0,0,0,0,1,1,1,1,1,0,0,0,1,1,1,0,0,1,1,1,1,1,0,1,1},
                   {1,1,0,1,1,1,0,0,0,0,0,1,0,0,1,0,1,1,0,0,0,1,0,1,1,0,1,0,1,0,0,0,0,0,0,1,1,1,0,1},
                   {1,0,0,0,0,1,1,0,1,0,0,0,0,0,1,0,1,0,0,1,1,1,1,0,1,1,1,0,0,1,0,1,1,0,0,0,1,1,0,1},
                   {1,0,0,0,0,1,1,0,1,0,0,1,0,0,0,1,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,1,1,0,0,1,0,1},
                   {1,1,0,0,1,0,1,0,1,1,0,1,0,0,1,1,0,0,1,1,1,0,1,0,0,1,0,1,0,1,0,0,1,0,0,0,1,0,0,1},
                   {1,0,1,0,1,0,0,0,0,0,0,1,1,0,1,1,1,1,1,0,0,0,0,0,1,0,0,0,1,1,0,0,1,1,0,1,1,0,0,1},
                   {1,1,1,0,0,0,1,0,1,1,1,1,0,1,0,0,0,0,0,0,1,0,1,1,0,1,0,0,0,1,1,1,1,0,1,0,0,1,1,1},
                   {1,0,0,1,0,0,1,0,0,1,1,1,0,1,1,0,1,0,0,0,0,0,1,1,0,0,0,0,1,1,1,0,1,0,1,1,0,0,1,1},
                   {1,0,0,1,0,1,0,0,1,0,1,0,0,1,0,0,0,0,0,1,1,0,0,0,1,0,1,0,0,1,0,0,0,0,1,1,1,0,0,1},
                   {1,0,1,0,1,0,1,1,0,1,0,1,0,0,0,0,1,0,1,1,0,0,1,1,1,0,1,0,0,1,1,0,0,0,1,0,0,1,0,1},
                   {1,1,1,1,0,1,0,0,0,1,1,1,1,0,0,0,0,0,0,0,1,0,0,1,0,1,1,1,0,0,0,0,0,0,1,0,1,0,0,1},
                   {1,0,1,0,1,1,0,0,0,0,1,0,1,1,0,0,1,0,1,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,1,0,1,1,0,1},
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};;

    public static void main(String[] args) {
        Main app = new Main();
        app.setShowSettings(false);
        app.start();

    }
    

    @Override
    public void simpleInitApp() {
        rootNode.attachChild(ducks);
        createPhisics();
        createFloor();
        createLight();
        createWalls();
        initKeys();
        
        System.out.println(breadthFirstSearch(1, 2));
    }
    
    private boolean breadthFirstSearch(int bi, int bj)
    {
        SimpleEntry begin = new SimpleEntry(bi, bj);
        ArrayList<SimpleEntry> visiteds = new ArrayList<>();
        Queue<SimpleEntry> Q = new LinkedList();
        
        Q.add(begin);
        
        while (Q.size() > 0)
        {
            SimpleEntry actual = Q.remove();
            System.out.println(actual);
            visiteds.add(actual);
            
            if (mat[(int)actual.getKey()][(int)actual.getValue()] == 2) {
                // End search
                return true;
            }
            
            if ((int)actual.getKey() + 1 < mat.length && 
                    !isVisited(visiteds, (int)actual.getKey() + 1, (int)actual.getValue()) && 
                    mat[(int)actual.getKey() + 1][(int)actual.getValue()] != 1) {
                
                Q.add(new SimpleEntry((int)actual.getKey() + 1, (int)actual.getValue()));
                
            }
            
            if ((int)actual.getKey() - 1 >= 0 && 
                    !isVisited(visiteds, (int)actual.getKey() - 1, (int)actual.getValue()) && 
                    mat[(int)actual.getKey() - 1][(int)actual.getValue()] != 1) {
                
                Q.add(new SimpleEntry((int)actual.getKey() - 1, (int)actual.getValue()));
                
            }
            
            if ((int)actual.getValue() + 1 < mat.length && 
                    !isVisited(visiteds, (int)actual.getKey(), (int)actual.getValue() + 1) && 
                    mat[(int)actual.getKey()][(int)actual.getValue() + 1] != 1) {
                
                Q.add(new SimpleEntry((int)actual.getKey(), (int)actual.getValue() + 1));
                
            }
            
            if ((int)actual.getValue() - 1 >= 0 && 
                    !isVisited(visiteds, (int)actual.getKey(), (int)actual.getValue() - 1) && 
                    mat[(int)actual.getKey()][(int)actual.getValue() - 1] != 1) {
                
                Q.add(new SimpleEntry((int)actual.getKey(), (int)actual.getValue() - 1));
                
            }
        }
        
        return false;
    }
    
    private boolean isVisited(ArrayList<SimpleEntry> lst, int begin, int end)
    {
        for (SimpleEntry e : lst) {
            if ((int)e.getKey() == begin && (int)e.getValue() == end)
                return true;
        }
        
        return false;
    }

    @Override
    public void simpleUpdate(float tpf) {
        player.upDateKeys(tpf, up, down, left, right);
        
        for(Spatial d : ducks.getChildren())
            d.rotate(0, tpf, 0);
  
        if(totalItens == totalDucks){
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
                       totalDucks++;
                    }
                    if(mat[i][j]==3){
                       createPlayer(new Vector3f(-40+i*2,1,-40+j*2));
                    }
                }
            }
        }
        

    }

    private void createPrize(Vector3f posicao) {
        Spatial duck = assetManager.loadModel("Models/Duck.gltf");
        duck.setName("duck");
        duck.setLocalTranslation(posicao);
        Material defaultMat = new Material( assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        duck.setMaterial(defaultMat);
        ducks.attachChild(duck);
        
        RigidBodyControl r = new RigidBodyControl(0);
        duck.addControl(r);
        state.getPhysicsSpace().add(r);       
        
        
    }

    private void restartGame() {
       totalItens=0;
       totalDucks=0;
       
        
       
       for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {

                    if(mat[i][j] == 2){
                       createPrize(new Vector3f(-40+i*2,1,-40+j*2));
                       totalDucks++;
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
              
        if(nodeA.getName().equals("duck"))
        {
            if(ducks.getChildIndex(nodeA) != -1){
             state.getPhysicsSpace().remove(nodeA);
             ducks.detachChild(nodeA);
             totalItens++;
            
            }
        }
        else{
         if(nodeB.getName().equals("duck")){
             if(ducks.getChildIndex(nodeB) != -1){
             state.getPhysicsSpace().remove(nodeB);
             ducks.detachChild(nodeB);
             totalItens++;
             }
         }
        }
        
    }

}
