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
        fw2.write("");
        fw2.close();
        return noteName;
    }

    public void delete(String noteName) throws IOException {
        noteName = noteName.replaceAll("[^a-zA-Z0-9]","").replace(" ", "");
        File file = new File("./data/notes.csv");
        File newFile = new File("./data/temp.csv");
        FileWriter temp = new FileWriter("./data/temp.csv", false);

        temp.write("ID,NAME,CREATED,FILEPATH");

        Scanner sc = null;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if (line.equals("ID,NAME,CREATED,FILEPATH") == false){
                    List<String> items = List.of(line.split(","));
                    if (noteName.equals(items.get(1)) == false){
                        // If the line is to be kept
                        temp.write("\n" + line);
                    }
                }
            }
            temp.close(); // temp.csv is now a duplicate of notes.csv without the line to be deleted
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Now to make notes.csv a copy of temp.csv
        newFile.renameTo(file);

        // to delete the text file associated with noteName
        File toBeDeleted = new File("./data/"+ noteName+".txt");
        toBeDeleted.delete();
    }

    public void rename(String noteName, String newName) throws IOException {
        newName = newName.replaceAll("[^a-zA-Z0-9]","").replace(" ", "");
        if (newName.equals("") == false){
            // Rename the entry in the index
            String temp = create(newName);

            // 'Rename' the .txt file
            String oldFileDir = "./data/"+noteName+".txt";
            String newFileDir = "./data/"+newName+".txt";
            File oldFile = new File(oldFileDir);
            File newFile = new File(newFileDir);

            oldFile.renameTo(newFile);

            // Delete old file
            delete(noteName);
        }
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