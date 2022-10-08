package request;

import java.io.InputStream;

public class Request {
  
    private InputStream inputStream = null;

    public Request(InputStream inStream) {
        System.out.println("INPUT STREAM");
        inputStream = inStream;

        System.out.println("stream:");
        System.out.println(inStream);
    }


    public InputStream getInputStream() {
        return inputStream;
    }
}

