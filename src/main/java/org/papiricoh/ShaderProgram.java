package org.papiricoh;
import java.io.InputStream;
import java.util.Scanner;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {

    private int programId;

    public ShaderProgram() {
        programId = glCreateProgram();
    }
    private String readFile(String path) {
        InputStream inputStream = getClass().getResourceAsStream(path);
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        String content = scanner.hasNext() ? scanner.next() : "";
        scanner.close();
        return content;
    }
    public void loadAndCompileShader(String path, int type) {
        String shaderSource = readFile(path);
        int shaderID = glCreateShader(type);

        glShaderSource(shaderID, shaderSource);
        glCompileShader(shaderID);

        // Check for compile errors
        int success = glGetShaderi(shaderID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(shaderID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: Shader compilation failed");
            System.out.println(glGetShaderInfoLog(shaderID, len));
            System.exit(1);
        }

        // Attach shader to program
        glAttachShader(programId, shaderID);
    }

    public void link() {
        // Link the program
        glLinkProgram(programId);

        // Check for linking errors
        int success = glGetProgrami(programId, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            int len = glGetProgrami(programId, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: Shader program linking failed");
            System.out.println(glGetProgramInfoLog(programId, len));
            System.exit(1);
        }
    }

    public void use() {
        // Tell OpenGL to use this shader program
        glUseProgram(programId);
    }

    public void cleanup() {
        glUseProgram(0);
        glDeleteProgram(programId);
    }
}