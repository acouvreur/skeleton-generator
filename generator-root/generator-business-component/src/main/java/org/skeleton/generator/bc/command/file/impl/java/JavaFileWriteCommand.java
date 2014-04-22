package org.skeleton.generator.bc.command.file.impl.java;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.skeleton.generator.bc.command.file.impl.AbstractFileWriteCommand;
import org.skeleton.generator.model.metadata.FileType;




/**
 * Extension of a generic file write command to handle specificities of java files
 * <br/>Handles conservation of imports
 * @author Nicolas Thibault
 *
 */
public abstract class JavaFileWriteCommand extends AbstractFileWriteCommand {

	/*
	 * properties
	 */
	protected Set<String> javaImports;
	
	
	/*
	 * constructor
	 */
	public JavaFileWriteCommand(String folderName, String fileName) {
		super(folderName, fileName, FileType.JAVA);
		this.javaImports = new HashSet<>();
	}

	private final void fetchImports() throws IOException {
		if (Files.exists(filePath)) {
			List<String> lines = Files.readAllLines(filePath,
					fileType.getEncoding());
			
			for (String line : lines) {
				if (line.startsWith("import ")){
                    javaImports.add(line);
                }
			}
		}
	}
	
	protected abstract void fetchSpecificImports();
	
	protected final void writeImports() throws IOException{
		fetchImports();
		fetchSpecificImports();
		
		for (String javaImport:javaImports) {
			writeLine(javaImport);
		}
	}

	
	

}
