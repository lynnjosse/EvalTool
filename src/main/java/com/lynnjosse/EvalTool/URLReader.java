package com.lynnjosse.EvalTool;

import java.net.*;
import java.io.*;

public class URLReader {

    public static void main(String[] args)throws Exception {
        URL google = new URL(need to figure out how to build this from db object);

        /*
        "https://maps.googleapis.com/maps/api/geocode/json?address="+building.address+",Saint+Louis,+
        MO&key=AIzaSyB5Zp0pblaGY2knVWWtbA20mQX9UkqDFSI"

        there is a problem here with the address, because it has spaces insstead of plus signs, so substitute plus signs for spaces.
        bah.

         */
        BufferedReader in = new BufferedReader(
                new InputStreamReader(google.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();


    }
}
