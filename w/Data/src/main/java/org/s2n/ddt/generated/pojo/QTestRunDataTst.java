package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestRunDataTst is a Querydsl query type for QTestRunDataTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestRunDataTst extends com.mysema.query.sql.RelationalPathBase<QTestRunDataTst> {

    private static final long serialVersionUID = -442839669;

    public static final QTestRunDataTst TestRunDataTst = new QTestRunDataTst("TestRunData_tst");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath status = createString("Status");

    public final NumberPath<Integer> testCaseID = createNumber("TestCaseID", Integer.class);

    public final NumberPath<Integer> testPlanID = createNumber("TestPlanID", Integer.class);

    public final NumberPath<Integer> testRunDataId = createNumber("TestRunDataId", Integer.class);

    public final NumberPath<Integer> testStepID = createNumber("TestStepID", Integer.class);

    public final NumberPath<Integer> testSuiteID = createNumber("TestSuiteID", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestRunDataTst> primary = createPrimaryKey(testRunDataId);

    public QTestRunDataTst(String variable) {
        super(QTestRunDataTst.class, forVariable(variable), "null", "TestRunData_tst");
    }

    @SuppressWarnings("all")
    public QTestRunDataTst(Path<? extends QTestRunDataTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestRunData_tst");
    }

    public QTestRunDataTst(PathMetadata<?> metadata) {
        super(QTestRunDataTst.class, metadata, "null", "TestRunData_tst");
    }

}

