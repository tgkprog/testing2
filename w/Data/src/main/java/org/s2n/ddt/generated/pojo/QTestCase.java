package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestCase is a Querydsl query type for QTestCase
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestCase extends com.mysema.query.sql.RelationalPathBase<QTestCase> {

    private static final long serialVersionUID = 580010043;

    public static final QTestCase TestCase = new QTestCase("TestCase");

    public final NumberPath<Integer> active = createNumber("Active", Integer.class);

    public final StringPath caseName = createString("CaseName");

    public final StringPath classificationTag = createString("ClassificationTag");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> orderBy = createNumber("OrderBy", Integer.class);

    public final NumberPath<Integer> positive = createNumber("Positive", Integer.class);

    public final NumberPath<Integer> runnerId = createNumber("RunnerId", Integer.class);

    public final NumberPath<Integer> testCaseID = createNumber("TestCaseID", Integer.class);

    public final NumberPath<Integer> testSuiteID = createNumber("TestSuiteID", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestCase> primary = createPrimaryKey(testCaseID);

    public QTestCase(String variable) {
        super(QTestCase.class, forVariable(variable), "null", "TestCase");
    }

    @SuppressWarnings("all")
    public QTestCase(Path<? extends QTestCase> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestCase");
    }

    public QTestCase(PathMetadata<?> metadata) {
        super(QTestCase.class, metadata, "null", "TestCase");
    }

}

