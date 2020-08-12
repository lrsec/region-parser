package tech.linbox.region.parser.data.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@ToString
public class RegionPolygon {
  @Id private String code;

  private String polygon;
}
