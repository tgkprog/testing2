package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QObjectGroupTst is a Querydsl query type for QObjectGroupTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QObjectGroupTst extends com.mysema.query.sql.RelationalPathBase<QObjectGroupTst> {

    private static final long serialVersionUID = 482158030;

    public static final QObjectGroupTst ObjectGroupTst = new QObjectGroupTst("ObjectGroup_tst");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> objectGroupId = createNumber("ObjectGroupId", Integer.class);

    public final StringPath objectGroupName = createString("ObjectGroupName");

    public final NumberPath<Integer> screenId = createNumber("ScreenId", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QObjectGroupTst> primary = createPrimaryKey(objectGroupId);

    public QObjectGroupTst(String variable) {
        super(QObjectGroupTst.class, forVariable(variable), "null", "ObjectGroup_tst");
    }

    @SuppressWarnings("all")
    public QObjectGroupTst(Path<? extends QObjectGroupTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "ObjectGroup_tst");
    }

    public QObjectGroupTst(PathMetadata<?> metadata) {
        super(QObjectGroupTst.class, metadata, "null", "ObjectGroup_tst");
    }

}

