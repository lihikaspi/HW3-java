public class Song implements Cloneable{

    private final String name;
    private final String artist;
    private Genre genre;
    private int duration;

    public Song(String name, String artist, Genre genre,int duration) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration; // what if negative
    }

    @Override
    protected Song clone() throws CloneNotSupportedException {
        // use try-catch
        // catch --> return null
        // notice if mutable
    }

    @Override
    public boolean equals(Object obj) {
        // only if name == name && artist == artist
    }

    @Override
    public int hashCode() {

    }

    @Override
    public String toString() {
        // (name, artist, genre, length)

        // turn duration to mm:ss
        return "(" + name + ", "  + artist + ", " + genre + ", " + duration + ")";
    }

    public enum Genre {
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO
    }
}
