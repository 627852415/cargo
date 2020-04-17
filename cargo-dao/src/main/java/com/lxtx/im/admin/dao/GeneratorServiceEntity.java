package com.lxtx.im.admin.dao;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 代码生成器演示
 *
 * @author liboyan
 * @Date 2018-08-03 11:10
 * @Description
 */
@SuppressWarnings("Duplicates")
public class GeneratorServiceEntity {

    private static String DBURL = "jdbc:mysql://127.0.0.1:3306/cargo";
    private static String USERNAME = "root";
    private static String PASSWORD = "root123";
    private static String OUTPUT_DIR = "E:\\git_work\\cargo\\cargo-dao\\src\\main\\java";
    private static String AUTHOR = "";

    public static void main(String[] args) {
        GeneratorServiceEntity generatorServiceEntity = new GeneratorServiceEntity();
        generatorServiceEntity.generateCode();

    }

    public void generateCode() {
        String packageName = "com.lxtx.im.admin.dao";
        // user -> UserDao, 设置成true: user -> IUserService
        boolean serviceNameStartWithI = false;
        generateByTables(serviceNameStartWithI, packageName, "t_paper","t_paper_type");
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = DBURL;
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL).setUrl(dbUrl).setUsername(USERNAME).setPassword(PASSWORD)
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true).setEntityLombokModel(true).setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                // 修改替换成你需要的表名，多个表名传数组
                .setInclude(tableNames)
                // 此处可以修改为您的表前缀
                .setTablePrefix(new String[]{"t_"})
                .setSuperEntityClass("com.lxtx.im.admin.dao.model.BaseModel")
                .setSuperEntityColumns(new String[]{"remarks", "create_time", "create_by", "update_time", "update_by", "del_flag"})
                .setSuperServiceClass("com.lxtx.im.admin.dao.dao.BaseDao")
                .setSuperServiceImplClass("com.lxtx.im.admin.dao.impl.BaseDaoImpl");
        config.setActiveRecord(false)
                .setAuthor(AUTHOR)
                .setOutputDir(OUTPUT_DIR)
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sDao");
            config.setServiceImplName("%sDaoImpl");
        }
        // XML 二级缓存
        config.setEnableCache(false);
        // XML ResultMap
        config.setBaseResultMap(false);
        // XML columList
        config.setBaseColumnList(false);

        new AutoGenerator().setGlobalConfig(config).setDataSource(dataSourceConfig).setStrategy(strategyConfig)
                .setPackageInfo(new PackageConfig().setParent(packageName).setController("web").setEntity("model")
                        .setService("dao").setServiceImpl("impl")).execute();
    }

}
