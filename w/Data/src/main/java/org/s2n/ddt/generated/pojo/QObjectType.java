package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QObjectType is a Querydsl query type for QObjectType
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QObjectType extends com.mysema.query.sql.RelationalPathBase<QObjectType> {

    private static final long serialVersionUID = -1141398990;

    public static final QObjectType ObjectType = new QObjectType("ObjectType");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final NumberPath<Integer> defaultActionId = createNumber("DefaultActionId", Integer.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> objectTypeId = createNumber("ObjectTypeId", Integer.class);

    public final StringPath objectTypeName = createString("ObjectTypeName");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QObjectType> primary = createPrimaryKey(objectTypeId);

    public QObjectType(String variable) {
        super(QObjectType.class, forVariable(variable), "null", "ObjectType");
    }

    @SuppressWarnings("all")
    public QObjectType(Path<? extends QObjectType> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "ObjectType");
    }

    public QObjectType(PathMetadata<?> metadata) {
        super(QObjectType.class, metadata, "null", "ObjectType");
    }

}

