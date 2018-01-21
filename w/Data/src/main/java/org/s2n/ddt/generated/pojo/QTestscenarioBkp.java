package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestscenarioBkp is a Querydsl query type for QTestscenarioBkp
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestscenarioBkp extends com.mysema.query.sql.RelationalPathBase<QTestscenarioBkp> {

    private static final long serialVersionUID = -1549006772;

    public static final QTestscenarioBkp testscenarioBkp = new QTestscenarioBkp("testscenario_bkp");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> testScenarioId = createNumber("TestScenarioId", Integer.class);

    public final StringPath testScenarioName = createString("TestScenarioName");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestscenarioBkp> primary = createPrimaryKey(testScenarioId);

    public QTestscenarioBkp(String variable) {
        super(QTestscenarioBkp.class, forVariable(variable), "null", "testscenario_bkp");
    }

    @SuppressWarnings("all")
    public QTestscenarioBkp(Path<? extends QTestscenarioBkp> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "testscenario_bkp");
    }

    public QTestscenarioBkp(PathMetadata<?> metadata) {
        super(QTestscenarioBkp.class, metadata, "null", "testscenario_bkp");
    }

}

