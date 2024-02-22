import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MacroProcessor {
    private Map<String, Macro> macros;

    public MacroProcessor() {
        macros = new HashMap<>();
    }

    public void process(String inputFileName) {
        List<String> outputLines = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith("MACRO")) {
                    processMacroDefinition(br, line);
                } else if (line.trim().startsWith("CALL")) {
                    String macroName = line.trim().substring(5).trim();
                    Macro macro = macros.get(macroName);
                    if (macro != null) {
                        List<String> arguments = parseArguments(line);
                        List<String> expandedMacro = macro.expand(arguments);
                        outputLines.addAll(expandedMacro);
                    }else {
                    throw new IllegalArgumentException("Erro: Macro '" + macroName + "' não definida. Linha: " + line);
                    }
                } else {
                    outputLines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro de entrada/saída ao processar o arquivo.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("MASMAPRG.ASM"))) {
            for (String outputLine : outputLines) {
                bw.write(outputLine);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro de entrada/saída ao gravar o arquivo de saída.");
            e.printStackTrace();
        }
    }
    
    private void processMacroDefinition(BufferedReader br, String macroDefinition) throws IOException {
        String trimmedDefinition = macroDefinition.trim();
        if (!trimmedDefinition.toUpperCase().equals("MACRO")) {
            throw new IllegalArgumentException("Erro: Definição de macro inválida. Linha: " + trimmedDefinition);
        }
        
        String macroName = br.readLine().trim(); // Obtendo o nome da macro
        Macro macro = new Macro();
        List<String> macroLines = new ArrayList<>();
        
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().equals("MEND")) {
                break;
            }
            macroLines.add(line);
        }
        
        macro.setLines(macroLines);
        macros.put(macroName, macro);
    }
    
    
    private List<String> parseArguments(String line) {
        List<String> arguments = new ArrayList<>();
        String[] parts = line.trim().substring(5).trim().split(",");
        for (String part : parts) {
            arguments.add(part.trim());
        }
        return arguments;
    }

    public static void main(String[] args) {
        MacroProcessor processor = new MacroProcessor();
        processor.process("input.asm");
    }
}

class Macro {
    private List<String> lines;

    public List<String> expand(List<String> arguments) {
        List<String> expandedMacro = new ArrayList<>();
        for (String line : lines) {
            String expandedLine = line;
            for (int i = 0; i < arguments.size(); i++) {
                expandedLine = expandedLine.replace("&&" + (i + 1), arguments.get(i));
            }
            expandedMacro.add(expandedLine);
        }
        return expandedMacro;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
