/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._nguyenNgocMaiHan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author HAN NGUYEN
 */
public class Menu {
    private String title;
    List<String> list = new ArrayList<>();

    public Menu(String title) {
        this.title = title;
    }

    public void addItem(String element) {
        list.add(element);
    }

    public static boolean sayBye() {
        System.out.println("You really want to go?(T_T)");
        System.out.println("1 = Yes; 2 = I'll go back with you");

        Scanner sc = new Scanner(System.in);
        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            choice = 2;
        }

        if (choice == 1) {
            System.out.println("We will miss you very much!!·´¯`(>.<)´¯`·. ");
            return true;
        } else {
            return false;
        }
    }

    public int getChoice() {
        int rs = 0;
        try {
            display();
            System.out.print("Please select an operation: ");
            Scanner sc = new Scanner(System.in);
            rs = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid number!");
        }
        return rs;
    }

    public void display() {
        System.out.println("====*:========~======*===.=.:*============`*:.========");
        System.out.printf("| %-50s |\n", title);
        System.out.println("======================================================");
        int count = 1;
        for (String number : list) {
            System.out.printf("| %-2d. %-46s |\n", count, number);
            count++;
        }
        System.out.println("====*:========~======*===.=.:*============`*:.=======");
    }    
}
