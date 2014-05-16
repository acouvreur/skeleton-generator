package org.skeleton.generator.repository.dao.datasource.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.skeleton.generator.exception.ReadBackupFailureException;
import org.skeleton.generator.model.om.Table;
import org.skeleton.generator.repository.dao.datasource.interfaces.BackupArgumentReader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * Implementation of a {@link BackupArgumentReader} that uses a script, a {@link JdbcTemplate} and a {@link Table} for meta-data
 * the {@link JdbcTemplate} is instantiated with a {@link DataSource} when instantiating the class
 * @author Nicolas Thibault
 *
 */
public class SourceAndScriptBackupReader{

	/*
	 * properties
	 */
	private JdbcTemplate jdbcTemplate;
	private Table table;
	
	/*
	 * constructor
	 */
	public SourceAndScriptBackupReader(DataSource dataSource, Table table) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.table = table;
	}
	
	
	public List<Object[]> readBackupArgs(String script) throws ReadBackupFailureException {
		
		return jdbcTemplate.query(script, new BasicRowMapper());

	}
	


	private class BasicRowMapper implements RowMapper<Object[]>{
		
		@Override
	    public Object[] mapRow(ResultSet resultSet, int rowNum) throws SQLException {

	        List<Object> objectList = new ArrayList<>();
	        for (int i=1;i<=table.getInsertColumnList().size();i++) {
	        	objectList.add(resultSet.getObject(i));
	        }
	        
	        return objectList.toArray();
	    }

	}

}
