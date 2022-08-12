package com.sey.exam.board;

import java.util.ArrayList;
import java.util.Scanner;


class Article {
  int id;
  String title;
  String body;

  Article(int id, String title, String body) {
    this.id = id;
    this.title = title;
    this.body = body;
  }

  @Override
  public String toString() {
    return String.format("{id : %d, title : \"%s\", body : \"%s\"}", id, title, body);
  }
}

public class Main {
  static void TestDatelist(ArrayList<Article> articles){
    articles.add(new Article(1, "wae", "asd"));
    articles.add(new Article(2, "wae2", "asd"));
    articles.add(new Article(3, "wae3", "asd"));

  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int articleLastId = 0;
    Article lastarticle = null;



    ArrayList<Article> articles = new ArrayList<>();
    TestDatelist(articles);


    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");
    while (true) {
      System.out.printf("명령) ");
      String cmd = sc.nextLine();
      if (cmd.equals("exit")) {
        break;
      } else if (cmd.equals("wri")) {
        System.out.println("== 게시물 등록 ==");
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();
        int id = articleLastId + 1;
        articleLastId = id;

        Article article = new Article(id, title, body);
        lastarticle = article;


        System.out.println("생성 된 게시물 객체 : " + article);
        System.out.printf("%d번 게시물이 입력 되었습니다.\n", article.id);
      } else if (cmd.equals("det")) {
        if (lastarticle == null) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }
        Article article = lastarticle;


        System.out.println("- 게시물 상세보기 -");
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.title);
        System.out.printf("내용 : %s\n", article.body);


      } else if (cmd.equals("lis")) {
        System.out.println("-게시물 리스트-");
        System.out.println("---------------");
        System.out.println("번호 / 제목 / 내용");
        System.out.println("---------------");
        for (int i = articles.size()-1; i >= 0; i-- ) {
          Article article = articles.get(i);
          System.out.printf("%d/제목 : %s/ 내용 : %s\n", article.id, article.title , article.body);
          continue;

        }

      } else {
        System.out.printf("입력 된 명령어 : %s\n", cmd);
      }
    }
    System.out.println("== 프로그램 종료 ==");
    sc.close();
  }
}
