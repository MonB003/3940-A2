package servlets;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.Clock;

import request.*;
import response.*;


import java.io.*;

public class UploadServlet extends Servlet {
//    @Override
    public void doGet(Response res, Request req) {
        System.out.println("GET REQUEST MADE");
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();

        // writer.println("<html>\n" + "<head><title>" + "Login" + "</title></head>\n" + "<body>\n"
        //         + "<h1 align="center">" + "Login" + "</h1>\n" + "<form action="login" method="POST">\n"
        //         + "Username: <input type="text" name="user_id">\n" + "<br />\n"
        //         + "Password: <input type="password" name="password" />\n" + "<br />\n"
        //         + "<input type="submit" value="Sign in" />\n" + "</form>\n"
        //         + "</form>\n" + "</body>\n</html\n");


        /* FORM */
        String html = "<!DOCTYPE html>\r\n" +
                           "<html>\r\n" +
                           "<head>\r\n" + 
                           "<title>File Upload Form</title>\r\n" +
                           "</head>\r\n" +
                           "<body>\r\n" + 
                           "<h1>Upload file</h1>\r\n" + 
                           "<form method=\"POST\" action=\"upload\" enctype=\"multipart/form-data\">\r\n" +
                           "<input type=\"file\" name=\"fileName\"/><br/><br/>\r\n"+
                           "Caption: <input type=\"text\" name=\"caption\"<br/><br/>\r\n" +
                           "<br />\n"+
                           "Date: <input type=\"date\" name=\"date\"<br/><br/>\r\n" +
                           "<br />\n" +
                           "<input type=\"submit\" value=\"Submit\"/>\r\n" +
                           "</form>\r\n" +
                           "</body>\r\n</html>\r\n";
            System.out.println(html);
            writer.println(html);

        // writer.write("<!DOCTYPE html>\r\n");
        // writer.write("<html>\r\n");
        // writer.write("<head>\r\n");
        // writer.write("<title>File Upload Form</title>\r\n");
        // writer.write("</head>\r\n");
        // writer.write("<body>\r\n");
        // writer.write("<h1>Upload file</h1>\r\n");
        // writer.write("<form method=\"POST\" action=\"upload\" enctype=\"multipart/form-data\">\r\n");
        // writer.write("<input type=\"file\" name=\"fileName\"/><br/><br/>\r\n");
        // writer.write("Caption: <input type=\"text\" name=\"caption\"<br/><br/>\r\n");
        // writer.write("<br />\n");
        // writer.write("Date: <input type=\"date\" name=\"date\"<br/><br/>\r\n");
        // writer.write("<br />\n");
        // writer.write("<input type=\"submit\" value=\"Submit\"/>\r\n");
        // writer.write("</form>\r\n");
        // writer.write("</body>\r\n</html>\r\n");
    }

//    @Override
    public void doPost(Response res, Request req) {
        System.out.println("POST");

        try {
            InputStream in = req.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] content = new byte[1];
            int bytesRead = -1;
            while ((bytesRead = in.read(content)) != -1) {
                baos.write(content, 0, bytesRead);
            }
            Clock clock = Clock.systemDefaultZone();
            long milliSeconds = clock.millis();
            OutputStream outputStream = new FileOutputStream(new File(String.valueOf(milliSeconds) + ".png"));
            baos.writeTo(outputStream);
            outputStream.close();
            PrintWriter out = new PrintWriter(res.getOutputStream(), true);
            File dir = new File(".");
            String[] chld = dir.list();
            for (int i = 0; i < chld.length; i++) {
                String fileName = chld[i];
                out.println(fileName + "\n");
                System.out.println(fileName);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }
}
