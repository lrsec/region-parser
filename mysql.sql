# 地理位置
CREATE TABLE `region` (
    `code` VARCHAR(10) NOT NULL COMMENT '行政区域编码',
    `name` VARCHAR(200) DEFAULT NULL COMMENT '区域名称',
    `pinyin` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '拼音',
    `pinyin_prefix` VARCHAR(5) NOT NULL DEFAULT '' COMMENT '拼音首字母',
    `deep` INT NOT NULL COMMENT '区域深度',
    `pcode` VARCHAR(10) NOT NULL DEFAULT 0 COMMENT '父区域编码',
    `lat` decimal(10,6) DEFAULT NULL COMMENT '纬度',
    `lon` decimal(10,6) DEFAULT NULL COMMENT '经度',
    PRIMARY KEY (`code`),
    KEY(`deep`),
    KEY(`pcode`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='地理位置';


# 地理位置边界
CREATE TABLE `region_polygon` (
    `code` VARCHAR(10) NOT NULL COMMENT '行政区域编码',
    `polygon` MEDIUMTEXT  COMMENT '边界坐标',
    PRIMARY KEY (`code`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='地理位置边界';
