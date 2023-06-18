import java.util.*;

public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable{
    private ArrayList<Song> songs;
    private Playlist filtered;
    private int size;
    private int numberOfSongs;

    public Playlist() {
        size = 0;
        numberOfSongs = 0;
        songs = new ArrayList<>();
    }

    private class PlaylistIterator<E> implements Iterable<E>, Iterator<E>{
        private ArrayList<E> nextItem;
        private int index;

        public PlaylistIterator(ArrayList<E> nextItem) {
            this.nextItem = nextItem;
            index = 0;
        }

        public E next() {
            E val = nextItem.get(index);
            index++;
            return val;
        }

        public boolean hasNext() {
            return nextItem.size() > index;
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
        song.setSerialNumber(numberOfSongs);
    }

    public boolean removeSong(Song song) {
        int index = songs.indexOf(song);
        if (index == -1) return false;
        songs.remove(index);
        numberOfSongs--;
        song.setSerialNumber(-1);
        return true;
    }

    @Override
    public void filterArtist(String artist) {
        // if null --> keep all
        // keep only songs by artist

        filtered = clone();
        if (artist == null) return;

        for (Song song : filtered.songs) {
            if (!(song.getArtist().equals(artist))) filtered.removeSong(song);
        }
    }

    @Override
    public void filterGenre(Song.Genre genre) {
        // if null --> keep all
        // keep only songs of genre

        if (genre == null) return;

        for (Song song : filtered.songs) {
            if (song.getGenre() != genre) filtered.removeSong(song);
        }
    }


    @Override
    public void filterDuration(int maxDuration) {
        // keep only --> duration <= max duration

        if (maxDuration < 0) {
            filtered.songs = null;
            return;
        }

        for (Song song : filtered.songs) {
            if (song.getDuration() > maxDuration) filtered.removeSong(song);
        }
    }

    class SortBySerialNumber implements Comparator<Song> {
        public int compare(Song a, Song b) {
            return a.getSerialNumber() - b.getSerialNumber();
        }
    }

    class SortByName implements Comparator<Song> {
        public int compare(Song a, Song b) {
            return a.getName().compareTo(b.getName());
        }
    }

    class SortByDuration implements Comparator<Song> {
        public int compare(Song a, Song b) {
            return a.getDuration() - b.getDuration();
        }
    }

    @Override
    public void setScanningOrder(ScanningOrder order) {
        switch (order) {
            case ADDING:
                Collections.sort(songs, new SortBySerialNumber());

            case NAME:
                Collections.sort(songs, new SortByName());

            case DURATION:
                Collections.sort(songs, new SortByDuration());

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
        for (Song song: songs) {
            if (i == 0) str += "(" + song.toString() + ")";
            else str += "," + "(" + song.toString() + ")";
            i++;
        }
        return str + "]";
    }

    @Override
    protected Playlist clone() {
        // use try-catch
        // catch --> return null
        // notice if mutable

        try {
            Playlist copy = new Playlist();
            for (Song song: songs) {
                Song songCopy = song.clone();
                if (songCopy == null) throw new NullPointerException();
                copy.addSong(songCopy);
            }
            return copy;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
