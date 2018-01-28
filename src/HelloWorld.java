import java.util.Stack;

import com.base.engine.core.CoreEngine;
import com.base.engine.data.Mesh;
import com.base.engine.data.Resources;
import com.base.engine.data.Texture;
import com.base.engine.input.GLFWInputEngine;
import com.base.engine.input.InputMapping;
import com.base.engine.input.InputMapping.ActionPair;
import com.base.engine.input.InputMapping.RangePair;
import com.base.engine.rendering.Camera;
import com.base.engine.rendering.MaterialMap;
import com.base.math.Transform;

import math.Vector3f;

public class HelloWorld {
	public static float player_speed = .5f;
	public static float player_rotate_speed = 0.2f;
	public static Player player = new Player();
	public static GameObject[] objects;
	
	public static void main(String [] args) {
		player.transform = new Transform();
		
		
		Stack<ActionPair> actions = InputMapping.actions = new Stack<ActionPair>();
		actions.push(new ActionPair("Jump", (Runnable)() -> System.out.println("Jump!")));
		actions.push(new ActionPair("ActionTest", (Runnable)() -> {System.out.println("Action Works!");}));
		actions.push(new ActionPair("LockCursor", Camera::lockCursor));
		actions.push(new ActionPair("UnlockCursor", Camera::unlockCursor));
		actions.push(new ActionPair("Interact", Interaction::pickup));
		
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
		
		player.camera = RenderingEngine.camera;
		
		objects = new GameObject[10]; //Feel free to increase size, adding objects to the array will render them
		PhysicsEngine.physical_objects = new GameObject[5]; //Holds objects that will fall and interact with enviroment (not implemented)
		Interaction.interactable_objects = new GameObject[5];
		//Game objects have transforms and meshes just like objects in unity
		//Game Objects can also have a collider which should be an AABB from primitives.AABB
		
		Mesh mesh = Resources.loadMesh("Basic Cube 2.obj"); //You can load a mesh with this method, supports all filetypes suported by Assimp
		
		MaterialMap material = new MaterialMap();				//Create Materials
		Texture texture = Resources.loadTexture("bricks.png"); //You can load Textures with this
		material.diffuse = texture.texture;						//Assigns the diffuse texture to the material (Diffuse is the color)
		material.specular = material.diffuse;					//Assigns the specular texture to the material (Specular is the shiny parts)
		material.shininess = 0f;
		
		//Materials and meshes can be shared between objects, Transforms should not (Unless you know what you're doing)
		
		objects[0] = new GameObject(new Transform(5,3,2), mesh, material);
		
		
		PhysicsEngine.physical_objects[0] = Interaction.interactable_objects[0] = objects[0]; //To add objects to the physical and interactable objects
		
		// Load basket
		mesh = Resources.loadMesh("basket.obj"); //You can load a mesh with this method, supports all filetypes suported by Assimp
		
		material = new MaterialMap();				//Create Materials
		texture = Resources.loadTexture("basket.png"); //You can load Textures with this
		material.diffuse = texture.texture;						//Assigns the diffuse texture to the material (Diffuse is the color)
		material.specular = material.diffuse;					//Assigns the specular texture to the material (Specular is the shiny parts)
		material.shininess = 0f;
		
		//Materials and meshes can be shared between objects, Transforms should not (Unless you know what you're doing)
		
		objects[1] = new GameObject(new Transform(10,2,2), mesh, material);
		
		// Load watermelons
		mesh = Resources.loadMesh("watermelon.obj"); //You can load a mesh with this method, supports all filetypes suported by Assimp
		
		material = new MaterialMap();				//Create Materials
		texture = Resources.loadTexture("watermelon.png"); //You can load Textures with this
		material.diffuse = texture.texture;						//Assigns the diffuse texture to the material (Diffuse is the color)
		material.specular = material.diffuse;					//Assigns the specular texture to the material (Specular is the shiny parts)
		material.shininess = 0f;
		
		//Materials and meshes can be shared between objects, Transforms should not (Unless you know what you're doing)
		
		objects[2] = new GameObject(new Transform(-2,2,2), mesh, material);
		objects[3] = new GameObject(new Transform(-4,2,2), mesh, material);
		objects[4] = new GameObject(new Transform(-2,2,4), mesh, material);
		
		
		PhysicsEngine.physical_objects[1] = Interaction.interactable_objects[1] = objects[2];
		PhysicsEngine.physical_objects[2] = Interaction.interactable_objects[2] = objects[3];
		PhysicsEngine.physical_objects[3] = Interaction.interactable_objects[3] = objects[4];
		
		
		
		// Load USBs
		mesh = Resources.loadMesh("usb.obj"); //You can load a mesh with this method, supports all filetypes suported by Assimp
		
		material = new MaterialMap();				//Create Materials
		texture = Resources.loadTexture("usb.png"); //You can load Textures with this
		material.diffuse = texture.texture;						//Assigns the diffuse texture to the material (Diffuse is the color)
		material.specular = material.diffuse;					//Assigns the specular texture to the material (Specular is the shiny parts)
		material.shininess = 0f;
		
		//Materials and meshes can be shared between objects, Transforms should not (Unless you know what you're doing)

		objects[5] = new GameObject(new Transform(-10,2,5), mesh, material);
		
		PhysicsEngine.physical_objects[4] = Interaction.interactable_objects[4] = objects[5];
		
		// Load skybox
		mesh = Resources.loadMesh("skybox.obj"); //You can load a mesh with this method, supports all filetypes suported by Assimp
		
		material = new MaterialMap();				//Create Materials
		texture = Resources.loadTexture("1496310477.azureparagon_desloate_rain_field_hills.jpg"); //You can load Textures with this
		material.diffuse = texture.texture;						//Assigns the diffuse texture to the material (Diffuse is the color)
		material.specular = material.diffuse;					//Assigns the specular texture to the material (Specular is the shiny parts)
		material.shininess = 0f;
		
		//Materials and meshes can be shared between objects, Transforms should not (Unless you know what you're doing)

		objects[5] = new GameObject(new Transform(0,0,0), mesh, material);
				
		
		ce.run();
		
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
