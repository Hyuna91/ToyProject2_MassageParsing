package com.example.bootapp.after;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hex {
    public static void main(String[] args) {

        // String to hex
        String apple = "Apple";     // 변경할 String
        String appendValue = "";    // 변경된 String

        StringBuffer sbuf = new StringBuffer();
        for(int i=0; i<apple.length(); i++){
            sbuf.append( "0x" + Integer.toHexString(apple.charAt(i)) );
        }
        appendValue = sbuf.toString();

        System.out.println("Original Page default " + apple);   // Apple
        System.out.println("Original Page convert " + appendValue); // 0x410x700x700x6c0x65


        // hex to String
        String convertValue = "";
        Pattern p = Pattern.compile("(0x([a-fA-F0-9]{2}([a-fA-F0-9]{2})?))");
        Matcher m = p.matcher(appendValue);

        StringBuffer sb = new StringBuffer();
        int hashCode = 0;
        while( m.find() ) {
            hashCode = Integer.decode("0x" + m.group(2));
            m.appendReplacement(sb, new String(Character.toChars(hashCode)));
        }
        m.appendTail(sb);
        convertValue = sb.toString();

        System.out.println("GPKIError Page convert : " + convertValue);

    }
}
