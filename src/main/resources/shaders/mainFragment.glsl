#version 460 core
//varying out vec4 outColor;

in vec2 outTextCoord;

out vec4 outColor;

uniform sampler2D txtSampler;

void main()
{
    outColor = texture(txtSampler, outTextCoord);
}
