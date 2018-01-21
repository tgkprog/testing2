package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestStepResult is a Querydsl query type for QTestStepResult
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestStepResult extends com.mysema.query.sql.RelationalPathBase<QTestStepResult> {

    private static final long serialVersionUID = -1679555884;

    public static final QTestStepResult TestStepResult = new QTestStepResult("TestStepResult");

    public final StringPath actionName = createString("ActionName");

    public final StringPath comment = createString("Comment");

    public final StringPath detailMsg = createString("DetailMsg");

    public final StringPath duration = createString("Duration");

    public final StringPath exception = createString("Exception");

    public final StringPath paramData = createString("ParamData");

    public final StringPath paramObjectGroup = createString("ParamObjectGroup");

    public final StringPath request = createString("Request");

    public final StringPath response = createString("Response");

    public final StringPath result = createString("Result");

    public final StringPath runnerName = createString("RunnerName");

    public final StringPath stepParam = createString("StepParam");

    public final NumberPath<Integer> testCaseResultId = createNumber("TestCaseResultId", Integer.class);

    public final NumberPath<Integer> testStepId = createNumber("TestStepId", Integer.class);

    public final NumberPath<Integer> testStepResultId = createNumber("TestStepResultId", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QTestStepResult> primary = createPrimaryKey(testStepResultId);

    public QTestStepResult(String variable) {
        super(QTestStepResult.class, forVariable(variable), "null", "TestStepResult");
    }

    @SuppressWarnings("all")
    public QTestStepResult(Path<? extends QTestStepResult> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestStepResult");
    }

    public QTestStepResult(PathMetadata<?> metadata) {
        super(QTestStepResult.class, metadata, "null", "TestStepResult");
    }

}

