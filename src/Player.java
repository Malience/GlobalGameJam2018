import com.base.engine.data.Resources;
import com.base.engine.rendering.Camera;
import com.base.engine.rendering.Mesh;
import com.base.math.Transform;

import math.Matrix4f;

public class Player {
	public static Mesh mesh;
	Transform transform;
	public GameObject held_item;
	Camera camera;
	Transform heldItemTransform = new Transform();
	
	
	public Player() {
		heldItemTransform.translate(0, 0, 3);
	}
	
	public void interact() {
		if(held_item == null) {
			//Do stuff
		}
		
		
	}
}
