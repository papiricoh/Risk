package org.papiricoh.game.map;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public class Province {
    private final int vbo;
    private final int vao;
    private float[] vertices;

    public Province(float[] vertices) {
        this.vertices = vertices;
        this.vbo = glGenBuffers();
        this.vao = glGenVertexArrays();

        // Cargar datos en el VBO y configurar el VAO
        glBindVertexArray(vao);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, this.vertices, GL_DYNAMIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void render() {
        // Enlazar el VAO y dibujar la geometría
        glBindVertexArray(vao);
        glDrawArrays(GL_TRIANGLES, 0, vertices.length / 3);

        // Desenlazar el VAO
        glBindVertexArray(0);
    }

    public void update(float[] newVertices) {
        this.vertices = newVertices;
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, this.vertices, GL_DYNAMIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void cleanup() {
        glDeleteBuffers(vbo);
        glDeleteVertexArrays(vao);
    }

}
