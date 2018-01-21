package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestCaseTst is a Querydsl query type for QTestCaseTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestCaseTst extends com.mysema.query.sql.RelationalPathBase<QTestCaseTst> {

    private static final long serialVersionUID = 425843610;

    public static final QTestCaseTst TestCaseTst = new QTestCaseTst("TestCase_tst");

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

    public final com.mysema.query.sql.PrimaryKey<QTestCaseTst> primary = createPrimaryKey(testCaseID);

    public QTestCaseTst(String variable) {
        super(QTestCaseTst.class, forVariable(variable), "null", "TestCase_tst");
    }

    @SuppressWarnings("all")
    public QTestCaseTst(Path<? extends QTestCaseTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestCase_tst");
    }

    public QTestCaseTst(PathMetadata<?> metadata) {
        super(QTestCaseTst.class, metadata, "null", "TestCase_tst");
    }

}

