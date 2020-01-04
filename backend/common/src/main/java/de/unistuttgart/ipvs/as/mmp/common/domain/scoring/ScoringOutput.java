package de.unistuttgart.ipvs.as.mmp.common.domain.scoring;

import de.unistuttgart.ipvs.as.mmp.common.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "scoring_output")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoringOutput extends BaseEntity {
    private String name;
    private String value;
}
