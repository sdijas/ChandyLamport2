package edu.sharif.ce.snapshot.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The Report class.
 */
public class Report {
  private static final String REPORT_FOLDER = "log";

  static {
    Path path = Paths.get(REPORT_FOLDER);
    if (!Files.exists(path)) {
      try {
        Files.createDirectories(path);
      } catch (IOException e) {
        System.err.println("Failed to create log directory");
        e.printStackTrace();
      }
    }
  }

  /**
   * Write.
   *
   * @param object the object
   */
  public static void write(Object object) {
    try {
      PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(REPORT_FOLDER + "/snapshot" + ".log", true)));
      writer.println(object);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Failed to write object: " + object);
    }
  }
}
