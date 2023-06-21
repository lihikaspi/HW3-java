import java.util.*;

/**
 * This class represents a song playlist
 * Implements Cloneable, FilteredSongIterable, OrderedSongIterable
 */
public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable{
    private ArrayList<Song> songs;
    private Playlist filtered; // a filtered Playlist of current Playlist
    private int numberOfSongs; // current amount of songs
    private int total; // total amount of songs that are/were in the playlist

    /**
     * Constructs a new playlist
     */
    public Playlist() {
        numberOfSongs = 0;
        total = 0;
        songs = new ArrayList<>();
    }

    /**
     * This class represents an Iterator
     * Implements Iterator
     */
    private class PlaylistIterator implements Iterator<Song>{
        private ArrayList<Song> playlist;
        private int index;

        /**
         * Constructs a new playlist iterator
         *
         * @param playlist playlist to iterate
         */
        public PlaylistIterator(ArrayList<Song> playlist) {
            this.playlist = playlist;
            index = 0;
        }

        @Override
        public Song next() {
            Song val = playlist.get(index);
            index++;
            return val;
        }

        @Override
        public boolean hasNext() {
            return playlist.size() > index;
        }
    }

    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator(songs);
    }

    /**
     * Adds a new song to the playlist
     *
     * @param song song to add
     */
    public void addSong(Song song) {
        if (songs.contains(song)) throw new SongAlreadyExistsException();
        songs.add(song);
        numberOfSongs++;
        total++;
        song.setSerialNumber(total);
    }

    /**
     * Removes a song from the playlist
     *
     * @param song song to remove
     * @return was the remove successful
     */
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

        int i = 0;
        while (i < filtered.numberOfSongs) {
            Song songi = filtered.songs.get(i);
            if (!(songi.getArtist().equals(artist))) {
                filtered.removeSong(songi);
                continue;
            }
            i++;
        }
    }

    @Override
    public void filterGenre(Song.Genre genre) {
        // if null --> keep all
        // keep only songs of genre

        if (genre == null) return;
        if (filtered.songs == null) {
            filtered.songs = new ArrayList<>();
            filtered.numberOfSongs = 0;
            return;
        }

        int i = 0;
        while (i < filtered.numberOfSongs) {
            if (filtered.songs.get(i).getGenre().ordinal() != genre.ordinal()) {
                filtered.removeSong(filtered.songs.get(i));
                continue;
            }
            i++;
        }
    }


    @Override
    public void filterDuration(int maxDuration) {
        // keep only --> duration <= max duration

        if (maxDuration < 0) {
            filtered.songs = null;
            return;
        }

        if (filtered.songs == null) {
            filtered.songs = new ArrayList<>();
            filtered.numberOfSongs = 0;
            return;
        }

        int i = 0;
        while (i < filtered.numberOfSongs) {
            if (filtered.songs.get(i).getDuration() > maxDuration) {
                filtered.removeSong(filtered.songs.get(i));
                continue;
            }
            i++;
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

        Comparator<Song> compareBySerialNumber = Comparator.comparing( Song::getSerialNumber );
        Comparator<Song> compareByName = Comparator.comparing( Song::getName );
        Comparator<Song> compareByArtist = Comparator.comparing( Song::getArtist);
        Comparator<Song> compareByDuration = Comparator.comparing( Song::getDuration );
        Comparator<Song> compareByAlphabet = compareByName.thenComparing(compareByArtist);
        Comparator<Song> compareByLength = compareByDuration.thenComparing(compareByAlphabet);
        if (filtered.songs == null) return;

        switch (order) {
            case ADDING:
                Collections.sort(filtered.songs, compareBySerialNumber);
                Collections.sort(filtered.songs, new SortBySerialNumber());

            case NAME:
                Collections.sort(filtered.songs, compareByAlphabet);
                Collections.sort(filtered.songs, new SortByName());

            case DURATION:
                Collections.sort(filtered.songs, compareByLength);
                Collections.sort(filtered.songs, new SortByDuration());

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
        for (Song song : songs) {
            hashcode += song.hashCode();
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
            else str += ", " + "(" + song.toString() + ")";
            i++;
        }
        return str + "]";
    }

    @Override
    public Playlist clone() {
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
