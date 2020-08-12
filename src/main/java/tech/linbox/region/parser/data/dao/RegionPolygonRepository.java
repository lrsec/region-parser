package tech.linbox.region.parser.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tech.linbox.region.parser.data.entity.RegionPolygon;

public interface RegionPolygonRepository extends JpaRepository<RegionPolygon, String>, JpaSpecificationExecutor<RegionPolygon> {
}
