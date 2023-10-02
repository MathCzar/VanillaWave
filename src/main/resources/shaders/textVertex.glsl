#version 460 core

in vec3 position;
in vec4 inColor;
in vec2 texCoord;

out vec4 fragColor;
out vec2 outTexCoord;

uniform mat4 projection;
uniform mat4 model;

void main()
{
    gl_Position = projection * model * vec4(position, 1.0);

    fragColor = inColor;
    outTexCoord = texCoord;
}
