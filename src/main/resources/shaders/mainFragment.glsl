#version 460 core
//varying out vec4 outColor;

in vec2 outTextCoord;

out vec4 fragColor;

uniform sampler2D txtSampler;

void main()
{
    fragColor = texture(txtSampler, outTextCoord);
}

//void main() {
//
//    outColor = vec4(1.0, 1.0, 0.0, 1.0);
//
//}