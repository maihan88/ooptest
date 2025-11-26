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
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("| %-6s | %-7s | %-40s | %-25s | %-5s | %-10s | %-8s |\n", 
                           "Number", "ID", "Title", "Author", "Rate", "Status", "Language");
        System.out.println("------------------------------------------------------------------------------------------");
        
        int number=1;

        for (Story story : this) {
            story.output(number);
            number++;
        }
        
        System.out.println("-----------------------------------------------------------------------------------");
    }
    
    public void addFromFile(String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            
            while ((line = br.readLine()) != null) {
                if (line.trim().equals("")) continue;
                
                String[] arr = line.split("[,]+");
                
                int id = Integer.parseInt(arr[1].trim()); 
                String name = arr[2].trim();
                double rating = Double.parseDouble(arr[3].trim());
                String author = arr[4].trim();
                boolean isCompleted = arr[5].trim().equals("1");
                char language = arr[6].trim().charAt(0);
                
                Story story = new Story(id, name, rating, author,isCompleted,language);
                this.add(story);
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
        int id = Integer.parseInt(sc.nextLine());
        int pos = searchStoryById(id);
        if (pos >= 0) {
            System.out.print("Enter new Title: ");
            this.get(pos).setTitle(sc.nextLine());
            System.out.println("Updated successfully!");
        } else {
            System.out.println("ID not found!");
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
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getTitle().equalsIgnoreCase(title)) {
                return i;
            }
        }
        return -1;
    }
    
    public int searchStoryById (int id) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId() == id)
            return i;
        }
        return -1;
    }
    
    public void searchStories() {
        System.out.print("Enter ID or Title to search: ");
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

        if (pos != -1) {
            System.out.println(">> Found story:");
            this.get(pos).output(1);
        } else {
            System.out.println(">> Not found!");
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
