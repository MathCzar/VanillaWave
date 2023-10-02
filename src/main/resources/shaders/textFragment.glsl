#version 460 core

in vec2 outTexCoord;

out vec4 outColor;

uniform sampler2D txtSampler;

void main()
{

    outColor = vec4(0.0, 0.0, 1.0, 1.0) * texture(txtSampler, outTexCoord);

}
