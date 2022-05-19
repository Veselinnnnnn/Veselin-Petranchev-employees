package com.company.services;

import com.company.models.Employee;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ReadFromFile {
    // Boolean to check if file was read successfully
    boolean temp = true;

    // Array list to store information from file
    private final ArrayList<Employee> employees = new ArrayList<>();

    // Getter
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    // Reading from file method
    public void ReadFile(String file) throws FileNotFoundException, ParseException {

       if(!file.equals("temp") && file.endsWith(".csv")) {
           Scanner scanner = new Scanner(new File(file));
           scanner.useDelimiter(Pattern.compile(", |(\\n)"));

           while(scanner.hasNext()){
               boolean flag = false;

               int rememberIndex = 0;

               String temp = scanner.next();

               for(int i = 0 ; i < employees.size(); i++) {
                   if (Integer.parseInt(temp) == employees.get(i).getEmpId()) {
                       flag = true;
                       rememberIndex = i;
                       break;
                   }
               }

               // if the employee already exists - add new project to the employee's projects
               // if not - create a new employee object
               if (flag) {
                   employees.get(rememberIndex).addProject(Integer.parseInt(scanner.next()), scanner.next(), scanner.next());
               } else {
                   employees.add(new Employee(Integer.parseInt(temp), Integer.parseInt(scanner.next()), scanner.next(), scanner.next()));
               }
           }
           scanner.close();
       }else{
           this.temp = false;
           JOptionPane.showMessageDialog(new Frame(), "    " +
                   "       Roses are red,\n" +
                   "          Violets are blue,\n" +
                   "      Now what do we do ?\n\n"+
                   "HINT: Only .csv files are allowed!");
       }

    }

    // Getter
    public boolean isTemp() {
        return temp;
    }
}
