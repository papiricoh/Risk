#version 330 core
layout (location = 0) in vec3 aPos; // La posición del vértice en el espacio del objeto

void main() {
    gl_Position = vec4(aPos, 1.0); // Transforma la posición del vértice al espacio de clip
}