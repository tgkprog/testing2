package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QConditionGroup is a Querydsl query type for QConditionGroup
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QConditionGroup extends com.mysema.query.sql.RelationalPathBase<QConditionGroup> {

    private static final long serialVersionUID = 261296317;

    public static final QConditionGroup ConditionGroup = new QConditionGroup("ConditionGroup");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final NumberPath<Integer> condGrpId = createNumber("CondGrpId", Integer.class);

    public final StringPath condGrpName = createString("CondGrpName");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QConditionGroup> primary = createPrimaryKey(condGrpId);

    public QConditionGroup(String variable) {
        super(QConditionGroup.class, forVariable(variable), "null", "ConditionGroup");
    }

    @SuppressWarnings("all")
    public QConditionGroup(Path<? extends QConditionGroup> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "ConditionGroup");
    }

    public QConditionGroup(PathMetadata<?> metadata) {
        super(QConditionGroup.class, metadata, "null", "ConditionGroup");
    }

}

