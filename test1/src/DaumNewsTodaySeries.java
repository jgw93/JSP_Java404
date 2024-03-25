
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DaumNewsTodaySeries {
    public static void main(String[] args) {
        System.out.println("다음 뉴스의 오늘의 연재 출력하기\n");
        // 문제 1) Jsoup흫 사용하여 다음 뉴스의 오늘의 연재 부분을 파싱하여 화면에 출력하는 프로그램을 작성하세요.
        // 출력 형태 = 기사 제목, 기사 링크 2가지 내용을 출력하세요.
        // 실행 순서
        // 1. url 설정
        // 2. Document 객체 설정
        // 3. Connection.Response 객체 생성 및 Jsoup.connect()로 지정한 url에 접속
        // 4. 받아온 데이터를 Document 객체로 전환
        // 5. 가져올 데이터가 있는 태그 중 가장 가까운 조상 태그 가져오기
        // 6. Select()를 사용하여 원하는 태그 가져오기
        // 7. 마지막에 선택한 태그레서 기사 제목 및 기사 링크 가져오기
        String url = "https://news.daum.net/?nil_profile=mini&nil_src=news";
        Document html = null;

        try {
            Connection.Response res = Jsoup.connect(url).method(Connection.Method.GET)
                    .execute();
            html = res.parse();
        } catch (IOException e) {
            System.out.println("데이터 파싱중 오류 발생");
            e.printStackTrace();
        }
        Element list_todayseries = html.select(".list_todayseries").first();
        Elements item_todayseries = list_todayseries.select(".item_todayseries");
        for (int i = 0; i < item_todayseries.size(); i++) {
            Element item = item_todayseries.get(i);
//            Element cont_thumb = item.select(".cont_thumb").first();
//            Element tit_g = cont_thumb.select(".tit_g").first();
//            Element link_txt = tit_g.select(".link_txt").first();
//            String title = link_txt.text();
//            String link = link_txt.attr("href");
//            System.out.println("기사 제목 : " + title);
//            System.out.println("기사 링크 : " + link);
            Element newsTag = item.select(".link_txt").first();
            String newsTitle = newsTag.text();
            String newsLink = newsTag.attr("href");

            System.out.println("기사 제목 : " + newsTitle);
            System.out.println("기사 링크 : " + newsLink);
            System.out.println("-----------------------------------------\n");

        }


    }
}
