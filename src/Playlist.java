import java.util.Iterator;

public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable{

    public Playlist() {

    }

    class PlaylistIterator implements Iterable<Song>{

        @Override
        public Iterator<Song> iterator() {

        }
    }

    public void addSong(Song song) {
        // add to playlist only if it doesn't already have it
        // already there --> SongAlreadyExistsException (unmarked)
    }

    public boolean removeSong(Song song) {
        // if song there --> remove and return true
        // if song not there --> return false
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
        // call for each song.toString()
        // by order of adding
    }

    @Override
    protected Playlist clone() throws CloneNotSupportedException {
        // use try-catch
        // catch --> return null
        // notice if mutable
    }
}
