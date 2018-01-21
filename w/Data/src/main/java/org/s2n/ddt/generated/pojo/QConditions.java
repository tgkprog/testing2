package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QConditions is a Querydsl query type for QConditions
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QConditions extends com.mysema.query.sql.RelationalPathBase<QConditions> {

    private static final long serialVersionUID = 1155852753;

    public static final QConditions Conditions = new QConditions("Conditions");

    public final NumberPath<Integer> condGrpId = createNumber("CondGrpId", Integer.class);

    public final NumberPath<Integer> condId = createNumber("CondId", Integer.class);

    public final StringPath condName = createString("CondName");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final StringPath expression = createString("Expression");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QConditions> primary = createPrimaryKey(condId);

    public QConditions(String variable) {
        super(QConditions.class, forVariable(variable), "null", "Conditions");
    }

    @SuppressWarnings("all")
    public QConditions(Path<? extends QConditions> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Conditions");
    }

    public QConditions(PathMetadata<?> metadata) {
        super(QConditions.class, metadata, "null", "Conditions");
    }

}

