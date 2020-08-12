package tech.linbox.region.parser;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegionData {
  private String id;
  private String pid;
  private int deep;
  private String name;
  private String pinyin_prefix;
  private String pinyin;
  private String ext_id;
  private String ext_name;
}
