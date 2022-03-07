/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distribuidos.middleware;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


/**
 *
 * @author Mortis
 */
public class Middleware implements Runnable {
    
    public static void sendInfoFromClientToServer(String info) throws IOException {        
        JsonParser parser = new JsonParser();
        JsonObject information = (JsonObject) parser.parse(info);
         
        try {
            
            Socket server = new Socket("localhost", 555);
            
            PrintWriter sendMsg
                    = new PrintWriter(server.getOutputStream(), true); // true -> flushing
            
            sendMsg.println(information);
            
            server.close();
            
        } catch (Exception ex) {
           
        }
    }
    
    public static void sendInfoFromServerToClient(String info) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject information = (JsonObject) parser.parse(info);
         
        try {
            
            Socket server = new Socket("localhost", 444);
            
            PrintWriter sendMsg
                    = new PrintWriter(server.getOutputStream(), true); // true -> flushing
            
            sendMsg.println(information);
            
            server.close();
            
        } catch (Exception ex) {
           
        }
    }
    
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(777);
        ServerSocket clientSocket = new ServerSocket(666);
        
        Thread client = new Thread() {
            public void run(){
                try {
                    while (true) {
                        System.out.println("Waiting for client connection...");
                        Socket soClient = clientSocket.accept();
                        System.out.println("Client connected");
                        
                        Scanner scanner = new Scanner(soClient.getInputStream());
                      
                        sendInfoFromClientToServer(scanner.nextLine());
                        
                        soClient.close();
                    }
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        };

        Thread server = new Thread() {
            public void run() {
                try {
                    while (true) {
                        System.out.println("Waiting for server connection...");
                        Socket soServer = serverSocket.accept();
                        System.out.println("Server connected");
                        
                        Scanner scanner = new Scanner(soServer.getInputStream());
                      
                        sendInfoFromServerToClient(scanner.nextLine());
                        
                        soServer.close();
                    }
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        };
        
        client.start();
        server.start();
    }

    @Override
    public void run() {

    }
}
