/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._nguyenNgocMaiHan;

/**
 *
 * @author HAN NGUYEN
 */
public class Story implements Comparable<Story>{
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

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public void setLanguage(char language) {
        this.language = language;
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

    @Override
    public int compareTo(Story story) {
        if (this.title.compareTo(story.title) > 0) {
            return 1;
        } else if (this.title.compareTo(story.title) == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}
