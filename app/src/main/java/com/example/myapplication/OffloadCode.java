package com.example.myapplication;


import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

public class OffloadCode {
    Context context;
    String code;
    String parameters;
    public String Codeformat(String code,String parameters){
        String format="import java.*;" +
                "class NQueenProblem{"+
                "final static int N ="+Integer.parseInt(parameters)+";"+
                    code+
                    "public static void main(String[] args) {"+
                        "NQueenProblem Queen = new NQueenProblem();\n" +
                        "Queen.solveNQ();"+
                    "}"+
                "}";
        return format;
    }
    OffloadCode(String code, String parameters, Context context){
        this.code=Codeformat(code,parameters);
        this.parameters = parameters;
        this.context=context;
    }
    public String getConnection(){
        OffloadingConnection conn=new OffloadingConnection(context);
        return conn.makeJsonObjectRequest(code,parameters);

    }

}
