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

    public static void sayBye() {
        System.out.println("We will miss you very much!!·´¯`(>.<)´¯`·. ");
    }

    public int getChoice() {
        int rs = 0;
        try {
            display();
            System.out.println("Please select an operation: ");
            Scanner sc = new Scanner(System.in);
            rs = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid number!");
        }
        return rs;
    }

    public void display() {
        System.out.println(title);
        int count = 1;
        for (String number : list) {
            System.out.println(count + ". " + number);
            count++;
        }
    }    
}
