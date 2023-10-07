#version 460 core

in vec2 outTexCoord;

out vec4 outColor;

uniform sampler2D txtSampler;

void main()
{

    outColor = texture(txtSampler, outTexCoord);

}
