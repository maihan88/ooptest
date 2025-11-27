/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany._nguyenNgocMaiHan;

import java.util.Scanner;

/**
 *
 * @author HAN NGUYEN
 */
public class Main {
    
    public static void main(String[] args) {
        Menu menu = new Menu("Welcome to the world of Banh Su Vi Kem stories");
        menu.addItem("Show stories");
        menu.addItem("Sort by title");
        menu.addItem("Sort by title and rating");
        menu.addItem("Add new story");
        menu.addItem("Update story");
        menu.addItem("Delete story");
        menu.addItem("Search stories");
        menu.addItem("Import stories");
        menu.addItem("Export stories");
        menu.addItem("Exit");
        
        StoryList storyList = new StoryList();
        
        storyList.addFromFile("Stories.txt");

        while (true) {
            int choice = menu.getChoice();
            Scanner sc=new Scanner(System.in);
            switch (choice) {
                case 1 -> {
                    storyList.output();
                }
                case 2 -> {
                    storyList.sortByTitle();
                    storyList.output();
                }
                case 3 -> {
                    storyList.sortByTitleAndRating();
                    storyList.output();
                }
                case 4 -> {
                    storyList.addStory();
                }
                case 5 -> {
                    storyList.updateStory();
                }
                case 6 -> {
                    storyList.deleteStory();
                }
                case 7 -> {
                    storyList.searchStories();
                }
                case 8 -> {
                    System.out.print("Enter file name to import: ");
                    String filename = sc.nextLine();
                    storyList.addFromFile(filename);
                }
                case 9 -> {
                    System.out.print("Enter file name to export: ");
                    String filename = sc.nextLine();
                    storyList.saveToFile(filename);
                }
                default -> {
                    if (Menu.sayBye()){
                        return;                        
                    }
                }
            }
            System.out.println("Press Enter to continue...");
            sc.nextLine();
        }        
    }
}
