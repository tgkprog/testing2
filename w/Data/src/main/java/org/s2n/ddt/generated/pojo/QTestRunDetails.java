package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestRunDetails is a Querydsl query type for QTestRunDetails
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestRunDetails extends com.mysema.query.sql.RelationalPathBase<QTestRunDetails> {

    private static final long serialVersionUID = -328303102;

    public static final QTestRunDetails TestRunDetails = new QTestRunDetails("TestRunDetails");

    public final StringPath buildVersion = createString("BuildVersion");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath machineId = createString("MachineId");

    public final StringPath notificationDetails = createString("NotificationDetails");

    public final StringPath os = createString("OS");

    public final DatePath<java.sql.Date> runTime = createDate("RunTime", java.sql.Date.class);

    public final StringPath status = createString("Status");

    public final NumberPath<Integer> testPlanID = createNumber("TestPlanID", Integer.class);

    public final NumberPath<Integer> testRunID = createNumber("TestRunID", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestRunDetails> primary = createPrimaryKey(testRunID);

    public QTestRunDetails(String variable) {
        super(QTestRunDetails.class, forVariable(variable), "null", "TestRunDetails");
    }

    @SuppressWarnings("all")
    public QTestRunDetails(Path<? extends QTestRunDetails> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestRunDetails");
    }

    public QTestRunDetails(PathMetadata<?> metadata) {
        super(QTestRunDetails.class, metadata, "null", "TestRunDetails");
    }

}

