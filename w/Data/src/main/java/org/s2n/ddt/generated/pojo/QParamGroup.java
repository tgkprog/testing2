package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QParamGroup is a Querydsl query type for QParamGroup
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QParamGroup extends com.mysema.query.sql.RelationalPathBase<QParamGroup> {

    private static final long serialVersionUID = -1777274613;

    public static final QParamGroup ParamGroup = new QParamGroup("ParamGroup");

    public final NumberPath<Integer> appID = createNumber("AppID", Integer.class);

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath description = createString("Description");

    public final NumberPath<Integer> paramGroupId = createNumber("ParamGroupId", Integer.class);

    public final StringPath paramGroupName = createString("ParamGroupName");

    public final StringPath tag = createString("Tag");

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QParamGroup> primary = createPrimaryKey(paramGroupId);

    public QParamGroup(String variable) {
        super(QParamGroup.class, forVariable(variable), "null", "ParamGroup");
    }

    @SuppressWarnings("all")
    public QParamGroup(Path<? extends QParamGroup> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "ParamGroup");
    }

    public QParamGroup(PathMetadata<?> metadata) {
        super(QParamGroup.class, metadata, "null", "ParamGroup");
    }

}

