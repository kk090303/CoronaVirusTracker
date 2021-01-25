package CoronaVirusTracker.CoronaVirusTracker.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class OverseasStats {

    private String country;

    private String total;

    private String dead;

}