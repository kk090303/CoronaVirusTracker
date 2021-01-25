package CoronaVirusTracker.CoronaVirusTracker.controller;

import CoronaVirusTracker.CoronaVirusTracker.domain.KoreaStats;
import CoronaVirusTracker.CoronaVirusTracker.domain.OverseasStats;
import CoronaVirusTracker.CoronaVirusTracker.service.CoronaVirusDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CoronaVirusDataController {
    private final CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/korea")
    public String Korea(Model model) throws IOException {
        List<KoreaStats> koreaStatsList = coronaVirusDataService.getKoreaCovidDatas();
        model.addAttribute("koreaStats", koreaStatsList);
        return "korea";
    }

    @GetMapping("/overseas")
    public String Overseas(Model model) throws IOException {
        List<OverseasStats> overseasStatsList = coronaVirusDataService.getOverseasCovidDatas();
        model.addAttribute("OverseasStats", overseasStatsList);
        return "overseas";
    }
}
