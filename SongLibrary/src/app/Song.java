package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//Holds detail for each song
public class Song {
    public int key;
    public String name;
    public String artist;
    public String album;
    public String year;

    public Song(){
        this(0,null, null, null, null);
    }

    public Song(int key, String name, String artist, String album, String year){
        this.key = key;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.year = year;
    }

    public static Song getSongFromKey(int key) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("songDatabase"));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split("\\|", -1);
            if(Integer.parseInt(data[0]) == key){
                return new Song(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]);
            }
        }
        csvReader.close();
        return null;
    }

    public static ArrayList<Integer> getSongOrder() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("songSort"));
        String row;
        ArrayList<Integer> order = new ArrayList<Integer>();
        row = csvReader.readLine();
        csvReader.close();
        String[] data = row.split(",");
        for(String temp : data){
            order.add(Integer.parseInt(temp));
        }
        return order;
    }
}
