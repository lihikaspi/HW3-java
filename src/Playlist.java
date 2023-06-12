import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable{
    private LinkedList<Song> songs;
    private int size;
    private int numberOfSongs;

    public Playlist() {
        size = 0;
        numberOfSongs = 0;
        songs = new LinkedList<>();
    }

    private class PlaylistIterator<E> implements Iterable<E>{
        private LinkedList<E> nextItem;
        private int index;

        public PlaylistIterator(LinkedList<E> nextItem) {
            this.nextItem = nextItem;
            index = 0;
        }

        public E next() {
            E val = nextItem.get(index);
            index++;
            return val;
        }

        public boolean hasNext() {
            return nextItem.get(index) != null;
        }

        @Override
        public Iterator<E> iterator() {
            return nextItem.iterator();
        }
    }

    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator<Song>(songs);
    }

    public void addSong(Song song) {
        if (songs.contains(song)) throw new SongAlreadyExistsException();
        songs.add(song);
        numberOfSongs++;
    }

    public boolean removeSong(Song song) {
        int index = songs.indexOf(song);
        if (index == -1) return false;
        songs.remove(index);
        return true;
    }

    @Override
    public void filterArtist(String artist) {
        // if null --> keep all
        // keep only songs by artist

        Comparator.comparing();
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
        switch (order) {
            case ADDING:


            case NAME:


            case DURATION:


        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Playlist))
            return false;

        Playlist pl = (Playlist)obj;
        int count = 0;
        for (int i = 0; i < numberOfSongs; i++) {
            for (int j = 0; j < pl.numberOfSongs; j++) {
                if (songs.get(i).equals(pl.songs.get(j))) count++;
            }
        }
        return count == numberOfSongs;
    }

    @Override
    public int hashCode() {
        int hashcode = 0;
        for (int i = 0; i < numberOfSongs; i++) {
            hashcode += songs.get(i).hashCode();
        }
        return hashcode;
    }

    @Override
    public String toString() {
        // [(name, artist, genre, length), ... , (name, artist, genre, length)]
        int i = 0;
        String str = "[";
        while (songs.get(i) != null) {
            if (i == 0) str += songs.get(i).toString();
            else str += "," + songs.get(i).toString();
        }
        return str + "]";
    }

    @Override
    protected Playlist clone() {
        // use try-catch
        // catch --> return null
        // notice if mutable

        try {
            return (Playlist) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
