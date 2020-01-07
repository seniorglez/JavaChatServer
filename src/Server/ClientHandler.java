/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class ClientHandler extends Thread {

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    Socket s;

    ClientHandler(Socket s, ObjectInputStream ois, ObjectOutputStream oos) {
        this.ois = ois;
        this.oos = oos;
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {

            try {
                ChatMessage chmsg = (ChatMessage) ois.readObject();

                Server.clients.forEach((ch) -> {
                    try {
                        ch.oos.writeObject(chmsg);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
