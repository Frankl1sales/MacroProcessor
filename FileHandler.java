

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    // é melhor que seja StringBuilder para manuseio de strings
    public StringBuilder readInputFile() {
        // content 
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("inputFile.asm"))) {
            String line;
            while ((line = reader.readLine()) != null) { // le linha por linha
                content.append(line).append("\n"); // adiciona a linha e um espaço  no content
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content; // retorna content
    }

    public void writeOutputFile(String content, String outputFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true))) {
            writer.append(content);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

