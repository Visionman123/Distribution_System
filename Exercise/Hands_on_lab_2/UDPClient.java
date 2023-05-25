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
public class UDPClient {

    public static void main(String[] args) throws Exception {
        // Server configuration
        String serverIP = "localhost";  // Replace with your server IP address
        int serverPort = 6789;         // Use the same port number as the server

        // Create a UDP socket
        DatagramSocket clientSocket = new DatagramSocket();

        byte[] sendData;
        byte[] receiveData = new byte[1024];

        String clientName = getInput("Enter your name: ");

        // Get the client's IP address and port
        InetAddress clientAddress = InetAddress.getLocalHost();
        int clientPort = clientSocket.getLocalPort();
        System.out.println("Connected to Server " + InetAddress.getLocalHost().getHostAddress() + ":" + serverPort
                + " from Client " + clientAddress.getHostAddress() + ":" + clientPort);

        while (true) {
            // Send a message to the server
            InetAddress serverAddress = InetAddress.getByName(serverIP);

            String message = getInput(clientName + ": ");
            sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            // Check if client wants to end the session
            if (message.trim().equalsIgnoreCase("bye")) {
                System.out.println("Client have ended the session.");
                break;
            }

            // Receive the server's response
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server: " + response);

            // Check if server wants to end the session
            if (response.trim().equalsIgnoreCase("bye")) {
                System.out.println("Server has ended the session.");
                break;
            }
        }

        // Close the client socket
        clientSocket.close();
    }

    private static String getInput(String prompt) throws Exception {
        System.out.print(prompt);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedReader.readLine();
    }
}
