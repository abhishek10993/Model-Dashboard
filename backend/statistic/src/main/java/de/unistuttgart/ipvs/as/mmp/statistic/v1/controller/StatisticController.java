package de.unistuttgart.ipvs.as.mmp.statistic.v1.controller;

import de.unistuttgart.ipvs.as.mmp.statistic.service.StatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.unistuttgart.ipvs.as.mmp.statistic.v1.controller.StatisticController.PATH;

@Controller
@CrossOrigin
@RequestMapping(value = PATH)
public class StatisticController {

  public static final String PATH = "/v1/statistic";
  private static final String PROJECT_ID_PATTERN = "/{projectId}";

  private final StatisticService statisticService;

  public StatisticController(StatisticService statisticService) {
    this.statisticService = statisticService;
  }

  @GetMapping(value = PROJECT_ID_PATTERN)
  public ResponseEntity<List<String>> getAllFileNamesByProjectId(@PathVariable Long projectId) {
    List<String> outputs = statisticService.getAllFileNamesByProjectId(projectId);
    return ResponseEntity.ok(outputs);
  }

  @GetMapping
  public ResponseEntity<List<String>> getAllFileNames() {
    List<String> outputs = statisticService.getAllFileNames();
    return ResponseEntity.ok(outputs);
  }
}
