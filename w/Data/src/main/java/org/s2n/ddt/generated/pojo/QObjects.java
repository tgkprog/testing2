package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QObjects is a Querydsl query type for QObjects
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QObjects extends com.mysema.query.sql.RelationalPathBase<QObjects> {

    private static final long serialVersionUID = -357003109;

    public static final QObjects Objects = new QObjects("Objects");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final StringPath identifier = createString("Identifier");

    public final NumberPath<Integer> identifierTypeId = createNumber("IdentifierTypeId", Integer.class);

    public final NumberPath<Integer> objectGroupId = createNumber("ObjectGroupId", Integer.class);

    public final NumberPath<Integer> objectId = createNumber("ObjectId", Integer.class);

    public final NumberPath<Integer> objectTypeId = createNumber("ObjectTypeId", Integer.class);

    public final StringPath objName = createString("ObjName");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QObjects> primary = createPrimaryKey(objectId);

    public QObjects(String variable) {
        super(QObjects.class, forVariable(variable), "null", "Objects");
    }

    @SuppressWarnings("all")
    public QObjects(Path<? extends QObjects> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Objects");
    }

    public QObjects(PathMetadata<?> metadata) {
        super(QObjects.class, metadata, "null", "Objects");
    }

}

