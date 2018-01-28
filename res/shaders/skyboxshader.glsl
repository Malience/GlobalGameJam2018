@VERTEX
#version 330 core
layout (location = 0) in vec3 position;

out vec3 TexCoord0;

uniform mat4 projection;
uniform mat4 view;

void main()
{
    TexCoord0 = position;
    vec4 pos = projection * mat4(mat3(view)) * vec4(position, 1.0);
    gl_Position = pos.xyww;
} 

@FRAGMENT
#version 330 core
out vec4 FragColor;

in vec3 TexCoord0;

uniform samplerCube skybox;

void main()
{    
    FragColor = texture(skybox, TexCoord0);
}