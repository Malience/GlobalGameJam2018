import java.util.Stack;

import com.base.engine.core.CoreEngine;
import com.base.engine.input.GLFWInputEngine;
import com.base.engine.input.InputMapping;
import com.base.engine.input.InputMapping.ActionPair;
import com.base.engine.input.InputMapping.RangePair;
import com.base.engine.rendering.Camera;

import math.Vector3f;

public class HelloWorld {
	public static void main(String [] args) {
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
		
		Stack<RangePair> ranges = InputMapping.ranges = new Stack<RangePair>(); 
		ranges.push(new RangePair("RotateYaw", Camera::rotateYaw));
		ranges.push(new RangePair("RotatePitch", Camera::rotatePitch));
		
		CoreEngine ce = new CoreEngine(new GLFWRenderingEngine2(), new GLFWInputEngine());
		ce.start();
	}
}
