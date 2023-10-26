package org.example;

import java.io.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<Singer> allSingers = new ArrayList<Singer>();

        //Exercise 2
        //Create Singers
        Singer edSheeran = Singer.builder().name("Ed Sheeran").age(32).build();
        Singer adamLevine = Singer.builder().name("Adam Levine").age(44).build();
        Singer justinTimberlake = Singer.builder().name("Justin Timberlake").age(42).build();
        allSingers.add(edSheeran);
        allSingers.add(adamLevine);
        allSingers.add(justinTimberlake);


        //Create Songs
        Song perfect = Song.builder().name("Perfect").year(2017).singer(edSheeran).build();
        Song shapeOfYou = Song.builder().name("Shape of You").year(2017).singer(edSheeran).build();
        Song badHabits = Song.builder().name("Bad Habits").year(2021).singer(edSheeran).build();
        Song payphone = Song.builder().name("Payphone").year(2012).singer(adamLevine).build();
        Song memories = Song.builder().name("Memories").year(2021).singer(adamLevine).build();
        Song animals = Song.builder().name("Animals").year(2012).singer(adamLevine).build();
        Song mirrors = Song.builder().name("Mirrors").year(2013).singer(justinTimberlake).build();
        Song cryMeARiver = Song.builder().name("Cry Me a River").year(2004).singer(justinTimberlake).build();

        //Exercise 3
        Set<Song> allSongs = Stream.of(edSheeran, adamLevine, justinTimberlake).flatMap(singer -> singer.getSongs().stream()).collect(Collectors.toSet());

        //Exercise 4
        System.out.println("Exercise 4");
        Stream<Song> filteredSongsStream = allSongs.stream().filter(song -> song.getSinger().getName() == "Adam Levine").sorted(Comparator.comparing(Song::getName));
        filteredSongsStream.forEach(song -> System.out.println(song));
        System.out.println();

        //Exercise 5
        System.out.println("Exercise 5");
        List<SongDto> allSongsDto = allSongs.stream().map(song -> new SongDto(song.getName(), song.getYear(), song.getSinger().getName())).sorted().collect(Collectors.toList());
        allSongsDto.forEach(songDto -> System.out.println(songDto));
        System.out.println();

        //Exercise 6
        System.out.println("Exercise 6");
        ArrayList<Singer> deserializedCollection = null;
        try {
            FileOutputStream fileOut = new FileOutputStream("singers.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(allSingers);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/singers.ser\n");
        } catch (IOException i) {
            i.printStackTrace();
        }

        try {
            FileInputStream fileIn = new FileInputStream("singers.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            deserializedCollection = (ArrayList<Singer>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }

        System.out.println(deserializedCollection);

        //Exercise 7
        System.out.println("Exercise 7");
        ForkJoinPool customThreadPool = new ForkJoinPool(3);
            allSingers.parallelStream().forEach(singer -> {
                customThreadPool.execute(() -> {
                    for (Singer singer1 : allSingers) {
                        try {
                            Thread.sleep(rand.nextInt(3000) + 1000);
                            System.out.print(singer1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });
            });

        customThreadPool.shutdown();
        while (!customThreadPool.isTerminated()) {
        }

    }
}