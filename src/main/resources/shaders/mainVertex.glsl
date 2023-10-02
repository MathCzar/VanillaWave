#version 460 core

in vec3 position;
in vec3 inColor;
in vec2 texCoord;

out vec3 fragColor;
out vec2 outTextCoord;

uniform mat4 model;
uniform mat4 projection;
uniform mat4 view;

void main() {

    gl_Position = projection * view * model * vec4(position, 1.0);

    fragColor = inColor;
    outTextCoord = texCoord;

}