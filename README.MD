# 项目说明

本项目用于解析 github 项目 [xiangyuecn/AreaCity-JsSpider-StatsGov](https://github.com/xiangyuecn/AreaCity-JsSpider-StatsGov/tree/master/assets)  中提供的行政区域划分数据，并将其导入到 mysql 当中

# 操作说明

* 下载本项目
* 下载项目 [AreaCity-JsSpider-StatsGov的/src/采集到的数据/ok_data_level3.csv](https://github.com/xiangyuecn/AreaCity-JsSpider-StatsGov/blob/master/src/%E9%87%87%E9%9B%86%E5%88%B0%E7%9A%84%E6%95%B0%E6%8D%AE/ok_data_level3.csv), 转换成 excel 格式,并将其存储到本项目的 src/main/resources目录中
* 下载项目 [AreaCity-JsSpider-StatsGov的/src/采集到的数据/ok_geo.csv.7z](https://github.com/xiangyuecn/AreaCity-JsSpider-StatsGov/blob/master/src/%E9%87%87%E9%9B%86%E5%88%B0%E7%9A%84%E6%95%B0%E6%8D%AE/ok_data_level3.csv)，解压缩后,存储到本项目的  src/main/resources 目录中
* 执行 mysql.sql 中的语句，创建数据库表格
* 查看 src/main/application.yml 中的数据库配置，以符合当前数据库
* 运行程序 ```./run.sh```

