package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestSuite is a Querydsl query type for QTestSuite
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestSuite extends com.mysema.query.sql.RelationalPathBase<QTestSuite> {

    private static final long serialVersionUID = 815805261;

    public static final QTestSuite TestSuite = new QTestSuite("TestSuite");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> featureId = createNumber("FeatureId", Integer.class);

    public final NumberPath<Integer> functionalId = createNumber("FunctionalId", Integer.class);

    public final NumberPath<Integer> orderBy = createNumber("OrderBy", Integer.class);

    public final StringPath suiteName = createString("SuiteName");

    public final NumberPath<Integer> testSuiteID = createNumber("TestSuiteID", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestSuite> primary = createPrimaryKey(testSuiteID);

    public QTestSuite(String variable) {
        super(QTestSuite.class, forVariable(variable), "null", "TestSuite");
    }

    @SuppressWarnings("all")
    public QTestSuite(Path<? extends QTestSuite> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestSuite");
    }

    public QTestSuite(PathMetadata<?> metadata) {
        super(QTestSuite.class, metadata, "null", "TestSuite");
    }

}

