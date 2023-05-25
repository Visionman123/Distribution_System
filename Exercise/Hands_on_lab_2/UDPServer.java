/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import java.net.*;
/**
 *
 * @author hoangnamcute
 */

public class UDPServer {
    public static void main(String[] args) throws Exception {
        // Server configuration
        int serverPort = 6789;  // Choose an available port number

        // Create a UDP socket
        DatagramSocket serverSocket = new DatagramSocket(serverPort);
        
        // Get the server's IP address
        InetAddress serverAddress = InetAddress.getLocalHost();

        byte[] receiveData = new byte[1024];

        while (true) {
            // Receive data from the client
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            // Get client details
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            
            // Print connection details
            System.out.println("Connected to Server " + serverAddress.getHostAddress() + ":" + serverPort +
                                " from Client " + clientAddress.getHostAddress() + ":" + clientPort);

            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Client: " + message);
            
            // Check if client wants to end the session
            if (message.trim().equalsIgnoreCase("bye")) {
                System.out.println("Client has ended the session.");
                break;
            }
            
            // Send a response to the client
            String response = getInput("Enter a response to send to Client: ");
            byte[] sendData = response.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
            
            // Check if server wants to end the session
            if (response.trim().equalsIgnoreCase("bye")) {
                System.out.println("Server has ended the session.");
                break;
            }
        }

        // Close the server socket
        serverSocket.close();
    }

    private static String getInput(String prompt) throws Exception {
        System.out.print(prompt);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedReader.readLine();
    }
}


