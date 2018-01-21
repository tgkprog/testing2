package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestCondDataTst is a Querydsl query type for QTestCondDataTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestCondDataTst extends com.mysema.query.sql.RelationalPathBase<QTestCondDataTst> {

    private static final long serialVersionUID = 961923294;

    public static final QTestCondDataTst TestCondDataTst = new QTestCondDataTst("TestCondData_tst");

    public final NumberPath<Integer> conditionId = createNumber("ConditionId", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final NumberPath<Integer> paramId = createNumber("ParamId", Integer.class);

    public final StringPath paramValue = createString("ParamValue");

    public final NumberPath<Integer> testCondDataId = createNumber("TestCondDataId", Integer.class);

    public final NumberPath<Integer> testPlanID = createNumber("TestPlanID", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestCondDataTst> primary = createPrimaryKey(testCondDataId);

    public QTestCondDataTst(String variable) {
        super(QTestCondDataTst.class, forVariable(variable), "null", "TestCondData_tst");
    }

    @SuppressWarnings("all")
    public QTestCondDataTst(Path<? extends QTestCondDataTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestCondData_tst");
    }

    public QTestCondDataTst(PathMetadata<?> metadata) {
        super(QTestCondDataTst.class, metadata, "null", "TestCondData_tst");
    }

}

