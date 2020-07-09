package checksystem;

import javax.swing.*;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

/**
 * copyright wzh
 * email 20195379@stu.neu.edu.cn
 * 加入八小时不能签退功能
 * 更加合理的打卡提示包含不能重复打卡，重复签退，以及下班后再次打卡
 * 公司采用单例设计
 * 员工id去重（未展示）
 * 展示时会区分还在上班的及已经下班的
 * 防范任意非法输入
 */
public class Main
{
    Company company = Company.getInstance();
    Scanner xxx = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException
    {
        Main system = new Main();
        system.run();
    }

    private boolean initial(Company company)
    {
        try
        {
            company.addEmployee(new Employee("12345", "wzh"));
            company.addEmployee(new Employee("20195379", "jxc"));
            company.addEmployee(new Employee("1234", "cgl"));
            company.addEmployee(new Employee("12314", "xxx"));
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

    private int getChoice()
    {
        int res = 0;
        xxx = new Scanner(System.in);
        while (true)
        {
            System.out.println("-------员工打卡系统-------");
            System.out.println("输入0------退出");
            System.out.println("输入1------签到");
            System.out.println("输入2------签退");
            System.out.println("输入3------查看签到信息");
            System.out.print("请输入想执行的操作：");
            try
            {
                res = Integer.parseInt(xxx.nextLine());
            } catch (NumberFormatException e)
            {
                System.out.println("含有非法字符");
                continue;
            }
            if (res > 3 || res < 0)
            {
                System.out.println("超出范围");
            }
            else
            {
                break;
            }
        }
        return res;
    }

    private void in()
    {
        System.out.print("请输入签到员工ID：");
        String s;
        int id = 0;
        try
        {
            s = xxx.nextLine();
            id = Integer.parseInt(s);
            company.checkin(id);
        } catch (NumberFormatException e)
        {
            System.out.println("输入非法");
        }

    }

    private void out()
    {
        System.out.print("请输入签到员工ID：");
        String s;
        int id = 0;
        try
        {
            s = xxx.nextLine();
            id = Integer.parseInt(s);
            company.checkout(id);
        } catch (NumberFormatException e)
        {
            System.out.println("输入非法");
        }

    }

    public void run() throws InterruptedException
    {
        if (initial(company))
        {
            System.out.println("添加信息成功！");
        }
        else
        {
            System.out.println("添加信息失败！10秒后即将关闭程序");
            Thread.sleep(10000);
            Thread.sleep(10000);
            System.exit(-1);
        }
        boolean running = true;
        while (running)
        {
            int choice = getChoice();
            switch (choice)
            {
                case 0:
                    running = false;
                    break;
                case 1:
                    in();
                    break;
                case 2:
                    out();
                    break;
                case 3:
                    putCheckInfo();
                    break;
            }
        }
    }

    private void putCheckInfo()
    {
        company.showCheckInfo();
    }
}
