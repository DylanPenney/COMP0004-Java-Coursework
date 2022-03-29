package ucl.ac.uk.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Model {
    public Model() {
    }

    public String create(String noteName) throws IOException {

        noteName = noteName.replaceAll("[^a-zA-Z0-9]","").replace(" ", "");

        File f = new File ("./data/notes.csv");
        FileWriter fw = new FileWriter(f,true);

        ArrayList<String> contents = readFile(f);
        String newID = "1";
        if (contents.size() > 1) {
            String lastEntry = contents.get(contents.size() - 1);
            List<String> items = List.of(lastEntry.split(","));
            newID = String.valueOf(Integer.parseInt(items.get(0)) + 1);
        }
        List<String> newItems = new ArrayList<String>();

        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);
        String newFileName = noteName + ".txt";

        newItems.add(newID);
        newItems.add(noteName);
        newItems.add(date);
        newItems.add(newFileName);
        fw.write("\n" + String.join(",", newItems));
        fw.flush();

        File f2 = new File("./data/"+noteName+".txt");
        FileWriter fw2 = new FileWriter(f2, false);
        //fw2.write("test");
        fw2.close();
        return noteName;
    }

    public List<String> getNoteNames() {
        File f = new File ("./data/notes.csv");
        ArrayList<String> contents = readFile(f);
        ArrayList<String> noteNames = new ArrayList<String>();

        contents.remove(contents.get(0));
        if (contents.size() > 0) {
            for (String s : contents) {
                List<String> items = List.of(s.split(","));
                noteNames.add(items.get(1));
            }
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
            List<String> items = List.of(s.split(","));
            if (items.get(1).contains(keyword)){
                searchHits.add(items.get(1));
            }
        }
        return searchHits;
    }

    public void write(File f, String contents) throws IOException {
        FileWriter fw = new FileWriter(f, false);
        fw.write(contents);
        fw.close();
    }
}