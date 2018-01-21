package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestPlanTestScenarioMap is a Querydsl query type for QTestPlanTestScenarioMap
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestPlanTestScenarioMap extends com.mysema.query.sql.RelationalPathBase<QTestPlanTestScenarioMap> {

    private static final long serialVersionUID = -1354302234;

    public static final QTestPlanTestScenarioMap TestPlanTestScenarioMap = new QTestPlanTestScenarioMap("TestPlan_TestScenario_Map");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final NumberPath<Integer> testPlanId = createNumber("TestPlanId", Integer.class);

    public final NumberPath<Integer> testPlanTestScenarioId = createNumber("TestPlanTestScenarioId", Integer.class);

    public final NumberPath<Integer> testScenarioId = createNumber("TestScenarioId", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestPlanTestScenarioMap> primary = createPrimaryKey(testPlanTestScenarioId);

    public QTestPlanTestScenarioMap(String variable) {
        super(QTestPlanTestScenarioMap.class, forVariable(variable), "null", "TestPlan_TestScenario_Map");
    }

    @SuppressWarnings("all")
    public QTestPlanTestScenarioMap(Path<? extends QTestPlanTestScenarioMap> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestPlan_TestScenario_Map");
    }

    public QTestPlanTestScenarioMap(PathMetadata<?> metadata) {
        super(QTestPlanTestScenarioMap.class, metadata, "null", "TestPlan_TestScenario_Map");
    }

}

