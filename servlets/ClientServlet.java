package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import request.Request;
import response.Response;

public class ClientServlet extends ConcreteServlet {

    // Defining for Socket, the name of the Host
    public String serverName = "localhost";

    // Socket Port Number
    public int portNumber = 8999;

    // File Path for Image
    public String imagePath;
    public String MultiDate = "";
    public String MultiKeyword = "";
    public String MultiCaption = "";
    
    /*
     * Objectives:
     * Create Socket Connection to Server.java
     * Recieve Input information about:
     * Name of Server, in this case, which is "Server". (Server.java)
     * Information [Data]
     * Information [Keyword]
     * Information [Caption]
     * Information [Image]
     * 
     * Only need to implement POST Request, as we are only required to specify a POST Request to "Upload" an image to our /images directory.
    */

    public void POSTRequest(){
        // HttpURLConnection conn = null;
        BufferedReader reader = null;
        String charset = "UTF-8";

        // Assign File w/ imagePath
        Path file = Paths.get(imagePath);

        // POST Request: Upload an image from File System as "Multipart Data" along with other form data (Date, Keyword, Caption, Filename)
        try {

            // Create Socket Connection to "server" at "portNumber".
            Socket serverSocket = new Socket(serverName,portNumber);

            // Get OutputStream for Socket
            OutputStream outputStream = serverSocket.getOutputStream();

            // Create PrintWriter for output capabilities to Server
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream,charset));

            // Define and open connection to URL.
            // URL url = new URL("http://localhost:8081/Client-Server-A1b/upload");
            // conn = (HttpURLConnection) url.openConnection();
            String boundary = "boundary";
            String payload = "";
            String newLine = "\r\n";

            // Set output capability to true
            writer.append("POST / HTTP/1.1").append(newLine);
            writer.append("Content-Type: multipart/form-data; boundary=" + boundary).append(newLine);
            writer.append("User-Agent: cli").append(newLine).append(newLine).flush();
            
            // Sending text "Data"
            writer.append("--"+boundary).append(newLine);
            writer.append("Content-Disposition: form-data; name=\"Date\"").append(newLine);
            writer.append("Content-Type: text/plain; charset=" + charset).append(newLine);
            writer.append(newLine).append(MultiDate).append(newLine).flush();

            // Sending text "Keyword"
            writer.append("--"+boundary).append(newLine);
            writer.append("Content-Disposition: form-data; name=\"Keyword\"").append(newLine);
            writer.append("Content-Type: text/plain; charset=" + charset).append(newLine);
            writer.append(newLine).append(MultiKeyword).append(newLine).flush();

            // Sending text "Caption"
            writer.append("--"+boundary).append(newLine);
            writer.append("Content-Disposition: form-data; name=\"Caption\"").append(newLine);
            writer.append("Content-Type: text/plain; charset=" + charset).append(newLine);
            writer.append(newLine).append(MultiCaption).append(newLine).flush();
            
            // Sending Image File 
            writer.append("--" + boundary).append(newLine);
            writer.append("Content-Disposition: form-data; name=\"File\"; filename=\""+ file.getFileName() +"\"").append(newLine);
            writer.append("Content-Type: image/png").append(newLine);
            writer.append("Content-Transfer-Encoding: binary").append(newLine);
            writer.append(newLine).flush();
            Files.copy(file, outputStream);
            outputStream.flush();
            writer.append(newLine).flush();
            // End of Multipart.
            writer.append("--"+boundary+"--").append(newLine).flush();

            BufferedReader respnse = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

            serverSocket.close();

        } catch(Exception e) {
            System.out.println("POST Request Error: " + e);
            e.printStackTrace();
        }
    }

    public void getUserInput(){

        MultiDate = java.time.LocalDate.now().toString();

        // UserInput Scanner
        Scanner scanner = new Scanner(System.in);

        // User Input for Image
        System.out.print("Please enter the file path for the image\n> ");
        String userInput = scanner.nextLine();
        System.out.println("\n");
        while (userInput.equals("")){
            System.out.println("Invalid Input...");
            System.out.print("Please enter the file path for the image\n> ");
            userInput = scanner.nextLine();
        }
        imagePath = userInput;
        System.out.println("Image Path: " + imagePath + "\n");

        // User Input for Keyword
        System.out.print("Please enter a Keyword for the image\n> ");
        userInput = scanner.nextLine();
        System.out.println("\n");
        while (userInput.equals("")){
            System.out.println("Invalid Input...");
            System.out.print("Please enter a Keyword for the image\n> ");
            userInput = scanner.nextLine();
        }
        MultiKeyword = userInput;
        System.out.println("Keyword: " + MultiKeyword + "\n");

        // User Input for Keyword
        System.out.print("Please enter a Caption for the image\n> ");
        userInput = scanner.nextLine();
        System.out.println("\n");
        while (userInput.equals("")){
            System.out.println("Invalid Input...");
            System.out.print("Please enter a Caption for the image\n> ");
            userInput = scanner.nextLine();
        }
        MultiCaption = userInput;
        System.out.println("Caption: " + MultiCaption + "\n");

    }


    public void doGet(Response res, Request req){};
    public void doPost(Response res, Request req){};



    /** main method */
    public static void main(String args[]){

        ClientServlet client = new ClientServlet();
        client.getUserInput(); // get request --> making connection to server
        client.POSTRequest();

    }

}
