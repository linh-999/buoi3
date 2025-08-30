package utils;

public class IOUtils {
    public static void copy(java.io.InputStream input, java.io.OutputStream output) throws java.io.IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }
}
