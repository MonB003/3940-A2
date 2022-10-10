package request;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Request {

    private InputStream inputStream = null;
    private String reqType = null;
    private String reqUserAgent = "";

    public Request(InputStream inStream) {
        System.out.println("INPUT STREAM");

        inputStream = inStream;

        // switch(reqUserAgent){
        // // Depending on the User-Agent, we need to send back different response.
        // case "browser":
        // break;
        // case "cli":
        // break;
        // }

        // Create a method to parse incoming request payload
        parsePayload(inputStream);

    }

    public void parsePayload(InputStream inStream) {
        System.out.println("PARSE PAYLOAD");

        Scanner scanner = new Scanner(inStream);

        // Need to know Request type: GET or POST
        // Parse First Line
        reqType = scanner.next();
        System.out.println("The Request Type is: " + reqType);

        // Need to know User-Agent type: Find where the connection is comming from, CLI
        // or Browser.
        // If User agent exists - browser, else - command line

        // Stores the current line being read
        String temp = "";

        // Loops until user agent line in the header
        for (int i = 0; i < 6; i++) {
            temp = scanner.nextLine();
            System.out.println(temp);

            // User agent is the sixth line in the header
            if (i == 5) {
                System.out.println("LAST LINE:");
                String userAgentLine = temp;
                String[] userAgentStr = userAgentLine.split(" ", 2);

                System.out.println("FOR LOOP:");
                for (int j = 0; j < userAgentStr.length; j++) {
                    System.out.println(j + ": " + userAgentStr[j]);
                }

                // Check user agent value
                if (temp.startsWith("User-Agent:")) {
                    reqUserAgent = "browser";

                } else {
                    reqUserAgent = "cli";
                }

                System.out.println("***User agent found in Request.java: " + reqUserAgent);
            }
        }

        // ArrayList<String,String> FormData = new ArrayList<String,String>(); -
        // Consider using hashmap instead..
        HashMap<String, String> FormData = new HashMap<String, String>();

        // Parse First Line
        // String FirstLine = scanner.next();

        // // Check First Line
        // switch(reqType){
        // case "GET":
        // // We will know to execute GET Request
        // FormData.put("GET", reqType);
        // break;
        // case "POST":
        // // We will know to execute POST request
        // FormData.put("POST",reqType);
        // break;
        // }

        // String currentLine;

        // while (scanner.hasNextLine()) {

        // currentLine = scanner.nextLine();
        // System.out.println(currentLine);
        // // Parse each line after the first..
        // if (currentLine.startsWith("User-Agent")) {
        // reqUserAgent = "browser";
        // } else {
        // reqUserAgent = "cli";
        // }
        // //FormData.add(currentLine); //do this after we see a boundary
        // }

        System.out.println("Made it here");
        // scanner.close();
    }

    public void setReqType(final String reqType) {
        this.reqType = reqType;
    }

    public String getReqMethod() {
        return reqType;
    }

    public String getUserAgent() {
        return reqUserAgent;
    }

    public void setUserAgent(String userAgent) {
        reqUserAgent = userAgent;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
