package de.unistuttgart.ipvs.as.mmp.statistic.service.impl;

import de.unistuttgart.ipvs.as.mmp.model.repository.ModelFileRepository;
import de.unistuttgart.ipvs.as.mmp.statistic.service.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl implements StatisticService {

  private final ModelFileRepository modelFileRepository;
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  public StatisticServiceImpl(ModelFileRepository modelFileRepository) {
    this.modelFileRepository = modelFileRepository;
  }

  @Override
  public List<String> getAllFileNames() {
    return modelFileRepository.findAll().stream()
        .map(file -> file.getDbFile().getFileName())
        .collect(Collectors.toList());
  }

  @Override
  public List<String> getAllFileNamesByProjectId(Long projectId) {
    return modelFileRepository.findAllByProjectId(projectId).stream()
        .map(file -> file.getDbFile().getFileName())
        .collect(Collectors.toList());
  }
}
