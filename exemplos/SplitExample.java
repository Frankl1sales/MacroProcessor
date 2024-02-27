package exemplos;

public class SplitExample {
    public static void main(String[] args) {
        String inputString = "kkkkkkkkkghgg ggfdd &INDEV, &BUFADR, &RECLTH";

        // Encontrando a posição do primeiro "&" na string
        int indexOfAmpersand = inputString.indexOf("&");

        // Verificando se "&" foi encontrado
        if (indexOfAmpersand != -1) {
            // Obtendo a substring a partir do primeiro "&"
            String substring = inputString.substring(indexOfAmpersand);
            System.out.println(substring);
            
            // Dividindo a substring usando "&" como delimitador e removendo as vírgulas
            String[] elements = substring.split("&|,");

            // Exibindo os elementos resultantes
            for (String element : elements) {
                System.out.println(element.trim());
            }
        } else {
            System.out.println("Nenhum '&' encontrado na string.");
        }
    }
}
