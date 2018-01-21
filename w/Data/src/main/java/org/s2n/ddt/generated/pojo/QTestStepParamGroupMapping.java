package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestStepParamGroupMapping is a Querydsl query type for QTestStepParamGroupMapping
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestStepParamGroupMapping extends com.mysema.query.sql.RelationalPathBase<QTestStepParamGroupMapping> {

    private static final long serialVersionUID = 35715941;

    public static final QTestStepParamGroupMapping TestStepParamGroupMapping = new QTestStepParamGroupMapping("TestStep_ParamGroup_Mapping");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final NumberPath<Integer> paramGroupID = createNumber("ParamGroupID", Integer.class);

    public final NumberPath<Integer> testCaseID = createNumber("TestCaseID", Integer.class);

    public final NumberPath<Integer> testScenarioId = createNumber("TestScenarioId", Integer.class);

    public final NumberPath<Integer> testStepID = createNumber("TestStepID", Integer.class);

    public final NumberPath<Integer> testStepParamGroupID = createNumber("TestStepParamGroupID", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestStepParamGroupMapping> primary = createPrimaryKey(testStepParamGroupID);

    public QTestStepParamGroupMapping(String variable) {
        super(QTestStepParamGroupMapping.class, forVariable(variable), "null", "TestStep_ParamGroup_Mapping");
    }

    @SuppressWarnings("all")
    public QTestStepParamGroupMapping(Path<? extends QTestStepParamGroupMapping> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestStep_ParamGroup_Mapping");
    }

    public QTestStepParamGroupMapping(PathMetadata<?> metadata) {
        super(QTestStepParamGroupMapping.class, metadata, "null", "TestStep_ParamGroup_Mapping");
    }

}

