package com.company.models;

import java.text.ParseException;
import java.util.ArrayList;

public class Employee {
    private final int empId;
    private final ArrayList<Projects> projects = new ArrayList<>();

    // constructor
    public Employee(int empId,int projectId, String dateFrom, String dateTo) throws ParseException {
        this.empId = empId;
        this.projects.add( new Projects(projectId,dateFrom,dateTo));
    }

    // getter
    public int getEmpId() {
        return this.empId;
    }

    // getter
    public ArrayList<Projects> getProjects() {
        return projects;
    }

    public void addProject(int projectId, String dateFrom, String dateTo) throws ParseException {
        this.projects.add(new Projects(projectId,dateFrom,dateTo));
    }
}
