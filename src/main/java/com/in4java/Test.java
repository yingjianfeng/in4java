package com.in4java;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;


public class Test {

    public static void main(String[] args) {
        for (GarbageCollectorMXBean b : ManagementFactory.getGarbageCollectorMXBeans()) {
            System.out.println(b.getName());
        }

    }

    public boolean strongPasswordCheckerII(String password) {
        if(password.length() < 8) return false;
        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        String str ="!@#$%^&*()-+";
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) hasLowerCase = true;
            if (Character.isUpperCase(password.charAt(i))) hasUpperCase = true;
            if (Character.isDigit(password.charAt(i))) hasDigit = true;
            if(str.indexOf(password.charAt(i)) != -1)  hasSpecial = true;
            if(i>0&& password.charAt(i-1) == password.charAt(i)) return false;
        }
        return hasLowerCase && hasUpperCase && hasDigit && hasSpecial;
    }
}


