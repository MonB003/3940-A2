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

    public void run() {
        System.out.println("Running");

        try {
            InputStream in = socket.getInputStream();
            Request req = new Request(in);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Response res = new Response(socket, baos);
            // Class<?> c = Class.forName("UploadServlet.java");
            // UploadServlet up = (UploadServlet) c.getDeclaredConstructor().newInstance();
            // if(req.getParameter("submit") != null){ //we need to check what page the request wants to go to

            // }
            
            UploadServlet up = new UploadServlet();
            System.out.println("1");

            

            up.doGet(res, req);
            up.doPost(res, req);
            // OutputStream out = socket.getOutputStream();
            // out.write(res.createByteArray());
            // System.out.println("2");
            // socket.close();


            // This code gets the form page on localhost:8999
            System.out.println("Client from web");

            System.out.println("2");
            socket.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // /*----------------------------------------------------------------------------------------------
        // */
        // Class<?> c = Class.forName("UploadServlet"); // reflection is working
        // HttpServlet upload = (HttpServlet) c.getDeclaredConstructor().newInstance();
        // HttpServlet httpServlet = upload; //new UploadServlet(); // where reflection
        // has to work, different server names, all classes must extend http servlet

        // /*----------------------------------------------------------------------------------------------
        // */
        // httpServlet.doPost(req, res);
        // OutputStream out = socket.getOutputStream();
        // out.write(((ByteArrayOutputStream) baos).toByteArray());
        // socket.close();

    }

}
