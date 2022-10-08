package servlets;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.Clock;
import java.io.*;

import request.Request;
import response.Response;

public class UploadServlet implements Servlet {
    @Override
    public void doGet(Response res, Request req) {
        System.out.println("GET REQUEST MADE");
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        writer.write("<!DOCTYPE html>\r\n");
        writer.write("<html>\r\n");
        writer.write("<head>\r\n");
        writer.write("<title>File Upload Form</title>\r\n");
        writer.write("</head>\r\n");
        writer.write("<body>\r\n");
        writer.write("<h1>Upload file</h1>\r\n");
        writer.write("<form method=\"POST\" action=\"upload\" enctype=\"multipart/form-data\">\r\n");
        writer.write("<input type=\"file\" name=\"fileName\"/><br/><br/>\r\n");
        writer.write("Caption: <input type=\"text\" name=\"caption\"<br/><br/>\r\n");
        writer.write("<br />\n");
        writer.write("Date: <input type=\"date\" name=\"date\"<br/><br/>\r\n");
        writer.write("<br />\n");
        writer.write("<input type=\"submit\" value=\"Submit\"/>\r\n");
        writer.write("</form>\r\n");
        writer.write("</body>\r\n</html>\r\n");
    }

    @Override
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
