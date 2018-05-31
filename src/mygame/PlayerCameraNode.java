package mygame;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl;

/**
 * @authors Emanuel(150329) e Daniel(132118) *
 *
 */
public class PlayerCameraNode extends Node {

    private BetterCharacterControl physicsCharacter;
    private final AnimControl animationControl;
    private final AnimChannel animationChannel;
    private Vector3f walkDirection = new Vector3f(0, 0, 0);
    private Vector3f viewDirection = new Vector3f(0, 0, 0);
    private float airTime;
    private  CameraNode camNode;
    private final Node oto;
    
    public PlayerCameraNode(String name, AssetManager assetManager, BulletAppState bulletAppState, Camera cam, Vector3f posicao) {
        super(name);

       oto = (Node) assetManager.loadModel("Models/Oto/Oto.mesh.xml");
        oto.setLocalTranslation(0, 4.5f, 0);
        super.scale(0.25f);
        super.setLocalTranslation(posicao);
        super.attachChild(oto);

        physicsCharacter = new BetterCharacterControl(0.9f, 2.5f, 16f);

        super.addControl(physicsCharacter);

        bulletAppState.getPhysicsSpace().add(physicsCharacter);

        animationControl = oto.getControl(AnimControl.class);
        animationChannel = animationControl.createChannel();

        camNode = new CameraNode("CamNode", cam);
        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        camNode.setLocalTranslation(new Vector3f(0, 20, -30));
        camNode.lookAt(oto.getLocalTranslation(), Vector3f.UNIT_Y);

        super.attachChild(camNode);

    }

    public Vector3f getWalkDirection() {
        return walkDirection;
    }

    public void setWalkDirection(Vector3f walkDirection) {
        this.walkDirection = walkDirection;
    }

    public Vector3f getViewDirection() {
        return viewDirection;
    }

    public void setViewDirection(Vector3f viewDirection) {
        this.viewDirection = viewDirection;
    }

    void upDateAnimationPlayer() {

        if (walkDirection.length() == 0) {
            if (!"stand".equals(animationChannel.getAnimationName())) {
                animationChannel.setAnim("stand", 1f);
            }
        } else {
            if (airTime > .3f) {
                if (!"stand".equals(animationChannel.getAnimationName())) {
                    animationChannel.setAnim("stand");
                }
            } else if (!"Walk".equals(animationChannel.getAnimationName())) {
                animationChannel.setAnim("Walk", 0.7f);
            }
        }

    }

    void upDateKeys(float tpf, boolean up, boolean down, boolean left, boolean right) {
        Vector3f camDir = getWorldRotation().mult(Vector3f.UNIT_Z);

        viewDirection.set(camDir);
        walkDirection.set(0, 0, 0);

        if (up) {
            walkDirection.addLocal(camDir.mult(3));
        } else if (down) {
            walkDirection.addLocal(camDir.mult(3).negate());
        }

        if (left) {
            Quaternion rotateL = new Quaternion().fromAngleAxis(FastMath.PI * tpf, Vector3f.UNIT_Y);
            rotateL.multLocal(viewDirection);
        } else if (right) {
            Quaternion rotateR = new Quaternion().fromAngleAxis(-FastMath.PI * tpf, Vector3f.UNIT_Y);
            rotateR.multLocal(viewDirection);
        }

        physicsCharacter.setWalkDirection(walkDirection);
        physicsCharacter.setViewDirection(viewDirection);

        upDateAnimationPlayer();

    }
}
