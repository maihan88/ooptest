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
        Menu menu = new Menu("Welcome to the world of Banh Su Vi Kem stories ^o^");
        menu.addItem("Show stories");
        menu.addItem("Sort by title");
        menu.addItem("Add new story");
        menu.addItem("Exit");
        
        StoryList storyList = new StoryList();

        while (true) {
            int choice = menu.getChoice();
            Scanner sc = new Scanner(System.in);
            switch (choice) {
                case 1 -> {
                    storyList.addFromFile("Stories.txt");
                    storyList.output();
                }
                case 2 -> {
                    System.out.println("You have just select option 2");
                }
                case 3 -> {
                    System.out.println("You have just select option 3");
                }
                default -> {
                    Menu.sayBye();
                    return;
                }
            }
            System.out.println("Press Enter to continue...");
        }        
    }
}
