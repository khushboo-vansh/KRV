package org.krv.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * @author Khushboo_Vansh
 */
public class HelloLambda implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        String data= input != null ? input.toString() : "{}";
        context.getLogger().log("Input: " + data);
        return "Test completed."+data;
    }
}