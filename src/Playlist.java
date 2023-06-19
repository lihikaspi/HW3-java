import java.util.*;

public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable{
    private ArrayList<Song> songs;
    private Playlist filtered;
    private int size;
    private int numberOfSongs; // current amount of songs
    private int total;

    public Playlist() {
        numberOfSongs = 0;
        total = 0;
        songs = new ArrayList<>();
    }

    private class PlaylistIterator implements Iterable<Song>, Iterator<Song>{
        private ArrayList<Song> nextItem;
        private int index;

        public PlaylistIterator(ArrayList<Song> nextItem) {
            this.nextItem = nextItem;
            index = 0;
        }

        public Song next() {
            Song val = nextItem.get(index);
            index++;
            return val;
        }

        public boolean hasNext() {
            return nextItem.size() > index;
        }

        @Override
        public Iterator<Song> iterator() {
            return nextItem.iterator();
        }
    }

    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator(songs);
    }

    public void addSong(Song song) {
        if (songs.contains(song)) throw new SongAlreadyExistsException();
        songs.add(song);
        numberOfSongs++;
        total++;
        song.setSerialNumber(total);
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

        int i = 0;
        while (i < filtered.numberOfSongs) {
            if (filtered.songs == null) throw new NullPointerException("hii :)");
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
        if (filtered.songs == null) return;

        switch (order) {
            case ADDING:
                Collections.sort(filtered.songs, new SortBySerialNumber());

            case NAME:
                Collections.sort(filtered.songs, new SortByName());

            case DURATION:
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

        Playlist copy = new Playlist();
        for (Song song: songs) {
            Song songCopy = song.clone();
            copy.addSong(songCopy);
        }
        return copy;

//        try {
//            Playlist copy = new Playlist();
//            for (Song song: songs) {
//                Song songCopy = song.clone();
//                if (songCopy == null) throw new NullPointerException();
//                copy.addSong(songCopy);
//            }
//            return copy;
//        } catch (NullPointerException e) {
//            return null;
//        }
    }
}
