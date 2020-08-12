package tech.linbox.region.parser;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.base.Strings;
import tech.linbox.region.parser.data.dao.RegionRepository;
import tech.linbox.region.parser.data.entity.Region;

import java.util.LinkedList;
import java.util.List;

public class RegionDataListener extends AnalysisEventListener<RegionData> {
  private static final int FILTER_SIZE = 10;

  private org.slf4j.Logger log;
  private RegionRepository regionRepository;
  private List<Region> regions = new LinkedList<>();

  public RegionDataListener(RegionRepository regionRepository, org.slf4j.Logger log) {
    this.regionRepository = regionRepository;
    this.log = log;
  }

  @Override
  public void invoke(RegionData regionData, AnalysisContext context) {
    log.info("RegionData {}", regionData);
    if (Strings.isNullOrEmpty(regionData.getExt_name())) {
      return;
    }

    Region region = new Region();
    region.setCode(regionData.getId());
    region.setName(regionData.getExt_name());
    region.setPinyin(regionData.getPinyin());
    region.setPinyinPrefix(regionData.getPinyin_prefix());
    region.setDeep(regionData.getDeep());
    region.setPcode(regionData.getPid());
    region.setLat(null);
    region.setLon(null);

    regions.add(region);

    if (regions.size() >= FILTER_SIZE) {
      saveData();
    }

//    regionRepository.save(region);
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext context) {
    saveData();
  }

  private void saveData() {
    if (regions != null && regions.size() > 0) {
      regionRepository.saveAll(regions);
      regions = new LinkedList<>();
    }
  }
}
