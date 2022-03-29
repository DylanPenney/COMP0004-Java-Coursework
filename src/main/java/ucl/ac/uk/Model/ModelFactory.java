package ucl.ac.uk.Model;

import java.io.File;
import java.io.IOException;

public class ModelFactory {
    private static Model model;

    public ModelFactory() {
    }

    public static Model getModel() throws IOException {
        if (model == null) {
            model = new Model();
            model.readFile(new File("./data/notes.csv"));
        }

        return model;
    }
}
