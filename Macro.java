import java.util.ArrayList;
import java.util.List;

public class Macro {
    private String name;
    private String[] parameters;
    private List<String> lines;

    public Macro(String name) {
        this.name = name;
        this.lines = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addLine(String line) {
        lines.add(line);
    }

    public List<String> getLines() {
        return lines;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

    public String[] getParameters() {
        return parameters;
    }
}
