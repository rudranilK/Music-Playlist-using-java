import java.util.ArrayList;
import java.util.Scanner;

public class Album {
    private final String albumName;
    private final String artistName;
    private ArrayList<Song> songs;
    private Scanner sc = new Scanner(System.in);

    public Album(String albumName, String artistName) {
        this.albumName = !albumName.equals(null) ? albumName.toUpperCase() : "Unnamed Album";
        this.artistName = !artistName.equals(null) ? artistName.toUpperCase() : "Unknown";
        songs = new ArrayList<Song>();
    }

    public boolean addSong(String songName, String artistName) {

        songName = songName.toUpperCase();
        artistName = artistName.toUpperCase();

        Song obj = searchExistingSong(songName,artistName);

        if (obj == null) {                              //Searched song couldn't be found in the album, hence add it to the album

            if (this.artistName.equals(artistName)) {                         //This album and the song to be added is of same artist, go ahead add it

                System.out.println("Enter the duration of the song...");
                if (sc.hasNextDouble()) {
                    songs.add(new Song(songName, sc.nextDouble(), artistName));
                    System.out.println(songName + " added successfully in album " + this.albumName);
                    return true;
                } else {
                    System.out.println("Wrong input format for song duration...\nError adding song to the album");
                    return false;
                }
            } else {
                System.out.println("The Artist name " + artistName + " do not match with " + this.artistName);
                System.out.println("Choose the appropiate album to add the song");
                return false;
            }
        } else {
            System.out.println(songName + " already exists in the album " + albumName);
            return false;
        }
    }

//    private boolean searchSong(String songName, String artistName) {
//        for (Song s : songs) {
//            if (s.getTitle().equals(songName) && s.getArtist().equals(artistName))     //this song exists in the album
//                return true;
//        }
//        return false;  //the song does not exist in the album
//    }

    private Song searchExistingSong(String songName, String artistName) {
        for (Song s : songs) {
            if (s.getTitle().equals(songName.toUpperCase()) && s.getArtist().equals(artistName))     //this song exists in the album
                return s;
        }
        return null;  //the song does not exist in the album
    }


    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public Song doesSongExistsInAlbum(String songName, String artistName) {
        return searchExistingSong(songName,artistName);
    }

    public void printSongsInAnAlbum() {
        for (Song s : songs) {
            System.out.println("title : " + s.getTitle() + " ;artist :" + s.getArtist() + " ;duration: " + s.getDuration());
        }
    }

}