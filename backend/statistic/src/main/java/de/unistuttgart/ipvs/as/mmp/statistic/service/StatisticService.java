package de.unistuttgart.ipvs.as.mmp.statistic.service;

import java.util.List;

public interface StatisticService {

  List<String> getAllFileNames();

  List<String> getAllFileNamesByProjectId(Long projectId);
}
