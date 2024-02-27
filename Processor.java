import java.util.StringTokenizer;

public class Processor {
    

    public void processLines(StringBuilder content){
        String lineContainsMacro = "MACRO";  // Correção aqui
        // divide cada linha em formato de elementos em array de strings
        String[] lines = content.toString().split("\n");
        MacroManager macroManager = new MacroManager();
        FileHandler fileHandler = new FileHandler();
        
        // imprimir linha desejada
        //if (lines.length > 5) {
        //    System.out.println(lines[5]);
        //} else {
         //   System.out.println("Índice 5 fora do alcance do array.");
        //}
    
        // Verifica se a palavra desejada está presente em algum token da linha (case-insensitive)
        
        int totalLines = lines.length;
        for (int i = 0; i < totalLines; i++) {
            String line = lines[i];
            StringTokenizer tokenizer = new StringTokenizer(line);
            // Verifica se há pelo menos um token disponível
            if (tokenizer.hasMoreTokens()) {
                String macroName = tokenizer.nextToken(); // Obtém o primeiro token sem avançar para o próximo

                
                if (line.toLowerCase().contains(lineContainsMacro.toLowerCase())) {
                    // cria uma macro para adicionar 
                    Macro macro = new Macro();
                    macro.setName(macroName);  // 
                    System.out.println("A palavra '" + lineContainsMacro + "' está presente na linha: " + line + " nome da Macro: " + macroName);
                    // pode ser adicionado na defTAB 
                    i = expandToDefTab(macro, line, lines, i);
                    macroManager.addMacro(macroName,macro);
                } else if(macroManager.containsMacro(line)){
                    String macroNameCall = macroManager.findMacroNameInLine(line);
                    Macro macroCall = macroManager.getMacroByName(macroNameCall);
    
                    String macroExpand = macroManager.replaceDefTab(macroCall, line);
                    fileHandler.writeOutputFile(macroExpand, "outputFile.asm");
                    
                }else{
                    fileHandler.writeOutputFile(line, "outputFile.asm");
                
                }
            }
        }
    }
    public int expandToDefTab(Macro macro, String line, String[] lines, int iterator) {
        // Adiciona a linha fornecida ao defTab
        macro.table.addStringToDefTab(line);
        /// configura os parametros
        macro.table.setParameters(line);
    
        // Encontrando a posição da linha fornecida no array lines
        int indexOfLine = -1;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].equals(line)) {
                indexOfLine = i;
                break;
            }
        }
    
        // Se a linha fornecida foi encontrada, continua a partir da próxima linha
        if (indexOfLine != -1) {
            for (int i = indexOfLine + 1; i < lines.length; i++) {
                String currentLine = lines[i];
                macro.table.addStringToDefTab(currentLine);
    
                if (currentLine.contains("MEND")) {
                    // Se a palavra "MEND" for encontrada, interrompe a iteração
                    System.out.println("--------------defTAB------------------");
                    macro.table.imprimirDefTab();
                    return i;
                }
            }
        } else {
            System.out.println("A linha fornecida não foi encontrada no array 'lines'.");
        }
    
        
        // Se não encontrou "MEND", retorna null ou uma indicação adequada para indicar que não há próxima linha
        return iterator;
    }
    
}
