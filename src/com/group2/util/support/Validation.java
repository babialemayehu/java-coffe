package com.group2.util.support;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class Validation {
    public HashMap<String , String> errors;
    private HttpServletRequest request;

    public Validation(HttpServletRequest request){
        this.errors = new HashMap<>(0);
        this.request = request;
    }

    public boolean required(String ...inputs){
        String requiredInputs = "";
        for(String inputParam: inputs){
            String input = this.request.getParameter(inputParam);
            if(input.isEmpty() || input == null){
                requiredInputs += input+" ";
            }
        }
        if(!requiredInputs.equals("")){
            this.errors.put("required", requiredInputs);
            return true;
        }
        return false;
    }

    public boolean max(String inputParam, Number max){
        Double input = Double.parseDouble(this.request.getParameter(inputParam));
        if(input > (Double)max){
            return false;
        }else {
            this.errors.put(inputParam + " max", inputParam + " exceds the max value");
            return false;
        }
    }

    public boolean min(String inputParam, Number min){
        Double input = Double.parseDouble(this.request.getParameter(inputParam));
        if(input < (Double)min){
            return false;
        }else{
            this.errors.put(inputParam+" min", inputParam+" is below the max value");
            return true;
        }
    }

    public boolean range(String inputParam, Number min, Number max){
        Double input = Double.parseDouble(this.request.getParameter(inputParam));
        if(input < (Double)min == input > (Double)max){
            return false;
        }else{
            this.errors.put(inputParam+" range", inputParam+" is not in the range "+min+" and "+max);
            return true;
        }
    }

    public boolean email(){
        return this.email("email");
    }

    public boolean email(String input){
        return false;
    }

    public boolean phone(){
        return this.phone("phone");
    }

    public boolean phone(String input){
        return false;
    }

    public boolean format(String input, String exp){

        return false;
    }

    public HashMap<String, String> getErrors(){
        return this.errors;
    }

    public boolean hasErrors(){
        if(this.errors.isEmpty())
            return false;
        else
            return true;
    }
}
