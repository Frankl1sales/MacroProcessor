import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MacroManager {
    private Map<String, Macro> macros;

    public MacroManager() {
        this.macros = new HashMap<>();
    }

    public void addMacro(String name, Macro macro) {
        macros.put(name, macro);
        System.out.println("------------MACRO ADICIONADA EM MACROMANAGER--------------------");
    }

    public Macro getMacroByName(String name) {
        System.out.println("---------Chamada para MACRO encontrada-------------------");
        return macros.get(name);
    }

    
    public boolean isMacroCreated(String name) {
        return macros.containsKey(name);
    }
    public Set<String> getAllMacroNames() {
        return macros.keySet();
    }
    public boolean containsMacro(String line) {
        String lowercaseLine = line.toLowerCase();

        for (String macroName : macros.keySet()) {
            if (lowercaseLine.contains(macroName.toLowerCase())) {
                return true;
            }
        }

        return false;
    }
    public String findMacroNameInLine(String line) {
        String lowercaseLine = line.toLowerCase();

        for (String macroName : macros.keySet()) {
            if (lowercaseLine.contains(macroName.toLowerCase())) {
                return macroName;
            }
        }

        return null;
    }
     // agora começa a brincadeira de substituição
     public String replaceDefTab(Macro macro, String line){
        // encontra a posição do primeiro ""&""
        int indexOfAmpersand = line.indexOf("&");
        // verifica se foi encontrado
        if(indexOfAmpersand != -1){
            // obtenha a substring a partir de & - argumentos
            String subString = line.substring(indexOfAmpersand);
            String expand = macro.table.getDefTab();
            String par = macro.table.getParamenters();
            // Dividindo a substring usando "&" como delimitador e removendo as vírgulas
            String[] argumentos = subString.split(",");
            String[] parametros = par.split(",");

            for (int i = 0; i < expand.length() && i < parametros.length; i++) {
                expand = expand.replace(parametros[i].trim(),"&" + i);
            }
            for (int i = 0; i < expand.length() && i < argumentos.length; i++) {
                expand = expand.replace("&" + i, argumentos[i].trim());
            }
            System.out.println("---------------Replcace -----------------");
            System.out.println(expand);
        return expand;

        }else{System.out.println("ERRO NO REPLACE");return null;}
    }

}
