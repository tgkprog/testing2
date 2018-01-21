package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestSuiteTst is a Querydsl query type for QTestSuiteTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestSuiteTst extends com.mysema.query.sql.RelationalPathBase<QTestSuiteTst> {

    private static final long serialVersionUID = -1565313208;

    public static final QTestSuiteTst TestSuiteTst = new QTestSuiteTst("TestSuite_tst");

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

    public final com.mysema.query.sql.PrimaryKey<QTestSuiteTst> primary = createPrimaryKey(testSuiteID);

    public QTestSuiteTst(String variable) {
        super(QTestSuiteTst.class, forVariable(variable), "null", "TestSuite_tst");
    }

    @SuppressWarnings("all")
    public QTestSuiteTst(Path<? extends QTestSuiteTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestSuite_tst");
    }

    public QTestSuiteTst(PathMetadata<?> metadata) {
        super(QTestSuiteTst.class, metadata, "null", "TestSuite_tst");
    }

}

