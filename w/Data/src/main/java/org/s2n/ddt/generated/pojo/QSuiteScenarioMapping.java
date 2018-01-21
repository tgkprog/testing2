package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSuiteScenarioMapping is a Querydsl query type for QSuiteScenarioMapping
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSuiteScenarioMapping extends com.mysema.query.sql.RelationalPathBase<QSuiteScenarioMapping> {

    private static final long serialVersionUID = -1245506337;

    public static final QSuiteScenarioMapping SuiteScenarioMapping = new QSuiteScenarioMapping("Suite_Scenario_Mapping");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final NumberPath<Integer> suiteScenarioId = createNumber("SuiteScenarioId", Integer.class);

    public final NumberPath<Integer> testScenarioId = createNumber("TestScenarioId", Integer.class);

    public final NumberPath<Integer> testSuiteId = createNumber("TestSuiteId", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QSuiteScenarioMapping> primary = createPrimaryKey(suiteScenarioId);

    public QSuiteScenarioMapping(String variable) {
        super(QSuiteScenarioMapping.class, forVariable(variable), "null", "Suite_Scenario_Mapping");
    }

    @SuppressWarnings("all")
    public QSuiteScenarioMapping(Path<? extends QSuiteScenarioMapping> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Suite_Scenario_Mapping");
    }

    public QSuiteScenarioMapping(PathMetadata<?> metadata) {
        super(QSuiteScenarioMapping.class, metadata, "null", "Suite_Scenario_Mapping");
    }

}

