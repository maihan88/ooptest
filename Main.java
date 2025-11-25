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
        menu.addItem("Show stories");                 // 1. Hiển thị danh sách
        menu.addItem("Sort by title");                // 2. Sắp xếp theo tên
        menu.addItem("Sort by title and rating");     // 3. Sắp xếp theo tên và điểm
        menu.addItem("Add new story");                // 4. Thêm truyện mới
        menu.addItem("Update story");                 // 5. Cập nhật truyện
        menu.addItem("Delete story");                 // 6. Xóa truyện
        menu.addItem("Search stories");               // 7. Tìm kiếm (Thông minh)
        menu.addItem("Import stories");               // 
        menu.addItem("Export stories");
        menu.addItem("Exit");                         // 10. Thoát
        
        StoryList storyList = new StoryList();
        
        storyList.addFromFile("Stories.txt");

        while (true) {
            int choice = menu.getChoice();
            Scanner sc = new Scanner(System.in);
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
                    Menu.sayBye();
                    return;
                }
            }
            System.out.println("Press Enter to continue...");
        }        
    }
}
