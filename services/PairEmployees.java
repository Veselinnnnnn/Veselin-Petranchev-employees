package com.company.services;

import com.company.models.Employee;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PairEmployees {
    private final ArrayList<Employee> employees;
    private final ArrayList<ArrayList<Integer>> info = new ArrayList<>();

    private int idOfFirstEmp = -1;
    private int idOfSecondEmp = -1;

    // class constructor
    public PairEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    // a function which calculates the pair of employees that has worked
    // together for the longest period of time
    public Object[][] pairEmployees() {
        long days = 0;

        int temp = 0;

        // finds the pair that has worked together for the longest period of time
        for (int i = 0; i < employees.size() - 1; i++) {
            for (int k = 0; k < employees.get(i).getProjects().size(); k++) {
                long dateFrom = employees.get(i).getProjects().get(k).getNewDateFrom().getTime();
                long dateTo = employees.get(i).getProjects().get(k).getNewDateTo().getTime();

                for (int j = i + 1; j < employees.size(); j++) {
                    for (int l = 0; l < employees.get(j).getProjects().size(); l++) {
                        if (employees.get(i).getProjects().get(k).getProjectId() == employees.get(j).getProjects().get(l).getProjectId()) {
                            long dateFrom1 = employees.get(j).getProjects().get(l).getNewDateFrom().getTime();
                            long dateTo1 = employees.get(j).getProjects().get(l).getNewDateTo().getTime();

                            if (dateFrom1 > dateFrom && dateTo1 < dateTo) {
                                if (days < TimeUnit.MILLISECONDS.toDays(dateTo1 - dateFrom1) ) {
                                    days = TimeUnit.MILLISECONDS.toDays(dateTo1 - dateFrom1) ;

                                    this.idOfFirstEmp = i;
                                    this.idOfSecondEmp = j;
                                }
                            } else if (dateFrom1 > dateFrom && dateFrom1 < dateTo && dateTo1 > dateTo) {
                                if (days < TimeUnit.MILLISECONDS.toDays(dateTo - dateFrom1) ) {
                                    days = TimeUnit.MILLISECONDS.toDays(dateTo - dateFrom1) ;

                                    this.idOfFirstEmp = i;
                                    this.idOfSecondEmp = j;
                                }
                            } else if (dateFrom1 < dateFrom && dateTo1 < dateTo && dateTo1 > dateFrom) {
                                if (days < TimeUnit.MILLISECONDS.toDays(dateTo - dateFrom1) ) {
                                    days = TimeUnit.MILLISECONDS.toDays(dateTo - dateFrom1) ;

                                    this.idOfFirstEmp = i;
                                    this.idOfSecondEmp = j;
                                }
                            }
                        }
                    }
                }
            }
        }

        // find other common projects for the pair
        for(int i = 0; i < employees.get(idOfFirstEmp).getProjects().size(); i++){
            for(int j = 0; j < employees.get(idOfSecondEmp).getProjects().size(); j++){
                if(employees.get(idOfFirstEmp).getProjects().get(i).getProjectId()==employees.get(idOfSecondEmp).getProjects().get(j).getProjectId()){
                    if (employees.get(idOfSecondEmp).getProjects().get(j).getNewDateFrom().getTime() >= employees.get(idOfFirstEmp).getProjects().get(i).getNewDateFrom().getTime() && employees.get(idOfSecondEmp).getProjects().get(j).getNewDateTo().getTime() <=  employees.get(idOfFirstEmp).getProjects().get(i).getNewDateTo().getTime()) {
                        info.add(new ArrayList<>());
                        days = TimeUnit.MILLISECONDS.toDays(employees.get(idOfSecondEmp).getProjects().get(j).getNewDateTo().getTime() - employees.get(idOfSecondEmp).getProjects().get(j).getNewDateFrom().getTime()) ;
                        info.get(temp).add(0,employees.get(idOfFirstEmp).getEmpId());
                        info.get(temp).add(1,employees.get(idOfSecondEmp).getEmpId());
                        info.get(temp).add(2,employees.get(idOfSecondEmp).getProjects().get(j).getProjectId());
                        info.get(temp).add(3,(int)days);
                        temp++;
                    } else if (employees.get(idOfSecondEmp).getProjects().get(j).getNewDateFrom().getTime() >= employees.get(idOfFirstEmp).getProjects().get(i).getNewDateFrom().getTime() && employees.get(idOfSecondEmp).getProjects().get(j).getNewDateFrom().getTime() <= employees.get(idOfFirstEmp).getProjects().get(i).getNewDateTo().getTime() && employees.get(idOfSecondEmp).getProjects().get(j).getNewDateTo().getTime() >= employees.get(idOfFirstEmp).getProjects().get(i).getNewDateTo().getTime()) {
                        info.add(new ArrayList<>());
                        days = TimeUnit.MILLISECONDS.toDays(employees.get(idOfFirstEmp).getProjects().get(i).getNewDateTo().getTime() - employees.get(idOfSecondEmp).getProjects().get(j).getNewDateFrom().getTime()) ;
                        info.get(temp).add(0,employees.get(idOfFirstEmp).getEmpId());
                        info.get(temp).add(1,employees.get(idOfSecondEmp).getEmpId());
                        info.get(temp).add(2,employees.get(idOfSecondEmp).getProjects().get(j).getProjectId());
                        info.get(temp).add(3,(int)days);
                        temp++;
                    } else if (employees.get(idOfSecondEmp).getProjects().get(j).getNewDateFrom().getTime() <= employees.get(idOfFirstEmp).getProjects().get(i).getNewDateFrom().getTime() && employees.get(idOfSecondEmp).getProjects().get(j).getNewDateTo().getTime() <= employees.get(idOfFirstEmp).getProjects().get(i).getNewDateTo().getTime() && employees.get(idOfSecondEmp).getProjects().get(j).getNewDateTo().getTime() >= employees.get(idOfFirstEmp).getProjects().get(i).getNewDateFrom().getTime()) {
                        info.add(new ArrayList<>());
                        days = TimeUnit.MILLISECONDS.toDays(employees.get(idOfFirstEmp).getProjects().get(i).getNewDateTo().getTime() - employees.get(idOfSecondEmp).getProjects().get(j).getNewDateFrom().getTime()) ;
                        info.get(temp).add(0,employees.get(idOfFirstEmp).getEmpId());
                        info.get(temp).add(1,employees.get(idOfSecondEmp).getEmpId());
                        info.get(temp).add(2,employees.get(idOfSecondEmp).getProjects().get(j).getProjectId());
                        info.get(temp).add(3,(int)days);
                        temp++;
                    }
                }
            }
        }
        sortArray();

        return convertToObject();

    }

    // sort the output by days worked together in descending order
    private void sortArray(){
        ArrayList<Integer> temp;

        for(int i = 0; i < info.size()-1 ; i++ ){
            for( int j = 0; j < info.size()-i-1;j++){
                if(info.get(j).get(3) < info.get(j+1).get(3) ){
                    temp = info.get(j);
                    info.set(j,info.get(j+1));
                    info.set(j+1,temp);
                }
            }
        }
    }

    // convert arrayList<Integer> to object
    private Object[][] convertToObject(){
        Object[][] data = new Object[info.size()][4];

        for(int i = 0; i < info.size();i++){
            for(int k = 0; k < 4;k++){
                data[i][k] = info.get(i).get(k);
            }
        }
        return data;
    }
}

