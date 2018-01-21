package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTaskResult is a Querydsl query type for QTaskResult
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTaskResult extends com.mysema.query.sql.RelationalPathBase<QTaskResult> {

    private static final long serialVersionUID = -1280662245;

    public static final QTaskResult TaskResult = new QTaskResult("TaskResult");

    public final NumberPath<Integer> cloneNo = createNumber("cloneNo", Integer.class);

    public final StringPath endTime = createString("endTime");

    public final NumberPath<Integer> laneRepeationNo = createNumber("laneRepeationNo", Integer.class);

    public final StringPath reportFilePath = createString("ReportFilePath");

    public final NumberPath<Integer> runResultId = createNumber("RunResultId", Integer.class);

    public final StringPath startTime = createString("startTime");

    public final NumberPath<Integer> taskId = createNumber("TaskId", Integer.class);

    public final NumberPath<Integer> taskRepeationNo = createNumber("taskRepeationNo", Integer.class);

    public final NumberPath<Integer> taskResultId = createNumber("TaskResultId", Integer.class);

    public final NumberPath<Integer> testPlanResultId = createNumber("TestPlanResultId", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QTaskResult> primary = createPrimaryKey(taskResultId);

    public QTaskResult(String variable) {
        super(QTaskResult.class, forVariable(variable), "null", "TaskResult");
    }

    @SuppressWarnings("all")
    public QTaskResult(Path<? extends QTaskResult> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TaskResult");
    }

    public QTaskResult(PathMetadata<?> metadata) {
        super(QTaskResult.class, metadata, "null", "TaskResult");
    }

}

