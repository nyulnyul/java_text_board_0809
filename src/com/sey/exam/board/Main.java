package com.sey.exam.board;

import java.util.Scanner;


class Article{
  int id;
  String title;
  String body;

  void showingar(Article ar){
    System.out.println("제목은 " + ar.title+ "\n내용은" + ar.body + "\n번호는" + ar.id);
  }
}


public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int lastid = 0;

    System.out.println("==게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");
    while (true) {
      System.out.printf("명령)");
      String cmd = sc.nextLine();

      if (cmd.equals("see")) {
        System.out.println("==게시물 등록==");
        System.out.printf("제목 : \n");
        String title = sc.nextLine();


        System.out.printf("내용 : \n");
        String body = sc.nextLine();
        int id = lastid + 1;
        lastid++;
          System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
          Article ar1 = new Article();
          ar1.id = id;
          ar1.body = body;
          ar1.title = title;
          ar1.showingar(ar1);

        System.out.println("생성된 객체 : " +ar1);



      } else if (cmd.equals("exit")) {
        break;
      } else {
        System.out.printf("입력 받은 명령어 : %s\n", cmd);
      }
    }
    System.out.println("==프로그램 종료==");
    sc.close();


  }

}
