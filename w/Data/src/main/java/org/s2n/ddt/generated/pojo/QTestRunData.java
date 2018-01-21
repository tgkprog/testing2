package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestRunData is a Querydsl query type for QTestRunData
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestRunData extends com.mysema.query.sql.RelationalPathBase<QTestRunData> {

    private static final long serialVersionUID = 1420491754;

    public static final QTestRunData TestRunData = new QTestRunData("TestRunData");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath exceptionMessage = createString("ExceptionMessage");

    public final StringPath status = createString("Status");

    public final NumberPath<Integer> testCaseID = createNumber("TestCaseID", Integer.class);

    public final NumberPath<Integer> testPlanID = createNumber("TestPlanID", Integer.class);

    public final NumberPath<Integer> testRunDataId = createNumber("TestRunDataId", Integer.class);

    public final NumberPath<Integer> testStepID = createNumber("TestStepID", Integer.class);

    public final NumberPath<Integer> testSuiteID = createNumber("TestSuiteID", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestRunData> primary = createPrimaryKey(testRunDataId);

    public QTestRunData(String variable) {
        super(QTestRunData.class, forVariable(variable), "null", "TestRunData");
    }

    @SuppressWarnings("all")
    public QTestRunData(Path<? extends QTestRunData> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestRunData");
    }

    public QTestRunData(PathMetadata<?> metadata) {
        super(QTestRunData.class, metadata, "null", "TestRunData");
    }

}

