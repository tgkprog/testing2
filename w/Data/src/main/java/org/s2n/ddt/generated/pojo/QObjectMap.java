package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QObjectMap is a Querydsl query type for QObjectMap
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QObjectMap extends com.mysema.query.sql.RelationalPathBase<QObjectMap> {

    private static final long serialVersionUID = 517362532;

    public static final QObjectMap ObjectMap = new QObjectMap("ObjectMap");

    public final StringPath buildNumber = createString("BuildNumber");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final NumberPath<Integer> objectId = createNumber("ObjectId", Integer.class);

    public final NumberPath<Integer> objectMapId = createNumber("ObjectMapId", Integer.class);

    public final StringPath realObjectText = createString("RealObjectText");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QObjectMap> primary = createPrimaryKey(objectMapId);

    public QObjectMap(String variable) {
        super(QObjectMap.class, forVariable(variable), "null", "ObjectMap");
    }

    @SuppressWarnings("all")
    public QObjectMap(Path<? extends QObjectMap> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "ObjectMap");
    }

    public QObjectMap(PathMetadata<?> metadata) {
        super(QObjectMap.class, metadata, "null", "ObjectMap");
    }

}

