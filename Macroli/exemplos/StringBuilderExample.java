package exemplos;
public class StringBuilderExample {
    public static void main(String[] args) {
        // Criando um StringBuilder
        StringBuilder stringBuilder = new StringBuilder("Exemplo de StringBuilder"+"\n"+"ABAcate");

        // Convertendo para minúsculas
        String conteudo = stringBuilder.toString().toLowerCase();
        String elementoProcurado = "abacate".toLowerCase();
        System.out.println(conteudo);
        // Encontrando a posição de um elemento
        int posicao = conteudo.indexOf(elementoProcurado);

        // Verificando se o elemento foi encontrado
        if (posicao != -1) {
            System.out.println("O elemento '" + elementoProcurado + "' foi encontrado na posição: " + posicao);
        } else {
            System.out.println("O elemento '" + elementoProcurado + "' não foi encontrado.");
        }
    }
}
