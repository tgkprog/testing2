package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestStep is a Querydsl query type for QTestStep
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestStep extends com.mysema.query.sql.RelationalPathBase<QTestStep> {

    private static final long serialVersionUID = 580504535;

    public static final QTestStep TestStep = new QTestStep("TestStep");

    public final NumberPath<Integer> actionId = createNumber("ActionId", Integer.class);

    public final NumberPath<java.math.BigDecimal> actionsId = createNumber("actionsId", java.math.BigDecimal.class);

    public final NumberPath<Integer> active = createNumber("Active", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final StringPath expectedResult = createString("ExpectedResult");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> inputParam = createNumber("InputParam", Integer.class);

    public final NumberPath<java.math.BigDecimal> inputParamGroupId = createNumber("inputParamGroupId", java.math.BigDecimal.class);

    public final NumberPath<Integer> orderBy = createNumber("OrderBy", Integer.class);

    public final NumberPath<Integer> outputParam = createNumber("OutputParam", Integer.class);

    public final NumberPath<java.math.BigDecimal> outputParamGroupId = createNumber("outputParamGroupId", java.math.BigDecimal.class);

    public final NumberPath<Integer> postCondition = createNumber("PostCondition", Integer.class);

    public final NumberPath<java.math.BigDecimal> postConditionGroupId = createNumber("postConditionGroupId", java.math.BigDecimal.class);

    public final NumberPath<Integer> preCondition = createNumber("PreCondition", Integer.class);

    public final NumberPath<java.math.BigDecimal> preConditionGroupId = createNumber("preConditionGroupId", java.math.BigDecimal.class);

    public final NumberPath<Integer> runnerId = createNumber("RunnerId", Integer.class);

    public final StringPath stepName = createString("StepName");

    public final NumberPath<Integer> testCaseId = createNumber("TestCaseId", Integer.class);

    public final NumberPath<Integer> testStepID = createNumber("TestStepID", Integer.class);

    public final StringPath testStepType = createString("TestStepType");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestStep> primary = createPrimaryKey(testStepID);

    public QTestStep(String variable) {
        super(QTestStep.class, forVariable(variable), "null", "TestStep");
    }

    @SuppressWarnings("all")
    public QTestStep(Path<? extends QTestStep> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestStep");
    }

    public QTestStep(PathMetadata<?> metadata) {
        super(QTestStep.class, metadata, "null", "TestStep");
    }

}

