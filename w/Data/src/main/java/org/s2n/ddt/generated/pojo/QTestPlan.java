package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestPlan is a Querydsl query type for QTestPlan
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestPlan extends com.mysema.query.sql.RelationalPathBase<QTestPlan> {

    private static final long serialVersionUID = 580407348;

    public static final QTestPlan TestPlan = new QTestPlan("TestPlan");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final StringPath planName = createString("PlanName");

    public final NumberPath<Integer> postCondition = createNumber("PostCondition", Integer.class);

    public final NumberPath<Integer> preCondition = createNumber("PreCondition", Integer.class);

    public final NumberPath<Integer> testPlanID = createNumber("TestPlanID", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestPlan> primary = createPrimaryKey(testPlanID);

    public QTestPlan(String variable) {
        super(QTestPlan.class, forVariable(variable), "null", "TestPlan");
    }

    @SuppressWarnings("all")
    public QTestPlan(Path<? extends QTestPlan> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestPlan");
    }

    public QTestPlan(PathMetadata<?> metadata) {
        super(QTestPlan.class, metadata, "null", "TestPlan");
    }

}

