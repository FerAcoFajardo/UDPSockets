package com.distribuidos.server;


import com.distribuidos.dominio.Person;
import static com.distribuidos.middleware.Middleware.sendInfoFromClientToServer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mavel
 */
public class Server {
    
    public static void sendInfoToMiddleware(String info) {
        JsonParser parser = new JsonParser();
        JsonObject informationRecived = (JsonObject) parser.parse(info);
        
        Person person = new Person(
                String.valueOf(informationRecived.get("name")),
                Float.parseFloat(String.valueOf(informationRecived.get("weight"))),
                Float.parseFloat(String.valueOf(informationRecived.get("height")))
        );
        
        person.calculateBMI();
        
        JsonObject information = new JsonObject();
        
        information.addProperty("name", String.valueOf(person.getName()));
        information.addProperty("weight", person.getWeight());
        information.addProperty("height", person.getHeight());
        information.addProperty("bmi", person.getBmi());
        
        try {
            
            Socket middleware = new Socket("localhost", 777);
            
            PrintWriter sendMsg
                    = new PrintWriter(middleware.getOutputStream(), true); // true -> flushing
            
            sendMsg.println(information);
            
            middleware.close();
            
        } catch (Exception ex) {
           
        } 
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        ServerSocket middlewareSocket = new ServerSocket(555);
        
        try {
                while (true) {
                    System.out.println("Waiting for middleware connection...");
                    Socket so = middlewareSocket.accept();
                    System.out.println("Middleware connected");

                    Scanner scanner = new Scanner(so.getInputStream());
                    
                    sendInfoToMiddleware(scanner.nextLine());
                    
                    so.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
    }

}
