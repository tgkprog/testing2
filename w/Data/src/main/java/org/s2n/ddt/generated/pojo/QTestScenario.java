package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestScenario is a Querydsl query type for QTestScenario
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestScenario extends com.mysema.query.sql.RelationalPathBase<QTestScenario> {

    private static final long serialVersionUID = -480658661;

    public static final QTestScenario TestScenario = new QTestScenario("TestScenario");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> orderBy = createNumber("OrderBy", Integer.class);

    public final NumberPath<Integer> testScenarioId = createNumber("TestScenarioId", Integer.class);

    public final StringPath testScenarioName = createString("TestScenarioName");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestScenario> primary = createPrimaryKey(testScenarioId);

    public QTestScenario(String variable) {
        super(QTestScenario.class, forVariable(variable), "null", "TestScenario");
    }

    @SuppressWarnings("all")
    public QTestScenario(Path<? extends QTestScenario> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestScenario");
    }

    public QTestScenario(PathMetadata<?> metadata) {
        super(QTestScenario.class, metadata, "null", "TestScenario");
    }

}

