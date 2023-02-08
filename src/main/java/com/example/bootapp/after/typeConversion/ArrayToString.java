package com.example.bootapp.after.typeConversion;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ArrayToString {
    public static void main(String[] args) {
        // 1. Array to String
        String[] strArray = {"Hello", " ", "Java", " ", "World"};

        // (1) Arrays.toString()
        System.out.println(strArray);   // 배열의 주소 값이 나온다.
        String strArrayToString1 = Arrays.toString(strArray);
        System.out.println(strArrayToString1);   // strArrayToString
        System.out.println("Arrays.toString() ===>>>" +strArrayToString1 instanceof String);    // true

        // (2) StringBuilder.Append()
        // StringBuilder 타입의 객체를 생성하고,
        // StringBuilder 클래스의 Append()메서드를 사용하여 문자열 배열 요소를 하나씩 추가한다.
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < strArray.length; i++) {
            stringBuilder.append(strArray[i]+ "");
        }

        // 문자열 배열의 모든 요소가 StringBuilder 객체에 추가되면
        // toString()메서드를 사용하여 하나의 문자열로 만든다.
        String strArrayToString2 = stringBuilder.toString();

        System.out.println("StringBuilder.Append()===>>>" + strArrayToString2);  // Hello Java World

        // (3) join()
        // join() 메서드는 두 개의 인수를 가진다.
        // join(문자열의 요소를 구분하는 기호, 문자열 배열)
        String strArrayToString3 = String.join("_", strArray);

        System.out.println("join()===>>>" + strArrayToString3); // Hello_ _Java_ _World

        // (4) Stream API - Collectors.joining()
        String strArrayToString4 = Arrays
                .stream(strArray)   // 문자열 배열 전달
                .collect(Collectors.joining()); // 문자열 배열을 Join

        System.out.println();




    }
}
