package view;


import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

import app.Song;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class SongController {
    @FXML ListView<String> songList;
    @FXML TextArea songDisplay;

    private ObservableList<String> obsList;
    ArrayList<Song> obsSongList = new ArrayList<Song>();

    public void updateList(ArrayList<Integer> order) throws IOException {
        obsList.clear();
        obsSongList.clear();
        for (int i = 0; i < order.size(); i++) {
            Song temp = Song.getSongFromKey(order.get(i));
            obsList.add(temp.name + ", by " + temp.artist);
            obsSongList.add(temp);
        }
        songList.setItems(obsList);
    }

    public void start(Stage mainStage) throws IOException {
        obsList = FXCollections.observableArrayList();

        ArrayList<Integer> songOrder = Song.getSongOrder();
        updateList(songOrder);

        // select the first item
        songList.getSelectionModel().select(0);
        if(songList.getSelectionModel().getSelectedIndex() != -1) {
            showSongDetails(mainStage);
        }

        // set listener for the items
        songList
                .getSelectionModel()
                .selectedIndexProperty()
                .addListener(
                        (obs, oldVal, newVal) ->
                                showSongDetails(mainStage));

    }

    private void showSongDetails(Stage mainStage) {
        int index = songList.getSelectionModel().getSelectedIndex();
        Song temp = obsSongList.get(index);

        songDisplay.setText(
                "Name: " + temp.name+ "\n" +
                "Artist: " + temp.artist + "\n" +
                "Album: " + temp.album + "\n" +
                "Year: " + temp.year);

    }
}
