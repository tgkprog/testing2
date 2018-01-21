package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QObjectGroup is a Querydsl query type for QObjectGroup
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QObjectGroup extends com.mysema.query.sql.RelationalPathBase<QObjectGroup> {

    private static final long serialVersionUID = -1035844985;

    public static final QObjectGroup ObjectGroup = new QObjectGroup("ObjectGroup");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> objectGroupId = createNumber("ObjectGroupId", Integer.class);

    public final StringPath objectGroupName = createString("ObjectGroupName");

    public final NumberPath<Integer> screenId = createNumber("ScreenId", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QObjectGroup> primary = createPrimaryKey(objectGroupId);

    public QObjectGroup(String variable) {
        super(QObjectGroup.class, forVariable(variable), "null", "ObjectGroup");
    }

    @SuppressWarnings("all")
    public QObjectGroup(Path<? extends QObjectGroup> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "ObjectGroup");
    }

    public QObjectGroup(PathMetadata<?> metadata) {
        super(QObjectGroup.class, metadata, "null", "ObjectGroup");
    }

}

