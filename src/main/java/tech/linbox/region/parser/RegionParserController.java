package tech.linbox.region.parser;

import com.alibaba.excel.EasyExcel;
import com.csvreader.CsvReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.linbox.region.parser.data.dao.RegionPolygonRepository;
import tech.linbox.region.parser.data.dao.RegionRepository;
import tech.linbox.region.parser.data.entity.RegionPolygon;

import java.io.InputStream;
import java.nio.charset.Charset;

@Slf4j
@RestController
public class RegionParserController {
  @Autowired RegionRepository regionRepository;
  @Autowired RegionPolygonRepository regionPolygonRepository;

  @PostMapping("/parse/region")
  public void parseRegion() {
    try {
      ClassPathResource resource = new ClassPathResource("ok_data_level3.xlsx");
      // 获取文件流
      InputStream inputStream = resource.getInputStream();

      EasyExcel.read(inputStream, RegionData.class, new RegionDataListener(regionRepository, log))
          .sheet()
          .doRead();
    } catch (Exception e) {
      log.error("parse region get exception ", e);
    }
  }

  @PostMapping("/parse/region/polygon")
  public void parseRegionPolygon() {
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
    } catch (Exception e) {
      log.error("parse region polygon get exception ", e);
    }
  }
}
