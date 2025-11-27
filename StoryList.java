/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._nguyenNgocMaiHan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author HAN NGUYEN
 */
public class StoryList extends ArrayList<Story> {
    Scanner sc = new Scanner(System.in);
    
    public void output(){
        System.out.println("===========================================================================================================================");
        System.out.printf("| %-6s | %-7s | %-40s | %-25s | %-5s | %-10s | %-8s |\n", 
                           "Number", "ID", "Title", "Author", "Rate", "Complete", "Language");
        
        int number=1;

        for (Story story : this) {
            story.output(number);
            number++;
        }
        
        System.out.println("===========================================================================================================================");
    }
    
    public void addFromFile(String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().equals("")) continue;

                try { 
                    String[] arr = line.split("[,]+");

                    int id = Integer.parseInt(arr[0].trim());
                    String name = arr[1].trim();
                    double rating = Double.parseDouble(arr[2].trim());
                    String author = arr[3].trim();
                    boolean isCompleted = arr[4].trim().equals("1");
                    char language = arr[5].trim().charAt(0);

                    Story story = new Story(id, name, rating, author, isCompleted, language);
                    this.add(story);

                } catch (Exception e) {
                    System.out.println("Error with line: " + line + e.getMessage());
                }
            }

            br.close();
            fr.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void sortByTitle() {
        Collections.sort(this);
    }
    
    public void sortByTitleAndRating() {
        Collections.sort(this, new Comparator<Story>() {
            @Override
            public int compare(Story s1, Story s2) {
                int titleCompare = s1.getTitle().compareTo(s2.getTitle());
                if (titleCompare != 0) 
                return titleCompare;
                return Double.compare(s2.getRating(), s1.getRating());
            }
        });
    }
    
    public void addStory() {
        try {
            System.out.print("Enter ID: "); 
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Title: "); 
            String title = sc.nextLine();
            System.out.print("Enter Rating: "); 
            double rating = Double.parseDouble(sc.nextLine());
            System.out.print("Enter Author: "); 
            String author = sc.nextLine();
            System.out.print("Is Completed (true/false): "); 
            boolean isCompleted = Boolean.parseBoolean(sc.nextLine());
            System.out.print("Language (char): "); 
            char lang = sc.nextLine().charAt(0);
            this.add(new Story(id, title, rating, author, isCompleted, lang));
        } catch (Exception e) { 
            System.out.println("Invalid input!"); 
        }
    }
    
    public void updateStory() {
        System.out.print("Enter ID to update: ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            int pos = searchStoryById(id);

            if (pos == -1) {
                System.out.println("ID not found!");
                return;
            }

            Story story = this.get(pos);

            System.out.println("\n UPDATE OPTIONS FOR: " + story.getTitle());
            System.out.println("1. Title");
            System.out.println("2. Rating");
            System.out.println("3. Author");
            System.out.println("4. Completed Status");
            System.out.println("5. Language");
            System.out.println("Example: Enter '1, 3' to update Title and Author.");
            System.out.print("Enter your choices: ");

            String input = sc.nextLine();
            String[] choices = input.split("[,\\s]+"); 

            for (String choiceStory : choices) {
                try {
                    int choice = Integer.parseInt(choiceStory);
                    switch (choice) {
                        case 1 -> {
                            System.out.print("Enter new Title (Current: " + story.getTitle() + "): ");
                            story.setTitle(sc.nextLine());
                        }
                        case 2 -> {
                            System.out.print("Enter new Rating (Current: " + story.getRating() + "): ");
                            story.setRating(Double.parseDouble(sc.nextLine()));
                        }
                        case 3 -> {
                            System.out.print("Enter new Author (Current: " + story.getAuthor() + "): ");
                            story.setAuthor(sc.nextLine());
                        }
                        case 4 -> {
                            System.out.print("Is Completed (true/false) (Current: " + story.isIsCompleted() + "): ");
                            story.setIsCompleted(Boolean.parseBoolean(sc.nextLine()));
                        }
                        case 5 -> {
                            System.out.print("Enter new Language (Current: " + story.getLanguage() + "): ");
                            String langStr = sc.nextLine();
                            if (!langStr.isEmpty()) {
                                story.setLanguage(langStr.charAt(0));
                            }
                        }
                        default -> System.out.println("Warning: Option '" + choice + "' is invalid and was skipped.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: '" + choiceStory + "' is not a valid number.");
                }
            }
            System.out.println("Update completed!");

        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    
    public void deleteStory() {
        System.out.print("Enter ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        int pos = searchStoryById(id);
        if (pos >= 0) {
            this.remove(pos);
            System.out.println("Deleted successfully!");
        } else {
            System.out.println("ID not found!");
        }
    }
    
    public int searchStoryByTitle(String title) {
        int index = 0;
        for (Story story : this) { 
            if (story.getTitle().equalsIgnoreCase(title)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public int searchStoryById(int id) {
        int index = 0;
        for (Story story : this) {
            if (story.getId() == id) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public int searchStoryByAuthor(String author) {
        int index = 0;
        for (Story story : this) {
            if (story.getAuthor().equalsIgnoreCase(author)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public void searchStories() {
        System.out.print("Enter ID, Title or Author to search: ");
        String input = sc.nextLine().trim();

        int pos = -1;

        try {
            int id = Integer.parseInt(input); 
            pos = searchStoryById(id);
        } catch (NumberFormatException e) {

        }

        if (pos == -1) {
            pos = searchStoryByTitle(input);
        }

        if (pos == -1) {
            pos = searchStoryByAuthor(input);
        }

        if (pos != -1) {
            System.out.println("Found story:");
            this.get(pos).output(1);
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");

        } else {
            System.out.println("Not found!");
        }
    }
       
    public void saveToFile(String fileName) {
        File f = new File(fileName);
        try {
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            
            for (Story s : this) {
                pw.println("Story," + s.getId() + "," + s.getTitle() + "," + s.getRating() + "," + 
                           s.getAuthor() + "," + (s.isIsCompleted() ? "1" : "0") + "," + s.getLanguage());
            }
            
            pw.close();
            fw.close();
            System.out.println("Exported to file successfully!");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
