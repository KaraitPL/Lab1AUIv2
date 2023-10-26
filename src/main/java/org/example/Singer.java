package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
public class Singer implements Comparable<Singer>, Serializable {
    private String name;
    private int age;
    @Builder.Default
    private List<Song> songs = new ArrayList<>();




    @Override
    public String toString() {
        return "\nSinger{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", songs=" + songs +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Singer singer = (Singer) o;
        return age == singer.age && Objects.equals(name, singer.name) && Objects.equals(songs, singer.songs);
    }

    @Override
    public int hashCode() {
        int sum = 0;
        for (Song song : songs) {
            sum += song.getName().length();
            sum += song.getYear();
        }
        return name.length() + age + sum;
    }

    @Override
    public int compareTo(Singer other){
        if(this.name.compareTo(other.name) != 0){
            return this.name.compareTo(other.name);
        }
        else if(this.age != other.age){
            return this.age - other.age;
        }
        else{
            return this.songs.size() - other.songs.size();
        }
    }

    public void addSong(Song song){
        songs.add(song);
    }

    public void sortSongs(){
        songs.sort(Song::compareTo);
    }
}
