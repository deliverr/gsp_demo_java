
package demos.dlineage.dataflow.model;

import demos.dlineage.util.Pair;
import gudusoft.gsqlparser.TSourceToken;
import gudusoft.gsqlparser.nodes.TParseTreeNode;

import java.util.ArrayList;
import java.util.List;

public class ResultSet {

    private int id;
    protected String schema;
    protected String database;
    private Pair<Long, Long> startPosition;
    private Pair<Long, Long> endPosition;
    private List<ResultColumn> columns = new ArrayList<ResultColumn>();

    private TParseTreeNode gspObject;
    private boolean isTarget;

    public ResultSet(TParseTreeNode gspObject, boolean isTarget) {
        if (gspObject == null) {
            throw new IllegalArgumentException("ResultSet arguments can't be null.");
        }

        id = ++ModelBindingManager.get().TABLE_COLUMN_ID;

        this.gspObject = gspObject;
        this.isTarget = isTarget;

        TSourceToken startToken = gspObject.getStartToken();
        TSourceToken endToken = gspObject.getEndToken();
        if (startToken != null) {
            this.startPosition = new Pair<Long, Long>(startToken.lineNo,
                    startToken.columnNo);
        } else {
            System.err.println();
            System.err.println("Can't get start token, the start token is null");
        }
        if (endToken != null) {
            this.endPosition = new Pair<Long, Long>(endToken.lineNo,
                    endToken.columnNo + endToken.astext.length());
        } else {
            System.err.println();
            System.err.println("Can't get end token, the end token is null");
        }
        
        this.schema = ModelBindingManager.getGlobalSchema();
        this.database = ModelBindingManager.getGlobalDatabase();
    }

    public Pair<Long, Long> getStartPosition() {
        return startPosition;
    }

    public Pair<Long, Long> getEndPosition() {
        return endPosition;
    }

    public List<ResultColumn> getColumns() {
        return columns;
    }

    public void addColumn(ResultColumn column) {
        if (column != null && !columns.contains(column)) {
            this.columns.add(column);
        }
    }

    public TParseTreeNode getGspObject() {
        return gspObject;
    }

    public int getId() {
        return id;
    }

    public boolean isTarget() {
        return isTarget;
    }

	public String getSchema() {
		return schema;
	}

	public String getDatabase() {
		return database;
	}
}
