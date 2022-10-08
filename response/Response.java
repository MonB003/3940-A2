package response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Response {
    
final public static String HTTP_VERSION = "HTTP/1.1 OK";

    // private OutputStream out;
    private PrintWriter printWriter;
    private String characterEncoding;
    private String contentType;
    private ByteArrayOutputStream out;
    private ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy,MM,dd HH:mm:ss");

    public Response(ByteArrayOutputStream outStream) {
        this.out = outStream;
        printWriter = new PrintWriter(out);
    }

    private void createHeader() throws IOException{
        String headerString = "GET /"+ HTTP_VERSION + "\r\n" 
                            + "Date: "+ LocalDateTime.now() + "\r\n"
                          
                            + "Content-Type: " + contentType + "\r\n" 
                            + "Character-Encoding: " + characterEncoding + "\r\n";
        System.out.println(headerString);
        out.write(headerString.getBytes());
    }

    public OutputStream getOutputStream() {

        return out;
    }

    public byte[] createByteArray() {
       System.out.println("CREATE BYTE ARRAY");
        try {
            createHeader();
            out.write(outStream.toByteArray());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }

    public ByteArrayOutputStream getByteArray(){
        System.out.println("GET BYTE ARRAY");
        return outStream;
    }

    public PrintWriter getWriter() {
        return printWriter;
    }

    public void setCharacterEncoding(String string) {

        this.characterEncoding = string;
    }

    public void setContentType(String string) {
        this.contentType = string;
    }
}
