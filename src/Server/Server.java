/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class Server {

    static ArrayList<ClientHandler> clients;

    public static void main(String[] args) {
        clients = new ArrayList<>();
        ServerSocket ss;
        Socket s;
        try {
            ss = new ServerSocket(1234);
            while (true) {
                s = ss.accept();
                System.out.println("Client request: " + s);
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                System.out.println("Builing a new handler");
                ClientHandler handler = new ClientHandler(s, ois, oos);
                handler.start();

            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
