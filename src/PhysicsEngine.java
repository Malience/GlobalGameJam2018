import com.base.engine.core.Engine;
import com.base.engine.core.Time;
import com.base.engine.rendering.opengl.Terrain;

public class PhysicsEngine implements Engine {
	public static GameObject[] physical_objects;
	
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		Player player = HelloWorld.player;
		Terrain terrain = RenderingEngine.terrain;
		float height = terrain.getHeight(player.transform.pos.x, player.transform.pos.z);
		
		player.transform.pos.y = height + 1f;
		player.transform.hasChanged();
		//System.out.println("PhysicsEngineDelta: " + Time.getDelta());
	}
	
}
