package com.example.javafxdemo2;

import java.io.Serializable;

public class TransferMessage implements Serializable {
    public static String sender;
    public static Movie transferMovie;
    public static String receiver;

    public TransferMessage(String sender, Movie transferMovie, String receiver) {
        this.sender = sender;
        this.transferMovie = transferMovie;
        this.receiver = receiver;
    }
}
