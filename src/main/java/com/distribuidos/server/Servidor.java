package com.distribuidos.server;


import com.distribuidos.dominio.Person;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
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
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int PUERTO = 3000;
        byte buffer[] = new byte[65535];

        try(DatagramSocket udpSocket = new DatagramSocket(PUERTO)){
            Gson gson = new Gson();

            System.out.println("iniciando servidor");
            
            while(true){
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                udpSocket.receive(packet);

                String message = new String(packet.getData());

//                buffer = message.getBytes();

//                System.out.println(message);
                JsonReader reader = new JsonReader(new StringReader(message));
                reader.setLenient(true);

                Person persona = gson.fromJson(reader, Person.class);

                persona.calculateBMI();

                String respuesta = gson.toJson(persona);

                int puertoCliente = packet.getPort();
                InetAddress direccion = packet.getAddress();
//                System.out.println(respuesta);
                buffer = null;
                buffer = respuesta.getBytes();
                DatagramPacket answer = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);

                udpSocket.send(answer);
            }
        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
