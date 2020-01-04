package de.unistuttgart.ipvs.as.mmp.statistic.v1.controller;

import de.unistuttgart.ipvs.as.mmp.common.exception.MMPExceptionHandler;
import de.unistuttgart.ipvs.as.mmp.statistic.service.StatisticService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = {StatisticController.class})
public class StatisticControllerTest {

  public static final String FILE_NAME = "iristree_example.xml";
  private static final Long PROJECT_ID = 1337L;

  private MockMvc rest;
  @MockBean private StatisticService statisticService;

  @Autowired private StatisticController statisticController;

  @BeforeEach
  public void setUp(RestDocumentationContextProvider restDocumentation) {
    this.rest =
        MockMvcBuilders.standaloneSetup(this.statisticController)
            .setControllerAdvice(new MMPExceptionHandler(), this.statisticController)
            .apply(
                documentationConfiguration(restDocumentation)
                    .operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint()))
            .build();
  }

  @Test
  public void shouldGetAllFileNames() throws Exception {
    given(statisticService.getAllFileNames()).willReturn(Collections.singletonList(FILE_NAME));
    rest.perform(get(StatisticController.PATH))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(FILE_NAME));
  }

  @Test
  public void shouldGetAllFileNamesByProjectId() throws Exception {
    given(statisticService.getAllFileNamesByProjectId(PROJECT_ID))
        .willReturn(Collections.singletonList(FILE_NAME));
    rest.perform(get(StatisticController.PATH + "/" + PROJECT_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(FILE_NAME));
  }
}
