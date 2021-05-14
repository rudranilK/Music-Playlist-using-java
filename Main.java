import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Album> myAlbums;
    private static List<Song> playlist;

    public static void main(String[] args) {
        myAlbums = new ArrayList<>();
        playlist = new LinkedList<>();

        addAlbum(myAlbums);

        //when you enter a song in the album , make sure to add the same album name and artist name while creating the album....

        addSongToAlbum("album1","keegan");
        addSongToAlbum("album1","keegan");
        addSongToAlbum("album1","keegan");

        addAlbum(myAlbums);
        addSongToAlbum("album2","Eminem");
        addSongToAlbum("album2","Eminem");


        addAlbum(myAlbums);
        addSongToAlbum("album3","The Wanted");
        addSongToAlbum("album3","The Wanted");
        addSongToAlbum("album3","The Wanted");

        addAlbum(myAlbums);
        addSongToAlbum("album4","Babes");
        addSongToAlbum("album4","Babes");


        printAlbums(myAlbums);

        addSongToPlaylist("Mood","keegan");
        addSongToPlaylist("Godzilla","Eminem");
        addSongToPlaylist("Chasing the sun","The Wanted");
        addSongToPlaylist("Once more","Babes");

        runPlaylist();

    }

    public static void printAlbums(ArrayList<Album> albums){
        for(Album a : albums){
            System.out.println("album :"+a.getAlbumName()+" ; artist : "+a.getArtistName()+" ; songs :");
            a.printSongsInAnAlbum();
        }
    }

    public static void addAlbum(ArrayList<Album> albums) {
        System.out.println("Enter Album Name : \r");
        String albumName = sc.nextLine().toUpperCase();
        System.out.println("Enter Artist Name : \r");
        String artistName = sc.nextLine().toUpperCase();
        if(albumExists(albumName,artistName) == -1) {
            albums.add(new Album(albumName, artistName));
            System.out.println("Album : " + albumName + " added to the list of albums");
        }else
            System.out.println("Album : " + albumName + " already exists in the list of albums");
    }

    private static int albumExists(String albumName, String artistName){
        int i = 0;
        for (Album s : myAlbums) {
            if (s.getAlbumName().equals(albumName.toUpperCase()) && s.getArtistName().equals(artistName.toUpperCase())) {     //this song exists in the album
                System.out.println("Album : " +s.getAlbumName()+ " artist : "+ s.getArtistName());
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void addSongToAlbum(String albumName, String artistName) {
        albumName = albumName.toUpperCase();
        artistName = artistName.toUpperCase();
        int index = albumExists(albumName, artistName);

        if (index >= 0) {
            Album b = myAlbums.get(index);
            System.out.println("Enter song name\r");
            b.addSong(sc.nextLine(), artistName);

        }
        else
            System.out.println("Album "+albumName+ " does not exist...");
    }

    public static void addSongToPlaylist(String songName, String artistName) {
        boolean flag = false;
        for (Album s : myAlbums) {
            Song obj = s.doesSongExistsInAlbum(songName.toUpperCase(),artistName.toUpperCase());
            if (obj != null) {              // if the song exists we want to add that object into the playlist....
                playlist.add(obj);
                System.out.println(songName + " added successfully in the playlist ");
                flag = true;
            }
        }
        if (!flag)
            System.out.println("You don't own song : " + songName + " by " + artistName + " \n" +
                    "Song can not be added to the playlist");
    }


    public static void runPlaylist() {

        ListIterator<Song> songIterator = playlist.listIterator();
        boolean goingForward = true;
        if (playlist.isEmpty()) {
            System.out.println("No songs in the Playlist :( ");
            return;
        }
        else {
            Song s = songIterator.next();
            System.out.println("Playing " + s.getTitle() + " by " + s.getArtist());
            printMenu();
        }
        boolean flag = true;

        while (flag) {
            System.out.println("Enter your choice \r");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:                                             //QUIT
                    flag = false;
                    break;

                case 2:                                             //Skip Forward
                    if (!goingForward) {
                        songIterator.next();
                        goingForward = true;
                    }
                    if (songIterator.hasNext()) {
                        Song s = songIterator.next();
                        System.out.println("Playing " + s.getTitle() + " by " + s.getArtist());
                    } else
                        System.out.println("This is the last track on the playlist\n");

                    break;

                case 3:                                             //Skip Backward
                    if (goingForward) {
                        songIterator.previous();
                        goingForward = false;
                    }
                    if (songIterator.hasPrevious()) {
                        Song s = songIterator.previous();
                        System.out.println("Playing " + s.getTitle() + " by " + s.getArtist());
                    } else
                        System.out.println("This is the first track on the playlist\n");
                    break;

                case 4:                    //Replay The Current Song....(We don't need to change the direction here
                    Song s;
                    if (goingForward) {
                        s = songIterator.previous();
                        songIterator.next();        // setting up the position as it was before replaying the song.
                    }else{
                        s = songIterator.next();
                        songIterator.previous();
                    }
                    System.out.println("Playing " + s.getTitle() + " by " + s.getArtist());
                    break;

                case 5:
                    listSongsInPlaylist();
                    break;
                case 6:
                    songIterator.remove();
                    System.out.println("Removed the current song from playlist");
                    //when all songs are deleted from playlist....
                    if(playlist.isEmpty()) {
                        System.out.println("All songs removed from the playlist.");
                        flag = false;
                    }
                    break;
                case 7:
                    printMenu();
                    break;
                default:
                    System.out.println("Wrong input\n");
            }
        }

    }

    public static void printMenu() {
        System.out.println("Select 1 to quit \n" +
                "2 to Skip forward to the next song \n" +
                "3 to skip backward to the previous song \n" +
                "4 to replay the current song \n" +
                "5 to List all the songs in the playlist \n" +
                "6 to delete the current song from the playlist \n" +
                "7 to see the menu again \n");
    }

    public static void listSongsInPlaylist() {
        ListIterator<Song> songIterator = playlist.listIterator();
        System.out.println("Songs present in the playlist : \n");
        int i = 1;
        while (songIterator.hasNext()) {
            Song s = songIterator.next();
            System.out.println(i + " : " + s.getTitle() + "  " + s.getDuration() + "  By " + s.getArtist());
            i++;
        }

        //another way of iterating through the linkedList...
//        for( Song s : playlist){
//            System.out.println(i+" : " +s.getTitle() + "  " +s.getDuration()+"  By " +s.getArtist() );
//            i++;
//        }

    }


}
