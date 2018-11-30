/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author DesarrolloPc
 */
@Configuration
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages = {"com.jsoft"}, excludeFilters = {
    @ComponentScan.Filter(Configuration.class)})
@EnableTransactionManagement
@EnableMBeanExport
@PropertySource(value = {"classpath:queries.properties"})
public class AppConfig {

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        DataSource dataSource = dsLookup.getDataSource("java:/InvPartsDS");
        return dataSource;
    }
}
