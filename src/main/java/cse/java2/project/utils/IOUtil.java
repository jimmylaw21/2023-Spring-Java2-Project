package cse.java2.project.utils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class IOUtil {
  public static <T> Set<T> readDataFromFile(String fileName) {
    File file = new File(fileName);
    if (!file.exists() || file.length() == 0) {
      return new HashSet<>();
    } else {
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
        return (Set<T>) ois.readObject();
      } catch (IOException | ClassNotFoundException e) {
        System.err.println("Error reading from file: " + e.getMessage());
        return new HashSet<>();
      }
    }
  }

  public static <T> void writeDataToFile(String fileName, Set<T> data) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
      oos.writeObject(data);
    } catch (IOException e) {
      System.err.println("Error writing to file: " + e.getMessage());
    }
  }
}
