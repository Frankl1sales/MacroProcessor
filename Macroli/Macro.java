public class Macro{
    Tables table;
    String name;

    // Construtor que recebe uma instância existente de Tables
    public Macro(Tables table, String name) {
        this.table = table;
        this.name = name;
    }

    // Construtor que cria uma nova instância de Tables
    public Macro() {
        this.table = new Tables();
        this.name = new String();
    }
    // Método para obter o valor de name
    public String getName() {
        return name;
    }

    // Método para definir o valor de name
    public void setName(String name) {
        this.name = name;
    }
    // Método para adicionar uma string à defTab da instância de Tables
    public void addStringToDefTab(String newString) {
        table.addStringToDefTab(newString);
    }
    public String getDeftaString(){
        return table.getDefTab();
    }


    /*
     * Aqui ocorre a segunda fase do desenolvimento
     * é feita a substituição de parametro por argumento
     * 
     */
    public void addArgTab(String line){
        table.addArgTab(line);
    }
    public String getArgTab(){
        return table.getAgrTab();
    }

    // obter parametros
    public void setParameters(String line) {
        // encontra a posição do primeiro ""&""
        table.setParameters(line);

    }
    public String getParamenters() {
        return table.getParamenters();
    }

    // Método para imprimir o conteúdo de defTab da instância de Tables
    public void imprimirDefTab() {
        table.imprimirDefTab();
    }
   
}
