package CoronaVirusTracker.CoronaVirusTracker.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class KoreaStats {

    private String city; //시도명

    private int totalComparePrevDay; //전일대비 합계

    private int domesticOccurPrevDay; //전일대비 국내발생

    private int overseasOccurPrevDay; //전일대비 해외유입

    private String totalConfirmed; //확진환자

    private String inQuarantine; //격리중

    private String outQuarantine; //격리해제

    private String deathTotal; //사망자

    private String occurRate; //발생률률
}