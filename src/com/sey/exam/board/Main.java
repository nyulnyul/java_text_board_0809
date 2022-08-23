package com.sey.exam.board;

import java.util.*;

public class Main {
 static int articleLastId = 0;
  static List<Article> articles = new ArrayList<>();
  static void makeTestData() {
    for (int i = 0; i < 100; i++) {
      int id = i + 1;
      articles.add(new Article(id, "제목" + id, "내용" + id));
    }

  }

  public static void main(String[] args) {
    Scanner sc = Conatainer.sc;

    makeTestData();
    if (articles.size() > 0) {
      articleLastId = articles.get(articles.size() - 1).id;
    }
    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");
    while (true) {
      System.out.printf("명령) ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);
      Map<String, String> params = rq.getParams();

      if (rq.getUrlPath().equals("exit")) {
        break;
      } else if (rq.getUrlPath().equals("/usr/article/list")) {
        actionlist(rq);
      } else if (rq.getUrlPath().equals("/usr/article/write")) {
        actionwrite();

      } else if (rq.getUrlPath().equals("/usr/article/detail")) {
        actiondetail(rq);
      } else if (rq.getUrlPath().equals("/usr/article/modify")) {
        actionmodify(rq);
      }else if (rq.getUrlPath().equals("/usr/article/delete")) {
        actiondelete(rq);
      }
      else {
        System.out.printf("입력 된 명령어 : %s\n", cmd);
      }
    }
    System.out.println("== 프로그램 종료 ==");
    sc.close();
  }
/////////////////////////////////////////////////////////////////////////
  private static void actiondelete(Rq rq) {
    Map<String, String>params = rq.getParams();
    if (params.containsKey("id") == false){
      System.out.printf("id를 입력해주세요.");
      return;
    }

    int id = 0;
    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    Article foundarticle = null;
    for (Article article: articles) {
      if(article.id == id){
        foundarticle =article;
        break;
      }

    }

    if (foundarticle==null) {
      System.out.printf("%d번 게시물이 존재하지 않습니다.\n",id);
      return;
    }
    articles.remove(foundarticle);
    System.out.printf("%d번 게시물을 삭제하였습니다. \n",foundarticle.id);


  }
////////////////////////////////////////////////////////////////////////////////////
  private static void actionmodify( Rq rq) {
    Map<String, String> params = rq.getParams();

    if (params.containsKey("id") == false){
      System.out.printf("id를 입력해주세요.");
      return;
    }

    int id = 0;
    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    Article article = articles.get(id - 1);

    if (id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    System.out.println("수정할 내용을 입력해주세요");
    System.out.printf("제목 : ");
    article.title = Conatainer.sc.nextLine();
    System.out.printf("내용 : ");
    article.body = Conatainer.sc.nextLine();

    System.out.printf("%d번 게시물을 수정하였습니다. \n",article.id);
  }



  ////////////////////////////////////////////////디테일///////////////////////////////////////
  public static void actiondetail(Rq rq) {
    Map<String, String> params = rq.getParams();
      if (params.containsKey("id") == false){
        System.out.printf("id를 입력해주세요.");
        return;
      }


      int id =0;
      try {
        id = Integer.parseInt(params.get("id"));
      }
      catch (NumberFormatException e){
        System.out.println("id를 정수 형태로 입력해주세요.");
        return;
      }

      Article article = articles.get(id - 1);

      if (id > articles.size()) {
        System.out.println("게시물이 존재하지 않습니다.");
        return;
      }


      System.out.println("== 게시물 상세내용 ==");
      System.out.printf("번호 : %d\n", article.id);
      System.out.printf("제목 : %s\n", article.title);


      System.out.printf("내용 : %s\n", article.body);
    }





  //////////////////////////////////////쓰기/////////////////////////////////////////////////
  public static void actionwrite() {
    System.out.println("== 게시물 등록 ==");
    System.out.printf("제목 : ");
    String title = Conatainer.sc.nextLine();
    System.out.printf("내용 : ");
    String body = Conatainer.sc.nextLine();
    int id = ++articleLastId;
    articleLastId++;
    Article article = new Article(id, title, body);
    articles.add(article);
    System.out.println("생성 된 게시물 객체 : " + article);
    System.out.printf("%d번 게시물이 입력 되었습니다.\n", article.id);
  }

/////////////////////////////////////////////////////////////////리스트////////////////
public static void actionlist(Rq rq) {
    System.out.println("== 게시물 리스트 ==");
    System.out.println("-------------------");
    System.out.println("번호 / 제목 / 내용");
    System.out.println("-------------------");

    Map<String, String> params = rq.getParams();
    //검색시작
    List<Article> fileredArticles = articles;

    if(params.containsKey("searchKeyword")) {
      String searchKeyword = params.get("searchKeyword");

      fileredArticles = new ArrayList<>();
      for (Article article: articles) {
        boolean matched = article.title.contains(searchKeyword) || article.body.contains(searchKeyword);

        if (matched){
          fileredArticles.add(article);
        }

      }

    }
    //검색끝

    List<Article> sortedArticles =fileredArticles;

    boolean orderByIdDesc = true;

    if(params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")){
      orderByIdDesc =false;

    }
    if(orderByIdDesc){
      sortedArticles = Util.reverseList(sortedArticles);
    }
    for (Article article: sortedArticles) {
      System.out.printf("%d/ %s/ %s\n",article.id , article.title , article.body);

    }
///////////////////////////////////////////////////////////////////////////////////////
  }
}



























// 수정불가능
