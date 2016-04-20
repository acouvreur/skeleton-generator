package org.sklsft.generator.repository.backup.datasource.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.sklsft.generator.exception.InvalidFileException;
import org.sklsft.generator.exception.ReadBackupFailureException;
import org.sklsft.generator.model.backup.PopulateCommandType;
import org.sklsft.generator.repository.backup.datasource.interfaces.BackupArgumentReader;
import org.sklsft.generator.repository.backup.file.impl.CsvFileParserImpl;
import org.sklsft.generator.repository.backup.file.interfaces.CsvFileParser;
import org.sklsft.generator.repository.backup.file.model.CsvFile;


public class TextDelimitedFileBackupReader implements BackupArgumentReader {

	/*
	 * properties
	 */
	private CsvFileParser csvFileParser;
	
	/*
	 * constructor
	 */
	public TextDelimitedFileBackupReader() {
		this.csvFileParser = new CsvFileParserImpl(StandardCharsets.UTF_8);
	}
	
	public BackupCommandArguments readBackupArgs(String backupFilePath)  {
		BackupCommandArguments result = new BackupCommandArguments();
		result.setArguments(readArgs(backupFilePath));
		result.setType(readType());
		result.setArgumentsTyped(false);
		return result;
	}
	
	private PopulateCommandType readType() {
		return PopulateCommandType.INSERT; //TODO put type in csv header
	}

	private List<Object[]> readArgs(String backupFilePath){
		try {
			CsvFile csvFile = csvFileParser.readData(backupFilePath);
			
			return csvFile.getData();
			
		} catch (IOException | InvalidFileException e) {
			throw new ReadBackupFailureException("failed to read backup", e);
		}
	}
}