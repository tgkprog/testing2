package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestRunDetailsTst is a Querydsl query type for QTestRunDetailsTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestRunDetailsTst extends com.mysema.query.sql.RelationalPathBase<QTestRunDetailsTst> {

    private static final long serialVersionUID = -837094285;

    public static final QTestRunDetailsTst TestRunDetailsTst = new QTestRunDetailsTst("TestRunDetails_tst");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath notificationDetails = createString("NotificationDetails");

    public final DatePath<java.sql.Date> runTime = createDate("RunTime", java.sql.Date.class);

    public final StringPath status = createString("Status");

    public final NumberPath<Integer> testPlanID = createNumber("TestPlanID", Integer.class);

    public final NumberPath<Integer> testRunID = createNumber("TestRunID", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestRunDetailsTst> primary = createPrimaryKey(testRunID);

    public QTestRunDetailsTst(String variable) {
        super(QTestRunDetailsTst.class, forVariable(variable), "null", "TestRunDetails_tst");
    }

    @SuppressWarnings("all")
    public QTestRunDetailsTst(Path<? extends QTestRunDetailsTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestRunDetails_tst");
    }

    public QTestRunDetailsTst(PathMetadata<?> metadata) {
        super(QTestRunDetailsTst.class, metadata, "null", "TestRunDetails_tst");
    }

}

