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
        
        StoryList storyList=new StoryList();
        storyList.add(new Story(1, 101, "De Men Phieu Luu Ky", 9.5, "To Hoai", true,'V'));
        storyList.add(new Story(2, 102, "One Piece", 9.8, "Eiichiro Oda", false, 'J'));
        storyList.add(new Story(3, 205, "Mat Biec", 8.9, "Nguyen Nhat Anh", true, 'V'));
        storyList.add(new Story(4, 310, "Harry Potter", 9.2, "J.K. Rowling", true, 'A'));
        storyList.add(new Story(5, 404, "Conan", 8.5, "Gosho Aoyama", false, 'J'));

        while (true) {
            int choice = menu.getChoice();
            Scanner sc = new Scanner(System.in);
            switch (choice) {
                case 1 -> {
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
