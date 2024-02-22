import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MacroProcessor {
    private Map<String, Macro> macroTable;

    public MacroProcessor() {
        this.macroTable = new HashMap<>();
    }

    public void processMacros(String inputFileName, String outputFileName) {
        try {
            String fileContent = FileProcessor.readFile(inputFileName);
            processContent(fileContent);
            String result = processContent(fileContent);
            FileProcessor.writeFile(outputFileName, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processContent(String content) {
        StringBuilder result = new StringBuilder();
        String[] lines = content.split("\n");

        for (String line : lines) {
            line = line.trim();

            if (line.isEmpty()) {
                continue;  // Pular linhas em branco
            }

            System.out.println("Processing line: " + line);

            if (line.toUpperCase().startsWith("MACRO")) {
                Macro macro = parseMacroDefinition(line, lines);
                macroTable.put(macro.getName(), macro);
            } else {
                result.append(processLine(line)).append("\n");
            }
        }

        return result.toString().trim();
    }

    private Macro parseMacroDefinition(String line, String[] lines) {
        String[] parts = line.split("\\s+");
        String macroName = parts[1];
        Macro macro = new Macro(macroName);

        int i = 1;
        while (!lines[i].trim().toUpperCase().endsWith("MEND")) {
            macro.addLine(lines[i].trim());
            i++;
        }

        return macro;
    }

    private String processLine(String line) {
        String[] parts = line.split("\\s+");

        if (macroTable.containsKey(parts[0])) {
            Macro macro = macroTable.get(parts[0]);
            Stack<String> arguments = new Stack<>();
            for (int i = 1; i < parts.length; i++) {
                arguments.push(parts[i]);
            }

            return processMacro(macro, arguments);
        } else {
            return line;
        }
    }

    private String processMacro(Macro macro, Stack<String> arguments) {
        StringBuilder result = new StringBuilder();

        for (String macroLine : macro.getLines()) {
            if (macroLine.toUpperCase().startsWith("MACRO")) {
                Macro nestedMacro = parseNestedMacro(macroLine);
                result.append(processMacro(nestedMacro, arguments)).append("\n");
            } else {
                String expandedLine = macroLine;

                for (String parameter : macro.getParameters()) {
                    if (arguments.isEmpty()) {
                        break;
                    }

                    expandedLine = expandedLine.replace(parameter, arguments.pop());
                }

                result.append(expandedLine).append("\n");
            }
        }

        return result.toString().trim();
    }

    private Macro parseNestedMacro(String macroLine) {
        String[] parts = macroLine.split("\\s+");
        String macroName = parts[1];
        Macro macro = new Macro(macroName);

        // Assuming nested macros do not have parameters for simplicity
        int i = 1;
        while (!macroLine.trim().toUpperCase().endsWith("MEND")) {
            macro.addLine(macroLine);
            i++;
        }

        return macro;
    }

    public static void main(String[] args) {
        MacroProcessor processor = new MacroProcessor();
        processor.processMacros("entrada.asm", "MASMAPRG.ASM");
    }
}
