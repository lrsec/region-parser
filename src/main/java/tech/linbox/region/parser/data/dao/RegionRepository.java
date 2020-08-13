package tech.linbox.region.parser.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tech.linbox.region.parser.data.entity.Region;

public interface RegionRepository
    extends JpaRepository<Region, String>, JpaSpecificationExecutor<Region> {
  Region findByCode(String code);
}
