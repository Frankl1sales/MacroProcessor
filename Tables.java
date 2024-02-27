import java.util.LinkedHashMap;
import java.util.Map;

public class Tables {
    StringBuilder defTab;
    Map<Integer, String> agrTab;
    String parameters;

    public Tables(StringBuilder defTab) {
        this.defTab = defTab;
    }
    public Tables() {
        this.defTab = new StringBuilder();
        this.agrTab = new LinkedHashMap<>();
        this.parameters = new String();
    }
    // Método para incrementar defTab com uma string
    public void addStringToDefTab(String line) {
        defTab.append(line).append("\n");
    }

    // Método para imprimir o conteúdo de defTab
    public void imprimirDefTab() {
        System.out.println(defTab.toString());
    }
    // Método getter para obter a defTab como String
    public String getDefTab() {
        return defTab.toString();
    }



    public void addArgTab(String line){
        // encontra a posição do primeiro ""&""
        int indexOfAmpersand = line.indexOf("&");
        // verifica se foi encontrado
        if(indexOfAmpersand != -1){
            // obtenha a substring a partir de &
            String subString = line.substring(indexOfAmpersand);
            int order = agrTab.size();
            // adiciona a substring a map
            agrTab.put(order, subString);
        }
    }
    public String getAgrTab() {
        return agrTab.toString();
    }
    // obter parametros
    public void setParameters(String line) {
        // encontra a posição do primeiro ""&""
        int indexOfAmpersand = line.indexOf("&");
        // verifica se foi encontrado
        if(indexOfAmpersand != -1){
            // obtenha a substring a partir de &
            this.parameters = line.substring(indexOfAmpersand);
        }

    }
    public String getParamenters() {
        return parameters.toString();
    }

}
