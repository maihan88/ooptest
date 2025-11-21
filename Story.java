/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._nguyenNgocMaiHan;

/**
 *
 * @author HAN NGUYEN
 */
public class Story {
    private int number; //stt
    private int id;
    private String title;
    private double rating;
    private String author;
    private boolean isCompleted;
    private char language;

    public Story(int number, int id, String title, double rating, String author, boolean isCompleted, char language) {
        this.number = number;
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.author = author;
        this.isCompleted = isCompleted;
        this.language = language;
    }

    
    @Override
    public String toString(){
        return String.format("| %-4d | %-5d | %-20s | %-15s | %-5.1f | %-10s | %-5s |", number, id, title, author, rating, isCompleted, language);
    }
    
    public void output(){
        System.out.println(toString());
    }
}
