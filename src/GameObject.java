import com.base.engine.data.Mesh;
import com.base.engine.rendering.MaterialMap;
import com.base.math.Transform;

import primitives.Primitive;

public class GameObject {
	public Primitive collider;
	public Transform transform;
	public Mesh mesh;
	public MaterialMap material;
	
	public GameObject(Transform transform, Mesh mesh, MaterialMap material) {
		this.transform = transform; this.mesh = mesh; this.material = material;
	}
	
	public GameObject(Transform transform, Mesh mesh, MaterialMap material, Primitive collider) {
		this.transform = transform; this.mesh = mesh; this.material = material; this.collider = collider;
	}
	
}
