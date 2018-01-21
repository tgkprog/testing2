package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QObjectTypeTst is a Querydsl query type for QObjectTypeTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QObjectTypeTst extends com.mysema.query.sql.RelationalPathBase<QObjectTypeTst> {

    private static final long serialVersionUID = -161144253;

    public static final QObjectTypeTst ObjectTypeTst = new QObjectTypeTst("ObjectType_tst");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final NumberPath<Integer> defaultActionId = createNumber("DefaultActionId", Integer.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> objectTypeId = createNumber("ObjectTypeId", Integer.class);

    public final StringPath objectTypeName = createString("ObjectTypeName");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QObjectTypeTst> primary = createPrimaryKey(objectTypeId);

    public QObjectTypeTst(String variable) {
        super(QObjectTypeTst.class, forVariable(variable), "null", "ObjectType_tst");
    }

    @SuppressWarnings("all")
    public QObjectTypeTst(Path<? extends QObjectTypeTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "ObjectType_tst");
    }

    public QObjectTypeTst(PathMetadata<?> metadata) {
        super(QObjectTypeTst.class, metadata, "null", "ObjectType_tst");
    }

}

