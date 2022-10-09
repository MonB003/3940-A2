package servlets;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.Clock;

import request.*;
import response.*;

import java.io.*;

public class UploadServlet extends Servlet {
    public void doGet(Response res, Request req) {
        System.out.println("Here in do get");
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        String html = "<!DOCTYPE html>\r\n" +
                "<html>\r\n" +
                "<head>\r\n" +
                "<title>File Upload Form</title>\r\n" +
                "</head>\r\n" +
                "<body>\r\n" +
                "<h1>Upload file</h1>\r\n" +
                "<form id=\"form\" method=\"POST\" action=\"/\" enctype=\"multipart/form-data\">\r\n" + // up to us on where to go, another tcp request comes
                "<input type=\"file\" name=\"fileName\"/><br/><br/>\r\n" +
                "Caption: <input type=\"text\" name=\"caption\"<br/><br/>\r\n" +
                "<br />\n" +
                "Date: <input type=\"date\" name=\"date\"<br/><br/>\r\n" +
                "<br />\n" +
                "<input id='formBtn' type=\"submit\" name=\"submit\" value=\"Submit\"/>\r\n" +
                "</form>\r\n" +
                "</body>\r\n</html>\r\n";


        System.out.println(html);
        writer.println(html);

        System.out.println(req);
        res.send(html);

        // "<script>document.getElementById('formBtn').addEventListener('click', function(event){" +
        //         "event.preventDefault()});</script>" +

    }

    public void doPost(Response res, Request req) {
        System.out.println("POST");
        System.out.println("res: " + res);
        System.out.println("req: " + req);


        try {
            InputStream in = req.getInputStream();
         
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] content = new byte[1];
            int bytesRead = -1;
            while ((bytesRead = in.read(content)) != -1) {
                baos.write(content, 0, bytesRead);
            }
            System.out.println("2");
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
                System.out.println(fileName); // writing to the console
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }
}
