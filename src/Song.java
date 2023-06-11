public class Song implements Cloneable{

    private final String name;
    private final String artist;
    private Genre genre;
    private int duration;

    public Song(String name, String artist, Genre genre, int duration) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    protected Song clone() throws CloneNotSupportedException {
        // use try-catch
        // catch --> return null
        // notice if mutable
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Song))
            return false;
        Song song = (Song)obj;
        return name.equals(song.name) && artist.equals(song.artist);
    }

    @Override
    public int hashCode() {

    }

    @Override
    public String toString() {
        // (name, artist, genre, length)
        return "(" + name + ", "  + artist + ", " + genre + ", " + turnToMinutes() + ")";
    }

    private String turnToMinutes() {
        int minute = duration / 60;
        int seconds = duration % 60;
        if (seconds < 10) return minute + ":0" + seconds;
        return minute + ":" + seconds;
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
