package com.atguigu.gmall.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GmallSearchWebApplication {

	public static void main(String[] args) {

		SpringApplication.run(GmallSearchWebApplication.class, args);
		System.out.println("架构师加钱,不加钱就删库跑路");
		System.out.println("架构师已经跑路了,我也准备跑路了");
		System.out.println("我收到了，我也准备跑路了");
	}
		public String Branch(){
				return "null";
		}
}
