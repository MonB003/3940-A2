package router;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import request.Request;
import response.Response;
import servlets.UploadServlet;

public class Router extends Thread {

    private Socket socket = null;

    public Router(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        System.out.println("Running");


        try{
            InputStream in = socket.getInputStream(); 
            Request req = new Request(in);  
            ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
            Response res = new Response(baos); 
            // Class<?> c = Class.forName("UploadServlet.java");
            // UploadServlet up = (UploadServlet) c.getDeclaredConstructor().newInstance();
            UploadServlet up = new UploadServlet();
            System.out.println("1");
           // up.doGet(res, req);
            up.doPost(res, req);
            OutputStream out = socket.getOutputStream();
            out.write(res.createByteArray());
            System.out.println("2");
            // socket.close();

            String listing = "";
            // Socket socket = new Socket("localhost", 8999);
            BufferedReader bufferIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream outBuff = socket.getOutputStream();
            FileInputStream fis = new FileInputStream("AndroidLogo.png");
            byte[] bytes = fis.readAllBytes();
            out.write(bytes);
            socket.shutdownOutput();
            fis.close();
            System.out.println("Came this far\n");
            String filename = "";
            while ((filename = bufferIn.readLine()) != null) {
                listing += filename;
            }
            socket.shutdownInput();
      
        
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        
        
        // /*---------------------------------------------------------------------------------------------- */
        // Class<?> c = Class.forName("UploadServlet"); // reflection is working
        // HttpServlet upload = (HttpServlet) c.getDeclaredConstructor().newInstance();
        // HttpServlet httpServlet = upload; //new UploadServlet(); // where reflection has to work, different server names, all classes must extend http servlet

        // /*---------------------------------------------------------------------------------------------- */
        // httpServlet.doPost(req, res);
        // OutputStream out = socket.getOutputStream(); 
        // out.write(((ByteArrayOutputStream) baos).toByteArray());
        // socket.close();
     
  }

}
