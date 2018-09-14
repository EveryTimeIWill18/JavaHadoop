package com.company;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.FileVisitOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class FileParse {

    private Path    file_path;                           // file_path: type(java.nio.file.Path)
    private Integer f_counter;                           // counter for number of files read
    private HashMap<String, Collection<String>> map;     // map that holds filename and cleaned data

    public FileParse(){
        file_path = null;
        f_counter = null;
        map = null;
    }   // default constructor

    public FileParse(String path_name){
        this.file_path = Paths.get(path_name);
        this.f_counter = 0;
        this.map = new HashMap<String, Collection<String>>();
    }   // overloaded constructor

    public void PrintFiles(){
        // walk through the file system
        String pattern = ".eml";
        try(Stream<Path> walk = Files.walk(this.file_path, Integer.MAX_VALUE, FileVisitOption.FOLLOW_LINKS)) {
             // lambda used to remove non .eml files
             walk
                 .filter(p -> (p.toString()
                                .contains(pattern)))
                 .forEach(System.out::println);
             f_counter += 1;
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
}
