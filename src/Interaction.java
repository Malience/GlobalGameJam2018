import com.base.engine.rendering.Camera;

import math.Vector3f;
import primitives.AABB;

public class Interaction {
	public static GameObject[] interactable_objects;
	
	public static void pickup() {
		Camera camera = HelloWorld.player.camera;
		float closest = -1f;
		for(int i = 0; i < interactable_objects.length; i++) {
			GameObject object = interactable_objects[i];
			if(object == null) continue;
			float dist = simpleDetection(camera.pos, camera.forward, object.transform.pos);
			if(dist > 0) {
				closest = closest < 0 ? dist : (dist < closest) ? dist : closest;
			}
		}
		if(closest > 0) System.out.println("Works!");
		else System.out.println("Doesn't work!");
	}
	
	public static final float RANGE = .99f;
	public static final float MAX_DISTANCE_SQUARED = 8f * 8f; //Squared for efficiency
	
	private static final Vector3f v = new Vector3f();
	
	public static float simpleDetection(Vector3f pos, Vector3f dir, Vector3f objectpos) {
		float dist2 = pos.distanceSquared(objectpos);
		if(dist2 > MAX_DISTANCE_SQUARED) return -1f;
		
		pos.sub(objectpos, v);
		float dot = v.dot(dir);
		dot = dot * dot / dist2; //Normalize the dot product so we only have the angle cos
		
		if(dot > RANGE) return dist2;
		return -1f;
	}
}
