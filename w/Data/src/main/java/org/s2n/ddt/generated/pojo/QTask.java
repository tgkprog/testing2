package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTask is a Querydsl query type for QTask
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTask extends com.mysema.query.sql.RelationalPathBase<QTask> {

    private static final long serialVersionUID = 863858526;

    public static final QTask Task = new QTask("Task");

    public final StringPath dataSet = createString("DataSet");

    public final NumberPath<Integer> laneClone = createNumber("LaneClone", Integer.class);

    public final NumberPath<Integer> laneId = createNumber("LaneId", Integer.class);

    public final NumberPath<Integer> laneRepeat = createNumber("LaneRepeat", Integer.class);

    public final NumberPath<Integer> repeatNo = createNumber("RepeatNo", Integer.class);

    public final StringPath tagsToRun = createString("TagsToRun");

    public final NumberPath<Integer> taskId = createNumber("TaskId", Integer.class);

    public final StringPath taskName = createString("TaskName");

    public final StringPath testPlanXlsPath = createString("TestPlanXlsPath");

    public final com.mysema.query.sql.PrimaryKey<QTask> primary = createPrimaryKey(taskId);

    public QTask(String variable) {
        super(QTask.class, forVariable(variable), "null", "Task");
    }

    @SuppressWarnings("all")
    public QTask(Path<? extends QTask> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Task");
    }

    public QTask(PathMetadata<?> metadata) {
        super(QTask.class, metadata, "null", "Task");
    }

}

