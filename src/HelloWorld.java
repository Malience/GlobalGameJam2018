import java.util.Stack;

import com.base.engine.core.CoreEngine;
import com.base.engine.input.GLFWInputEngine;
import com.base.engine.input.InputMapping;
import com.base.engine.input.InputMapping.ActionPair;
import com.base.engine.input.InputMapping.RangePair;
import com.base.engine.rendering.Camera;
import com.base.math.Transform;

import math.Vector3f;

public class HelloWorld {
	public static float player_speed = .5f;
	public static float player_rotate_speed = 0.2f;
	public static Player player = new Player();
	public static GameObject[] objects;
	
	public static void main(String [] args) {
		player.transform = new Transform();
		objects = new GameObject[10];
		
		Stack<ActionPair> actions = InputMapping.actions = new Stack<ActionPair>();
		actions.push(new ActionPair("Jump", (Runnable)() -> System.out.println("Jump!")));
		actions.push(new ActionPair("ActionTest", (Runnable)() -> {System.out.println("Action Works!");}));
		actions.push(new ActionPair("LockCursor", Camera::lockCursor));
		actions.push(new ActionPair("UnlockCursor", Camera::unlockCursor));
		
		Stack<ActionPair> states = InputMapping.states = new Stack<ActionPair>();
		states.push(new ActionPair("MoveForward", Camera::moveForward));
		states.push(new ActionPair("MoveBackward", Camera::moveBackward));
		states.push(new ActionPair("MoveLeft", Camera::moveLeft));
		states.push(new ActionPair("MoveRight", Camera::moveRight));
		
		states.push(new ActionPair("MovePlayerForward", HelloWorld::movePlayerForward));
		states.push(new ActionPair("MovePlayerBackward", HelloWorld::movePlayerBackward));
		states.push(new ActionPair("MovePlayerLeft", HelloWorld::movePlayerLeft));
		states.push(new ActionPair("MovePlayerRight", HelloWorld::movePlayerRight));
		
		states.push(new ActionPair("RotatePlayerLeft", HelloWorld::rotatePlayerLeft));
		states.push(new ActionPair("RotatePlayerRight", HelloWorld::rotatePlayerRight));
		
		Stack<RangePair> ranges = InputMapping.ranges = new Stack<RangePair>(); 
		ranges.push(new RangePair("RotateYaw", Camera::rotateYaw));
		ranges.push(new RangePair("RotatePitch", Camera::rotatePitch));
		
		CoreEngine ce = new CoreEngine(new RenderingEngine(), new GLFWInputEngine(), new PhysicsEngine());
		ce.start();
	}
	public static Vector3f v = new Vector3f();
	public static void movePlayerForward() {
		player.transform.rot.transform(v.set(0, 0, player_speed));
		player.transform.translate(v);
	}
	
	public static void movePlayerBackward() {
		player.transform.rot.transform(v.set(0, 0, -player_speed));
		player.transform.translate(v);
	}
	
	public static void movePlayerLeft() {
		player.transform.rot.transform(v.set(player_speed, 0, 0));
		player.transform.translate(v);
	}
	
	public static void movePlayerRight() {
		player.transform.rot.transform(v.set(-player_speed, 0, 0));
		player.transform.translate(v);
	}
	
	public static void rotatePlayerLeft() {
		player.transform.rotate(0, player_rotate_speed, 0);
	}
	
	public static void rotatePlayerRight() {
		player.transform.rotate(0, -player_rotate_speed, 0);
	}
}
