package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestCondData is a Querydsl query type for QTestCondData
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestCondData extends com.mysema.query.sql.RelationalPathBase<QTestCondData> {

    private static final long serialVersionUID = -288163465;

    public static final QTestCondData TestCondData = new QTestCondData("TestCondData");

    public final NumberPath<Integer> conditionId = createNumber("ConditionId", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final NumberPath<Integer> paramId = createNumber("ParamId", Integer.class);

    public final StringPath paramValue = createString("ParamValue");

    public final NumberPath<Integer> testCondDataId = createNumber("TestCondDataId", Integer.class);

    public final NumberPath<Integer> testPlanID = createNumber("TestPlanID", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestCondData> primary = createPrimaryKey(testCondDataId);

    public QTestCondData(String variable) {
        super(QTestCondData.class, forVariable(variable), "null", "TestCondData");
    }

    @SuppressWarnings("all")
    public QTestCondData(Path<? extends QTestCondData> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestCondData");
    }

    public QTestCondData(PathMetadata<?> metadata) {
        super(QTestCondData.class, metadata, "null", "TestCondData");
    }

}

