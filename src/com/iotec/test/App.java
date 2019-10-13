package com.iotec.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
/**
 * 编写一个猜拳游戏，进入新游戏需要输入用户的昵称，选择对手（对手随机出拳），然后进行游戏，游戏可以多局的形式，
 * 游戏中胜利分数加1，平局和输了不加分不减分，游戏结束告知游戏中的成绩返回游戏主界面，用户可以通过排行榜查看前10的分数（需要排序），
 * 通过退出可以结束游戏。游戏中的数据不需要永久保存，只有在本次运行中作短期的保存即可，也可以自主的写入一些伪数据。
 * 该游戏要注意界面的友好，代码的合理，必须符合代码的规范和代码的优化，尽自己所学避免程序发生异常现象。
 * 游戏规则：石头剪刀布，石头赢剪刀，剪刀赢布，布赢石头
 */
public class App {
	//用于保存已有的用户名和密码
	private static Map<String,String>vip = new HashMap<>();
	//用于保存用户对应的得分情况
	private static Map<String,Integer>score = new HashMap<>();
	//保留当前登录的用户名
	private static String logName="";
	//出手，1代表石头，2代表剪刀，3代表布
	private static String[] tick =new String[] {"石头","剪刀","布"};
	private static String[] opponent = new String[] {"孙权","曹操","刘备"};
	static {
		//模拟已经注册过的玩家以及他们的得分情况
		vip.put("Tom","123");
		score.put("Tom",3);
		vip.put("Bob","456");
		score.put("Bob",4);
		vip.put("XiaoMing","bcd");
		score.put("XiaoMing",5);
		vip.put("Zhangsan","zsnihao");
		score.put("Zhangsan",2);
		vip.put("LiSi","nisinihao");
		score.put("LiSi",6);
		vip.put("Wangwu","wangwunihao");
		score.put("Wangwu",8);
		vip.put("zhf","zhfnihao");
		score.put("zhf",5);
		vip.put("Alice","Alicenihao");
		score.put("Alice",9);
		vip.put("Jack","Jacknihao");
		score.put("Jack",11);
		vip.put("fanfan","fanfannihao");
		score.put("fanfan",4);
		vip.put("Rose","Rosenihao");
		score.put("Rose",9);
		vip.put("Mary","Marynihao");
		score.put("Mary",6);
		
	}
	/**
	 * 游戏欢迎界面
	 */
	public static void welcome() {
		System.out.println("**************欢迎使用**************");
		System.out.println("1.注册");
		System.out.println("2.登陆");
		System.out.println("0.退出");
		System.out.println("**********************************");
		System.out.println("====>请输入选项（0~2）:");
	}
	/**
	 * 游戏欢迎界面提供进行登录、注册或退出等选择
	 */
	public static void option() {
		welcome();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		//提供用户进行输入
		int opt = 0;
		opt = input.nextInt();
		while(opt<0||opt>2) {
			System.out.println("没有指定的选项,请重新输入:");
			opt = input.nextInt();
		}
		switch(opt) {
			//选择0退出游戏，程序结束
			case 0:
				System.out.println("游戏结束!");
				System.exit(0);
			//选择1进行注册用户,将新增的用户用map暂存起来
			case 1:
				System.out.println("请输入要注册的用户名:");
				String userName = input.next();
				System.out.println("请输入密码:");
				String pwd = input.next();
				System.out.println("请再次输入密码:");
				String rePwd = input.next();
				while(vip.containsKey(userName)) {
					System.out.println("该用户已经存在,请注册其他用户名!");
					System.out.println("请输入要注册的用户名:");
					userName = input.next();
					System.out.println("请输入密码:");
					pwd = input.next();
					System.out.println("请再次输入密码:");
					rePwd = input.next();
				}
				while(!pwd.equals(rePwd)) {
					System.out.println("两次输入的密码不统一,请重新输入!");
					System.out.println("请输入密码:");
					pwd = input.next();
					System.out.println("请再次输入密码:");
					rePwd = input.next();
				}
				System.out.println("注册成功,请登陆!");
				vip.put(userName,pwd);
				score.put(userName,0);
				option();
				break;
			//选择2进行登陆
			case 2:
				System.out.println("请输入用户名:");
				logName = input.next();
				System.out.println("请输入密码:");
				String logPwd = input.next();
				while(!(vip.containsKey(logName)&&vip.get(logName).equals(logPwd))){
					System.out.println("输入的用户名或密码错误，请重新输入!");
					System.out.println("请输入用户名:");
					logName = input.next();
					System.out.println("请输入密码:");
					logPwd = input.next();
				}
				System.out.println("欢迎"+logName+"加入游戏!");
		}
		
	}
	/**
	 * 进入游戏的指引界面
	 */
	public static void game_Guide() {
		System.out.println("**************猜拳游戏**************");
		System.out.println("1.新游戏");
		System.out.println("2.英雄排行榜");
		System.out.println("0.返回");
		System.out.println("**********************************");
		System.out.println("====>请输入选项（0~2）:");
	}
	public static void startGame() {
		game_Guide();
		Scanner input = new Scanner(System.in);
		//提供用户进行输入
		int opt = input.nextInt();
		//没有指定选项，要求重新输入
		while(opt<0||opt>2) {
			System.out.println("没有指定的选项,请重新输入:");
			opt = input.nextInt();
		}
		switch(opt) {
			//选择0返回上一级登录注册页面
			case 0: 
				option();
				startGame();
				break;
			//选择1进入新的游戏局
			case 1:
				System.out.println("请输入对局数目:");
				int gameNum = input.nextInt();
				System.out.println("请选择对手:1.孙权\t2.曹操\t3.刘备");
				int opponent = input.nextInt();
				Random random = new Random();
				int count = 0;
				while(count<gameNum) {
					//1		代表石头
					//2		代表剪刀
					//3		代表布
					/*
					 * 根据游戏规则可知，
					 * 1和2出现时，1大
					 * 2和3出现时，2大
					 * 3和1出现时，3大
					 * 相同出现时，平分
					 */
					//对手随机出的招数
					int optTrick = 1+random.nextInt(3);
					System.out.println("请出拳:(1代表石头,2代表剪刀,3代表布)");
					int myTrick = input.nextInt();
					while(myTrick<=0||myTrick>3) {
						System.out.println("输入的数字不在范围之内(1-3)，请重新输入!");
						System.out.println("请出拳:(1代表石头,2代表剪刀,3代表布)");
						myTrick = input.nextInt();
					}
					System.out.println("你出了:"+tick[myTrick-1]);
					System.out.println(App.opponent[opponent-1]+"出了:"+tick[optTrick-1]);
					if(optTrick == myTrick) {
						System.out.println("平局");
					}else if((myTrick==1&&optTrick==2)||
							(myTrick==2&&optTrick==3)|| 
							(myTrick==3&&optTrick==1)){
						System.out.println("你赢了");
						score.put(logName,score.get(logName)+1);
					}else if((myTrick==2&&optTrick==1)||
							(myTrick==3&&optTrick==2)||
							(myTrick==1&&optTrick==3)){
						System.out.println("你输了");
					}
					count++;
				}
				startGame();
				break;
				
			case 2:
				//打印排行榜
				String[][] scoreList = sort();
				if(scoreList.length<10) {
					for(int i =0;i<scoreList.length;i++) {
						System.out.print("英雄名:"+scoreList[i][0]+"\t\t\t");
						System.out.println("得分:"+scoreList[i][1]);
					}
				}else {
					for(int i = 0;i<10;i++) {
						System.out.print("英雄名:"+scoreList[i][0]+"\t\t\t");
						System.out.println("得分:"+scoreList[i][1]);
					}
				}
				startGame();
		}
		input.close();
		
	}
	public static String[][] sort() {
		//获取玩家个数
		int len = score.size();
		//产生一个二维数组，用于存储排序后的内容。
		String[][]scoreList = new String[len][2];
		int i = 0;
		int j = 0;
		//将数据存储至二维数组中，方便进行排序
		for(Entry<String,Integer>ent:score.entrySet()) {
			scoreList[i][j]=ent.getKey();
			scoreList[i][j+1]=String.valueOf(ent.getValue());
			i++;
			j=0;
		}
		//用选择排序实现分数排序
		for(int m=0;m<scoreList.length-1;m++) {
			int max = Integer.parseInt(scoreList[m][1]);
			int flag = m;
			for(int n=m+1;n<scoreList.length;n++) {
				int temp = Integer.parseInt(scoreList[n][1]);
				if(temp>max) {
					max = temp;
					flag = n;
				}
			}
			//如果最大值不是当前第一个元素，则进行交换
			if(flag!=m) {
				String userTemp = scoreList[m][0];
				String userScore = scoreList[m][1];
				scoreList[m][0] = scoreList[flag][0];
				scoreList[m][1] = scoreList[flag][1];
				scoreList[flag][0] = userTemp;
				scoreList[flag][1] = userScore;
			}
		}
		return scoreList;
	}
	public static void main(String[] args) {
		option();
		startGame();
	}
}
