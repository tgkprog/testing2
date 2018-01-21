package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestPlanTst is a Querydsl query type for QTestPlanTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestPlanTst extends com.mysema.query.sql.RelationalPathBase<QTestPlanTst> {

    private static final long serialVersionUID = -622945023;

    public static final QTestPlanTst TestPlanTst = new QTestPlanTst("TestPlan_tst");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final StringPath planName = createString("PlanName");

    public final StringPath postCondition = createString("PostCondition");

    public final StringPath preCondition = createString("PreCondition");

    public final NumberPath<Integer> testPlanID = createNumber("TestPlanID", Integer.class);

    public final NumberPath<Integer> testScenarioId = createNumber("TestScenarioId", Integer.class);

    public final NumberPath<Integer> testSuiteID = createNumber("TestSuiteID", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestPlanTst> primary = createPrimaryKey(testPlanID);

    public QTestPlanTst(String variable) {
        super(QTestPlanTst.class, forVariable(variable), "null", "TestPlan_tst");
    }

    @SuppressWarnings("all")
    public QTestPlanTst(Path<? extends QTestPlanTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestPlan_tst");
    }

    public QTestPlanTst(PathMetadata<?> metadata) {
        super(QTestPlanTst.class, metadata, "null", "TestPlan_tst");
    }

}

