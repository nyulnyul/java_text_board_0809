package com.sey.exam.board;

import java.util.Map;

public class Rq {
  private String url; // 접근제어자를 붙이는게 관례. 외부에서 접근 불가능.
  private String urlPath;
  private Map<String, String> params;

  // 인스턴스 변수 -> 여기에 다 붙임
  // 필드추가가능
  // 수정가능

  Rq(String url) {
    this.url = url;
    urlPath = Util.getUrlPathFromUrl(this.url);
    params = Util.getParamsFromUrl(this.url);
  }

  // 수정가능, if문 금지
  public Map<String, String> getParams() {
    return params;
  }

  // 수정가능, if문 금지
  public String getUrlPath() {
    return urlPath;
  }
}

