package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestScenarioTst is a Querydsl query type for QTestScenarioTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestScenarioTst extends com.mysema.query.sql.RelationalPathBase<QTestScenarioTst> {

    private static final long serialVersionUID = 118879418;

    public static final QTestScenarioTst TestScenarioTst = new QTestScenarioTst("TestScenario_tst");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> orderBy = createNumber("OrderBy", Integer.class);

    public final NumberPath<Integer> testScenarioId = createNumber("TestScenarioId", Integer.class);

    public final StringPath testScenarioName = createString("TestScenarioName");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestScenarioTst> primary = createPrimaryKey(testScenarioId);

    public QTestScenarioTst(String variable) {
        super(QTestScenarioTst.class, forVariable(variable), "null", "TestScenario_tst");
    }

    @SuppressWarnings("all")
    public QTestScenarioTst(Path<? extends QTestScenarioTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestScenario_tst");
    }

    public QTestScenarioTst(PathMetadata<?> metadata) {
        super(QTestScenarioTst.class, metadata, "null", "TestScenario_tst");
    }

}

