package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QActions is a Querydsl query type for QActions
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QActions extends com.mysema.query.sql.RelationalPathBase<QActions> {

    private static final long serialVersionUID = 140842116;

    public static final QActions Actions = new QActions("Actions");

    public final NumberPath<Integer> actionId = createNumber("ActionId", Integer.class);

    public final StringPath actionName = createString("ActionName");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QActions> primary = createPrimaryKey(actionId);

    public QActions(String variable) {
        super(QActions.class, forVariable(variable), "null", "Actions");
    }

    @SuppressWarnings("all")
    public QActions(Path<? extends QActions> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Actions");
    }

    public QActions(PathMetadata<?> metadata) {
        super(QActions.class, metadata, "null", "Actions");
    }

}

