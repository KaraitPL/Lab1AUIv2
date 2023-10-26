package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class SongDto implements Comparable<SongDto> {
    private String name;
    private int year;
    private String singer;

    @Override
    public String toString() {
        return "SongDto{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", singer='" + singer + '\'' +
                '}';
    }

    @Override
    public int compareTo(SongDto other) {
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
