package ucl.ac.uk.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Model {
    public Model() {
    }

    public List<String> getNoteNames() {
        File f = new File ("./data/notes.csv");
        ArrayList<String> contents = readFile(f);
        ArrayList<String> noteNames = new ArrayList<String>();

        contents.remove(contents.get(0));
        for (String s : contents){
            List<String> items = List.of(s.split(", "));
            noteNames.add(items.get(1));
        }

        return noteNames;
    }

    public ArrayList<String> readFile(File file) {

        ArrayList<String> contents = new ArrayList<String>();
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()){
                contents.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return contents;
    }

    public ArrayList<String> searchFor(String keyword) {
        File f = new File("./data/notes.csv");
        ArrayList<String> contents = readFile(f);
        ArrayList<String> searchHits = new ArrayList<String>();
        contents.remove(contents.get(0));
        for (String s : contents){
            List<String> items = List.of(s.split(", "));
            if (items.get(1).contains(keyword)){
                searchHits.add(items.get(1));
            }
        }
        return searchHits;
    }
}