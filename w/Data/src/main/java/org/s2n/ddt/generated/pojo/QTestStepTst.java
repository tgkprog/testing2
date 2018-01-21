package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestStepTst is a Querydsl query type for QTestStepTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestStepTst extends com.mysema.query.sql.RelationalPathBase<QTestStepTst> {

    private static final long serialVersionUID = -2022614402;

    public static final QTestStepTst TestStepTst = new QTestStepTst("TestStep_tst");

    public final NumberPath<Integer> actionId = createNumber("ActionId", Integer.class);

    public final NumberPath<Integer> active = createNumber("Active", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final StringPath expectedResult = createString("ExpectedResult");

    public final NumberPath<Integer> inputParam = createNumber("InputParam", Integer.class);

    public final NumberPath<Integer> orderBy = createNumber("OrderBy", Integer.class);

    public final NumberPath<Integer> outputParam = createNumber("OutputParam", Integer.class);

    public final NumberPath<Integer> postCondition = createNumber("PostCondition", Integer.class);

    public final NumberPath<Integer> preCondition = createNumber("PreCondition", Integer.class);

    public final NumberPath<Integer> runnerId = createNumber("RunnerId", Integer.class);

    public final StringPath stepName = createString("StepName");

    public final NumberPath<Integer> testCaseId = createNumber("TestCaseId", Integer.class);

    public final NumberPath<Integer> testStepID = createNumber("TestStepID", Integer.class);

    public final StringPath testStepType = createString("TestStepType");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestStepTst> primary = createPrimaryKey(testStepID);

    public QTestStepTst(String variable) {
        super(QTestStepTst.class, forVariable(variable), "null", "TestStep_tst");
    }

    @SuppressWarnings("all")
    public QTestStepTst(Path<? extends QTestStepTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestStep_tst");
    }

    public QTestStepTst(PathMetadata<?> metadata) {
        super(QTestStepTst.class, metadata, "null", "TestStep_tst");
    }

}

