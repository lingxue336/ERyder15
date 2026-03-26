package org.example;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args){
        ERyder bike1 = new ERyder(12345,90,true,10.0);
        bike1.printBikeDetail();
        bike1.printRideDetail(15);

        ERyder bike2 = new ERyder(123456,20,true,30.0,"Lingxue","12345678");
        bike2.printBikeDetail();
        bike2.printRideDetail(100);

        String s1 = "I was very satisfied with the service.";
        String s2 = "The e-Bike is quite comfortable to ride.";
        String s3 = "The battery life of the e-Bike is impressive.";
        String s4 = "The customer support was helpful and responsive.";
        String s5 = "I would recommend this e-Bike to my friends and family.";

        FeedBack feedBack =new FeedBack("lin","@123456","xue");

        // 调用StringBuilder拼接方法
         feedBack.analyseFeedback(false,s1, s2, s3, s4, s5);
        // 转String后输出完整反馈

        System.out.println(feedBack);

        UserRegistration user = new UserRegistration();
        user.registration();
        System.out.println(user);

        AdminPanel adminPanel = new AdminPanel();
        adminPanel.userManagementOptions();

    }
}