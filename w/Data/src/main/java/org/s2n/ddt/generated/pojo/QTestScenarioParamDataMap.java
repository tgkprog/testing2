package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestScenarioParamDataMap is a Querydsl query type for QTestScenarioParamDataMap
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestScenarioParamDataMap extends com.mysema.query.sql.RelationalPathBase<QTestScenarioParamDataMap> {

    private static final long serialVersionUID = 1079417536;

    public static final QTestScenarioParamDataMap TestScenarioParamDataMap = new QTestScenarioParamDataMap("TestScenario_ParamData_Map");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final NumberPath<Integer> testParamDataId = createNumber("TestParamDataId", Integer.class);

    public final NumberPath<Integer> testScenarioId = createNumber("TestScenarioId", Integer.class);

    public final NumberPath<Integer> testScenarioParamDataId = createNumber("TestScenarioParamDataId", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestScenarioParamDataMap> primary = createPrimaryKey(testScenarioParamDataId);

    public QTestScenarioParamDataMap(String variable) {
        super(QTestScenarioParamDataMap.class, forVariable(variable), "null", "TestScenario_ParamData_Map");
    }

    @SuppressWarnings("all")
    public QTestScenarioParamDataMap(Path<? extends QTestScenarioParamDataMap> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestScenario_ParamData_Map");
    }

    public QTestScenarioParamDataMap(PathMetadata<?> metadata) {
        super(QTestScenarioParamDataMap.class, metadata, "null", "TestScenario_ParamData_Map");
    }

}

