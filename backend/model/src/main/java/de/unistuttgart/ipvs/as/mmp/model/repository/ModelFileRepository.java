package de.unistuttgart.ipvs.as.mmp.model.repository;

import de.unistuttgart.ipvs.as.mmp.common.domain.ModelFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelFileRepository extends JpaRepository<ModelFile, Long> {

  @Query(
      "select modelFile from ModelFile modelFile join Model model on modelFile.model = model where model.project.id = ?1")
  List<ModelFile> findAllByProjectId(Long projectId);
}
