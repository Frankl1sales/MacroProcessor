import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class MacroProcessor {
    /*
     *  Um Map para armazenar definições de macros, onde as chaves são os 
     *   nomes das macros e os valores são objetos Macro
     */
    private Map<String, Macro> macroTable;
    // Construtor - inicia macroTable com uma hashmap vazia quando uma instancia de MacroProcessor é criada
    public MacroProcessor() {
        this.macroTable = new HashMap<>();
    }


    public void processMacros(String inputFileName, String outputFileName) {
        try {
             // Le o arquivo de entrada 
            String fileContent = FileProcessor.readFile(inputFileName);
            // processa o conteúdo
            String result = processContent(fileContent);
             // escreve o resultado no arquivo de saída
            FileProcessor.writeFile(outputFileName, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*Autor: Franklin
     * o método processContent percorre cada linha do conteúdo, identifica definições de macros (linhas
     * começando com "MACRO") e as armazena no macroTable. Para as linhas que não são definições
     * de macros, chama processLine para processar a linha e anexa o resultado ao StringBuilder. 
     * O conteúdo resultante é retornado como uma string.
     */ 
    private String processContent(String content) { // recebe uma string de conteúdo como entrada
        StringBuilder result = new StringBuilder(); // cria um objeto StringBuilder
        String[] lines = content.split("\n");/*
        /Divide a string content em um array de string, onde 
        / cada elemento representa uma linha do conteúdo original*/



        for (String line : lines) {// inicia um loop que percorre cada linha do array de linhas
            line = line.trim();// remove espaços em branco extras do inicio e do final de cada linha


            if (line.isEmpty()) {// verifica se a linha está vázia em após a remoção dos espaços
                continue;  // Pular linhas em branco
            }


            System.out.println("Processing line: " + line);/*  imprime a mensagem indicando 
            /que a linha está sendo processada*/


            if (line.toUpperCase().startsWith("MACRO")) { //Verifica se a linha é uma Macro
                Macro macro = parseMacroDefinition(line, lines); /* se for, chama o metodo 
                /para analisar definiçaão e retonar  um objeto Macro*/
                macroTable.put(macro.getName(), macro);// adiciona a Macro a macroTable
            } 
            result.append(processLine(line)).append("\n");// chama o metodo processLine
        }
        return result.toString().trim(); // retorna o conteúdo acumulado no StringBuilder como uma string
    }

    /*
     * Este método é responsável por analisar a definição de uma macro a partir de uma linha
     * específica (`line`) e do conjunto de linhas inteiro (`lines`). 
     */
    private Macro parseMacroDefinition(String line, String[] lines) {
        String[] parts = line.split("\\s+"); // divide a linha em partes usando espaços em branco
        String macroName = parts[1];
        Macro macro = new Macro(macroName);
        // verificador
        System.out.print("\n\n\n\n" + macroName+ "\n\n\n\n");
        int i = 1;
        while (!lines[i].trim().toUpperCase().endsWith("MEND")) {
            macro.addLine(lines[i].trim());
            i++;
        }
        return macro;
    }


    private String processLine(String line) {
        String[] parts = line.split("\\s+"); // divide a linha em partes


        if (macroTable.containsKey(parts[0])) {  // se o primeiro elemento da linha tiver na macroTable
            Macro macro = macroTable.get(parts[0]);  // obtem a macro correspondente
            Stack<String> arguments = new Stack<>(); // cria uma pilha para armazenar os argumentos
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
