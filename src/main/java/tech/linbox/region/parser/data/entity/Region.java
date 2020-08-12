package tech.linbox.region.parser.data.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@ToString
public class Region {
  public static final int DEEP_PROVINCE = 0;
  public static final int DEEP_CITY = 1;
  public static final int DEEP_DISTRICT = 2;

  @Id private String code;

  private String name;
  private String pinyin;
  private String pinyinPrefix;

  private int deep; // 0, 1 ,2 ,3
  private String pcode; // 父区域 code
  private Double lat; // 纬度
  private Double lon; // 经度
}
