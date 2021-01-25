package CoronaVirusTracker.CoronaVirusTracker.service;

import CoronaVirusTracker.CoronaVirusTracker.domain.KoreaStats;
import CoronaVirusTracker.CoronaVirusTracker.domain.OverseasStats;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {
    private static String KOREA_COVID_DATAS_URL
            = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=" +
            "1&brdGubun=13&ncvContSeq=&contSeq=&board_id=&gubun=";
    private static String OVERSEAS_COVID_DATAS_URL
            = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&" +
            "brdGubun=14&ncvContSeq=&contSeq=&board_id=&gubun=";


    @PostConstruct
    public List<KoreaStats> getKoreaCovidDatas() throws IOException {

        List<KoreaStats> koreaStatsList = new ArrayList<>();
        Document doc = Jsoup.connect(KOREA_COVID_DATAS_URL).get();
        Elements contents = doc.select("table tbody tr");


        for (Element content : contents) {
            Elements tdContents = content.select("td");

            KoreaStats koreaStats = KoreaStats.builder()
                    .city(content.select("th").text())
                    .totalComparePrevDay(Integer.parseInt(tdContents.get(0).text()))
                    .domesticOccurPrevDay(Integer.parseInt(tdContents.get(1).text()))
                    .overseasOccurPrevDay(Integer.parseInt(tdContents.get(2).text()))
                    .totalConfirmed(tdContents.get(3).text())
                    .inQuarantine(tdContents.get(4).text())
                    .outQuarantine(tdContents.get(5).text())
                    .deathTotal(tdContents.get(6).text())
                    .occurRate(tdContents.get(7).text())
                    .build();

            koreaStatsList.add(koreaStats);
        }

        return koreaStatsList;
    }


    public List<OverseasStats> getOverseasCovidDatas() throws IOException {

        List<OverseasStats> OverseasStatsList = new ArrayList<>();
        Document doc = Jsoup.connect(OVERSEAS_COVID_DATAS_URL).get();
        Elements base = doc.select("div.data_table.mgt8");
        Elements contents=base.select("tbody tr");



        for (Element content : contents) {
            Elements tdContents = content.select("td");

            OverseasStats overseasStats = OverseasStats.builder()
                    .country(content.select("th").text())
                    .total(tdContents.get(0).text())
                    .dead(tdContents.get(2).text())
                    .build();

            OverseasStatsList.add(overseasStats);
        }

        return OverseasStatsList;
    }

}
