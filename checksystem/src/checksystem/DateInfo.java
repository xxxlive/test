package checksystem;

import java.util.Date;

public class DateInfo
{
    private final Employee employee;
    private final Date startTime;
    private Date endTime;

    public DateInfo(Employee employee)
    {
        this.employee = employee;
        startTime = new Date();
        System.out.print("xsx");
    }

    public void setEndTime()
    {
        this.endTime = new Date();
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
}
