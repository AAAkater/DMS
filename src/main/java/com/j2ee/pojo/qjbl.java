package com.j2ee.pojo;

import java.util.LinkedHashSet;
import java.util.Scanner;

public class qjbl {
    public static int location = 0;

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.nextLine();
        LinkedHashSet<Character> strings=new LinkedHashSet<Character>();
        char[] a=s.toCharArray();
        for (int i = 0; i < a.length; i++) {
            strings.add(a[i]);
        }
        for(Character c:strings){
            System.out.print(c);
        }
    }
}
