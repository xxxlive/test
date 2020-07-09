package checksystem;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Company
{
    private Map<Integer, Employee> employeeMap;
    private Map<Integer, DateInfo> runningDateInfoMap;
    private Map<Integer, DateInfo> endDateInfoMap;
    private static Company company = new Company();
    private final static String LINE = System.lineSeparator();
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private Company()
    {
        employeeMap = new TreeMap<>();
        runningDateInfoMap = new TreeMap<>();
        endDateInfoMap = new TreeMap<>();
    }

    public static Company getInstance()
    {
        return company;
    }

    public boolean addEmployee(Employee e)
    {
        if (findEmployee(e.getId()))
        {
            System.out.println("该id已经存在");
            return false;
        }
        else
        {
            showEmployee(e);
            employeeMap.put(e.getId(), e);
            return true;
        }
    }

    private boolean findEmployee(int id)
    {
        if (employeeMap.containsKey(id))
        {
            showEmployee(employeeMap.get(id));
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean deleteEmployee(int id)
    {
        if (findEmployee(id))
        {
            employeeMap.remove(id);
            return true;
        }
        else
        {
            System.out.println("该员工不存在");
            return false;
        }
    }

    private void showEmployee(Employee e)
    {
        System.out.println(e);
    }

    public void checkin(int id)
    {
        if (!findEmployee(id))
        {
            System.out.println("员工不存在");
            return;
        }
        if (runningDateInfoMap.containsKey(id))
        {
            System.out.println("今天已经打过卡了");
        }
        else if (endDateInfoMap.containsKey(id))
        {
            System.out.println("今天已经打过卡了（已下班）");
        }
        else
        {
            DateInfo dateInfo = new DateInfo(employeeMap.get(id));
            runningDateInfoMap.put(id, dateInfo);
            System.out.println("卡号："+id+"  打卡成功");
        }
    }

    public void checkout(int id)
    {

        if (!findEmployee(id))
        {
            System.out.println("员工不存在");
            return;
        }
        if (runningDateInfoMap.containsKey(id))
        {
            DateInfo dateInfo = runningDateInfoMap.get(id);
            dateInfo.setEndTime();

            long division;
            long hour;
            division= (dateInfo.getEndTime().getTime()-dateInfo.getStartTime().getTime());
            hour=division/(60*60*1000);
            if(hour<8)
            {
                System.out.println("别想提前下班！！");
            }
            else
            {
                endDateInfoMap.put(dateInfo.getEmployee().getId(), dateInfo);
                runningDateInfoMap.remove(id);
                System.out.println(
                        "卡号："
                                + id
                                + "成功签退");
            }

        }
        else if (endDateInfoMap.containsKey(id))
        {
            System.out.println(
                    "卡号："
                            + id
                            + "已经签退");
        }
        else
        {
            System.out.println(
                    "卡号："
                            + id
                            + "今天还没有签到，无法签退");
        }
    }

    public void showCheckInfo()
    {
        if (!runningDateInfoMap.isEmpty())
        {
            Set<Integer> set = runningDateInfoMap.keySet();
            Iterator<Integer> iter = set.iterator();
            System.out.println("上班的员工");
            while (iter.hasNext())
            {
                int n = iter.next();
                DateInfo dateInfo = runningDateInfoMap.get(n);
                System.out.println(dateInfo.getEmployee());
                System.out.println("上班时间：" + sdf.format(dateInfo.getStartTime()));
            }
        }
        if (!endDateInfoMap.isEmpty())
        {
            Set<Integer> set = endDateInfoMap.keySet();
            Iterator<Integer> iter = set.iterator();
            System.out.println("已经班的员工");
            while (iter.hasNext())
            {
                int n = iter.next();
                DateInfo dateInfo = endDateInfoMap.get(n);
                System.out.println(dateInfo.getEmployee());
                System.out.println("上班时间：" + sdf.format(dateInfo.getStartTime()));
                System.out.println("下班时间：" + sdf.format(dateInfo.getEndTime()));
                long division;
                long hour;
                long min;
                long second;
                division= (dateInfo.getEndTime().getTime()-dateInfo.getStartTime().getTime());
                hour=division/(60*60*1000);
                division=division%(60*60*1000);
                min=division/(60*1000);
                division=division%(60*1000);
                second=division/(1000);
                System.out.println("工作时间"+hour+"小时"+min+"分钟"+second+"秒");
            }
        }
    }
}
