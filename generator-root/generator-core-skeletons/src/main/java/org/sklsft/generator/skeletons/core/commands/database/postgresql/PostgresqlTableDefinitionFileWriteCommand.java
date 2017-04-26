package org.sklsft.generator.skeletons.core.commands.database.postgresql;

import java.io.File;
import java.io.IOException;

import org.sklsft.generator.model.domain.Project;
import org.sklsft.generator.model.domain.database.Column;
import org.sklsft.generator.model.domain.database.Table;
import org.sklsft.generator.model.metadata.DataType;
import org.sklsft.generator.skeletons.commands.impl.typed.SqlFileWriteCommand;

public class PostgresqlTableDefinitionFileWriteCommand extends SqlFileWriteCommand {

	private Table table;
	private String sequenceName;

	/*
	 * constructor
	 */
	public PostgresqlTableDefinitionFileWriteCommand(Table table) {

		super(table.myPackage.model.project.sourceFolder + File.separator + Project.BUILD_SCRIPT_FOLDER + File.separator + "1" + File.separator + table.myPackage.name.toUpperCase(), table.originalName);

		this.table = table;
		this.sequenceName = table.name + "_id_seq";
	}

	@Override
	public void writeContent() throws IOException {

		createTable();

		if (table.myPackage.model.project.audited) {
			createAuditTable();
		}		

		writeNotOverridableContent();
	}

	/*
	 * create table
	 */
	private void createTable() {
		
		writeLine("-- create table --");
		writeLine("CREATE TABLE " + table.name);
		writeLine("(");
		write("id " + DataType.LONG.getPostgresqlType());

		for (Column column:table.columns) {
			writeLine(",");
			write(column.name + " " + column.dataType.getPostgresqlType());
			if (column.nullable) {
				write(" NULL");
			} else {
				write(" NOT NULL");
			}
		}

		skipLine();
		writeLine(");");
		writeLine("/");
		skipLine();
		
		if (table.cardinality > 0) {
			write("ALTER TABLE " + table.name + " ADD CONSTRAINT UC_" + table.name + " UNIQUE (" + this.table.columns.get(0).name);
			for (int i = 1; i < this.table.cardinality; i++) {
				write("," + this.table.columns.get(i).name);
			}
			writeLine(");");
			writeLine("/");
			skipLine();
		}

		writeLine("ALTER TABLE " + table.name + " ADD CONSTRAINT PK_" + table.name + " PRIMARY KEY (ID);");
		writeLine("/");
		skipLine();			

		writeLine("-- create sequence --");
		writeLine("CREATE SEQUENCE " + sequenceName);
		writeLine("INCREMENT 1");
		writeLine("MINVALUE 0");
		writeLine("MAXVALUE 9223372036854775807");
		writeLine("START 0");
		writeLine("CACHE 1;");
		writeLine("/");
		skipLine();
	}

	/*
	 * create audit table
	 */
	private void createAuditTable() {
		writeLine("-- table d'audit des elements --");
		writeLine("CREATE TABLE " + table.name + "_aud");
		writeLine("(");
		writeLine("id integer NOT NULL,");
		writeLine("rev integer NOT NULL,");
		writeLine("revtype smallint NOT NULL,");

		for (Column column:table.columns) {
			writeLine(column.name + " " + column.dataType.getPostgresqlType() + " NULL,");
		}

		writeLine("CONSTRAINT " + table.name + "_aud_pkey PRIMARY KEY (id, rev),");
		writeLine("CONSTRAINT " + table.name + "_aud_rev FOREIGN KEY (rev)");
		writeLine("REFERENCES auditentity (id) MATCH SIMPLE");
		writeLine("ON UPDATE NO ACTION ON DELETE NO ACTION");
		writeLine(")");
		writeLine(";");
		skipLine();
	}
}
