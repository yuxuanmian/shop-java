package com.zlt.shop.controller;

import com.zlt.shop.entity.Classify;
import com.zlt.shop.entity.Good;
import com.zlt.shop.entity.UserAcc;
import com.zlt.shop.service.IClassifyService;
import com.zlt.shop.service.IGoodService;
import com.zlt.shop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Starter {

    Scanner sc = new Scanner(System.in);

    @Autowired
    IUserService userService;

    @Autowired
    IClassifyService classifyService;

    @Autowired
    IGoodService goodService;

    public void init() {

        System.out.println("请选择要进行的操作");
        System.out.println("1.登录");
        System.out.println("2.注册");
        System.out.println("3.退出");
        int code = sc.nextInt();
        switch (code) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.exit(1);
        }

    }

    /**
     * 登录方法
     */
    private void login() {
        System.out.println("请输入用户名");
        String username = sc.next();
        System.out.println("请输入密码");
        String password = sc.next();
        //进行登录
        try {
            userService.login(new UserAcc()
                    .setUsername(username)
                    .setUserPassword(password));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            login();
        }
        System.out.println("登录成功");

        showClassify();

    }

    /**
     * 注册函数
     */
    private void register() {
        System.out.println("请输入用户名");
        String username = sc.next();
        System.out.println("请输入密码");
        String password = sc.next();

        try {
            userService.register(new UserAcc()
                    .setUsername(username)
                    .setUserPassword(password));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            register();
        }
        System.out.println("注册成功,请登录");
        login();
    }

    /**
     * 为用户展示所有的分类
     */
    private void showClassify() {
        List<Classify> all = classifyService.findAll();
        for (int i = 0; i < all.size(); i++) {
            System.out.println(i + "." + all.get(i).getName());
        }

        System.out.println("要修改嘛?0否1是");
        if (sc.nextInt() == 1) {
            modifyClassify(all);
        }


        System.out.println("请选择分类");

        int code = sc.nextInt();
        showAllGoods(all.get(code));


    }

    private void showAllGoods(Classify classify) {
        List<Good> all = goodService.findAll(classify);

        for (int i = 0; i < all.size(); i++) {
            Good good = all.get(i);
            System.out.println(i + "." + good.getName() + "\t" + good.getDescription());
        }

        System.out.println("要修改嘛?0否1是");
        if(sc.nextInt()==1){
            modifyGood(all);
        }
        System.out.println("请选择商品");

        System.out.println("购买成功!");
    }

    private void modifyGood(List<Good> list) {
        System.out.println("请输入要进行的操作");
        System.out.println("1.修改");
        System.out.println("2.增加");
        System.out.println("3.删除");
        int code = sc.nextInt();
        switch (code) {
            case 1:
                System.out.println("请选择要修改的对象");
                int next = sc.nextInt();
                System.out.println("请输入新的内容");
                goodService.modifyOne(list.get(next),
                        new Good(list.get(next).getId(),
                                sc.next(),
                                sc.next(),
                                list.get(next).getGoodClassid()));

                break;
            case 2:
                System.out.println("请输入新的内容");
                goodService.insertOne(new Good().setName(sc.next())
                        .setGoodClassid(list.get(0).getGoodClassid())
                        .setDescription(sc.next()));
            case 3:
                System.out.println("请输入要删除的内容");
                int next1 = sc.nextInt();
                goodService.deleteOne(list.get(next1));
                break;
        }
    }

    private void modifyClassify(List<Classify> list) {
        System.out.println("请输入要进行的操作");
        System.out.println("1.修改");
        System.out.println("2.增加");
        System.out.println("3.删除");
        int code = sc.nextInt();
        switch (code) {
            case 1:
                System.out.println("请选择要修改的对象");
                int next = sc.nextInt();
                System.out.println("请输入新的内容");
                classifyService.modifyOne(list.get(next),
                        new Classify().setName(sc.next()));
                break;
            case 2:
                System.out.println("请输入新的内容");
                classifyService.insertOne(new Classify().setName(sc.next()));
                break;
            case 3:
                System.out.println("请输入要删除的内容");
                int next1 = sc.nextInt();
                classifyService.deleteOne(list.get(next1));
        }
    }


}
