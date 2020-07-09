package checksystem;

public class Employee
{
    private int id;
    private String name;

    public Employee(String id, String name)
    {
        this.setId(id);
        this.setName(name);
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setId(String id)
    {
        try
        {
            this.id = Integer.parseInt(id);
        } catch (NumberFormatException e)
        {
            System.out.println("id包含非法内容");
            throw e;
        }

    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Employee{" +
                "员工id='" + id + '\'' +
                ", 员工姓名='" + name + '\'' +
                '}';
    }

}
