package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Builder
@Getter
@Setter
public class Song implements Comparable<Song>, Serializable {
    private String name;
    private int year;
    private Singer singer;

    public static class SongBuilder {
        public Song build() {
            Song song = new Song(this.name, this.year, this.singer);
            singer.addSong(song);
            return song;
        }
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", singer=" + singer.getName() +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return year == song.year && Objects.equals(name, song.name) && Objects.equals(singer, song.singer);
    }

    @Override
    public int hashCode() {
        return name.length() + year + singer.getName().length();
    }

    @Override
    public int compareTo(Song other){
        if(this.name.compareTo(other.name) != 0){
            return this.name.compareTo(other.name);
        }
        else if(this.year != other.year){
            return this.year - other.year;
        }
        else{
            return this.singer.compareTo(other.singer);
        }
    }

}
