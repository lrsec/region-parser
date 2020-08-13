package tech.linbox.region.parser;

import com.alibaba.excel.EasyExcel;
import com.csvreader.CsvReader;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.linbox.region.parser.data.dao.RegionPolygonRepository;
import tech.linbox.region.parser.data.dao.RegionRepository;
import tech.linbox.region.parser.data.entity.Region;
import tech.linbox.region.parser.data.entity.RegionPolygon;

import java.io.InputStream;
import java.nio.charset.Charset;

@Slf4j
@RestController
public class RegionParserController {
  @Autowired RegionRepository regionRepository;
  @Autowired RegionPolygonRepository regionPolygonRepository;

  @PostMapping("/parse/region")
  public String parseRegion() {
    try {
      ClassPathResource resource = new ClassPathResource("ok_data_level3.xlsx");
      // 获取文件流
      InputStream inputStream = resource.getInputStream();

      EasyExcel.read(inputStream, RegionData.class, new RegionDataListener(regionRepository, log))
          .sheet()
          .doRead();

      return "success";
    } catch (Exception e) {
      log.error("parse region get exception ", e);
      return "exception";
    }
  }

  @PostMapping("/parse/region/polygon")
  public String parseRegionPolygon() {
    try {
      ClassPathResource resource = new ClassPathResource("ok_geo.csv");
      // 获取文件流
      InputStream inputStream = resource.getInputStream();

      CsvReader csvReader = new CsvReader(inputStream, ',', Charset.forName("UTF-8"));
      csvReader.setSafetySwitch(false);
      csvReader.readHeaders();

      // 读取每行的内容
      while (csvReader.readRecord()) {

        String id = csvReader.get(0);
        String polygon = csvReader.get("polygon");

        RegionPolygon rp = new RegionPolygon();
        rp.setCode(id);
        rp.setPolygon(polygon);

        log.info("parse region polygon {}", id);

        regionPolygonRepository.save(rp);
      }

      return "success";
    } catch (Exception e) {
      log.error("parse region polygon get exception ", e);
      return "exception";
    }
  }

  @PostMapping("/parse/region/loc")
  public String parseRegionLoc() {
    try {
      ClassPathResource resource = new ClassPathResource("ok_geo.csv");
      // 获取文件流
      InputStream inputStream = resource.getInputStream();

      CsvReader csvReader = new CsvReader(inputStream, ',', Charset.forName("UTF-8"));
      csvReader.setSafetySwitch(false);
      csvReader.readHeaders();

      // 读取每行的内容
      while (csvReader.readRecord()) {

        String id = csvReader.get(0);
        String geo = csvReader.get("geo");
        if ( Strings.isNullOrEmpty(geo)) {
          log.error("get empty geo for id {}", id);
          continue;
        }

        String[] locs = geo.split(" ");
        if (locs.length != 2) {
          log.error("loc size not correct. id {}, geo {}, size {}", id, geo, locs.length);
          continue;
        }

        Region region = regionRepository.findByCode(id);
        if (region == null) {
          log.error("can not find region for code {}", id);
          continue;
        }

        region.setLat(Double.valueOf(locs[1]));
        region.setLon(Double.valueOf(locs[0]));

        regionRepository.save(region);
        log.info("parse region loc {}", id);
      }

      return "success";
    } catch (Exception e) {
      log.error("parse region loc get exception ", e);
      return "exception";
    }
  }
}
