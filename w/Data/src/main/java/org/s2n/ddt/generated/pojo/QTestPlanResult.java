package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestPlanResult is a Querydsl query type for QTestPlanResult
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestPlanResult extends com.mysema.query.sql.RelationalPathBase<QTestPlanResult> {

    private static final long serialVersionUID = 328404337;

    public static final QTestPlanResult TestPlanResult = new QTestPlanResult("TestPlanResult");

    public final DateTimePath<java.sql.Timestamp> endDateTime = createDateTime("EndDateTime", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> startDateTime = createDateTime("StartDateTime", java.sql.Timestamp.class);

    public final StringPath statusSummary = createString("StatusSummary");

    public final NumberPath<Integer> testPlanId = createNumber("TestPlanId", Integer.class);

    public final NumberPath<Integer> testPlanResultId = createNumber("TestPlanResultId", Integer.class);

    public final NumberPath<Integer> testPlanResulttId = createNumber("TestPlanResulttId", Integer.class);

    public final StringPath testPlanRunName = createString("TestPlanRunName");

    public final com.mysema.query.sql.PrimaryKey<QTestPlanResult> primary = createPrimaryKey(testPlanResulttId);

    public QTestPlanResult(String variable) {
        super(QTestPlanResult.class, forVariable(variable), "null", "TestPlanResult");
    }

    @SuppressWarnings("all")
    public QTestPlanResult(Path<? extends QTestPlanResult> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestPlanResult");
    }

    public QTestPlanResult(PathMetadata<?> metadata) {
        super(QTestPlanResult.class, metadata, "null", "TestPlanResult");
    }

}

