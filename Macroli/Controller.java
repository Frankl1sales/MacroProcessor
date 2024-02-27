public class Controller {
    public static void main(String[] args) {
        StringBuilder content = new FileHandler().readInputFile();
        System.out.println("------------------------Leitura/armazenamento do arquivo inputFile----------------------------------------");
        System.out.println(content);
        System.out.println("------------------------Processamento Linha a Linha----------------------------------------");
        Processor enigma = new Processor();
        enigma.processLines(content);
    }
}
