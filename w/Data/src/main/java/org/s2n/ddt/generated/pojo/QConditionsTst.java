package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QConditionsTst is a Querydsl query type for QConditionsTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QConditionsTst extends com.mysema.query.sql.RelationalPathBase<QConditionsTst> {

    private static final long serialVersionUID = 1256636996;

    public static final QConditionsTst ConditionsTst = new QConditionsTst("Conditions_tst");

    public final NumberPath<Integer> condGrpId = createNumber("CondGrpId", Integer.class);

    public final NumberPath<Integer> condId = createNumber("CondId", Integer.class);

    public final StringPath condName = createString("CondName");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final StringPath expression = createString("Expression");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QConditionsTst> primary = createPrimaryKey(condId);

    public QConditionsTst(String variable) {
        super(QConditionsTst.class, forVariable(variable), "null", "Conditions_tst");
    }

    @SuppressWarnings("all")
    public QConditionsTst(Path<? extends QConditionsTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Conditions_tst");
    }

    public QConditionsTst(PathMetadata<?> metadata) {
        super(QConditionsTst.class, metadata, "null", "Conditions_tst");
    }

}

