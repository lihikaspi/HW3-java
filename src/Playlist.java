import java.util.Iterator;

public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable{
    private Song[] songs;
    private int size;
    private int numberOfSongs;

    public Playlist() {
        size = 0;
        numberOfSongs = 0;
        songs = new Song[2];
    }

    class PlaylistIterator<E> implements Iterable<E>{
        private int index;
        private Song[] array;

        public PlaylistIterator(Playlist playlist) {
            index = 0;
            array = playlist.songs;
        }

        public Song next() {
            index++;
            return array[index];
        }

        public boolean hasNext() {
            return index < array.length;
        }

        @Override
        public Iterator<Song> iterator() {

        }
    }

    @Override
    public PlaylistIterator<Song> iterator() {
        return new PlaylistIterator<>(this);
    }

    public void addSong(Song song) {
        if (isDuplicate(song)) throw new SongAlreadyExistsException();
        if (songs.length == size) {
            extendPlaylist();
        }
        songs[numberOfSongs] = song;
        numberOfSongs++;
    }

    private void extendPlaylist() {
        size *= 2;
        Song[] newPlaylist = new Song[size];
        for (int i = 0; i < size; i++) {
            newPlaylist[i] = songs[i];
        }
        songs = newPlaylist;
    }

    private boolean isDuplicate(Song duplicate) {
        for (Song song: songs) {
            if (song.equals(duplicate)) return true;
        }
        return false;
    }

    public boolean removeSong(Song song) {
        int index = findSong(song);
        if (index == -1) return false;
        songs[index] = null;
        moveAfterRemove();
        return true;
    }

    private void moveAfterRemove() {
        Song[] newPlaylist = new Song[size];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (songs[i] != null) {
                newPlaylist[j] = songs[i];
                j++;
            }
        }
        songs = newPlaylist;
    }

    private int findSong(Song song) {
        int i = 0;
        while (songs[i] != null) {
            if (songs[i].equals(song)) return i;
            i++;
        }
        return -1;
    }

    @Override
    public void filterArtist(String artist) {
        // if null --> keep all
        // keep only songs by artist
    }

    @Override
    public void filterGenre(Song.Genre genre) {
        // if null --> keep all
        // keep only songs of genre
    }

    @Override
    public void filterDuration(int maxDuration) {
        // keep only --> duration <= max duration
    }

    @Override
    public void setScanningOrder(ScanningOrder order) {

    }

    @Override
    public boolean equals(Object obj) {

    }

    @Override
    public int hashCode() {

    }

    @Override
    public String toString() {
        // [(name, artist, genre, length), ... , (name, artist, genre, length)]
        int i = 0;
        String str = "[";
        while (songs[i] != null) {
            if (i == 0) str += songs[i].toString();
            else str += "," + songs[i].toString();
        }
        return str + "]";
    }

    @Override
    protected Playlist clone() throws CloneNotSupportedException {
        // use try-catch
        // catch --> return null
        // notice if mutable
    }
}
