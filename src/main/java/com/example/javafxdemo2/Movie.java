package com.example.javafxdemo2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {
    private String Title;
    private int YearOfRelease;
    private String Genre1;
    private String Genre2;
    private String Genre3;
    private int RunningTime;
    private String ProductionCompany;
    private int  Budget;
    private int Revenue;
    private String Genre[];


    private static int attributeNumber = 10;

    public Movie(String movieAttribute, String movieAttribute1, String movieAttribute2, String movieAttribute3, String movieAttribute4, String movieAttribute5, String movieAttribute6, String movieAttribute7, String movieAttribute8) {
        this.Title = movieAttribute;
        this.YearOfRelease = Integer.parseInt(movieAttribute1);
        this.Genre1= movieAttribute2;
        this.Genre2 = movieAttribute3;
        this.Genre3 = movieAttribute4;
        this.RunningTime = Integer.parseInt(movieAttribute5);
        this.ProductionCompany = movieAttribute6;
        this.Budget = Integer.parseInt(movieAttribute7);
        this.Revenue = Integer.parseInt(movieAttribute8);
        this.Genre = new String[]{Genre1, Genre2, Genre3};
    }

    public Movie() {

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getYearOfRelease() {
        return YearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        YearOfRelease = yearOfRelease;
    }

    public String getGenre1() {
        return Genre1;
    }

    public void setGenre1(String genre1) {
        Genre1 = genre1;
    }

    public String getGenre2() {
        return Genre2;
    }

    public void setGenre2(String genre2) {
        Genre2 = genre2;
    }

    public String getGenre3() {
        return Genre3;
    }

    public void setGenre3(String genre3) {
        Genre3 = genre3;
    }

    public int getRunningTime() {
        return RunningTime;
    }

    public void setRunningTime(int runningTime) {
        RunningTime = runningTime;
    }

    public String getProductionCompany() {
        return ProductionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        ProductionCompany = productionCompany;
    }

    public int getBudget() {
        return Budget;
    }

    public void setBudget(int budget) {
        Budget = budget;
    }

    public int getRevenue() {
        return Revenue;
    }

    public void setRevenue(int revenue) {
        Revenue = revenue;
    }

    public void setGenre(String[] genre) {
        Genre = genre;
    }

    public static int getAttributeNumber() {
        return attributeNumber;
    }

    public static void setAttributeNumber(int attributeNumber) {
        Movie.attributeNumber = attributeNumber;
    }

    public int findProfit(){
        return this.Revenue-this.Budget;
    }

    //Display
    public void displayMovie() {
        System.out.println("Movie Title : " + this.Title);
        System.out.println("Year of Release : " + this.YearOfRelease);
        System.out.println("Genre : " + this.Genre1 + "," + this.Genre2 + "," + this.Genre3);
        System.out.println("Running Time : " + this.RunningTime);
        System.out.println("Production Company : " + this.ProductionCompany);
        System.out.println("Budget : " + this.Budget);
        System.out.println("Revenue : " + this.Revenue);
        System.out.println("\n");
    }

    public String[] getGenre() {
        return this.Genre;
    }
}
