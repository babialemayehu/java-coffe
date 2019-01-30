package com.group2.util.support;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;

public class Request {
    private HttpServletRequest request;
    public Request(HttpServletRequest request){
        this.request = request;
    }

    public HashMap<String, Object> all(String ... except){
        Enumeration params = this.request.getParameterNames();
        HashMap<String, Object> requestParams = new HashMap<>();
        while(params.hasMoreElements()){
            String param = (String)params.nextElement();
            if(!Arrays.asList(except).contains(param))
                requestParams.put(param, this.request.getParameter(param));
        }
        return (requestParams.isEmpty())? null: requestParams;
    }
    public String allInJson(String ...except){
        HashMap<String, Object> request = this.all(except);
        Gson json = new Gson();
        return json.toJson(request);
    }
}
