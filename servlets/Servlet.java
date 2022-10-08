package servlets;

import request.Request;
import response.Response;




public abstract interface Servlet{

    public void doGet(Response res, Request req);
    public void doPost(Response res, Request req);


}