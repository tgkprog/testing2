package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestCaseResult is a Querydsl query type for QTestCaseResult
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestCaseResult extends com.mysema.query.sql.RelationalPathBase<QTestCaseResult> {

    private static final long serialVersionUID = -1096508360;

    public static final QTestCaseResult TestCaseResult = new QTestCaseResult("TestCaseResult");

    public final StringPath comment = createString("Comment");

    public final NumberPath<Integer> duration = createNumber("duration", Integer.class);

    public final StringPath enddatetime = createString("enddatetime");

    public final StringPath exception = createString("Exception");

    public final NumberPath<Integer> failcount = createNumber("failcount", Integer.class);

    public final NumberPath<Integer> passcount = createNumber("passcount", Integer.class);

    public final StringPath request = createString("Request");

    public final StringPath response = createString("Response");

    public final StringPath result = createString("result");

    public final NumberPath<Integer> skipcount = createNumber("skipcount", Integer.class);

    public final StringPath startdatetime = createString("startdatetime");

    public final NumberPath<Integer> taskResultId = createNumber("TaskResultId", Integer.class);

    public final NumberPath<Integer> testCaseId = createNumber("TestCaseId", Integer.class);

    public final StringPath testCaseName = createString("TestCaseName");

    public final NumberPath<Integer> testCaseResultId = createNumber("TestCaseResultId", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QTestCaseResult> primary = createPrimaryKey(testCaseResultId);

    public QTestCaseResult(String variable) {
        super(QTestCaseResult.class, forVariable(variable), "null", "TestCaseResult");
    }

    @SuppressWarnings("all")
    public QTestCaseResult(Path<? extends QTestCaseResult> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestCaseResult");
    }

    public QTestCaseResult(PathMetadata<?> metadata) {
        super(QTestCaseResult.class, metadata, "null", "TestCaseResult");
    }

}

