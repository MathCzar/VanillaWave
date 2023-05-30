#version 460 core

in vec3 position;
in vec3 fragColor;
in vec2 texCoord;

out vec3 outColor;
out vec2 outTextCoord;

uniform mat4 model;
uniform mat4 projection;
uniform mat4 view;

void main() {

    gl_Position = projection * view * model * vec4(position, 1.0);

    outColor = fragColor;
    outTextCoord = texCoord;

}