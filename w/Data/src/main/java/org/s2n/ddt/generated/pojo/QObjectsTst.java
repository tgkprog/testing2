package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QObjectsTst is a Querydsl query type for QObjectsTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QObjectsTst extends com.mysema.query.sql.RelationalPathBase<QObjectsTst> {

    private static final long serialVersionUID = -1140510918;

    public static final QObjectsTst ObjectsTst = new QObjectsTst("Objects_tst");

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

    public final com.mysema.query.sql.PrimaryKey<QObjectsTst> primary = createPrimaryKey(objectId);

    public QObjectsTst(String variable) {
        super(QObjectsTst.class, forVariable(variable), "null", "Objects_tst");
    }

    @SuppressWarnings("all")
    public QObjectsTst(Path<? extends QObjectsTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Objects_tst");
    }

    public QObjectsTst(PathMetadata<?> metadata) {
        super(QObjectsTst.class, metadata, "null", "Objects_tst");
    }

}

