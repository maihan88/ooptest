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
    private int id;
    private String title;
    private double rating;
    private String author;
    private boolean isCompleted;
    private char language;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIsCompleted() {
        return isCompleted;
    }

    public char getLanguage() {
        return language;
    }

    public Story(int id, String title, double rating, String author, boolean isCompleted, char language) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.author = author;
        this.isCompleted = isCompleted;
        this.language = language;
    }

    public String toString(int number){
        return String.format("| %-6d | %-7d | %-40s | %-25s | %-5.1f | %-10s | %-8s |", number, id, title, author, rating, isCompleted, language);
    }
    
    public void output(int number){
        System.out.println(toString(number));
    }
}
