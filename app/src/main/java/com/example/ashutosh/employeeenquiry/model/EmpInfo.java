package com.example.ashutosh.employeeenquiry.model;

/**
 * Created by Ashutosh on 04-09-2016.
 */
public class EmpInfo{

        private String id ,emploreeName,employeeSalary,employeePost;
    private boolean isSelected;

    public boolean isSelected()
    {
        return isSelected;
    }
    public void setSelected(boolean selected)
    {
         isSelected=selected;
    }

        public EmpInfo(String id,String employeeName, String employeeSalary, String employeePost) {
            this.id=id;
            this.emploreeName = employeeName;
            this.employeeSalary = employeeSalary;
            this.employeePost = employeePost;
        }
    public EmpInfo() {

    }

   public String getId()
        {
         return id;
        }

    public void  setId(String id) {
        this.id=id;
    }
    public  String getEmployeeName()
    {
        return emploreeName;
    }
    public void  setEmployeeName(String employeeName)
    {
         this.emploreeName=emploreeName;
    }
    public String getEmployeeSalary()
    {
        return employeeSalary;
    }
    public void setEmployeeSalary(String employeeSalary)
    {
        this.employeeSalary=employeeSalary;
    }
    public String getEmployeePost()
    {
        return employeePost;
    }
    public void setEmployeePost(String employeePost)
    {
        this.employeePost=employeePost;
    }
}


