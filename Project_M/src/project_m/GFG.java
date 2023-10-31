/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_m;

// Java function to validate the time in
// 24-hour format using Regular Expression.
 
import java.util.regex.*;
 
class GFG {
    // Function to validate the time in 24-hour format
    public static boolean isValidTime(String time)
    {
 
        // Regex 
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
        Pattern p = Pattern.compile(regex);
        if (time == null) {
            return false;
        }
        Matcher m = p.matcher(time);
        return m.matches();
    }
 
    // Driver Code.
    // Test Case 1:
//     String str1 = "13:05:03";
//     System.out.println(str1 + " : " + isValidTime(str1));
    
}