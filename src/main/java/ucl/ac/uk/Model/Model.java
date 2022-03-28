package ucl.ac.uk.Model;

import java.io.File;
import java.util.List;

public class Model {
    public Model() {
    }

    public List<String> getNoteNames() {
        return List.of("Name1", "Name2", "Name3");
    }

    public void readFile(File file) {
    }

    public List<String> searchFor(String keyword) {
        return List.of("Search keyword is: " + keyword, "result1", "result2", "result3");
    }
}