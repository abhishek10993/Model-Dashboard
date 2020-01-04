package de.unistuttgart.ipvs.as.mmp.statistic.service;

import de.unistuttgart.ipvs.as.mmp.common.domain.*;
import de.unistuttgart.ipvs.as.mmp.common.domain.scoring.ScoringInput;
import de.unistuttgart.ipvs.as.mmp.common.pmml.PMMLMetadataParser;
import de.unistuttgart.ipvs.as.mmp.model.repository.ModelFileRepository;
import de.unistuttgart.ipvs.as.mmp.statistic.service.impl.StatisticServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class StatisticServiceTest {
  public static final String FILE_NAME = "iristree_example.xml";
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private static final Long PRPJECT_ID = 123L;
  private static final Long MODEL_ID = 123L;

  @Autowired private ModelFileRepository modelFileRepository;

  @Autowired private StatisticService statisticService;

  @TestConfiguration
  static class ScoringServiceTestConfiguration {

    @MockBean public ModelFileRepository modelFileRepository;

    @Bean
    public StatisticService statisticService() {
      return new StatisticServiceImpl(this.modelFileRepository);
    }
  }

  private Project testProject = Project.builder().name("TestProject").build();
  private Model testModel =
      Model.builder().project(testProject).modelMetadata(new ModelMetadata()).build();
  private ModelFile modelFile = new ModelFile();
  private DBFile dbFile = new DBFile();
  private List<ScoringInput> inputs;
  private PMMLMetadataParser pmmlMetadataParser = new PMMLMetadataParser();

  @BeforeEach
  public void setUp() {
    inputs = new ArrayList<>();
    inputs.add(new ScoringInput("petal_length", "3"));
    inputs.add(new ScoringInput("petal_width", "3"));
    inputs.add(new ScoringInput("sepal_length", "3"));
    inputs.add(new ScoringInput("sepal_width", "3"));

    dbFile.setFileName(FILE_NAME);
    dbFile.setFileType("xml");
    InputStream fs = null;
    try {
      File file = ResourceUtils.getFile("classpath:example/iristree_example.xml");
      dbFile.setFileName(file.getName());
      dbFile.setFileType("text/xml");
      dbFile.setData(Files.readAllBytes(file.toPath()));
      fs = new FileInputStream(file);
      List<Model> modelList = pmmlMetadataParser.parsePMMLFile(fs, testModel.getModelMetadata());
      testModel.setModelMetadata(modelList.get(0).getModelMetadata());
    } catch (Exception e) {
      log.info("Failed to load example pmml file, {}", e);
    } finally {
      try {
        fs.close();
      } catch (Exception e) {
        log.info("Failed to close input stream", e);
      }
    }
    modelFile.setDbFile(dbFile);
    modelFile.setModel(testModel);
    testModel.setModelFile(modelFile);

    given(modelFileRepository.findAll()).willReturn(Collections.singletonList(modelFile));
    given(modelFileRepository.findAllByProjectId(PRPJECT_ID))
        .willReturn(Collections.singletonList(modelFile));
  }

  @Test
  public void shouldGetAllFileNames() {
    assertEquals(Collections.singletonList(FILE_NAME), statisticService.getAllFileNames());
  }

  @Test
  public void shouldGetAllFileNamesByProjectId() {
    assertEquals(
        Collections.singletonList(FILE_NAME),
        statisticService.getAllFileNamesByProjectId(PRPJECT_ID));
  }
}
