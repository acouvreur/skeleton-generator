package org.skeleton.generator.bc.command.file.impl.presentation;

import org.skeleton.generator.bc.command.file.impl.AbstractFileWriteCommand;
import org.skeleton.generator.util.metadata.FileType;

public abstract class XhtmlFileWriteCommand extends AbstractFileWriteCommand {

	/*
	 * constructor
	 */
	public XhtmlFileWriteCommand(String folderName, String fileName) {
		super(folderName, fileName, FileType.XHTML);
	}
}
