=================================
COMPILE:
To compile, change directory to the ROOT folder and compile all java files in this folder.
Open a command prompt
cd into folder
javac *.java

RUN:
Run Server.java to start the Server.
java Server

On another command prompt:
java ClientServlet
Follow the instructions in the Command Line Interface:
- Enter image URL
- Enter Keyword
- Enter Caption

=================================

CONTRIBUTIONS:

Name: Jay Wang
ID: A01291640
Created ClientServlet.java and worked on:
- Socket Connection to Server
- Sending all needed data in a Multipart/form-data from CLI
    - Encoding and sending Image File data in Base64 and saving to Multipart/form-data. Date, Keyword and Caption data.
- Getting userInput for CLI interaction [getUserInput()]

Request.java
- Parsing Multipart/form-data and saving corresponding BodyPart values [Date, Keyword, Caption].
- Parsing Multipart/form-data to decode Base64 Image data and upload file in /images directory.
- Helped with Browser interaction for Uploading Images.

=================================

Name: Monica Bacatan
ID: A01268930
- Retrieve user agent from form data (determine if request comes from browser or cli)
- Custom exception - creating a class and throwing the exception in try catch blocks in ClientServlet.java
- Method to write error messages to a log text file
- Loop to read and get user's input from command line
- Helped with returning a list of alphabetically sorted images from the image folder
- Helped create the html page as a string for sending to the browser
- Helped edit the path images were being stored in (images folder)

=================================

Name: Brad Masciotra
ID: A01247718

- Reflection 
    - implemented reflection based on the servlet class and its derivatives
    - cast new instances of each class to create different servlets based on the type of request coming in
- Abstract classes and Derivitives
    - making base classes and Derivitives
    - implementing inheritance
    - designing hierarchie and how we wanted the classes to interact/take advantage of inheritance and reflection in router class.
- Response and Request Classes
    - creating classes and working on methods doGet/doPost
- Browser Side Servlets/methods
    - worked mainly on the first part of the project in rendering the html form to the browser through our initial get request
    - decomposing the html page into the respond class in the get method for uploadservlet
    - worked on router, implementing decision logic for post and get methods
- exception handling
    - directing the program flow so that exceptions did not halt the program and continue while error messages are being displayed (multiple loops and try catch statments)
    - appending nultiple lines to error log
- Project Setup
    - setup the original heirarhcy to maintain easy workflow and pathing between classes and subclasses
- AspectJ
    - researched and tried multiple times to include in project but was unsuccessful
    
=================================

Name: Will Whitfield
ID: A01267214

- Server, multithreading
- Image upload (support with base64 decoding and storage)
- Custom exception (with monica)
- Request and response classes (support)
- Project Setup (with brad)
- Reflections (support)
- AspectJ (Even though it didn't exactly work, we really tried to implement it)
- General bug fixes