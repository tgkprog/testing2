package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QConditionGroupTst is a Querydsl query type for QConditionGroupTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QConditionGroupTst extends com.mysema.query.sql.RelationalPathBase<QConditionGroupTst> {

    private static final long serialVersionUID = 1797923800;

    public static final QConditionGroupTst ConditionGroupTst = new QConditionGroupTst("ConditionGroup_tst");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final NumberPath<Integer> condGrpId = createNumber("CondGrpId", Integer.class);

    public final StringPath condGrpName = createString("CondGrpName");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QConditionGroupTst> primary = createPrimaryKey(condGrpId);

    public QConditionGroupTst(String variable) {
        super(QConditionGroupTst.class, forVariable(variable), "null", "ConditionGroup_tst");
    }

    @SuppressWarnings("all")
    public QConditionGroupTst(Path<? extends QConditionGroupTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "ConditionGroup_tst");
    }

    public QConditionGroupTst(PathMetadata<?> metadata) {
        super(QConditionGroupTst.class, metadata, "null", "ConditionGroup_tst");
    }

}

