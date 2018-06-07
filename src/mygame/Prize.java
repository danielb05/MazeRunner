/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingVolume;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.export.Savable;
import com.jme3.light.LightList;
import com.jme3.material.MatParamOverride;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Transform;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.util.SafeArrayList;
import java.util.HashMap;

/**
 *
 * @authors Emanuel(150329) e Daniel(132118) 
 *
 */
public class Prize extends Node {
    
        private Vector3f position;
        private Node carNode = new Node("carNode");
    
        public Prize(String name, AssetManager assetManager, Vector3f posicao)
        {
            Spatial car = assetManager.loadModel("Models/Car.mesh.xml");
            car.setName(name);
            car.setLocalTranslation(posicao);
            carNode.attachChild(car);
            createWheels(posicao, assetManager);
        }

    
        private void createWheels(Vector3f posicao, AssetManager assetManager) {
        
        Material defaultMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        defaultMat.setBoolean("UseMaterialColors", true);
        defaultMat.setColor("Ambient", ColorRGBA.Black);
        defaultMat.setColor("Diffuse", ColorRGBA.Black);
        
        
        posicao.add(10f, 0f, 0f);
        Spatial wheel1 = assetManager.loadModel("Models/WheelBackLeft.mesh.xml");
        wheel1.setName("wheel1");
        wheel1.setLocalTranslation(posicao);
        wheel1.setMaterial(defaultMat);
        carNode.attachChild(wheel1);
        
        
        posicao.add(0f, 0.5f, 0f);
        Spatial wheel2 = assetManager.loadModel("Models/WheelBackRight.mesh.xml");
        wheel2.setName("wheel2");
        wheel2.setLocalTranslation(posicao);
        wheel2.setMaterial(defaultMat);
        carNode.attachChild(wheel2);
        
        
        posicao.add(-10f, 0f, 0f);
        Spatial wheel3 = assetManager.loadModel("Models/WheelFrontLeft.mesh.xml");
        wheel3.setName("wheel3");
        wheel3.setLocalTranslation(posicao);
        wheel3.setMaterial(defaultMat);
        carNode.attachChild(wheel3);
        
        
        posicao.add(0f, -0.5f, 0f);
        Spatial wheel4 = assetManager.loadModel("Models/WheelFrontRight.mesh.xml");
        wheel4.setName("wheel4");
        wheel4.setLocalTranslation(posicao);
        wheel4.setMaterial(defaultMat);
        carNode.attachChild(wheel4);    

    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Node getCarNode() {
        return carNode;
    }

    public void setCarNode(Node carNode) {
        this.carNode = carNode;
    }
    
    
}
