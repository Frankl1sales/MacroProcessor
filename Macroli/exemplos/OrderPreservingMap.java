package exemplos;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderPreservingMap {
    public static void main(String[] args) {
        // Criando um LinkedHashMap para armazenar a ordem de chamada e os valores originais
        Map<Integer, String> orderPreservingMap = new LinkedHashMap<>();

        // Adicionando um exemplo de string à map
        addStringToMap(orderPreservingMap, "&INDEV, &BUFADR, &RECLTH");
        
        addStringToMap(orderPreservingMap, "&OUTDEV, &BUFADR, &RECLTH");
        // Exibindo o resultado na ordem de chamada
        for (Map.Entry<Integer, String> entry : orderPreservingMap.entrySet()) {
            System.out.println("Chamada " + entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void addStringToMap(Map<Integer, String> map, String inputString) {
        // Verificando se a string não está vazia
        if (!inputString.isEmpty()) {
            // Obtendo a ordem atual
            int order = map.size();

            // Adicionando a string à map
            map.put(order, inputString);
        }
    }
}
