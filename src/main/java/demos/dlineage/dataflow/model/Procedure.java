package demos.dlineage.dataflow.model;

import java.util.ArrayList;
import java.util.List;

import demos.dlineage.util.Pair;
import demos.dlineage.util.SQLUtil;
import gudusoft.gsqlparser.ESqlStatementType;
import gudusoft.gsqlparser.TSourceToken;
import gudusoft.gsqlparser.stmt.TStoredProcedureSqlStatement;

public class Procedure {
	private int id;
	private String database;
	private String schema;
	private String name;
	private String fullName;
	private Pair<Long, Long> startPosition;
	private Pair<Long, Long> endPosition;
	private List<Argument> arguments = new ArrayList<Argument>();
	private ESqlStatementType type;
	private TStoredProcedureSqlStatement procedureObject;

	public Procedure(TStoredProcedureSqlStatement procedure) {
		if (procedure == null) {
			throw new IllegalArgumentException("Procedure arguments can't be null.");
		} else {
			this.id = ++ModelBindingManager.get().TABLE_COLUMN_ID;
			this.procedureObject = procedure;
			TSourceToken startToken = this.procedureObject.getStoredProcedureName().getStartToken();
			TSourceToken endToken = this.procedureObject.getStoredProcedureName().getEndToken();
			this.startPosition = new Pair<Long, Long>(startToken.lineNo, startToken.columnNo);
			this.endPosition = new Pair<Long, Long>(endToken.lineNo,
					endToken.columnNo + (long) endToken.astext.length());
			this.fullName = this.procedureObject.getStoredProcedureName().toString();
			this.name = this.procedureObject.getStoredProcedureName().getTableString();
			
			if (!SQLUtil.isEmpty(this.procedureObject.getStoredProcedureName().getSchemaString())) {
				this.schema = SQLUtil.trimObjectName(this.procedureObject.getStoredProcedureName().getSchemaString());
			} else {
				this.schema = ModelBindingManager.getGlobalSchema();
			}
			
			if (!SQLUtil.isEmpty(this.procedureObject.getStoredProcedureName().getDatabaseString())) {
				this.database = SQLUtil.trimObjectName(this.procedureObject.getStoredProcedureName().getDatabaseString());
			} else {
				this.database = ModelBindingManager.getGlobalDatabase();
			}
			
			this.name = SQLUtil.trimObjectName(this.name);
			
			this.type = procedure.sqlstatementtype;
		}
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pair<Long, Long> getStartPosition() {
		return this.startPosition;
	}

	public Pair<Long, Long> getEndPosition() {
		return this.endPosition;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<Argument> getArguments() {
		return this.arguments;
	}

	public void setArguments(List<Argument> arguments) {
		this.arguments = arguments;
	}

	public void addArgument(Argument argument) {
		if (argument != null && !this.arguments.contains(argument)) {
			this.arguments.add(argument);
		}

	}

	public ESqlStatementType getType() {
		return this.type;
	}

	public void setType(ESqlStatementType type) {
		this.type = type;
	}

	public TStoredProcedureSqlStatement getProcedureObject() {
		return this.procedureObject;
	}

	public void setProcedureObject(TStoredProcedureSqlStatement procedureObject) {
		this.procedureObject = procedureObject;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStartPosition(Pair<Long, Long> startPosition) {
		this.startPosition = startPosition;
	}

	public void setEndPosition(Pair<Long, Long> endPosition) {
		this.endPosition = endPosition;
	}

	public String getDatabase() {
		return database;
	}

	public String getSchema() {
		return schema;
	}

}
